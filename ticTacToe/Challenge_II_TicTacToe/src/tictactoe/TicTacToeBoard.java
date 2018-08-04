package tictactoe;

public interface TicTacToeBoard 
{
	public final static int ROW_COUNT = 3;
	public final static int COLUMN_COUNT = 3;
	
	// part of pre: 0 <= ROW_COUNT && 0 <= column < COLUMN_COUNT
	// part of post: rv == null <==> the (row, column) spot on the 
	// 			     board is empty
	public Mark getMark(int row, int column);
	
	// part of pre: 0<= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	// part of pre: getMark(row, column) == null
	// part of pre: !isGameOver()
	// post: Left to student
	public void setMark(int row, int column);
	
	// part of post: rv == null <==> it is neither player's turn (i.e. 
	// 				game is over)
	// part of post: "number of Marks on board is even"-> rv == Mark.X
	// part of post: "number of Marks on board is odd"-> rv == Mark.O
	public Mark getTurn();
	
	// part of post: Left to student (see Tic-tac-toe rules in order 
	//				 to fill this out)
	public boolean isGameOver();
	
	// part of pre: isGameOver()
	// part of post: rv == null <==> neither player won (i.e the game 
	//				 ended in a tie)
	public Mark getWinner();
}