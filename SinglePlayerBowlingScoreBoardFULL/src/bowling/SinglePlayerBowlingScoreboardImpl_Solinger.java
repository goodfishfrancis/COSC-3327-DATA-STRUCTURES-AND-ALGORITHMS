package bowling;

import java.util.HashMap;
import java.util.Map;

public class SinglePlayerBowlingScoreboardImpl_Solinger implements SinglePlayerBowlingScoreboard, AssignmentMetaData
{
	private static final int MAXIMUM_ROLLS = 21;	//Maximum rolls in a one player game
	private String playerName;
	private int[] pinsKnockedDownArray = new int[MAXIMUM_ROLLS];
	private int rollCount = 0;
	
	public SinglePlayerBowlingScoreboardImpl_Solinger(String playerName)
	{
		assert playerName != null : "playerName is null!";
		this.playerName = playerName;
	}
	
	public String getPlayerName()
	{
		return playerName;
	}
	
	public int getCurrentFrame() 
	{
		int strikeCount = getStrikeCount();
		return (1 + strikeCount) + ((rollCount - strikeCount)/2);
	}
	
	private int getStrikeCount()
	{
		int strikeCount = 0;
		for (int i = 0; i <= rollCount; i++)
		{
			if (pinsKnockedDownArray[i] == 10)
			{
				strikeCount++;
			}
		}
		return strikeCount;
	}

	public Mark getMark(int frameNumber, int boxIndex) 
	{	
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";
		assert (frameNumber <= getCurrentFrame()) : "frameNumber > getCurrentFrame()!";
//		Exercise for student: Fix
//		assert (boxIndex != 2) || (!isStrike(frameNumber) && !isSpare(frameNumber)) : "boxIndex = " + boxIndex + ", but there was a Strike! : frameNumber = " + frameNumber;
//		assert (boxIndex != 2) || (!isSpare(frameNumber)) : "boxIndex = " + boxIndex + ", but there was a Spare! : frameNumber = " + frameNumber;
		/*Mark mark = null;
		if(frameNumber < 10) mark = getMarkSingleDigitFrameNumber(frameNumber, boxIndex);
		else mark = getMarkTenthFrame(boxIndex);
		assert mark != null : "mark is null!";
		return mark;*/
		Map<Integer, Mark> boxToMarkMapMap = getFrameToBoxToMarkMapMap().get(frameNumber);
		Mark mark = boxToMarkMapMap.get(boxIndex);
		
		return mark;
	}
		
	private Mark getMarkSingleDigitFrameNumber(int frameNumber, int boxIndex)
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 9 : "frameNumber = " + frameNumber + " > 9!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 2 : "boxIndex = " + boxIndex + " > 2!";
		
		
		Map<Integer, Mark> boxToMarkMapMap = getFrameToBoxToMarkMapMap().get(frameNumber);
		Mark mark = boxToMarkMapMap.get(boxIndex);
		
		return mark;
		
	}
	
	private Mark getMarkTenthFrame(int boxIndex)
	{
		
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";
		
		Mark mark = Mark.ZERO;
		return mark;
	}

	public int getScore(int frameNumber) 
	{
		
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";
		
		
		int score = 0;
		int i = 0;
		int currentFrame = 1;
		
		while (i < rollCount && currentFrame < frameNumber)
		{
			score += pinsKnockedDownArray[i];
			if (pinsKnockedDownArray[i] == 10)
			{
				score += (pinsKnockedDownArray[i+1] + pinsKnockedDownArray[i+2]);
				i++;
				currentFrame++;
			}
			else if ((score + pinsKnockedDownArray[i+1]) == 10)
			{
				score += pinsKnockedDownArray[i+1] + pinsKnockedDownArray[i+2];
				i+=2;
				currentFrame++;
			}
			else
			{
				score += pinsKnockedDownArray[i+1];
				i+=2;
				currentFrame++;
			}
		}
		
		//return score;
		return (getScoreHelper(pinsKnockedDownArray, 0, 2*frameNumber));
		//return getFrameToScoreMap().get(frameNumber);
	}

	private int getScoreHelper(int[] intArray, int beginIndex, int endIndex)
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

	public boolean isGameOver() 
	{
		boolean gameIsOver = (rollCount == 21 || getStrikeCount() == 12);
		if (rollCount == 20)
		{
			gameIsOver = (getMark(10, 1) != Mark.STRIKE && getMark(10, 2) != Mark.SPARE);
			
		}
		
		return gameIsOver;
	}

	public void recordRoll(int pinsKnockedDown) 
	{
		assert 0 <= pinsKnockedDown : "pinsKnockedDown = " + pinsKnockedDown + " < 0!";
		assert pinsKnockedDown <= 10 : "pinsKnockedDown = " + pinsKnockedDown + " > 10!";
		assert (getCurrentBall() == 1) || 
				((getCurrentBall() == 2) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 1))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10))) || 
				((getCurrentBall() == 3) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 2))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10)));
		assert !isGameOver() : "Game is over!";

		pinsKnockedDownArray[rollCount] = pinsKnockedDown;
		rollCount++;
	}

	public int getCurrentBall() 
	{
		
		assert !isGameOver() : "Game is over!";
		
		int currentBall = (rollCount % 2) + 1;
				
		return currentBall;
	}

	private static final String VERTICAL_SEPARATOR = "#";
	private static final String HORIZONTAL_SEPARATOR = "#";
	private static final String LEFT_EDGE_OF_SMALL_SQUARE = "[";
	private static final String RIGHT_EDGE_OF_SMALL_SQUARE = "]";
	private String getScoreboardDisplay()
	{
		StringBuffer frameNumberLineBuffer = new StringBuffer();
		StringBuffer markLineBuffer = new StringBuffer();
		StringBuffer horizontalRuleBuffer = new StringBuffer();
		StringBuffer scoreLineBuffer = new StringBuffer();
		frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
		
		markLineBuffer.append(VERTICAL_SEPARATOR);
		horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
		scoreLineBuffer.append(VERTICAL_SEPARATOR);

		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			frameNumberLineBuffer.append("  " + frameNumber + "  ");
			markLineBuffer.append(" ");
			markLineBuffer.append(getMark(frameNumber, 1));
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(frameNumber, 2));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
			
			final int CHARACTER_WIDTH_SCORE_AREA = 5;
			for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
			if(isGameOver() || frameNumber < getCurrentFrame())
			{
				int score = getScore(frameNumber);
				final int PADDING_NEEDED_BEHIND_SCORE = 1;
				final int PADDING_NEEDED_IN_FRONT_OF_SCORE = CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
				for(int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++) scoreLineBuffer.append(" ");
				scoreLineBuffer.append(score);
				for(int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++) scoreLineBuffer.append(" ");
			}
			else
			{
				for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) scoreLineBuffer.append(" ");
			}
			
			frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
			markLineBuffer.append(VERTICAL_SEPARATOR);
			horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
			scoreLineBuffer.append(VERTICAL_SEPARATOR);
		}
		//Frame 10:
		{
			final String THREE_SPACES = "   ";
			frameNumberLineBuffer.append(THREE_SPACES + 10 + THREE_SPACES);

			markLineBuffer.append(" ");
			markLineBuffer.append(getMark(10, 1));
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(10, 2));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(10, 3));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
			
			final int CHARACTER_WIDTH_SCORE_AREA = 8;
			for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
			if(isGameOver())
			{
				int score = getScore(10);
				final int PADDING_NEEDED_BEHIND_SCORE = 1;
				final int PADDING_NEEDED_IN_FRONT_OF_SCORE = CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
				for(int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++) scoreLineBuffer.append(" ");
				scoreLineBuffer.append(score);
				for(int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++) scoreLineBuffer.append(" ");
			}
			else
			{
				for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) scoreLineBuffer.append(" ");
			}
			
			frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
			markLineBuffer.append(VERTICAL_SEPARATOR);
			horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
			scoreLineBuffer.append(VERTICAL_SEPARATOR);
		}
			
		return 	getPlayerName() + "\n" +
				horizontalRuleBuffer.toString() + "\n" +
				frameNumberLineBuffer.toString() + "\n" +
				horizontalRuleBuffer.toString() + "\n" +
				markLineBuffer.toString() + "\n" +
				scoreLineBuffer.toString() + "\n" +
				horizontalRuleBuffer.toString();
	}
	
	public String toString()
	{
		return getScoreboardDisplay();
	}
	
	private int getRollIndexOfFirstBall(int frameNumber)
	{
		
		int rollIndexOfFirstBall = 1;
		
		return rollIndexOfFirstBall;
	}
	
	private boolean isStrike(int frameNumber)
	{
		return (getMark(frameNumber, 2) == Mark.STRIKE);
	}
	
	private boolean isSpare(int frameNumber)
	{
		return (getMark(frameNumber, 2) == Mark.SPARE);
	}

	private boolean isStrikeOrSpare(Mark mark)
	{
		return ((mark == Mark.STRIKE) || (mark == Mark.SPARE));
	}
	
	private Map<Integer, Map<Integer, Mark>> getFrameToBoxToMarkMapMap()
	{
		Map<Integer, Map<Integer, Mark>> frameToBoxToMarkMapMap = 
										new HashMap<Integer, Map<Integer, Mark>>();
		
		
		int i = 0;
		int currentFrame = 1;
		while (i < rollCount)
		{
			Map<Integer, Mark> boxToMarkMap = new HashMap<Integer, Mark>();
			// check for strike
			if (pinsKnockedDownArray[i] < 10) // no strike
			{
				// check for spare
				if ((pinsKnockedDownArray[i] + pinsKnockedDownArray[i+1]) == 10) // made spare
				{
					Mark box1_mark = Mark.translate(pinsKnockedDownArray[i]);
					Mark box2_mark = Mark.SPARE;
					boxToMarkMap.put(1, box1_mark);
					boxToMarkMap.put(2, box2_mark);
					frameToBoxToMarkMapMap.put(currentFrame, boxToMarkMap);
					i+=2;
				}
				else // no spare
				{
					Mark box1_mark = Mark.translate(pinsKnockedDownArray[i]);
					Mark box2_mark = Mark.translate(pinsKnockedDownArray[i+1]);
					boxToMarkMap.put(1, box1_mark);
					boxToMarkMap.put(2, box2_mark);
					frameToBoxToMarkMapMap.put(currentFrame, boxToMarkMap);
					i+=2;
				}
			}
			else if (pinsKnockedDownArray[i] == 10)// made strike
			{
				Mark box1_mark = Mark.EMPTY;
				Mark box2_mark = Mark.STRIKE;
				boxToMarkMap.put(1, box1_mark);
				boxToMarkMap.put(2, box2_mark);
				frameToBoxToMarkMapMap.put(currentFrame, boxToMarkMap);
				i++;
			}
			if (currentFrame < 10) {currentFrame++;}
		}
		
		return frameToBoxToMarkMapMap;
	}
	
	private Map<Mark, Integer> getMarkToPinsKnockedDownMap()
	{
		Map<Mark, Integer> markToPinsKnockedDownMap = new HashMap<Mark, Integer>();
		markToPinsKnockedDownMap.put(Mark.ZERO, 0);
		markToPinsKnockedDownMap.put(Mark.ONE, 1);
		markToPinsKnockedDownMap.put(Mark.TWO, 2);
		markToPinsKnockedDownMap.put(Mark.THREE, 3);
		markToPinsKnockedDownMap.put(Mark.FOUR, 4);
		markToPinsKnockedDownMap.put(Mark.FIVE, 5);
		markToPinsKnockedDownMap.put(Mark.SIX, 6);
		markToPinsKnockedDownMap.put(Mark.SEVEN, 7);
		markToPinsKnockedDownMap.put(Mark.EIGHT, 8);
		markToPinsKnockedDownMap.put(Mark.NINE, 9);
		markToPinsKnockedDownMap.put(Mark.STRIKE, 10);
		
		return markToPinsKnockedDownMap;
	}
	
	private Map<Integer, Integer> getFrameToScoreMap()
	{
		Map<Integer, Integer> frameToScoreMap = new HashMap();
		
		int score = 0;
		int box1_pinsKnockedDown = 0;
		int box2_pinsKnockedDown = 0;
		int i = 0;
		int currentFrame = 1;
		
		while (i < rollCount)
		{
			score += pinsKnockedDownArray[i];
			// check for strike
			if (pinsKnockedDownArray[i] == 10)
			{
				score += pinsKnockedDownArray[i+1] + pinsKnockedDownArray[i+2];
				i++;
			}
			else if (pinsKnockedDownArray[i] + pinsKnockedDownArray[i+1] == 10)
			{
				score += pinsKnockedDownArray[i+1] + pinsKnockedDownArray[i+2];
				i += 2;
			}
			else
			{
				score += pinsKnockedDownArray[i+1];
				i += 2;
			}
			
			frameToScoreMap.put(currentFrame, score);
			
		}
				
		return frameToScoreMap;		
	}
	
	//*************************************************
	//*************************************************
	//*************************************************
	//*********ASSIGNMENT METADATA STUFF***************
	public String getFirstNameOfSubmitter() 
	{
		return "Alexander";
	}

	public String getLastNameOfSubmitter() 
	{
		return "Solinger";
	}
	
	public double getHoursSpentWorkingOnThisAssignment()
	{
		return 12;
	}
	
	public int getScoreAgainstTestCasesSubset()
	{
		int score = 88;
		return score;
	}
}