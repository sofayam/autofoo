package com.example.libcommon;

import java.io.Serializable;
import java.util.HashMap;

//import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NestedMap extends HashMap<String,Object> implements Serializable{

	
	private static final long serialVersionUID = -5690396298231517172L;
    static Logger logger = LoggerFactory.getLogger(NestedMap.class);
	
	public NestedMap deepClone() {
		NestedMap deep = new NestedMap();
		for (String key: this.keySet()) {
			Object val = this.get(key);
			if (val.getClass() == this.getClass()) {
				NestedMap nm = (NestedMap)val;
				val = (Object)nm.deepClone();
			}
			deep.put(key, val);
		}
		return deep;
	}
	
	String[] tail(String[] s) {
		String[] restIndices = new String [s.length-1];
		System.arraycopy(s, 1,restIndices, 0, s.length-1);	
		return restIndices;
	}

	
	public void putNested(String indices, String val) {
		String[] exploded = indices.split(":");
		putNested(exploded,val);
	}
	
	public void putNested(String[] indices, String val) {
		String front = indices[0];
		int indLength = indices.length;
		if(indLength == 1) {
			this.put(front,val);
		} else {
			if  (!this.containsKey(front)) {
				this.put(front, new NestedMap());
			}
			NestedMap deeper = (NestedMap) this.get(front);
			deeper.putNested(tail(indices), val);
		}
		
	}
	public Object getNested(String[] indices) {
		Object base = this.get(indices[0]);
		if (indices.length == 1) {
			return base;
		} else {
			return ((NestedMap)base).getNested(tail(indices));	
		}	
	}
	public NestedMap getNestedMap(String indices) {
		return (NestedMap)getNested(indices.split(":")); 
	}
	public String getNestedString(String indices) {
		return (String)getNested(indices.split(":"));		
	}
	
	boolean isNM (Object o) {
		return (o.getClass() == this.getClass());
	}
	public void add(NestedMap more) {
		// TODO this will probably crash with badly structured configs (so best not do that :-)
		for (String key: more.keySet()) {
			if (this.containsKey(key)) {
				Object val = this.get(key);
				if (isNM(val)) {                            // if target is nm then recurse
					NestedMap nval = (NestedMap) val;
					nval.add((NestedMap) (more.get(key)));
				} else {
					this.put(key, more.get(key));           // else overwrite
				}
			} else {
				this.put(key, more.get(key));
			}
		}
	}
	
	public NestedMapContainer pack() {
		return new NestedMapContainer(this);
	}
	
	// Tests
	
	public static void doTests() {
		NestedMap nm1 = new NestedMap();
		nm1.putNested("a:b:c","abc");
		nm1.putNested("a:b:d","abd");
		nm1.putNested("a:d:b","adb");
		nm1.putNested("d:e:f","def");
		NestedMap nm2 = nm1.deepClone();
		if (nm2 == nm1) {
			logger.error("failed test");
		} else {
			logger.info("Passed test");
		}	
	}
	
	
}


