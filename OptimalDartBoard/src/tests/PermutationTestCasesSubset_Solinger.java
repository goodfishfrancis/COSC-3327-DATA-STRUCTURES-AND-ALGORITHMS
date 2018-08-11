package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import combinatorics.Permutation;
import combinatorics.PermutationImpl_Solinger;
import combinatorics.PermutationUtils_Solinger;

public class PermutationTestCasesSubset_Solinger
{
	@Test(expected=AssertionError.class)
	public void assertionsEnabledTest()
	{
		//TEST_GOAL_MESSAGE = "Check whether assertions are enabled";
		assert false;
		throw new RuntimeException("ENABLE ASSERTIONS IN RUN CONFIGURATIONS!");
	}
	
	@Test
	public void domain()
	{
		Set<List<Integer>> cycles = new HashSet<List<Integer>>();
		List<Integer> cycle = Arrays.asList(new Integer[]{1,3,5});
		cycles.add(cycle);
		
		Set<Integer> allCycleElements = getAllElements(cycles);
		Set<Integer> domain = allCycleElements;
		domain.addAll(Arrays.asList(new Integer[]{2, 4}));
		
		Permutation<Integer> permutation = getPermutation(cycles, domain);
		Set<Integer> desiredDomain = new HashSet<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5}));
		assertEquals(desiredDomain, permutation.getDomain());
	}
	
	@Test
	public void straightforwardPermutation()
	{
		Set<List<Integer>> cycles = new HashSet<List<Integer>>();
		List<Integer> soleCycle = new ArrayList<Integer>();
		final int MAX = 5;
		for(int i = 1; i <= MAX; i++)
		{
			soleCycle.add(i);
		}
		cycles.add(soleCycle);
		
		Set<Integer> domain = getAllElements(cycles);
		Permutation<Integer> permutation = getPermutation(cycles, domain);
		
		for(int i = 1; i <= MAX; i++)
		{
			assertEquals(new Integer(i % MAX + 1), permutation.getImage(i));
		}
	}
	
	@Test
	public void toStringTest()
	{
		Set<List<Integer>> cycles = new HashSet<List<Integer>>();
		List<Integer> cycle = Arrays.asList(new Integer[]{3,5,7,9});
		cycles.add(cycle);
		cycle = Arrays.asList(new Integer[]{1,2,4,8});
		cycles.add(cycle);
		cycle = Arrays.asList(new Integer[]{6});
		cycles.add(cycle);

		Set<Integer> domain = getAllElements(cycles);
		Permutation<Integer> permutation1 = getPermutation(cycles, domain);
		
		System.out.println(permutation1.toString());
		Permutation<Integer> inversePermutation = PermutationUtils_Solinger.getInverse(permutation1);
		System.out.println(inversePermutation);
	}
	
	@Test
	public void getCycleTest()
	{
		Set<List<Integer>> cycles = new HashSet<List<Integer>>();
		List<Integer> cycle = Arrays.asList(new Integer[]{3,5,7,9});
		cycles.add(cycle);
		cycle = Arrays.asList(new Integer[]{1,2,4,8});
		cycles.add(cycle);
		cycle = Arrays.asList(new Integer[]{6});
		cycles.add(cycle);

		Set<Integer> domain = getAllElements(cycles);
		Permutation<Integer> permutation1 = getPermutation(cycles, domain);
		
		List<Integer> testCycle = PermutationUtils_Solinger.getCycle(permutation1, 4);
		
		Set<List<Integer>> cycles2 = new HashSet<List<Integer>>();
		List<Integer> cycle2 = Arrays.asList(new Integer[]{3,5,7,9});
		cycles2.add(cycle2);
		cycle2 = testCycle;
		cycles2.add(cycle2);
		cycle2 = Arrays.asList(new Integer[]{6});
		cycles2.add(cycle2);
		Permutation<Integer> permutation2 = getPermutation(cycles, domain);
		assertEquals(permutation2.getImage(1), permutation1.getImage(1));
		assertEquals(permutation2.getImage(2), permutation1.getImage(2));
		assertEquals(permutation2.getImage(4), permutation1.getImage(4));
		assertEquals(permutation2.getImage(8), permutation1.getImage(8));
	}
	
	@Test
	public void inversePermutation()
	{
		Set<List<Integer>> cycles = new HashSet<List<Integer>>();
		List<Integer> cycle = Arrays.asList(new Integer[]{3,5,7,9});
		cycles.add(cycle);
		cycle = Arrays.asList(new Integer[]{1,2,4,8});
		cycles.add(cycle);
		cycle = Arrays.asList(new Integer[]{6});
		cycles.add(cycle);

		Set<Integer> domain = getAllElements(cycles);
		Permutation<Integer> permutation = getPermutation(cycles, domain);
		
		Set<Integer> desiredDomain = new HashSet<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
		
		Permutation<Integer> inversePermutation = PermutationUtils_Solinger.getInverse(permutation);
		
		assertEquals(new Integer(5), permutation.getImage(3));
		assertEquals(new Integer(7), permutation.getImage(5));
		assertEquals(new Integer(9), permutation.getImage(7));
		assertEquals(new Integer(3), permutation.getImage(9));
		assertEquals(new Integer(2), permutation.getImage(1));
		assertEquals(new Integer(4), permutation.getImage(2));
		assertEquals(new Integer(8), permutation.getImage(4));
		assertEquals(new Integer(1), permutation.getImage(8));
		assertEquals(new Integer(6), permutation.getImage(6));
		
		assertEquals(desiredDomain, inversePermutation.getDomain());
		
		assertEquals(new Integer(9), inversePermutation.getImage(3));
		assertEquals(new Integer(3), inversePermutation.getImage(5));
		assertEquals(new Integer(5), inversePermutation.getImage(7));
		assertEquals(new Integer(7), inversePermutation.getImage(9));
		assertEquals(new Integer(8), inversePermutation.getImage(1));
		assertEquals(new Integer(1), inversePermutation.getImage(2));
		assertEquals(new Integer(2), inversePermutation.getImage(4));
		assertEquals(new Integer(4), inversePermutation.getImage(8));
		assertEquals(new Integer(6), inversePermutation.getImage(6));
		
		for(Integer e : inversePermutation.getDomain())
		{
			assertEquals(e, inversePermutation.getImage(permutation.getImage(e)));
			assertEquals(e, permutation.getImage(inversePermutation.getImage(e)));
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