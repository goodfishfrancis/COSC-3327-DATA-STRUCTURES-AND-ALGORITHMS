package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import combinatorics.Permutation;
import combinatorics.PermutationImpl_Solinger;

import dartboard.Dartboard;
import dartboard.DartboardImpl_Solinger;

public class DartboardTestCasesSubset_Solinger
{	
	@Test(expected=AssertionError.class)
	public void assertionsEnabledTest()
	{
		//TEST_GOAL_MESSAGE = "Check whether assertions are enabled";
		assert false;
		throw new RuntimeException("ENABLE ASSERTIONS IN RUN CONFIGURATIONS!");
	}
	
	@Test
	public void threeNumberDartboard()
	{
		Set<List<Integer>> cycles = new HashSet<List<Integer>>();
		List<Integer> soleCycle = (Arrays.asList(new Integer[]{3, 2, 1}));
		cycles.add(soleCycle);
		
		Set<Integer> domain = getAllElements(cycles);
		Permutation<Integer> permutation = getPermutation(cycles, domain);
		Dartboard dartboard = new DartboardImpl_Solinger(permutation, 2);
		
		Set<Integer> desiredDartboardNumbersSet = new HashSet<Integer>(Arrays.asList(new Integer[]{1, 2, 3}));
		assertEquals(desiredDartboardNumbersSet, dartboard.getNumbers());

		assertEquals(new Integer(2), (Integer)dartboard.getTopmostNumber());
		
		assertEquals(new Integer(3), (Integer)dartboard.getClockwiseAdjacentNumber(1));
		assertEquals(new Integer(1), (Integer)dartboard.getClockwiseAdjacentNumber(2));
		assertEquals(new Integer(2), (Integer)dartboard.getClockwiseAdjacentNumber(3));
	}
	
	@Test
	public void standardDartboard()
	{
		Integer[] dartboardNumbers = new Integer[]{20, 1, 18, 4, 13, 6, 10, 15, 2, 17, 3, 19, 7, 16, 8, 11, 14, 9, 12, 5};
		testDartboard(dartboardNumbers);
	}
		
	private static void testDartboard(Integer[] dartboardNumbers)
	{
		Set<List<Integer>> cycles = new HashSet<List<Integer>>();
		List<Integer> soleCycle = (Arrays.asList(dartboardNumbers));
		cycles.add(soleCycle);
		
		Set<Integer> domain = getAllElements(cycles);
		Permutation<Integer> permutation = getPermutation(cycles, domain);
		Dartboard dartboard = new DartboardImpl_Solinger(permutation, dartboardNumbers[0]);
		
		Set<Integer> desiredDartboardNumbersSet = new HashSet<Integer>(Arrays.asList(dartboardNumbers));
		assertEquals(desiredDartboardNumbersSet, dartboard.getNumbers());

		assertEquals(dartboardNumbers[0], (Integer)dartboard.getTopmostNumber());
		
		Integer currentNumber = dartboard.getTopmostNumber();
		int i = 0;
		boolean completedCycle = false;
		while(!completedCycle)
		{
			assertEquals(dartboardNumbers[i], currentNumber);
			int nextClockwiseIndex = ((i + 1) % dartboardNumbers.length);
			Integer nextClockwiseNumber = dartboard.getClockwiseAdjacentNumber(currentNumber);
			assertEquals(dartboardNumbers[nextClockwiseIndex], nextClockwiseNumber);
			
			currentNumber = nextClockwiseNumber;
			completedCycle = (currentNumber == dartboard.getTopmostNumber());
			i++;
		}
	}
	
	private static <E> Permutation<E> getPermutation(Set<List<E>> cycles, Set<E> domain)
	{
		return new PermutationImpl_Solinger<E>(cycles, domain);
	}
	
	private static <E> Set<E> getAllElements(Set<List<E>> setOfLists)
	{
		Set<E> allElements = new HashSet<E>();
		Iterator<List<E>> setOfListsIterator = setOfLists.iterator();
		while(setOfListsIterator.hasNext())
		{
			List<E> list = setOfListsIterator.next();
			allElements.addAll(list);
		}
		return allElements;
	}
}