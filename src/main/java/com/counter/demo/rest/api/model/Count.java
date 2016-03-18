package com.counter.demo.rest.api.model;

import java.util.TreeMap;

public class Count {
	private TreeMap<String, Integer> count;

	public TreeMap<String, Integer> getCount() {
		return count;
	}

	public void setCount(TreeMap<String, Integer> count) {
		this.count = count;
	}

	public static Count createNew(TreeMap<String, Integer> count) {
		Count pw = new Count();
		pw.setCount(count);
		return pw;
	}

}
