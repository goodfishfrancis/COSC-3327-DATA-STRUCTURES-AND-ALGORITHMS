package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoardImpl_Solinger implements TicTacToeBoard
{
	
	// Symbolics
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;
	
	protected int[] movesArray;
	
	public TicTacToeBoardImpl_Solinger()
	{
		final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT;
		movesArray = new int[CELL_COUNT];
		for(int i = 0; i < CELL_COUNT; i++)
		{
			movesArray[i] = NO_MOVE;
		}
	}

	// part of pre: 0 <= ROW_COUNT && 0 <= column < COLUMN_COUNT
	// part of post: rv == null <==> the (row, column) spot on the 
	// 			     board is empty
	public Mark getMark(int row, int column) 
	{
		assert(0 <= row && row < ROW_COUNT) : "ERROR: row must be >= 0 and < " + ROW_COUNT;
		assert(0 <= column && column < COLUMN_COUNT) : "ERROR: column must be >= 0 and < " + COLUMN_COUNT;
		Mark[] markArray = getMarkArray(movesArray);
		
		return markArray[getBoardIndex(row, column)];
	}

	// part of pre: 0<= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	// part of pre: getMark(row, column) == null
	// part of pre: !isGameOver()
	// post: Left to student
	public void setMark(int row, int column) 
	{
		assert(0 <= row && row < ROW_COUNT) : "ERROR: row must be >= 0 and < " + ROW_COUNT;
		assert(0 <= column && column < COLUMN_COUNT) : "ERROR: column must be >= 0 and < " + COLUMN_COUNT;
		assert(!isGameOver()) : "ERROR; Game is over...";
		assert(getMark(row, column) == null) : "ERROR: spot is already marked...";
		
		movesArray[getNextEmptyIndex(movesArray)] = getBoardIndex(row, column);
		
	}

	// part of post: rv == null <==> it is neither player's turn (i.e. 
	// 				game is over)
	// part of post: "number of Marks on board is even"-> rv == Mark.X
	// part of post: "number of Marks on board is odd"-> rv == Mark.O
	public Mark getTurn() 
	{
		
		assert(getNextEmptyIndex(movesArray) != null) : "ERROR: NO FREE SPACES ON BOARD!!!";
		Mark markToReturn = null;
		
		if (isGameOver())
		{ 
			// do nothing
		}
		else if (getNextEmptyIndex(movesArray)%2 == 0)
		{
			markToReturn = Mark.X;
		}
		else if ((getNextEmptyIndex(movesArray)%2 != 0))
		{
			markToReturn = Mark.O;
		}
		return markToReturn;
	}

	// part of post: Left to student (see Tic-tac-toe rules in order 
	//				 to fill this out)
	public boolean isGameOver() 
	{
		boolean gameIsOver = false;
		
		if (getWinner() == Mark.X || getWinner() == Mark.O || getNextEmptyIndex(movesArray) == null)
		{
			gameIsOver = true;
		}
		
		return gameIsOver;
	}

	// part of pre: isGameOver()
	// part of post: rv == null <==> neither player won (i.e the game 
	//				 ended in a tie)
	public Mark getWinner() 
	{
		Mark winner = null;
		
		if (winnerX()) {winner = Mark.X;}
		else if (winnerO()) {winner = Mark.O;}
		
		return winner;
	}
	
	private boolean winnerO() 
	{
		boolean winnerO = false;
		Mark[] markArray = getMarkArray(movesArray);
		
		if (markArray[0] == Mark.O && markArray[1] == Mark.O && markArray[2] == Mark.O)
		{
			winnerO = true;
		}
		
		if (markArray[3] == Mark.O && markArray[4] == Mark.O && markArray[5] == Mark.O)
		{
			winnerO = true;
		}
		
		if (markArray[6] == Mark.O && markArray[7] == Mark.O && markArray[8] == Mark.O)
		{
			winnerO = true;
		}
		
		if (markArray[0] == Mark.O && markArray[3] == Mark.O && markArray[6] == Mark.O)
		{
			winnerO = true;
		}
		
		if (markArray[1] == Mark.O && markArray[4] == Mark.O && markArray[7] == Mark.O)
		{
			winnerO = true;
		}
		
		if (markArray[2] == Mark.O && markArray[5] == Mark.O && markArray[6] == Mark.O)
		{
			winnerO = true;
		}
		
		if (markArray[0] == Mark.O && markArray[4] == Mark.O && markArray[8] == Mark.O)
		{
			winnerO = true;
		}
		
		if (markArray[6] == Mark.O && markArray[4] == Mark.O && markArray[2] == Mark.O)
		{
			winnerO = true;
		}
		
		return winnerO;
	}

	private boolean winnerX() 
	{
		boolean winnerX = false;
		Mark[] markArray = getMarkArray(movesArray);
		

		if (markArray[0] == Mark.X && markArray[1] == Mark.X && markArray[2] == Mark.X)
		{
			winnerX = true;
		}
		
		if (markArray[3] == Mark.X && markArray[4] == Mark.X && markArray[5] == Mark.X)
		{
			winnerX = true;
		}
		
		if (markArray[6] == Mark.X && markArray[7] == Mark.X && markArray[8] == Mark.X)
		{
			winnerX = true;
		}
		
		if (markArray[0] == Mark.X && markArray[3] == Mark.X && markArray[6] == Mark.X)
		{
			winnerX = true;
		}
		
		if (markArray[1] == Mark.X && markArray[4] == Mark.X && markArray[7] == Mark.X)
		{
			winnerX = true;
		}
		
		if (markArray[2] == Mark.X && markArray[5] == Mark.X && markArray[6] == Mark.X)
		{
			winnerX = true;
		}
		
		if (markArray[0] == Mark.X && markArray[4] == Mark.X && markArray[8] == Mark.X)
		{
			winnerX = true;
		}
		
		if (markArray[6] == Mark.X && markArray[4] == Mark.X && markArray[2] == Mark.X)
		{
			winnerX = true;
		}
		
		return winnerX;
	}

	// Helper Method
	// returns the index of a spot on a tic-tac-toe board
	// part of pre: 0<= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	// part of post: 0 <= rv <= ((ROW_COUNT * COLUMN_COUNT) - 1)
	private static int getBoardIndex(int row, int column)
	{
		assert(0 <= row && row < ROW_COUNT) : "ERROR: row must be >= 0 and < " + ROW_COUNT;
		assert(0 <= column && column < COLUMN_COUNT) : "ERROR: column must be >= 0 and < " + COLUMN_COUNT;
		
		return ((3*row) + column);
	}
	
	// Helper Method 
	// returns the next available index of an integer array
	// rv == number of marks on tic-tac-toe board
	private static Integer getNextEmptyIndex(int[] intArray)
	{
		Integer nextEmptyIndex = null;
		boolean foundEmptyIndex = false;
		int i = 0;
		
		// search array for empty index until found or
		// entire array has been searched
		while (!foundEmptyIndex && i < intArray.length)
		{
			if (intArray[i] == NO_MOVE)
			{
				foundEmptyIndex = true;
				nextEmptyIndex = i;
			}
			else {i++;}
		}
		
		return nextEmptyIndex;
	}
	
	public String toString()
	{
		// Create an array of Marks with 9 indexes
		Mark[] markArray = getMarkArray(movesArray);
		
		StringBuffer aString = new StringBuffer();
		
		for(int i = 0; i < movesArray.length; i++) 
		{
			if (i%3 == 0) // end of row
			{
				aString.append('\n');
				aString.append("---------------------\n");
			}
			if (markArray[i] == Mark.X || markArray[i] == Mark.O)
			{
				aString.append(" |  " + markArray[i] + " ");
			}
			else {aString.append(" |    ");}
		}	
		aString.append("\n---------------------\n");
		aString.append('\n');
		aString.append('\n');
		
		// print movesArray values for debugging
		for (int i = 0; i < movesArray.length; i++)
		{
			aString.append(movesArray[i] + " ");
		}
		return aString.toString();
	}
	
	// Helper Method
	// Takes a movesArray and returns an array of Marks
	// corresponding to their places on a tictactoe board
	private Mark[] getMarkArray(int[] movesArray)
	{
		// Create an array of Marks with 9 indexes
		Mark[] markArray = new Mark[9];
		
		// put each X and O in the index specified by
		// movesArray[i]
		for (int i = 0; i < movesArray.length; i++)
		{
			if (i%2 == 0 && movesArray[i] != -1)
			{
				markArray[movesArray[i]] = Mark.X;
			}
			else if (i%2 != 0 && movesArray[i] != -1)
			{
				markArray[movesArray[i]] = Mark.O;
			}
		}
		return markArray;
	}
	
	
}


