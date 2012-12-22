package gameparts;
import java.awt.Color;

import basecomponents.BoardComponent;
import basecomponents.Coordinate;
import characters.Captain;
import characters.ThreeUnit;
import characters.TwoUnit;
import enums.Location;
import enums.Player;


public class ObjectData {
	BoardComponent[][] board;
	Player player1, player2;

	/**
	 * Constructor. Create the board and set it up for a new game.
	 */
	public ObjectData(Player p1, Player p2) {
		System.out.println("ObjectData constructor");
		player1 = p1;
		player2 = p2;
		board = new BoardComponent[9][15]; //[row][col]
		setUpGame();
	}

	/**
	 * Set up the board with checkers in position for the beginning of a game.
	 * Note that checkers can only be found in squares that satisfy row % 2 ==
	 * col % 2. At the start of the game, all such squares in the first three
	 * rows contain black squares and all such squares in the last three rows
	 * contain red squares.
	 */
	public void setUpGame() {
		System.out.println("Set Up Game");
		int index = 0;
		for (int col = 0; col < 15; col++) {
			for (int row = 0; row < 9; row++) {
				if (row == 4 && col == 1) {
					// position is Player One Taicho
					board[row][col] = new BoardComponent(new Captain(player1), Location.PLAYER_ONE_CASTLE, new Coordinate(col, row, index));
				} else if (row == 4 && col == 13) {
					// position is Player Two Taicho
					board[row][col] = new BoardComponent(new Captain(player2), Location.PLAYER_TWO_CASTLE, new Coordinate(col, row, index));
				} else if (((col == 10) && (row % 2 == 0))
						|| ((col == 9 || col == 11) && (row % 2 == 1))) {
					// position is Player Two Samurai
					 board[row][col] = new BoardComponent(new ThreeUnit(player2), Location.GAME_BOARD, new Coordinate(col, row, index));
				} else if (((col == 4) && (row % 2 == 0))
						|| ((col == 3 || col == 5) && (row % 2 == 1))) {
					// position is Player One Samurai
					board[row][col] = new BoardComponent(new TwoUnit(player1), Location.GAME_BOARD, new Coordinate(col, row, index));
				} else if (((row <= 2) && (col <= 2 || col >= 12))
						|| ((row >= 6) && (col <= 2 || col >= 12))) {
					// position is not on board (invisible section
					board[row][col] = new BoardComponent(Location.OUT_OF_BOUNDS, new Coordinate(col, row, index));
				} else {
					// position is empty
					board[row][col] = new BoardComponent(Location.GAME_BOARD, new Coordinate(col, row, index));
				}
				if(board[row][col].getLocation() != Location.OUT_OF_BOUNDS){
					if (row % 2 == col % 2) {
						board[row][col].setColor(Color.LIGHT_GRAY);
					} else {
						board[row][col].setColor(Color.GRAY);
					}
				}else{
					board[row][col].setColor(Color.WHITE);
				}
				index++;
			}
		}
		
	} // end setUpGame()

	/**
	 * Return the contents of the square in the specified row and column.
	 */
	public BoardComponent pieceAt(int row, int col) {
		return board[row][col];
	}
	
	public Coordinate getCoordinateOfId(int id){
		for (int col = 0; col < 15; col++) {
			for (int row = 0; row < 9; row++) {
				if(board[row][col].getCoordinate().getId() == id){
//					System.out.println("found coordinate of id - " + id + " at " + board[row][col].getCoordinate());
					return board[row][col].getCoordinate();
				}
			}
		}
		return new Coordinate();//new BoardComponent(null, null);
	}
	
	public BoardComponent getBoardComponentAtId(int id){
		for (int col = 0; col < 15; col++) {
			for (int row = 0; row < 9; row++) {
				if(board[row][col].getCoordinate().getId() == id){
//					System.out.println("found BoardComponent of id - " + id + " at " + board[row][col].getCoordinate());
					return board[row][col];
				}
			}
		}
		//shouldnt happen, but... :)		
		return null;
	}
	
	public BoardComponent getSelectedBoardComponent(){
		for (int col = 0; col < 15; col++) {
			for (int row = 0; row < 9; row++) {
				if( board[row][col].isSelected()){
					return board[row][col];
				}
			}
		}
		//return empty object if not found
		return new BoardComponent(Location.OUT_OF_BOUNDS, new Coordinate());
	}
}
