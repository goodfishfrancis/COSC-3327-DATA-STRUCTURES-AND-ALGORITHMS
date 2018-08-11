//package test;
//
//import static java.lang.annotation.ElementType.METHOD;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//import static org.junit.Assert.assertEquals;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import bowling.AssignmentMetaData;
//import bowling.Mark;
//import bowling.SinglePlayerBowlingScoreboard;
//import bowling.SinglePlayerBowlingScoreboardImpl_Solinger;
//
///**
// * @author kart
// *
// */
//public class SinglePlayerBowlingScoreboard_NoSparesStrikes_NoTenth_TestCasesSubsetForStudents_REVISED {
//	public static final String PACKAGE_NAME = "bowling";
//	public static final String PREFIX = "SinglePlayerBowlingScoreboard";
//	public static final String SUFFIX = "";
//	protected static SinglePlayerBowlingScoreboard singlePlayerBowlingScoreboard_STUDENT;
//	protected static AssignmentMetaData assignmentMetaData_STUDENT;
//	protected static final String BOWLER_NAME = "Big Lebowski";
//	protected static String TEST_GOAL_MESSAGE;
//	
//	@Retention(value=RUNTIME)
//	@Target(value=METHOD)
//	public @interface Points {
//		int value();
//	}
//	
//	@Before
//	public void initBeforeEachTestMethod()
//	{
//		singlePlayerBowlingScoreboard_STUDENT = new SinglePlayerBowlingScoreboardImpl_Solinger(BOWLER_NAME);
//		assignmentMetaData_STUDENT = (AssignmentMetaData)singlePlayerBowlingScoreboard_STUDENT;
//	}
//	
//	@Points(value=0)
//	@Test(expected=AssertionError.class)
//	public void assertionsEnabledTest()
//	{
//		TEST_GOAL_MESSAGE = "Check whether assertions are enabled";
//		assert false;
//		throw new RuntimeException("ENABLE ASSERTIONS IN RUN CONFIGURATIONS!");
//	}
//	
//	@Points(value=0)
//	@Test(timeout=3000)
//	public void hoursWorkedAndTestCasesScoreTest()
//	{
//		TEST_GOAL_MESSAGE = "Implement all methods in AssignmentMetaData";
//		System.out.println("Hours Worked: " + assignmentMetaData_STUDENT.getHoursSpentWorkingOnThisAssignment());
//		System.out.println("Score on TestCases_Subset: " + assignmentMetaData_STUDENT.getScoreAgainstTestCasesSubset());
//	}
//	
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void playerNameTest() {
//		TEST_GOAL_MESSAGE = "Get getPlayerName() correct";
//		assertEquals(BOWLER_NAME, singlePlayerBowlingScoreboard_STUDENT.getPlayerName());
//		//System.out.println("playerNameTest:\n" + singlePlayerBowlingScoreboard_STUDENT);
//	}
//	
//	@Points(value=5)
//	@Test(expected=AssertionError.class, timeout=3000)
//	public void tooManyPinsTest() {
//		TEST_GOAL_MESSAGE = "Throw AssertionError when too many pins are sent to recordRoll()";
//		int[] pinsKnockedDownArray = new int[]{19};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//	}
//	
//	@Points(value=5)
//	@Test(expected=AssertionError.class, timeout=3000)
//	public void tooManyPinsTest2() {
//		TEST_GOAL_MESSAGE = "Throw AssertionError when too many pins are sent to recordRoll()";
//		int[] pinsKnockedDownArray = new int[]{7, 7};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//	}
//	
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void noRollsCurrentFrameCurrentBallTest() {
//		TEST_GOAL_MESSAGE = "getCurrentFrame() and getCurrentBall both return 1 for empty scoreboard";
//		int[] pinsKnockedDownArray = new int[]{};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);		
//		assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
//		assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
//	}
//	
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void twoGutterBallMarkTest_OnlyNineFrames()
//	{
//		TEST_GOAL_MESSAGE = "Handle gutter balls correctly for first nine frames";
//		int[] pinsKnockedDownArray = new int[]{0, 0};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//		assertEquals(Mark.ZERO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
//		assertEquals(Mark.ZERO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
//	}
//	
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void twoGutterBallScoreTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getScore(1) correctly for two gutter ball first frame";
//		int[] pinsKnockedDownArray = new int[]{0, 0};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//		assertEquals(0, singlePlayerBowlingScoreboard_STUDENT.getScore(1));
//	}
//	
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void twoRollsMarkTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getMark(1,1) and getMark(1,2) correctly for simple first frame";
//		int[] pinsKnockedDownArray = new int[]{7, 2};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//
//		assertEquals(Mark.SEVEN, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
//		assertEquals(Mark.TWO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));	
//	}
//	
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void twoRollsMark2Test()
//	{
//		TEST_GOAL_MESSAGE = "Handle getMark(1,1) and getMark(1,2) correctly for simple first frame";
//		int[] pinsKnockedDownArray = new int[]{3, 4};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//
//		assertEquals(Mark.THREE, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
//		assertEquals(Mark.FOUR, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
//	}
//
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void twoRollsScoreTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getScore(1) correctly for simple first frame";
//		int[] pinsKnockedDownArray = new int[]{3, 4};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//
//		assertEquals(sum(pinsKnockedDownArray, 0, pinsKnockedDownArray.length), singlePlayerBowlingScoreboard_STUDENT.getScore(1));
//	}
//
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void fourRollsMarkTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getMark(f,1) and getMark(f,2) correctly for two simple frames";
//		int[] pinsKnockedDownArray = new int[]{2, 0, 4, 5};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//
//		assertEquals(Mark.TWO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
//		assertEquals(Mark.ZERO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
//
//		assertEquals(Mark.FOUR, singlePlayerBowlingScoreboard_STUDENT.getMark(2, 1));
//		assertEquals(Mark.FIVE, singlePlayerBowlingScoreboard_STUDENT.getMark(2, 2));
//	}
//
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void fourThreesScoreTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getScore(f) correctly for two simple frames";
//		int[] pinsKnockedDownArray = new int[]{3, 3, 3, 3};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//		
//		for(int frameNumber = 1; frameNumber <= 2; frameNumber++)
//		{
//			assertEquals(sum(pinsKnockedDownArray, 0, 2*frameNumber), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber));
//		}
//	}
//
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void fiveRollsScoreTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getScore(f) correctly for two simple frames";
//		int[] pinsKnockedDownArray = new int[]{2, 3, 4, 5, 6};
//		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray);
//		
//		for(int frameNumber = 1; frameNumber <= 2; frameNumber++)
//		{
//			assertEquals(sum(pinsKnockedDownArray, 0, 2*frameNumber), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber));
//		}
//	}
//
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void noSparesOrStrikesNineFrame_CurrentFrameCurrentBallTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getCurrentBall() correctly for nine simple frames";
//		int[] pinsKnockedDownArray = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
//
//		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
//		{
//			assertEquals(frameNumber, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
//			assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
//			
//			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
//			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";
//
//			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
//
//			assertEquals(frameNumber, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
//			assertEquals(2, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
//			
//			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
//			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";
//			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);
//
//			assertEquals(frameNumber + 1, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
//			assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
//		}
//	}
//
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void noSparesOrStrikesNineFrame_MarkTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getMark(f,1), getMark(f,2) correctly for nine simple frames";
//		int[] pinsKnockedDownArray = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
//
//		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
//		{
//			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
//			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";
//			
//			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
//			
//			assertEquals(Mark.translate(firstRollInFrame), singlePlayerBowlingScoreboard_STUDENT.getMark(frameNumber, 1));
//			
//			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
//			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";
//
//			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);
//
//			assertEquals(Mark.translate(firstRollInFrame), singlePlayerBowlingScoreboard_STUDENT.getMark(frameNumber, 1));
//			assertEquals(Mark.translate(secondRollInFrame), singlePlayerBowlingScoreboard_STUDENT.getMark(frameNumber, 2));
//		}
//	}
//
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void noSparesOrStrikesNineFrameScoreTest()
//	{
//		TEST_GOAL_MESSAGE = "Handle getScore(f) correctly for nine simple frames";
//		int[] pinsKnockedDownArray = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
//
//		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
//		{
//			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
//			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";
//			
//			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
//			
//			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
//			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";
//
//			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);
//
//			assertEquals(sum(pinsKnockedDownArray, 0, 2*frameNumber), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber));
//		}
//	}
//	
//	@Points(value=5)
//	@Test(timeout=3000)
//	public void noSparesOrStrikesNineFrameScore2Test()
//	{
//		TEST_GOAL_MESSAGE = "Handle getScore(f) correctly for nine simple frames";
//		int[] pinsKnockedDownArray = new int[]{1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0};
//
//		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
//		{
//			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
//			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";
//			
//			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
//			
//			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
//			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";
//
//			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);
//
//			assertEquals(sum(pinsKnockedDownArray, 0, 2*frameNumber), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber));
//		}
//	}
//
//	/**********************************************************************************************************************/
//	
//	private void recordPins(SinglePlayerBowlingScoreboard bowlingScoreboard, int[] pinsKnockedDownArray)
//	{
//		for(int i = 0; i < pinsKnockedDownArray.length; i++)
//		{
//			bowlingScoreboard.recordRoll(pinsKnockedDownArray[i]);
//		}
//	}
//	
//	//post: rv = intArray[beginIndex] + intArray[beginIndex + 1] + ... + intArray[endIndex - 1]
//	private static int sum(int[] intArray, int beginIndex, int endIndex)
//	{
//		assert beginIndex >= 0 : "beginIndex = " + beginIndex + " < 0!";
//		assert endIndex <= (intArray.length) : "endIndex = " + endIndex + " > " + (intArray.length) + " = (intArray.length)!";
//		assert beginIndex < endIndex : "beginIndex = " + beginIndex + " > " + endIndex + " = endIndex!";
//		int sum = 0;
//		for(int i = beginIndex; i < endIndex; i++)
//		{
//			sum = sum + intArray[i];
//		}
//		return sum;
//	}
//	
//	
//}
package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bowling.AssignmentMetaData;
import bowling.Mark;
import bowling.SinglePlayerBowlingScoreboard;
import bowling.SinglePlayerBowlingScoreboardImpl_Solinger;

/**
 * @author kart
 *
 */
public class SinglePlayerBowlingScoreboard_NoSparesStrikes_NoTenth_TestCasesSubsetForStudents_REVISED {
	public static final String PACKAGE_NAME = "bowling";
	public static final String PREFIX = "SinglePlayerBowlingScoreboard";
	public static final String SUFFIX = "";
	protected static SinglePlayerBowlingScoreboard singlePlayerBowlingScoreboard_STUDENT;
	protected static AssignmentMetaData assignmentMetaData_STUDENT;
	protected static final String BOWLER_NAME = "Big Lebowski";
	protected static String TEST_GOAL_MESSAGE;

	@Before
	public void initBeforeEachTestMethod()
	{
		singlePlayerBowlingScoreboard_STUDENT = new SinglePlayerBowlingScoreboardImpl_Solinger(BOWLER_NAME);
		assignmentMetaData_STUDENT = (AssignmentMetaData)singlePlayerBowlingScoreboard_STUDENT;
	}
	
	@Points(value=0)
	@Test
	public void hoursWorkedAndTestCasesScoreTest()
	{
		TEST_GOAL_MESSAGE = "Implement getHoursSpentWorkingOnThisAssignment() and getScoreAgainstTestCasesSubset()";
		
		System.out.println("Hours Worked: " + assignmentMetaData_STUDENT.getHoursSpentWorkingOnThisAssignment());
		System.out.println("Score on TestCases_Subset: " + assignmentMetaData_STUDENT.getScoreAgainstTestCasesSubset());
	}
	
	@Points(value=5)
	@Test
	public void playerNameTest() {
		TEST_GOAL_MESSAGE = "Implement getPlayerName() correctly";
		
		assertEquals(BOWLER_NAME, singlePlayerBowlingScoreboard_STUDENT.getPlayerName());
		//System.out.println("playerNameTest:\n" + singlePlayerBowlingScoreboard_STUDENT);
	}
	
	@Points(value=5)
	@Test(expected=AssertionError.class)
	public void tooManyPinsTest() {
		int[] pinsKnockedDownArray = new int[]{19};
		String messagePrefix = "Figure out there are too many pins in " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
	}
	
	@Points(value=5)
	@Test(expected=AssertionError.class)
	public void tooManyPinsTest2() {
		int[] pinsKnockedDownArray = new int[]{7, 7};
		String messagePrefix = "Figure out there are too many pins in " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);		
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
	}
	
	@Points(value=5)
	@Test
	public void noRollsCurrentFrameCurrentBallTest() {
		int[] pinsKnockedDownArray = new int[]{};
		String messagePrefix = "Get getCurrentFrame() and getCurrentBall() correct: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);	
		assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
		assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
	}
	
	@Points(value=5)
	@Test
	public void twoGutterBallMarkTest()
	{
		int[] pinsKnockedDownArray = new int[]{0, 0};
		String messagePrefix = "Get getMark() correct for all frames: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);		
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
		assertEquals(Mark.ZERO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
		assertEquals(Mark.ZERO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
	}
	
	@Points(value=5)
	@Test
	public void twoGutterBallScoreTest()
	{
		int[] pinsKnockedDownArray = new int[]{0, 0};
		String messagePrefix = "Get getScore() correct for the first frame: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);	
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
		assertEquals(0, singlePlayerBowlingScoreboard_STUDENT.getScore(1));
	}
	
	@Points(value=5)
	@Test
	public void twoRollsMarkTest()
	{
		int[] pinsKnockedDownArray = new int[]{7, 2};
		String messagePrefix = "Get getMark() correct for first frame: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);			
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);

		assertEquals(Mark.SEVEN, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
		assertEquals(Mark.TWO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));	
	}

	@Points(value=5)
	@Test
	public void oneRollStrikeBoxesTest()
	{
		int[] pinsKnockedDownArray = new int[]{10};
		String messagePrefix = "Place strike in the correct box: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);		
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
		
		assertEquals(Mark.EMPTY, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
		assertEquals(Mark.STRIKE, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
	}

	@Points(value=5)
	@Test
	public void twoRollsGutterSpareMarkTest()
	{
		int[] pinsKnockedDownArray = new int[]{0, 10};
		String messagePrefix = "Represent gutter ball and spare correctly in first frame: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
		
		assertEquals(Mark.ZERO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
		assertEquals(Mark.SPARE, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
	}
	
	@Points(value=5)
	@Test
	public void twoRollsSpareMark2Test()
	{
		int[] pinsKnockedDownArray = new int[]{4, 6};
		String messagePrefix = "Represent spare correctly in first frame: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);

		assertEquals(Mark.FOUR, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
		assertEquals(Mark.SPARE, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
	}
	
	@Points(value=5)
	@Test
	public void twoRollsMark2Test()
	{
		int[] pinsKnockedDownArray = new int[]{3, 4};
		String messagePrefix = "Represent marks correctly in first frame: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);		
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);

		assertEquals(Mark.THREE, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
		assertEquals(Mark.FOUR, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
	}

	@Points(value=5)
	@Test
	public void twoRollsScoreTest()
	{
		int[] pinsKnockedDownArray = new int[]{3, 4};
		String messagePrefix = "Get score correct in first frame: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);		
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);

		TEST_GOAL_MESSAGE = messagePrefix + "\nSubtest: getScore(1)";
		assertEquals(sum(pinsKnockedDownArray, 0, pinsKnockedDownArray.length), singlePlayerBowlingScoreboard_STUDENT.getScore(1));
	}

	@Points(value=5)
	@Test
	public void twoRollsSpareMarkTest()
	{
		int[] pinsKnockedDownArray = new int[]{3, 7};
		String messagePrefix = "Get both marks correct in first frame: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);			
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);

		assertEquals(Mark.THREE, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
		assertEquals(Mark.SPARE, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));
	}

	@Points(value=5)
	@Test
	public void fourRollsMarkTest()
	{
		int[] pinsKnockedDownArray = new int[]{2, 0, 4, 5};
		String messagePrefix = "Get marks correct in first two frames: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);					
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);

		assertEquals(Mark.TWO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 1));
		assertEquals(Mark.ZERO, singlePlayerBowlingScoreboard_STUDENT.getMark(1, 2));

		assertEquals(Mark.FOUR, singlePlayerBowlingScoreboard_STUDENT.getMark(2, 1));
		assertEquals(Mark.FIVE, singlePlayerBowlingScoreboard_STUDENT.getMark(2, 2));
	}

	@Points(value=5)
	@Test
	public void fourThreesScoreTest()
	{
		int[] pinsKnockedDownArray = new int[]{3, 3, 3, 3};
		String messagePrefix = "Get score correct in first two frames: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);		
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
		
		for(int frameNumber = 1; frameNumber <= 2; frameNumber++)
		{
			assertEquals(sum(pinsKnockedDownArray, 0, 2*frameNumber), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber));
		}
	}
	
	@Points(value=5)
	@Test
	public void threeStrikesMarkTest()
	{
		int[] pinsKnockedDownArray = new int[]{10, 10, 10};
		String messagePrefix = "Get marks correct in first three frames: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);		
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
		
		for(int frameNumber = 1; frameNumber <= 3; frameNumber++)
		{
			assertEquals(Mark.EMPTY, singlePlayerBowlingScoreboard_STUDENT.getMark(frameNumber, 1));
			assertEquals(Mark.STRIKE, singlePlayerBowlingScoreboard_STUDENT.getMark(frameNumber, 2));
		}
	}
	
	@Points(value=5)
	@Test
	public void threeStrikesScoreTest()
	{
		int[] pinsKnockedDownArray = new int[]{10, 10, 10};
		String messagePrefix = "Get score correct in first frame: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);				
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);

		assertEquals(30, singlePlayerBowlingScoreboard_STUDENT.getScore(1));
	}

	@Points(value=5)
	@Test
	public void fiveRollsScoreTest()
	{
		int[] pinsKnockedDownArray = new int[]{2, 3, 4, 5, 6};
		String messagePrefix = "Get score correct in two frames: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);				
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);
		
		for(int frameNumber = 1; frameNumber <= 2; frameNumber++)
		{
			assertEquals(sum(pinsKnockedDownArray, 0, 2*frameNumber), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber));
		}
	}

	@Points(value=5)
	@Test
	public void noSparesOrStrikesNineFrame_CurrentFrameCurrentBallTest()
	{
		int[] pinsKnockedDownArray = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
		String messagePrefix = "Get current frame and ball correct for first nine frames: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);				
		TEST_GOAL_MESSAGE = messagePrefix;
		
		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			assertEquals(frameNumber, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
			assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
			
			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";

			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
			
			assertEquals(frameNumber, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
			assertEquals(2, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
			
			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";
			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);

			assertEquals(frameNumber + 1, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
			assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
		}
	}
	
	@Points(value=5)
	@Test(timeout=3000)
	public void noSparesOrStrikesNineFrame_MarkTest()
	{
		//TEST_GOAL_MESSAGE = "Handle getMark(f,1), getMark(f,2) correctly for nine simple frames";
		int[] pinsKnockedDownArray = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};

		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";
			
			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
			
			assertEquals(Mark.translate(firstRollInFrame), singlePlayerBowlingScoreboard_STUDENT.getMark(frameNumber, 1));
			
			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";

			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);

			assertEquals(Mark.translate(firstRollInFrame), singlePlayerBowlingScoreboard_STUDENT.getMark(frameNumber, 1));
			assertEquals(Mark.translate(secondRollInFrame), singlePlayerBowlingScoreboard_STUDENT.getMark(frameNumber, 2));
		}
	}

	@Points(value=5)
	@Test
	public void noSparesOrStrikesNineFrameScoreTest()
	{
		int[] pinsKnockedDownArray = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
		String messagePrefix = "Get score correct for first nine frames: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);				
		TEST_GOAL_MESSAGE = messagePrefix;
		
		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";
			
			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
			
			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";

			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);

			assertEquals(sum(pinsKnockedDownArray, 0, 2*frameNumber), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber));
		}
	}
	
	@Points(value=5)
	@Test
	public void noSparesOrStrikesNineFrameScore2Test()
	{
		int[] pinsKnockedDownArray = new int[]{1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0};
		String messagePrefix = "Get score correct for first nine frames: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);
		TEST_GOAL_MESSAGE = messagePrefix;
		
		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";
			
			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
			
			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";

			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);
			
			assertEquals(sum(pinsKnockedDownArray, 0, 2*frameNumber), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber));
		}
	}
	
	@Points(value=5)
	@Test
	public void noSparesStrikesCompleteGameGameOverTest()
	{
		int[] pinsKnockedDownArray = new int[]{1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, 2, 2};
		String messagePrefix = "Get isGameOver() correct for: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);
		TEST_GOAL_MESSAGE = messagePrefix;
		
		for(int frameNumber = 1; frameNumber <= 10; frameNumber++)
		{
			int firstRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1)];
			assert firstRollInFrame < 10 : "firstRollInFrame = " + firstRollInFrame + " <> 10!";
			
			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRollInFrame);
			
			int secondRollInFrame = pinsKnockedDownArray[2*(frameNumber - 1) + 1];
			assert secondRollInFrame < 10 : "secondRollInFrame = " + secondRollInFrame + " <> 10!";

			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRollInFrame);
		}
		assertEquals(true, singlePlayerBowlingScoreboard_STUDENT.isGameOver());
	}
	
	@Points(value=5)
	@Test(expected=AssertionError.class)
	public void tryingToBowlAfterGameCompleted()
	{
		int[] pinsKnockedDownArray = new int[]{0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2};
		String messagePrefix = "Throw AssertionError on: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length) + ".recordRoll(3)";
		recordPins(singlePlayerBowlingScoreboard_STUDENT, pinsKnockedDownArray, messagePrefix);

		singlePlayerBowlingScoreboard_STUDENT.recordRoll(3);
	}
	
	@Points(value=5)
	@Test
	public void dutchTwoHundredFrameBallAndScoreTest()
	{
		int[] pinsKnockedDownArray = new int[]{1, 9, 10, 2, 8, 10, 3, 7, 10, 4, 6, 10, 5, 5, 10, 6, 4};
		String messagePrefix = "Get current frame, ball, and score correct: " + toString(pinsKnockedDownArray, pinsKnockedDownArray.length);
		TEST_GOAL_MESSAGE = messagePrefix;
		
		int rollCount = 0;
		
		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			assertEquals(frameNumber, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
			assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
			boolean frameHasStrikeInIt = (frameNumber % 2 == 0);
			if(frameHasStrikeInIt)
			{
				int roll = pinsKnockedDownArray[3*(frameNumber/2)-1];
				assert roll == 10 : "roll = " + roll + " <> 10!";

				singlePlayerBowlingScoreboard_STUDENT.recordRoll(roll);
				rollCount++;
				
				assertEquals(frameNumber + 1, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
				assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
			}
			else//frameHasSpareInIt
			{
				int firstRoll = pinsKnockedDownArray[3*((frameNumber - 1)/2)];
				singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRoll);
				rollCount++;
				
				assertEquals(frameNumber, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
				assertEquals(2, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());

				int secondRoll = pinsKnockedDownArray[3*((frameNumber - 1)/2) + 1];
				assert firstRoll + secondRoll == 10 : "(firstRoll + secondRoll) + " + (firstRoll + secondRoll) + " <> 10!";
				
				singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRoll);
				rollCount++;
				
				assertEquals(frameNumber + 1, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
				assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
			}
			boolean lastFrameHasSpareInIt = frameHasStrikeInIt;
			if(lastFrameHasSpareInIt && frameNumber > 2)
			{
				assertEquals(20*(frameNumber - 1), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber - 1));
				assertEquals(20*(frameNumber - 2), singlePlayerBowlingScoreboard_STUDENT.getScore(frameNumber - 2));
			}
		}
		//Tenth frame:
		{
			assertEquals(10, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
			assertEquals(1, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
			
			int firstRoll = pinsKnockedDownArray[3*(10/2)-1];
			assert firstRoll == 10 : "firstRoll = " + firstRoll + " <> 10!";
			
			singlePlayerBowlingScoreboard_STUDENT.recordRoll(firstRoll);
			rollCount++;
			
			assertEquals(180, singlePlayerBowlingScoreboard_STUDENT.getScore(9));
			
			assertEquals(10, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
			assertEquals(2, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
			
			int secondRoll = pinsKnockedDownArray[3*(10/2)];

			singlePlayerBowlingScoreboard_STUDENT.recordRoll(secondRoll);
			rollCount++;

			assertEquals(10, singlePlayerBowlingScoreboard_STUDENT.getCurrentFrame());
			assertEquals(3, singlePlayerBowlingScoreboard_STUDENT.getCurrentBall());
			
			int thirdRoll = pinsKnockedDownArray[3*(10/2) + 1];
			assert (secondRoll + thirdRoll) == 10 : "(secondRoll + thirdRoll) = " + (secondRoll + thirdRoll) + " <> 10!";
			
			singlePlayerBowlingScoreboard_STUDENT.recordRoll(thirdRoll);
			rollCount++;
			
			assertEquals(true, singlePlayerBowlingScoreboard_STUDENT.isGameOver());
		}
		assertEquals(200, singlePlayerBowlingScoreboard_STUDENT.getScore(10));
		System.out.println("Dutch 200: \n" + singlePlayerBowlingScoreboard_STUDENT);
	}
	
	private void recordPins(SinglePlayerBowlingScoreboard bowlingScoreboard, int[] pinsKnockedDownArray, String messagePrefix)
	{		
		TEST_GOAL_MESSAGE = messagePrefix;
		for(int i = 0; i < pinsKnockedDownArray.length; i++)
		{
			TEST_GOAL_MESSAGE = messagePrefix + "\n" + "Sub-test: recordRoll(" + pinsKnockedDownArray[i] + ")";
			bowlingScoreboard.recordRoll(pinsKnockedDownArray[i]);
		}
	}
	
	//post: rv = intArray[beginIndex] + intArray[beginIndex + 1] + ... + intArray[endIndex - 1]
	private static int sum(int[] intArray, int beginIndex, int endIndex)
	{
		assert beginIndex >= 0 : "beginIndex = " + beginIndex + " < 0!";
		assert endIndex <= (intArray.length) : "endIndex = " + endIndex + " > " + (intArray.length) + " = (intArray.length)!";
		assert beginIndex < endIndex : "beginIndex = " + beginIndex + " > " + endIndex + " = endIndex!";
		int sum = 0;
		for(int i = beginIndex; i < endIndex; i++)
		{
			sum = sum + intArray[i];
		}
		return sum;
	}
	
	private String toString(int[] intArray, int k)
	{
		assert k <= intArray.length : "k = " + k + " > " + intArray.length + " = intArray.length!";
		StringBuffer sb = new StringBuffer("");
		sb.append("[");
		for(int i = 0; i < k; i++)
		{
			sb.append(intArray[i]);
			if(i < intArray.length - 1) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
