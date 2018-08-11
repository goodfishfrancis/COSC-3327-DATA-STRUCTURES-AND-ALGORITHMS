package combinatorics;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class PermutationImpl_Solinger<E> implements Permutation<E> 
{
	private Set<E> domain;
	private Map<Integer, Map<E,E>> cycleToInputToImageMapMap;
	private int numberOfCycles;
	
	public PermutationImpl_Solinger(Set<List<E>> cycles)
	{
		this.cycleToInputToImageMapMap = getCycleToInputToImageMapMap(cycles);
		this.domain = getAllElements(cycles);
		numberOfCycles = cycles.size();
	}
	
	public PermutationImpl_Solinger(Set<List<E>> cycles, Set<E> domain)
	{
		assert(domain.containsAll(getAllElements(cycles)));
		//assert(getAllElements(cycles).containsAll(domain));
		this.cycleToInputToImageMapMap = getCycleToInputToImageMapMap(cycles);
		this.domain = domain;
		numberOfCycles = cycles.size();
	}
	
	private Map<Integer, Map<E, E>> getCycleToInputToImageMapMap(Set<List<E>> cycles) {
		
		//Make a list of cycles 
		List<List<E>> cyclesList = new ArrayList<List<E>>(cycles);
		
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

	@Override
	public E getImage(E element) {
		
		assert(domain.contains(element));
		
		// get cycle that contains given element 
		// and return image of given element
		E imageToReturn = getCycleOfElement(element).get(element); // this returns the image for the given element
		return imageToReturn;
	}

	@Override
	public E getPreImage(E element) 
	{
		assert(domain.contains(element));
		E preImage = null;
		
		// get cycle that contains element
		Map<E,E> cycleOfElement = getCycleOfElement(element);
		
		// make a list of images from cycle (outputs only)
		// and find index of element
		List<E> imageList = new ArrayList<E>(cycleOfElement.values());
		int indexOfPreImage = imageList.indexOf(element);
		
		List<E> preImageList = new ArrayList<E>(cycleOfElement.keySet());
		preImage = preImageList.get(indexOfPreImage);
		
		return preImage;
	}

	@Override
	public Set<E> getDomain() 
	{
		// return domain
		return domain;
	}
	
	// HELPER METHOD
	// returns Set<E> of all elements in a given Set<List<E>>
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
	
	// HELPER METHOD
	// gets cycle that contains given element e
	private Map<E,E> getCycleOfElement(E e)
	{
		Map <E, E> cycleToReturn = null;
		int i = 0;
		boolean cycleFound = false;
		
		while (i < cycleToInputToImageMapMap.size() && !cycleFound)
		{
			if(cycleToInputToImageMapMap.get(i).containsKey(e))
			{
				cycleFound = true;
				cycleToReturn = cycleToInputToImageMapMap.get(i);
			}
			else {i++;}
		}
		return cycleToReturn;
	}
	
	public String toString()
	{	
		StringBuffer aString = new StringBuffer();
		
		for (int i = 0; i < numberOfCycles; i++)
		{
			aString.append("Cycle " + i + ": \n");
			List<E> cycle_i_keyList = new ArrayList<E>(cycleToInputToImageMapMap.get(i).keySet());
			List<E> cycle_i_valueList = new ArrayList<E>(cycleToInputToImageMapMap.get(i).values());
			aString.append(cycle_i_keyList.toString());
			aString.append("\n");
			aString.append(cycle_i_valueList.toString());
			aString.append("\n");
			aString.append("\n");
		}
		
		return aString.toString();
		
	}

}
