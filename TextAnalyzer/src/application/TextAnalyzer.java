package application;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

public class TextAnalyzer {  
	
	public static LinkedHashMap<String, Long> htmlStringToFreqMap(String str){
		
		Map<String, Long> wordFreqMap =  
				
				Stream.of(
				str
				.replaceAll("&mdash;" , " ")
				.replaceAll("<[^>]*>"," ")
				.replaceAll("[\\s+\\W\\d]", " ")
				.trim()			
				.toLowerCase()
				.split("\\s+"))
				.collect(groupingBy(e ->e,counting())
				);
		 
		LinkedHashMap<String, Long>wordFreqHashMap = 
				
				wordFreqMap.entrySet()
						   .parallelStream()
		                   .sorted(Collections.reverseOrder(Entry.comparingByValue()))
		                   .limit(20)
		                   .collect(toMap(Entry::getKey, Entry::getValue, (e1,e2) -> e2,LinkedHashMap::new));	
		
		System.out.println(wordFreqHashMap);
		return  wordFreqHashMap;
		
	}
	}
	
	
