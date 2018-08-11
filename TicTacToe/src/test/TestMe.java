package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import tictactoe.Mark;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToeBoardImpl_Solinger;

public class TestMe {

	@Test
	public void test() {
		
		/*****TESTS FOR class TicTacToeBoardImpl_Solinger*****/
		System.out.println("TESTS FOR class TicTacToeBoardImpl_Solinger");
		System.out.println("--------------------------------------------- \n");
		
		TicTacToeBoard gameBoard = new TicTacToeBoardImpl_Solinger();	
		
		while(!gameBoard.isGameOver())
		{
			System.out.println(gameBoard.toString() + '\n');
			System.out.println("Player " + gameBoard.getTurn() + " turn.\n");	
			
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.println("Enter a row: ");
			int row = reader.nextInt(); // Scans the next token of the input as an int.
			
			System.out.println("Enter a column: ");
			int column = reader.nextInt(); // Scans the next token of the input as an int.
			
			
			// player sets mark
			gameBoard.setMark(row, column);
			
			System.out.println("Player entered " + gameBoard.getMark(row, column) + " at row: " + row + ", column: " + column);
			System.out.println("gameBoard.getMark(2,0): " + gameBoard.getMark(2, 0));
		}
		
		System.out.println(gameBoard.toString() + '\n');
		
		System.out.println("Winner: " + gameBoard.getWinner());
				
	}

}
