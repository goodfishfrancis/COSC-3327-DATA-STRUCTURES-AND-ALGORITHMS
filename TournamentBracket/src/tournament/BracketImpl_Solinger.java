package tournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BracketImpl_Solinger<P> extends BracketAbstract<P>
{
	public BracketImpl_Solinger(List<P> participantMatchups)
	{
		super(participantMatchups);
	}
	
	@Override
	public int getMaxLevel()
	{
		return (int)((Math.log((predictions.size()/2) + 1))/Math.log(2));
		//throw new RuntimeException("NOT IMPLEMENTED YET!");
	}

	@Override
	public Set<Set<P>> getGroupings(int level)
	{
		assert(0 <= level && level <= getMaxLevel());
		
		//create set to return
		Set<Set<P>> groupings = new HashSet<Set<P>>();
		
		// get first index of level zero and 
		// get size of sets for grouping
		int currentParticipantIndex = getFirstIndexOfLevelZero();
		int groupingSetSize = getSizeOfSetsForLevel(level);
		
		// construct sets of participants and add to groupings set
		while(currentParticipantIndex < predictions.size())
		{
			Set<P> groupingSet = new HashSet<P>();
			for(int i = 0; i <= groupingSetSize - 1; i++)
			{
				groupingSet.add(predictions.get(currentParticipantIndex));
				currentParticipantIndex++;
			}
			groupings.add(groupingSet);
		}
		
		return groupings;
	}

	@Override
	public Set<P> getViableParticipants(Set<P> grouping)
	{
		// get level for given grouping size
		int level = getLevelOfGrouping(grouping);
		assert(getGroupings(level).contains(grouping));
		
		List<P> groupingList = new ArrayList<P>(grouping);
		for(int i = 0; i < groupingList.size(); i++)
		{
			P currentParticipantFromGrouping = groupingList.get(i);
			int indexOfCurrentParticipant = getParticipantIndex(currentParticipantFromGrouping);
			int parentIndex = getParentIndex(indexOfCurrentParticipant);
			boolean stillViable = true;
			int count = 0;
			while (count < level && stillViable == true)
			{	
				if(predictions.get(parentIndex) != null && predictions.get(parentIndex) != currentParticipantFromGrouping)
				{
					grouping.remove(currentParticipantFromGrouping);
					stillViable = false;
				}
				else
				{
					count++;
					parentIndex = getParentIndex(parentIndex);
				}
			}
		}
		return grouping;
	}
	
	private int getLevelOfGrouping(Set<P> grouping) {
		
		return (int)(Math.log(grouping.size())/Math.log(2));
	}

	@Override
	public void setPredictedWinCount(P participant, int exactWinCount)
	{
		//throw new RuntimeException("NOT IMPLEMENTED YET!");
		assert(predictions.contains(participant)) : "ERROR: " + participant 
												 + " is not a participant in the tourney!!!";
		assert(participant != null);
		assert(0 <= exactWinCount && exactWinCount <= getMaxLevel());
		int currentIndexOfParticipant = getParticipantIndex(participant);
		
		for(int i = 0; i < exactWinCount; i++)
		{
			int nextIndexOfWin = getParentIndex(currentIndexOfParticipant);
			if(predictions.get(nextIndexOfWin) != null)
			{
				
			}
			predictions.set(nextIndexOfWin, participant);
			currentIndexOfParticipant = nextIndexOfWin;
		}
			
	}
	
	//Find two groupings a and b at a lower level such that a U b = grouping with a INT b = empty
	private Set<Set<P>> getSubordinateGroupings(Set<P> grouping)
	{
		assert grouping.size() > 1 : "grouping.size() = " + grouping.size() + " <= 1!: grouping = " + grouping;
		throw new RuntimeException("NOT IMPLEMENTED!");
	}
	
	private int getParticipantIndex(P participant)
	{
		return predictions.lastIndexOf(participant);
	}
	
	private static int getParentIndex(int childIndex)
	{
		return (((childIndex + 1)/2) - 1);
	}
	
	private Set<P> getGrouping(P member, int level)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}
	
	private int getFirstIndexOfLevelZero()
	{
		return (((predictions.size())/2));
	}
	
	private static int getSizeOfSetsForLevel(int level)
	{
		return (int)(Math.pow(2, level));
	}
	
	int getFirstIndexOfLevel(int level)
	{
		double exponent = ((getMaxLevel() - level) - 1);
		return (int)(Math.pow(2, exponent));
	}
	
}
