package com.ds.springboot.service;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

/**
 * <code>WordCount</code> is an utility class that provides utility method that takes a 
 * <i>paragraph</i> as a <code>String</code> input and returns the unique words list with
 * respective count. The algorithm assumes the input contains only words and standard
 * punctuation. 
 * 
 * 
 * @author dinesh
 *
 */
@Service
public class WordCountService {
	 
	 private static final char DOT 			= '.';
	 private static final char HYPHEN 		= '-';
	 private static final char APOSTROPHE 	= '\'';
	 private static final char EMDASH 		= '\u2014';
	 private static final char SPACE  		= ' ';
	 
	 
	/*
	 * TreeMap that holds unique words and respective count in alphabetical order
	 */
	private Map<String,Integer> wordsToCount = new TreeMap<String,Integer>();
	
	/*
	 * default no-arg constructor to help in JUnit testing
	 */
	public WordCountService()
	{
		
	}
	
	/**
	 * Constructor that takes the String for which
	 * words count needs to be determined
	 * @param input
	 */
	public WordCountService(String input)
	{
		populateWordsToCount(input);
	}
	
	
	/**
	 * This method populates tree map with unique words which are custom split 
	 * instead of String.split() since we have to iterate over the words either 
	 * before or after splitting to take care of punctuation. This method 
	 * doesn't throw error in case of empty string input rather return an empty map.
	 * <p>
	 * Runtime and memory cost are both O(n). This is the best we can do
	 * because we have to look at every character in the input string and we
	 * have to return a tree map of every unique word.
	 * 
	 * @param str 
	 */
	private void populateWordsToCount(String str)
	{  
		 int strLen = str.length();
		 
		 int currentWordLength = 0;
		 int currentWordStartIndex = 0;
		 
		 // iterates over each character in the input string, splitting
	     // words and passing them to addWordsToMap()
		 for(int i=0;i<strLen;i++) {
			 
			 char character = str.charAt(i);
			 
			 // if we reached the end of the string we check if the last
	         // character is a letter and add the last word to our map
			 if(strLen-1==i)
			 {
					 if(Character.isLetter(character)) {
						 currentWordLength++;
					 }
					 
					 if(currentWordLength >0) {
						 
						 String currentWord = str.substring(currentWordStartIndex,
								 						  currentWordStartIndex+currentWordLength);
						 
						 addWordsToMap(currentWord);
					 }
			 }
			 // if we reach a space or emdash we know we're at the end of a word
	         // so we add it to our map and reset our current word
			 else if (character ==SPACE || character == EMDASH) {
				 
					 if(currentWordLength >0) {
						 
						 String currentWord = str.substring(currentWordStartIndex,
								 					      currentWordStartIndex+currentWordLength);
						 
						 addWordsToMap(currentWord);
						 
						 // reset the word length
						 currentWordLength=0;
					 }
				 
			 }
			// we want to make sure we split on ellipses so if we get two periods in
	         // a row we add the current word to our map and reset our current word
			 else if (character ==DOT) {
				 
				 if(i< strLen -1 && str.charAt(i+1)==DOT) {
					 
					 if(currentWordLength >0) {
						 
						 String currentWord = str.substring(currentWordStartIndex,
								 						  currentWordStartIndex+currentWordLength);
						 
						 addWordsToMap(currentWord);
						 
						 // reset the word length
						 currentWordLength=0;
					 }
					 
				 }
			 }
	         // if the character is a hyphen, we want to check if it's surrounded by letters
	        // if it is, we add it to our current word
			else if (character ==HYPHEN) {
				
				if(i >0 && Character.isLetter(str.charAt(i-1)) &&
						   Character.isLetter(str.charAt(i+1))) {
					
					if(currentWordLength==0) {
						
						currentWordStartIndex = i;
					}
					
					currentWordLength++;	
				}
					
				
			}
			// if the character is a letter or an apostrophe, we add it to our current word
			else if(character==APOSTROPHE || Character.isLetter(character)) {
				 
					if(currentWordLength==0) {
						
						currentWordStartIndex = i;
					}
					
					currentWordLength++;
			}
			else {
				
				 if(currentWordLength >0) {
					 
					 String currentWord = str.substring(currentWordStartIndex, 
							 						  currentWordStartIndex+currentWordLength);
					 
					 addWordsToMap(currentWord);
					 
					 // reset the word length
					 currentWordLength=0;
				 }
				
			}
				
		}				 
			 
		 
	}
	
	
	/**
	 * This method adds each word to tree Map and updates the count (occurrence)
	 * based on below assumption.
	 *  <ul>
     *       <li>If a lower case version is in the tree map, we know our input word must be upper case
     *           but we only include upper case words if they're always upper case
     *           so we just increment the lower case version's count</li>
     *       <li>If an upper case version is in the tree map, we know our input word must be lower case.
     *   		 since we only include upper case words if they're always upper case, we add the
     *   		 lower case version and give it the upper case version's count</li>
     *       <li>Otherwise, the word is not in the tree map at all, lower case or upper case
     *            so we add it to the hash map</li>
     *       
     * </ul>
	 * 
	 * @param word to be added to tree Map
	 */
	private void addWordsToMap(String word)
	{ 
		
		 // if the word is already in the hash map we increment its count 
		if(wordsToCount.containsKey(word)) {
			
			wordsToCount.put(word, wordsToCount.get(word)+1);
		}
		
		// if a lower case version is in the tree map, we know our input word must be upper case
        // but we only include upper case words if they're always upper case
        // so we just increment the lower case version's count
		else if(wordsToCount.containsKey(word.toLowerCase())) {
			
			int newCount = wordsToCount.get(word.toLowerCase()) +1;
			
			wordsToCount.put(word.toLowerCase(), newCount);
		}
		

        // if an upper case version is in the tree map, we know our input word must be lower case.
        // since we only include upper case words if they're always upper case, we add the
        // lower case version and give it the upper case version's count
	    else if(wordsToCount.containsKey(capitalize(word))) {
			
			int newCount = wordsToCount.get(capitalize(word)) +1;
			
			wordsToCount.put(word, newCount);
			wordsToCount.remove(capitalize(word));
		}
		// otherwise, the word is not in the tree map at all, lower case or upper case
        // so we add it to the hash map
		else
		{
			wordsToCount.put(word, 1);
		}
		  
	}
	
	/**
	 * Return a tree Map that is sorted alphabetically with 
	 * count of occurrence of each unique word.
	 * 
	 * @return unmodifiable map containing the unique words to count list
	 */
	public Map<String,Integer> getWordsToCount()
	{
		return (wordsToCount);
	}
	
	/**
	 * Simple method to Capitalize word by updating first Letter to Capital
	 * @param word to be capitalized
	 * @return Capitalized word
	 */
	private String capitalize(String word)
	{
		return word.substring(0,1).toUpperCase()+word.substring(1);
	} 
	
	
	public static void main(String[] args)
	 {
		 
		 String inputString = "Testing special characters like @ # $ %  () _ * & ^ and number like 123,45,6789 to be exlcuded by word count algorithm.";
		  
		 WordCountService wc = new WordCountService(inputString);
		 System.out.println(wc.getWordsToCount());
	 }

}
