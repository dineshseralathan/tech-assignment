/**
 * 
 */
package com.ds.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ds.springboot.service.WordCountService;


/**
 * <code>WordCountTest</code> is the JUnit class for
 * <code>WordCount</code> containing necessary test coverage.
 * 
 * Note: Didn't use JMockit since the test cases were testing
 * the actual algorithm itself.
 * 
 * @author dinesh
 *
 */
public class WordCountServiceTest {
	 
		    
	    @Test
	    public void testWordCountMatchesEachWordSet1()
	    {
	    	
	    	WordCountService wc = new WordCountService("Given a paragraph of text and return a list of alphabetized unique words. "
					  + "After each word lists how many times the word appeared"); 
	    	
	     	// test each unique word is available in the response
	        assertThat( wc.getWordsToCount()).containsKey("After");
    	    assertThat( wc.getWordsToCount()).containsKey("Given");
    	    assertThat( wc.getWordsToCount()).containsKey("a");
    	    assertThat( wc.getWordsToCount()).containsKey("alphabetized");
    	    assertThat( wc.getWordsToCount()).containsKey("and");
    	    assertThat( wc.getWordsToCount()).containsKey("appeared");
    	    assertThat( wc.getWordsToCount()).containsKey("each");
    	    assertThat( wc.getWordsToCount()).containsKey("appeared");
    	    assertThat( wc.getWordsToCount()).containsKey("how");
    	    assertThat( wc.getWordsToCount()).containsKey("list");
      	    assertThat( wc.getWordsToCount()).containsKey("lists");
	       	assertThat( wc.getWordsToCount()).containsKey("many");
    	    assertThat( wc.getWordsToCount()).containsKey("of");
    	    assertThat( wc.getWordsToCount()).containsKey("paragraph");
    	    assertThat( wc.getWordsToCount()).containsKey("return");
    	    assertThat( wc.getWordsToCount()).containsKey("text");
      	    assertThat( wc.getWordsToCount()).containsKey("the");
      	    assertThat( wc.getWordsToCount()).containsKey("times");
    	    assertThat( wc.getWordsToCount()).containsKey("unique");
    	    assertThat( wc.getWordsToCount()).containsKey("word");
    	    assertThat( wc.getWordsToCount()).containsKey("words");
    	    
    	    assertThat(wc.getWordsToCount()).containsValues(1,2);
    	    
    	    assertTrue(wc.getWordsToCount().get("of")==2);
    	    assertTrue(wc.getWordsToCount().get("word")==2);
    	    assertTrue(wc.getWordsToCount().get("a")==2);
    	    
    	    // check for words not present
    	    assertThat(wc.getWordsToCount()).doesNotContainEntry("for", 1);
    	    //test WordCount doesn't match words not found
    	    assertThat(wc.getWordsToCount().values()).doesNotContain(0,3,4,5,6,7,8,9);
    	    //test WordCount doesn't contain not null values
    	    assertThat(wc.getWordsToCount().values()).doesNotContainNull();
	    }
	    
    
	    
	    @Test
	    public void testWordCountMatchesEachWordSet2()
	    { 
	    	WordCountService wc = new WordCountService("Testing in-line or embedded database like HyperSQL DB is made easy in Spring using spring JPA."
	    					+ " Spring auto configures the embedded db at the server start-up and makes life easy...Spring boot rocks!!!"); 
	  	  
	    	assertThat( wc.getWordsToCount()).containsKey("DB");
    	    assertThat( wc.getWordsToCount()).containsKey("HyperSQL");
    	    assertThat( wc.getWordsToCount()).containsKey("JPA");
    	    assertThat( wc.getWordsToCount()).containsKey("Testing");
    	    assertThat( wc.getWordsToCount()).containsKey("at");
    	    assertThat( wc.getWordsToCount()).containsKey("auto");
    	    assertThat( wc.getWordsToCount()).containsKey("boot");
    	    assertThat( wc.getWordsToCount()).containsKey("configures");
    	    assertThat( wc.getWordsToCount()).containsKey("database");
      	    assertThat( wc.getWordsToCount()).containsKey("db");
	       	assertThat( wc.getWordsToCount()).containsKey("easy");
    	    assertThat( wc.getWordsToCount()).containsKey("embedded");
    	    assertThat( wc.getWordsToCount()).containsKey("in");
    	    assertThat( wc.getWordsToCount()).containsKey("in-line");
    	    assertThat( wc.getWordsToCount()).containsKey("is");
      	    assertThat( wc.getWordsToCount()).containsKey("life");
      	    assertThat( wc.getWordsToCount()).containsKey("like");
    	    assertThat( wc.getWordsToCount()).containsKey("or");
    	    assertThat( wc.getWordsToCount()).containsKey("rocks");
    	    assertThat( wc.getWordsToCount()).containsKey("server");
    	    assertThat( wc.getWordsToCount()).containsKey("spring");
    	    assertThat( wc.getWordsToCount()).containsKey("start-up");
    	    assertThat( wc.getWordsToCount()).containsKey("the");
    	    assertThat( wc.getWordsToCount()).containsKey("using");
    	    
    	    assertTrue(wc.getWordsToCount().get("spring")==4);
    	    assertTrue(wc.getWordsToCount().get("embedded")==2);
    	    assertTrue(wc.getWordsToCount().get("the")==2);
    	  
    	    
    	    
    	    // check for words not present
    	    assertThat(wc.getWordsToCount()).doesNotContainEntry("Spring", 1);
    	    assertThat(wc.getWordsToCount()).doesNotContainEntry("Boot", 1);
    	    assertThat( wc.getWordsToCount()).doesNotContainEntry("likes",1);
    	    
    	    //test WordCount doesn't match words not found
    	    assertThat(wc.getWordsToCount().values()).doesNotContain(0,3,5);
    	    //test WordCount doesn't contain not null values
    	    assertThat(wc.getWordsToCount().values()).doesNotContainNull();
	    }
	    
	    @Test
	    public void testWordCountMatchesEachWordSet3()
	    { 
	    	WordCountService wc = new WordCountService("Testing special characters like @ # $ %  () _ * & ^ and number "
	    								+ "like 123,45,6789 to be excluded by word count algorithm."); 
	 
	    		    	
	        assertThat( wc.getWordsToCount()).containsKey("Testing").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("algorithm").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("and").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("be").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("by").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("characters").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("count").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("excluded").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("like").containsValue(2);
    	    assertThat( wc.getWordsToCount()).containsKey("number").containsValue(1);
      	    assertThat( wc.getWordsToCount()).containsKey("special").containsValue(1);
	       	assertThat( wc.getWordsToCount()).containsKey("to").containsValue(1);
    	    assertThat( wc.getWordsToCount()).containsKey("word").containsValue(1);
        
   	      
    	    // check for words not present
    	    assertThat(wc.getWordsToCount()).doesNotContainEntry("tetsing", 1);
    	    assertThat(wc.getWordsToCount()).doesNotContainEntry("exculded", 1);
    	    assertThat( wc.getWordsToCount()).doesNotContainEntry("character",1);
    	    //test WordCount doesn't match words not found
    	    assertThat(wc.getWordsToCount().values()).doesNotContain(0,3,5);
    	    //test WordCount doesn't contain not null values
    	    assertThat(wc.getWordsToCount().values()).doesNotContainNull();
	    }
	    
	    @Test
	    public void testEmptyMapisReturnedforEmptyString()
	    {
	    	String emptyString = ""; 
	    	WordCountService wc = new WordCountService(emptyString);
	    	
	    	 assertThat(wc.getWordsToCount()).isEmpty();
	    	
	    }


}
