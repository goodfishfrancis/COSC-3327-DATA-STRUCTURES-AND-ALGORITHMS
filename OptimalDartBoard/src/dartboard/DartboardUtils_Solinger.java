package dartboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import combinatorics.Permutation;
import combinatorics.PermutationImpl_Solinger;

// THE COMPILER IS NOT GOING TO LIKE THIS FILE AS IS…
public class DartboardUtils_Solinger
{
	public static Permutation<Integer> getPermutation(Dartboard dartboard) 
	{
		List<Integer> domain = new ArrayList<Integer>(dartboard.getNumbers());
		List<Integer> cycle = new ArrayList<Integer>();
		Set<List<Integer>> cycles = new HashSet<List<Integer>>();
		int e = domain.get(0);
		cycle.add(e); // the cycle contains e at the very least
		Integer image = dartboard.getClockwiseAdjacentNumber(e);
		boolean cycleIsComplete = (e == dartboard.getClockwiseAdjacentNumber(image)); // if p(e) = e, then cycle is complete
		
		// otherwise we get the next image and add to cycle until e == image
		while(!cycleIsComplete)
		{
			cycle.add(image); 
			image = dartboard.getClockwiseAdjacentNumber(image);
			cycleIsComplete = (e == image);
		}
		cycles.add(cycle);
		Permutation p = new PermutationImpl_Solinger(cycles);
		return p;
	}
	
	
	public static int getIntegerAtPosition(Dartboard dartboard, int position) 
	{
		int integerAtCurrentPosition = dartboard.getTopmostNumber();
		while (position > 0)
		{
			integerAtCurrentPosition = dartboard.getClockwiseAdjacentNumber(integerAtCurrentPosition);
			position--;
		}
		return integerAtCurrentPosition;
	}
}