package combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//THE COMPILER IS NOT GOING TO LIKE THIS FILE AS IS…
public class PermutationUtils_Solinger
{
	public static <E> List<E> getCycle(Permutation<E> permutation, E e)
	{
		assert(permutation.getDomain().contains(e));
		
		List<E> cycle = new ArrayList<E>();	
		cycle.add(e); // the cycle contains e at the very least
		E image = permutation.getImage(e);
		boolean cycleIsComplete = (e == permutation.getImage(image)); // if p(e) = e, then cycle is complete
		
		// otherwise we get the next image and add to cycle until e == image
		while(!cycleIsComplete)
		{
			cycle.add(image); 
			image = permutation.getImage(image);
			cycleIsComplete = (e == image);
		}
			
		return cycle;
		
	}
	
	private static <E> Integer getNumberOfCycles(Permutation<E> permutation)
	{
		int numberOfCycles = getPermutationAsSetOfLists(permutation).size();
		return numberOfCycles;
	}
	
	private static <E> Map<Integer, Map<E, E>> getCycleToInputToImageMapMap(Permutation<E> permutation) 
	{
		
		//Make a list of cycles 
		List<List<E>> cyclesList = new ArrayList<List<E>>(getPermutationAsSetOfLists(permutation));
		
		// map to go from a cycle to an input to an image
		Map<Integer, Map<E,E>> cycleToInputToImageMapMap = new HashMap<Integer, Map<E,E>>();
		
		// go through each element of each cycle and assign them as key:value pairs
		// in the map
		for(int i = 0; i < cyclesList.size(); i++)
		{
			Map<E,E> inputToImageMap = new HashMap<E,E>();
			for(int j = 0; j < cyclesList.get(i).size(); j++)
			{
				E input = cyclesList.get(i).get(j);
				E image = null;
				
				// if j is last element in the cycle, it's image
				// is the first element in the cycle
				if(j == cyclesList.get(i).size() -1)
				{
					image = cyclesList.get(i).get(0);
				}
				// otherwise, p(j) == j+1
				else {image = cyclesList.get(i).get(j+1);}
				
				// add to map of pre-image/image pairs
				// for current cycle
				inputToImageMap.put(input, image);
			}
			// add cycle to map
			cycleToInputToImageMapMap.put(i, inputToImageMap);
		}
		return cycleToInputToImageMapMap;
	}
	
	private static <E> Set<List<E>> getPermutationAsSetOfLists(Permutation<E> p)
	{
		// get domain of permutation and turn into a list
		List<E> domain = new ArrayList<E>(p.getDomain());
		Set<List<E>> permutationAsSetOfLists = new HashSet<List<E>>();
		while(!domain.isEmpty())
		{
			List<E> cycle = getCycle(p, domain.get(0));
			permutationAsSetOfLists.add(cycle);
			domain.removeAll(cycle);
		}
		return permutationAsSetOfLists;
	}
	
	public static <E> Permutation<E> getInverse(Permutation<E> permutation)
	{
		Set<List<E>> cycles = getPermutationAsSetOfLists(permutation);
		Map<Integer, Map<E,E>> cycleToInputToImageMapMap = getCycleToInputToImageMapMap(permutation);
		int numberOfCycles = getNumberOfCycles(permutation);
		Map<Integer, Map<E,E>> inverseCycleToInputToImageMapMap = new HashMap<Integer, Map<E,E>>();
		// get list of inputs and list of images 
		// for each cycle and switch
		for (int i = 0; i < numberOfCycles; i++)
		{
			Map<E,E> currentCycle = cycleToInputToImageMapMap.get(i);
			Map<E,E> inverted = new HashMap<E,E>();
			for(E j : currentCycle.keySet())
				inverted.put(currentCycle.get(j), j);
			inverseCycleToInputToImageMapMap.put(i, inverted);
		}
		Set<List<E>> permutationAsASetOfLists = new HashSet<List<E>>();
		
		for (int i = 0; i < numberOfCycles; i++)
		{
			Map<E,E> currentCycle = inverseCycleToInputToImageMapMap.get(i);
			List<E> currentCycleList = new ArrayList<E>();
			List<E> keySet = new ArrayList<E>(currentCycle.keySet());
			for(int j = 0; j < currentCycle.keySet().size(); j++)
			{
				currentCycleList.add(keySet.get(j));
				currentCycleList.add(currentCycle.get(keySet.get(j)));
				j++;
			}
			permutationAsASetOfLists.add(currentCycleList);
		}
		
		Permutation<E> inversePermutation = new PermutationImpl_Solinger<E>(permutationAsASetOfLists);
		
		
		
		return inversePermutation; 
	}
	
	//part of pre: permutation2.getDomain().containsAll(permutation1.getDomain())
	//post: rv = permutation1*permutation2, that is, 
	//rv.getImage(e) = permutation1.getImage(permutation2.getImage(e)) for all e in permutation2.getDomain()
	public static <E> Permutation<E> compose(Permutation<E> permutation1, Permutation<E> permutation2)
	{
		assert(permutation2.getDomain().containsAll(permutation1.getDomain()));
		List<E> permutation2Domain = new ArrayList<E>(permutation2.getDomain());
		Set<List<E>> compositePermutation = new HashSet<List<E>>();
				
		while(!permutation2Domain.isEmpty())
		{
			
			for (int i = 0; i < getNumberOfCycles(permutation2); i++)
			{
				List<E> cycle = getCycle(permutation2, permutation2Domain.get(0));
				permutation2Domain.removeAll(cycle);
			}
			
			
		}
		// TODO
		return null;
	}
	
	//part of post: rv = "permutation.getImage(e) != e
	//				(for all e in permutation.getDomain())"
	public static <E> boolean isCyclic(Permutation<E> permutation)
	{
		// TODO
		return false;
	}
}