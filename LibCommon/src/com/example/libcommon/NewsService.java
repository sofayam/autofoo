package com.example.libcommon;




public class NewsService {

	
	public static NewsForCategory getNews(String category, int count) {
		NewsItem [] newsItems = new NewsItem[count];
		for (int i=0; i<count; i++) {
			NewsItem n = new NewsItem();
			n.title = category + "item" + (i+1);
			n.length = 100;
			n.category = category;
			newsItems[i] = n;
		}
		NewsForCategory res = new NewsForCategory();
		res.category = category;
		res.items = newsItems;
		return res;
	}
	
}
