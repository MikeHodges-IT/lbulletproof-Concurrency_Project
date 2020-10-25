
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;

import application.TextAnalyzer;

class FirstJunit5Test {


	@Test
	@DisplayName("My Tester")
	void test() {
		String str = "<html>the the w <html>"; 
		LinkedHashMap<String, Long>wordFreqHashMap;
		
		wordFreqHashMap = TextAnalyzer.htmlStringToFreqMap(str);
		assertTrue(wordFreqHashMap.containsKey("the"),"is true");
		assertEquals(2,wordFreqHashMap.size(),"Equal");
		System.out.println("This test ran");
	}
	@RepeatedTest(3)
	void mytest() {
	
		String str = "<p class=\"poem\">\r\n"
				+ "  Once upon a midnight dreary, while I pondered, weak and weary,<br>\r\n"
				+ "  Over many a quaint and curious volume of forgotten lore—<br>\r\n"
				+ "  While I nodded, nearly napping, suddenly there came a tapping,<br>\r\n"
				+ "  As of some one gently rapping, rapping at my chamber door.<br>\r\n"
				+ "  \"'Tis some visitor,\" I muttered, \"tapping at my chamber door—<br>\r\n"
				+ "  <span style=\"margin-left: 20%\"> Only this and nothing more.\"</span><br>\r\n"
				+ "</p>"; 
		
		LinkedHashMap<String, Long>wordFreqHashMap = TextAnalyzer.htmlStringToFreqMap(str);
		assertTrue(wordFreqHashMap.containsKey("i"),"the word 'i' is in this HashMap");
		assertTrue(wordFreqHashMap.containsValue(Long.valueOf("2")),"the HashMap contains the value 2 ");
		wordFreqHashMap.keySet().forEach(e -> {
			
			assertTrue(wordFreqHashMap.containsKey(e),"the word "+e+"'' is in this HashMap");
			
			
		});
		System.out.println(wordFreqHashMap.keySet());
		
		assertFalse(wordFreqHashMap.isEmpty(),"wordFreqHashMap.isEmpty()");
		assertFalse(wordFreqHashMap.containsKey("the"),"the word 'the' is not in this HashMap");
		assertEquals(20,wordFreqHashMap.size(),"Equal");
		
			
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"and", "a", "i", "door", "some", "while", "chamber", "of", "tapping", "my", "at", "rapping", "lore", "nearly", "upon", "weak", "only", "came", "forgotten", "napping" })
	void testParameterized(String word) {
	String tempString = "<p class=\"poem\">\r\n"
			+ "  Once upon a midnight dreary, while I pondered, weak and weary,<br>\r\n"
			+ "  Over many a quaint and curious volume of forgotten lore—<br>\r\n"
			+ "  While I nodded, nearly napping, suddenly there came a tapping,<br>\r\n"
			+ "  As of some one gently rapping, rapping at my chamber door.<br>\r\n"
			+ "  \"'Tis some visitor,\" I muttered, \"tapping at my chamber door—<br>\r\n"
			+ "  <span style=\"margin-left: 20%\"> Only this and nothing more.\"</span><br>\r\n"
			+ "</p>"; 
	
	LinkedHashMap<String, Long>wordFreqHashMap = TextAnalyzer.htmlStringToFreqMap(tempString);
	
	    assertTrue(wordFreqHashMap.containsKey(word),"the word "+word+"'' is in this HashMap");
	}
	
	
}
