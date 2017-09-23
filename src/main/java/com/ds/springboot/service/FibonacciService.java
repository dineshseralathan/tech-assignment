package com.ds.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FibonacciService {
	
	
	/**
	 * This method calculates fibonacci series for a given
	 * number. 
	 * 
	 * @param number for which fibonacci series is calculated
	 * @param useRecursion boolean to indicate whether recursion method 
	 * can be used or not
	 * @return List<Long>, fibonacci series as a list of numbers.
	 */
	public List<Long> fibonacciSeries(int number, boolean useRecursion)
	{
		return useRecursion?fibonacciSeriesRecursion(number):fibonacciSeriesMemoization(number);
	}
 
	 /**
	  * This method calculates fibonacci series for a given
	  * number by calling <code>fibonaci(int num)<code/> method.
	  * 
	  * @param number 
	  * @return List<Long>, fibonacci series as a list of numbers.
	  * 
	  * <i>Note : This method is very inefficient when number > 45<i/>
	  */
	 private List<Long> fibonacciSeriesRecursion(int number)
     {  
		 // used list of long to avoid integer overflow
		  List<Long> list =new ArrayList<Long>();
		 
  	      if(number==0) return Arrays.asList(new Long[]{1l});
  	   
	  	   for( int i=0;i<number;i++)
	  	   {
	  		   list.add(fibonaci(i));
	  	   }
	  	   
	  	   return list;
     }
		
	/**
	 * Recursive method to calculate fibonacci number for 
	 * given number.
	 * 
	 * @param num for which fibonacci number is calculated
	 * @return resultant fibonacci number for the given number
	 */
	private long fibonaci(int num)
	{ 			
		if(num<=1) return 1;
	
		return fibonaci(num-1) + fibonaci(num-2);
					
		
	}
	
	/**
	 * This method returns a list of numbers representing
	 * Fibonacci series by using Memoization technique (dynamic programming).
	 * 
	 * @param number for which Fibonacci series is calculated
	 * @return List of numbers that represents 
	 * Fibonacci series
	 */
	private static List<Long> fibonacciSeriesMemoization(int number)
	{
		        
	       if(number==0){
	    	   
	    	   return Arrays.asList(new Long[]{1l});
	       }
	       if(number==1) {
	    	   return Arrays.asList(new Long[]{1l,1l});
	       }
	       else
	       {   
	    	   List<Long>  result = new ArrayList<Long>();
	    	   
	    	   long[] fib = new long[number+1];
	    	   
	    	   fib[0]=1;
	    	   fib[1]=1;
	    	   
	    	   result.add(fib[0]);
	    	   result.add(fib[1]);
		    	   
	 	      for(int i=2;i<number;i++)
	 	      {
	 	    	  fib[i] = fib[i-1] + fib[i-2];
	 	    	  
	 	    	  result.add(fib[i]);
	 	      }
	 	      
		 	  return result;
	 	      
	       }
	    	  
	}
	
	public static void main(String[] st)
	{
		System.err.println(fibonacciSeriesMemoization(15));
	}
	
}
