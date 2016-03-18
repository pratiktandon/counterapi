package com.counter.demo.rest.api.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

public class CounterApiUtil {
	
	public static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
        // Sorting the list based on values in decending order to change order replace the o1 and o2
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
               public int compare(Entry<String, Integer> o1,Entry<String, Integer> o2) {
                      if (order) {
                    	  return o2.getValue().compareTo(o1.getValue());
                      } else {
                            return o1.getValue().compareTo(o2.getValue());

                      }
               }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list) {
               sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
  }
	
	
	
	public static HttpServletResponse csvResponse(Map<String, Integer> result,HttpServletResponse response ) throws IOException{
		ArrayList<String> rows = new ArrayList<String>();
		rows.add("Name,Result");
		rows.add("\n");
		for (Map.Entry<String, Integer> entry : result.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			rows.add(key + "," + value.toString());
			rows.add("\n");
		}
		Iterator<String> iter = rows.iterator();
		while (iter.hasNext()) {
			String outputString = (String) iter.next();
			response.getOutputStream().print(outputString);
		}
		response.getOutputStream().flush();
		return response;
	} 
	
	/*public static String removeCharater(String tormove){
		String a = tormove.replaceAll("[=()?:!.';{}]+", "").replaceAll("searchText","").replaceAll("\\[","").replaceAll("\\]","");
		return a;
	}
	*/
	
	
	public static String deCrypt(String credentials) throws IOException{
		System.out.println(credentials);
		String finCredential = credentials.replaceAll("Basic " ,"");
		byte[] plainCredsBytes = finCredential.getBytes();
		byte[] base64CredsBytes = Base64.decodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
				return base64Creds;
	} 

	
	
}
