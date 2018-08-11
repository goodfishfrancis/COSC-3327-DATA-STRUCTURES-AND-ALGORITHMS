package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import tournament.Bracket;
import tournament.BracketImpl_Solinger;
import tournament.FIFASoccerTeam;
import static org.junit.Assert.*;
import static tournament.FIFASoccerTeam.*;

public class BracketTests_WorldCup2014_SUBSET_FOR_STUDENTS
{
	protected static Bracket<FIFASoccerTeam> worldCup2014KnockoutResults;
	
	@Before
	public void setUp()
	{
		List<FIFASoccerTeam> worldCup2014KnockoutRoundMatchups = Arrays.asList(BRAZIL, CHILE, COLOMBIA, URUGUAY, FRANCE, NIGERIA, GERMANY, ALGERIA, NETHERLANDS, MEXICO, COSTA_RICA, GREECE, ARGENTINA, SWITZERLAND, BELGIUM, USA);
		worldCup2014KnockoutResults = new BracketImpl_Solinger<FIFASoccerTeam>(worldCup2014KnockoutRoundMatchups);
		worldCup2014KnockoutResults.setPredictedWinCount(GERMANY, 4);
		worldCup2014KnockoutResults.setPredictedWinCount(NETHERLANDS, 3);
		worldCup2014KnockoutResults.setPredictedWinCount(BRAZIL, 2);
		worldCup2014KnockoutResults.setPredictedWinCount(ARGENTINA, 2);
		worldCup2014KnockoutResults.setPredictedWinCount(COLOMBIA, 1);
		worldCup2014KnockoutResults.setPredictedWinCount(FRANCE, 1);
		worldCup2014KnockoutResults.setPredictedWinCount(COSTA_RICA, 1);
		worldCup2014KnockoutResults.setPredictedWinCount(BELGIUM, 1);
	}
	
	@Test(expected=AssertionError.class)
	public void testSetPredictedWinCountNullParticipant()
	{
		worldCup2014KnockoutResults.setPredictedWinCount(null, 0);
	}
	
	@Test(expected=AssertionError.class)
	public void testSetPredictedWinCountParticipantNotInTournament()
	{
		worldCup2014KnockoutResults.setPredictedWinCount(SLOVAKIA, 0);
	}
	
	@Test
	public void testMaxLevel()
	{
		assertEquals(4, worldCup2014KnockoutResults.getMaxLevel());
	}
	
	@Test
	public void testGetGroupings1()
	{
		Set<Set<FIFASoccerTeam>> groupingsLevel1 = worldCup2014KnockoutResults.getGroupings(1);
		System.out.println("groupingsLevel1 = " + groupingsLevel1);
		assertTrue(groupingsLevel1.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(BRAZIL, CHILE))));
		assertTrue(groupingsLevel1.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(COLOMBIA, URUGUAY))));
		assertTrue(groupingsLevel1.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(FRANCE, NIGERIA))));
		assertTrue(groupingsLevel1.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(GERMANY, ALGERIA))));
		assertTrue(groupingsLevel1.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(NETHERLANDS, MEXICO))));
		assertTrue(groupingsLevel1.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(COSTA_RICA, GREECE))));
		assertTrue(groupingsLevel1.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(ARGENTINA, SWITZERLAND))));
		assertTrue(groupingsLevel1.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(BELGIUM, USA))));
	}
	
	@Test
	public void testGetGroupings3()
	{
		Set<Set<FIFASoccerTeam>> groupingsLevel3 = worldCup2014KnockoutResults.getGroupings(3);
		System.out.println("groupingsLevel3 = " + groupingsLevel3);
		assertTrue(groupingsLevel3.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(BRAZIL, CHILE, COLOMBIA, URUGUAY, FRANCE, NIGERIA, GERMANY, ALGERIA))));
		assertTrue(groupingsLevel3.contains(new HashSet<FIFASoccerTeam>(Arrays.asList(NETHERLANDS, MEXICO, COSTA_RICA, GREECE, ARGENTINA, SWITZERLAND, BELGIUM, USA))));
	}
	
	protected <P> Set<P> getSet(P[] participants)
	{
		Set<P> set = new HashSet<P>();
		for(int i = 0; i < participants.length; i++)
		{
			set.add(participants[i]);
		}
		return set;
	}
	
	@Test
	public void testGetViableParticpantsLevel1()
	{
		assertEquals(getSet(new FIFASoccerTeam[]{BRAZIL}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {BRAZIL, CHILE}))));
		assertEquals(getSet(new FIFASoccerTeam[]{COLOMBIA}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {COLOMBIA, URUGUAY}))));
		assertEquals(getSet(new FIFASoccerTeam[]{FRANCE}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {FRANCE, NIGERIA}))));
		assertEquals(getSet(new FIFASoccerTeam[]{GERMANY}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {GERMANY, ALGERIA}))));
		assertEquals(getSet(new FIFASoccerTeam[]{NETHERLANDS}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {NETHERLANDS, MEXICO}))));
		assertEquals(getSet(new FIFASoccerTeam[]{COSTA_RICA}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {COSTA_RICA, GREECE}))));
		assertEquals(getSet(new FIFASoccerTeam[]{ARGENTINA}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {ARGENTINA, SWITZERLAND}))));
		assertEquals(getSet(new FIFASoccerTeam[]{BELGIUM}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {BELGIUM, USA}))));
	}
	
	@Test
	public void testGetViableParticpantsLevel3()
	{
		assertEquals(getSet(new FIFASoccerTeam[]{GERMANY}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {BRAZIL, CHILE, COLOMBIA, URUGUAY, FRANCE, NIGERIA, GERMANY, ALGERIA}))));
		assertEquals(getSet(new FIFASoccerTeam[]{NETHERLANDS}), worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {NETHERLANDS, MEXICO, COSTA_RICA, GREECE, ARGENTINA, SWITZERLAND, BELGIUM, USA}))));
	}
	
	@Test(expected=AssertionError.class)
	public void testGetViableParticpantsBadGrouping()
	{
		worldCup2014KnockoutResults.getViableParticipants(new HashSet<FIFASoccerTeam>(getSet(new FIFASoccerTeam[] {BRAZIL, COLOMBIA, FRANCE, GERMANY, NETHERLANDS, COSTA_RICA, CHILE, URUGUAY})));
	}
}

