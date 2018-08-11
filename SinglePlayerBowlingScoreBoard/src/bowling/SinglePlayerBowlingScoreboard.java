package bowling;

public interface SinglePlayerBowlingScoreboard 
{

	//Be careful, ball number and box index don't always match up
	//Be careful, getCurrentFrame() is not always the same as (rollCount/2)
	//Concerned about correctness and clarity, not efficiency
	//Do not change the interface (SinglePlayerBowlingScoreboard)
	//Do not add data members
	//I do not want your first attempt
	//There is a difference between a 0 and an empty in box 3 of the tenth frame
	//Number of rolls in a bowling game is variable
	//Translate these three examples into the pinCount sequence
	//Give them the constructor
	//Not all roll sequences are legal: [10, 9, 4, 5, 8, 10, 0, 3, 9, 8]

	public String getPlayerName();
	
	//Ex A:      9-  44  9/  61  9-  1-  --   X  7/ 3- 
	//            9  17  33  40  49  50  50  70  83  86 86
	//Ex B:       X  51  8-   X  8/  81  X   X   X  X9-
	//           16  22  30  50  68  77 107 137 166 185 185
	//Ex Ashley:  X   X  7/   X  8/   X  9/   X   X X7/
	//           27  47  67  87 107 127 147 177  204 224
	public boolean isGameOver();
	
	//part of pre: 1 <= frameNumber <= 10
	//part of pre: getCurrentFrame() > frameNumber
	public int getScore(int frameNumber);
	
	//part of pre: 1 <= frameNumber <= 10
	//part of pre: 1 <= boxIndex <= 3
	//part of pre: frameNumber < 10 ==> getCurrentFrame() > frameNumber
	//part of pre: frameNumber = 10 ==> isGameOver()
	//part of pre: (1 <= frameNumber < 10) ==> (1 <= boxIndex <= 2)
	//part of pre: boxIndex = 3 ==> frameNumber = 10
	//part of post: ((rv = Mark.STRIKE) && (1 <= frameNumber <= 9)) ==> (boxIndex == 2)
	//part of post: ((rv = Mark.SPARE) && (1 <= frameNumber <= 9)) ==> (boxIndex == 2)
	//part of post: ((rv = Mark.SPARE) && (frameNumber = 10)) ==> (boxIndex >= 2)
	//Using the PBA standard here...
	//"If a bowler gets a strike, it is recorded with an X in the small square..."
	//http://www.pba.com/resources/basics/bowling101.asp
	public Mark getMark(int frameNumber, int boxIndex);
	
	//part of pre: !isGameOver()
	//part of post: 1 <= rv <= 10
	public int getCurrentFrame();
	
	//part of pre: !isGameOver()
	//part of post: 1 <= rv <= 3
	//part of post: frameNumber < 10 ==> rv <= 2
	//part of post: ((frameNumber < 10) && (rv == 2)) ==> getMark(frameNumber, 1).equals(Mark.STRIKE)
	public int getCurrentBall();
	
	//part of pre: 0 <= pinsKnockedDown <= 10
	//much more here...
	public void recordRoll(int pinsKnockedDown);
	
	//rv = r1 + "\n" + r2, where r1 = " " and r2 = " "
	//Ex: 9-  44  9/  61  9-  1-  --   X  7/  3- 
	//     9  17  33  40  49  50  50  70  83  86
	//
	public String toString();
}

