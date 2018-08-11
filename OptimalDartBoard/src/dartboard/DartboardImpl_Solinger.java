package dartboard;

import java.util.Set;

import combinatorics.Permutation;

public class DartboardImpl_Solinger implements Dartboard
{
	
	private Permutation<Integer> dartBoardPermutation;
	private int topmostNumber;
	
	//pre: left to student
	//post: left to student
	public DartboardImpl_Solinger(Permutation<Integer> p, int topmostNumber)
	{
		dartBoardPermutation = p;
		this.topmostNumber = topmostNumber;
	}

	@Override
	public int getTopmostNumber() 
	{
		// TODO Auto-generated method stub
		return topmostNumber;
	}

	@Override
	public int getClockwiseAdjacentNumber(int number) 
	{
		// TODO Auto-generated method stub
		return dartBoardPermutation.getImage(number);
	}

	@Override
	public Set<Integer> getNumbers() 
	{
		// TODO Auto-generated method stub
		return dartBoardPermutation.getDomain();
	}
	
}
