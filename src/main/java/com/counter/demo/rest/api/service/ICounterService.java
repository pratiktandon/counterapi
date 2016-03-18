package com.counter.demo.rest.api.service;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public interface ICounterService {
	public TreeMap<String, Integer> findCount(String paragraph,String findStr);
	public TreeMap<String, Integer> searchParagraph(File file,String search);
	public Map<String, Integer> searchTop(File file,String search,Integer id);
	
}
