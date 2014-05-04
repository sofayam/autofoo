package com.example.libcommon;

import java.io.Serializable;

public class NestedMapContainer implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private NestedMap contents;
	
	NestedMapContainer(NestedMap nm) {
		contents = nm;
	}
	
	public NestedMap unpack(){
		return contents;
	}
}
