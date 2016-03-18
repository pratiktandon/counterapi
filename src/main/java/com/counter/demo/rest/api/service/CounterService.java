package com.counter.demo.rest.api.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.counter.demo.rest.api.utils.CounterApiUtil;

@Service("counterService")
public class CounterService implements ICounterService {

	public TreeMap<String, Integer> findCount(String paragraph, String findStr) {
		TreeMap<String, Integer> tracker = new TreeMap<String, Integer>();
		String[] words = findStr.split(",");
		for (String word : words) {
			int lastIndex = 0;
			int count = 0;
			while (lastIndex != -1) {
				lastIndex = paragraph.indexOf(word, lastIndex);
				if (lastIndex != -1) {
					count++;
					lastIndex += word.length();
				}
			}
			tracker.put(word, count);
		}
		return tracker;
	}

	public TreeMap<String, Integer> searchParagraph(File fin, String findStr) {
		Integer flag = 0;
		TreeMap<String, Integer> finalcount = new TreeMap<String, Integer>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fin);
		} catch (FileNotFoundException e) {
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				TreeMap<String, Integer> calulate = findCount(line, findStr);

				for (Map.Entry<String, Integer> entry : calulate.entrySet()) {
					String key = entry.getKey();
					Integer value = entry.getValue();
					if (finalcount.containsKey(key) && value > 0) {
						Integer finalcountValue = finalcount.get(key);
						finalcount.put(key, value + finalcountValue);
					} else if (flag == 0) {
						finalcount.put(key, value);
					}
				}
				flag = 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return finalcount;
		/*
		 * for (Map.Entry<String, Integer> entry : finalcount.entrySet()) {
		 * String key = entry.getKey(); Integer value = entry.getValue();
		 * System.out.println(key + " => " + value); }
		 */
	}

	public Map<String, Integer> searchTop(File fin, String findStr, Integer id) {
		Integer flag = 0;
		Map<String, Integer> finalcount = new TreeMap<String, Integer>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fin);
		} catch (FileNotFoundException e) {
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				TreeMap<String, Integer> calulate = findCount(line, findStr);

				for (Map.Entry<String, Integer> entry : calulate.entrySet()) {
					String key = entry.getKey();
					Integer value = entry.getValue();
					if (finalcount.containsKey(key) && value > 0) {
						Integer finalcountValue = finalcount.get(key);
						finalcount.put(key, value + finalcountValue);
					} else if (flag == 0) {
						finalcount.put(key, value);
					}
				}
				flag = 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		CounterApiUtil utilClass = new CounterApiUtil();
		// Map<String, Integer> sorted =
		// utilClass.sortByComparator(finalcount,true);
		HashMap<String, Integer> countID = new HashMap<String, Integer>();

		for (Map.Entry<String, Integer> entry : utilClass.sortByComparator(
				finalcount, true).entrySet()) {
			if (countID.size() > id - 1)
				break;
			countID.put(entry.getKey(), entry.getValue());
		}
		return utilClass.sortByComparator(countID, true);
	}

	/*
	 * public CounterService(){ // create some dummy data }
	 */

}
