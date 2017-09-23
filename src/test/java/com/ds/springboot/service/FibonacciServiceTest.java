package com.ds.springboot.service;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import mockit.Deencapsulation;

/**
 * <code>Junit</code> for <code>FibonacciService</code> class
 * 
 * @author dinesh
 *
 */
public class FibonacciServiceTest {
	
 
	 @Test
	public void testFibonacciSeriesRecursionFor10()
	{
		FibonacciService fibSeries = new FibonacciService();
		
		List<Long> expected = Arrays.asList(new Long[] {1l,1l,2l,3l,5l,8l,13l,21l,34l,55l,89l,144l,233l,377l,610l});
		
		List<Long> actual =  Deencapsulation.invoke(fibSeries,"fibonacciSeriesRecursion",15);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
	 

	 @Test
	public void testFibonacciSeriesRecursionFor25()
	{
		FibonacciService fibSeries = new FibonacciService();
		
		List<Long> expected = Arrays.asList(new Long[] {1l,1l,2l,3l,5l,8l,13l,21l,34l,55l,89l,144l,233l,377l,610l,
												       987l,1597l,2584l,4181l,6765l,10946l,17711l,28657l,46368l,75025l});
		
		List<Long> actual =  Deencapsulation.invoke(fibSeries,"fibonacciSeriesRecursion",25);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
	 

	 @Test
	public void testFibonacciSeriesRecursionFor35()
	{
		FibonacciService fibSeries = new FibonacciService();
		
		List<Long> expected = Arrays.asList(new Long[] {1l,1l,2l,3l,5l,8l,13l,21l,34l,55l,89l,144l,233l,377l,610l,
				                                      987l,1597l,2584l,4181l,6765l,10946l,17711l,28657l,46368l,75025l,121393l,
				                                      196418l,317811l,514229l,832040l,1346269l,2178309l,3524578l,5702887l,9227465l});
		
		List<Long> actual =  Deencapsulation.invoke(fibSeries,"fibonacciSeriesRecursion",35);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
	 
	 @Test
	public void testFibonacciSeriesRecursionFor45()
	{
		FibonacciService fibSeries = new FibonacciService();
		
		List<Long> expected = Arrays.asList(new Long[] {1l,1l,2l,3l,5l,8l,13l,21l,34l,55l,89l,144l,233l,377l,610l,987l,1597l,2584l,
													   4181l,6765l,10946l,17711l,28657l,46368l,75025l,121393l,196418l,317811l,514229l,
													   832040l,1346269l,2178309l,3524578l,5702887l,9227465l,14930352l,24157817l,39088169l,
													   63245986l,102334155l,165580141l,267914296l,433494437l,701408733l,1134903170l});
		
		List<Long> actual =  Deencapsulation.invoke(fibSeries,"fibonacciSeriesRecursion",45);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
	 
	 
/*	@Test
	public void testfibonacciSeriesMemoizationFor35()
	{
		FibonacciService fibSeries = new FibonacciService();
		
		List<Long> expected = Arrays.asList(new Long[] {1l,1l,2l,3l,5l,8l,13l,21l,34l,55l,89l,144l,233l,377l,610l,
				                                      987l,1597l,2584l,4181l,6765l,10946l,17711l,28657l,46368l,75025l,121393l,
				                                      196418l,317811l,514229l,832040l,1346269l,2178309l,3524578l,5702887l,9227465l});
		
		List<Long> actual =  Deencapsulation.invoke(fibSeries,"fibonacciSeriesMemoization",35);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
		 
    @Test
	public void testfibonacciSeriesMemoizationFor45()
	{
		FibonacciService fibSeries = new FibonacciService();
		
		List<Long> expected = Arrays.asList(new Long[] {1l,1l,2l,3l,5l,8l,13l,21l,34l,55l,89l,144l,233l,377l,610l,987l,1597l,2584l,
													   4181l,6765l,10946l,17711l,28657l,46368l,75025l,121393l,196418l,317811l,514229l,
													   832040l,1346269l,2178309l,3524578l,5702887l,9227465l,14930352l,24157817l,39088169l,
													   63245986l,102334155l,165580141l,267914296l,433494437l,701408733l,1134903170l});
		
		List<Long> actual =  Deencapsulation.invoke(fibSeries,"fibonacciSeriesMemoization",45);
		
		assertArrayEquals(expected.toArray(), actual.toArray());
		
		//**NOTE** : This method when tested took 0.002 seconds whereas testFibonacciSeriesRecursionFor45 took 6.633 seconds 
	}*/

}
