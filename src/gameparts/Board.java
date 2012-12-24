package gameparts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import utilities.BoardDimensions;

import basecomponents.BoardComponent;
import basecomponents.MovableObject;
import characters.EmptyObject;
import characters.ThreeUnit;
import characters.TwoUnit;
import enums.Location;
import enums.Player;
import enums.Ranks;
import exceptions.BoardComponentNotFoundException;

/**
 * UI for game. Deals with how the game program interfaces with the user
 *
 */
public class Board extends JPanel implements ActionListener, MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TaichoGameData board; // The data for the checkers board is kept here.
						// This board is also responsible for generating
						// lists of legal moves.

	boolean gameInProgress; // Is a game currently in progress?

	int selectedRow, selectedCol; // If the current player has selected a piece
									// to
									// move, these give the row and column
									// containing that piece. If no piece is
									// yet selected, then selectedRow is -1.

	ArrayList<BoardComponent> validMoves; // An array containing the legal moves for the
								// current player.
	Player player1, player2, currentPlayer;

	BoardDimensions boardProperties;
//	Taicho game;

	/**player
	 * Constructor. Create the buttons and label. Listens for mouse clicks and
	 * for clicks on the buttons. Create the board and start the first game.
	 */
	public Board() {
		System.out.println("Board constructor");
		board = null;
		validMoves = new ArrayList<BoardComponent>();
		boardProperties = new BoardDimensions(30);          ///     <<<<<<<<<<<<<<<<<<<< CHANGE SCREEN SIZE
//		game = taicho;
		setBackground(Color.BLACK);
		addMouseListener(this);
		currentPlayer = Player.NONE;
//		taicho.setResignButton(new JButton("Resign"));
//		taicho.getResignButton().addActionListener(this);
//		taicho.setNewGameButton(new JButton("New Game"));
//		taicho.getNewGameButton().addActionListener(this);
//		taicho.setMessage(new JLabel("", JLabel.CENTER));
//		taicho.getMessage().setFont(new Font("Serif", Font.BOLD, 14));
//		taicho.getMessage().setForeground(Color.green);
		player1 = Player.PLAYER_ONE;
		player2 = Player.PLAYER_TWO;
		board = new TaichoGameData(player1, player2);
		// doNewGame();
		simulateMouseClick();
	}

	/**
	 * Respond to user's click on one of the two buttons.
	 */
	public void actionPerformed(ActionEvent evt) {
		// Object src = evt.getSource();
		// if (src == game.getNewGameButton())
		// doNewGame();
		// else if (src == game.getResignButton())
		// doResign();
	}

	// /**
	// * Start a new game
	// */
	void doNewGame() {
		// if (gameInProgress == true) {
		// // This should not be possible, but it doens't hurt to check.
		// game.getMessage().setText("Finish the current game first!");
		// return;
		// }
		// board.setUpGame(); // Set up the pieces.
		// currentPlayer = ObjectData.RED; // RED moves first.
		// legalMoves = board.getLegalMoves(ObjectData.RED); // Get RED's legal
		// moves.
		// selectedRow = -1; // RED has not yet selected a piece to move.
		// game.getMessage().setText("Red:  Make your move.");
		// gameInProgress = true;
		// game.getNewGameButton().setEnabled(false);
		// game.getResignButton().setEnabled(true);
		// game.repaint();
	}

	/**
	 * Current player resigns. Game ends. Opponent wins.
	 */
	void doResign() {
		if (gameInProgress == false) { // Should be impossible.
//			game.getMessage().setText("There is no game in progress!");
			return;
		}
		if (currentPlayer == player1)
			gameOver("RED resigns.  BLACK wins.");
		else
			gameOver("BLACK resigns.  RED wins.");
	}

	/**
	 * The game ends. The parameter, str, is displayed as a message to the user.
	 * The states of the buttons are adjusted so players can start a new game.
	 * This method is called when the game ends at any point in this class.
	 */
	void gameOver(String str) {
//		game.getMessage().setText(str);
//		game.getNewGameButton().setEnabled(true);
//		game.getResignButton().setEnabled(false);
		gameInProgress = false;
	}

	/**
	 * Draw checkerboard pattern in gray and lightGray. Draw the checkers. If a
	 * game is in progress, hilite the legal moves.
	 */
	public void paintComponent(Graphics g) {

		/* Draw a two-pixel black border around the edges of the canvas. */
		System.out.println("Paint components");
		g.setColor(currentPlayer.getColor()); 		
		int compSize = boardProperties.getComponentSize();
		int charSize = boardProperties.getCharacterDimension();
		int bL = boardProperties.getBoardLength();
		int bW = boardProperties.getBoardWidth();
		int charOffset = boardProperties.getCharacterOffset();
		
//		g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
//		g.drawRect(1, 1, getSize().width - 3, getSize().height - 3);
		g.drawRect(0, 0, bL - 1, bW - 1);
		g.drawRect(1, 1, bL - 3, bW - 3);

		/* Draw the squares of the checkerboard and the checkers. */
		for (int col = 0; col < 15; col++) {
			for (int row = 0; row < 9; row++) {
				BoardComponent bc = board.pieceAt(row, col);
				if (bc.getLocation() != Location.OUT_OF_BOUNDS) {
					if( bc.isHighlight() ){
						g.setColor(bc.getColor());
					}else if (row % 2 == col % 2) {
						g.setColor(bc.getColor());
					} else {
						g.setColor(bc.getColor());
					}
					
					g.fillRect(2 + col * compSize, 2 + row * compSize, compSize, compSize);

					if(bc.isOccupied()){
						g.setColor(bc.getCharacter().getColor());
						g.fillOval(2 + charOffset + col * compSize, 2 + charOffset + row * compSize, charSize, charSize);
					}
				}else{
					g.setColor(bc.getColor());
					g.fillRect(2 + col * compSize, 2 + row * compSize, compSize, compSize);
				}
			}
		}
	} // end paintComponent()
	
	private void simulateMouseClick(){
		/**
		 * for some reason if you click on the gameboard first it will repaint 2 boards, one offset from the other
		 * However if you click an empty square before that it will not repaint the board twice.
		 * This function does that, hopefully can be taken out later
		 */
//		MouseEvent me = new MouseEvent((Component) this, (int) 0, (long) 0, (int) 0, 10, 10, (int) 0, true);
//		mousePressed(me);
	}

	/**
	 * Respond to a user click on the board. If no game is in progress, show an
	 * error message. Otherwise, find the row and column that the user clicked
	 * and call doClickSquare() to handle it.
	 */
	public void mousePressed(MouseEvent evt) {
		
		int col = (evt.getX() - 2) / boardProperties.getComponentSize();
		int row = (evt.getY() - 2) / boardProperties.getComponentSize();
		System.out.println("mousePressed @ x_pos="+col+":y_pos="+row);
		
		try{
			BoardComponent bc = board.pieceAt(row, col);
			System.err.println("************* BOARD SQUARE DATE *************");
	    	System.err.println("" + bc.toString());
	    	System.err.println("************* BOARD SQUARE DATE *************");
	    	
	    	
			if(bc.getLocation() != Location.OUT_OF_BOUNDS){
				if(validMoves.isEmpty()){
					System.out.println("valid moves is empty, first click");
					doClickSquare(row, col);
					currentPlayer = bc.getCharacter().getPlayer();
				}else if( !validMoves.isEmpty() && validSelection(bc) ){
					System.out.println("make move to new VALID square");
					if(bc.isOccupied()){
						stackUnits(row, col);
					}else{
						makeMove(row, col);
					}
			    	for(int i = 0; i < validMoves.size(); i++){
			    		validMoves.get(i).setHighlight(false);
			    		validMoves.get(i).setStackable(false);
			    	}
			    	validMoves.clear();
				}else{
					System.out.println("checking if user clicked selected BC again. If so, Abort");
					try{
			    		BoardComponent selectedBc = board.getSelectedBoardComponent();
			    		if(bc.getCoordinate().equals( selectedBc.getCoordinate() )){
			    				//if there is a selected BC then and the user clicked it a second time, 
			    					//Then clear the valid moves array
			    			for(int i = 0; i < validMoves.size(); i++){
					    		validMoves.get(i).setHighlight(false);
					    		validMoves.get(i).setStackable(false);
					    	}
					    	validMoves.clear();
			    		}
			    	}catch(BoardComponentNotFoundException bcnfe){
			    		System.err.println(bcnfe.getMessage());
			    	}
				}
			}	
		}catch(BoardComponentNotFoundException bcnfe){
			System.err.println(bcnfe.getMessage());
		}
		
		repaint();
	}

	public void mouseReleased(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	/**
     * This is called by mousePressed() when a player clicks on the
     * square in the specified row and col.  It has already been checked
     * that a game is, in fact, in progress.
     */
    private void doClickSquare(int row, int col) {
    	System.out.println("doClickSquare");
    	BoardComponent bc = board.pieceAt(row, col);
    	if(bc.isOccupied()){
	    	if(validMoves.isEmpty()){
	    		bc.setSelected(true);
	    		System.out.println("you clicked BoardComponent with ID of -- " + bc.getId());
	    		validMoves = bc.getCharacter().getPossibleMoves(board, bc);
	    	}
    	}
       	
       board.getCoordinateOfId(bc.getId());
       board.getBoardComponentAtId(bc.getId());
       repaint();
    }  // end doClickSquare()
       
    /**
     * Move units from one BC to another. params are coordinates of new location. 
     * @param row
     * @param col
     */
    private void makeMove(int row, int col){
    	System.out.println("makeMove");
    	BoardComponent bc = board.pieceAt(row, col);
    	BoardComponent selectedBc = board.getSelectedBoardComponent();
    	if(!bc.isOccupied() && bc.getLocation() != Location.OUT_OF_BOUNDS){
    		System.out.println("square IS NOT occupied");
    		MovableObject temp = selectedBc.removeCharachter();
    		bc.setCharacter( temp );
    	}
    	selectedBc.setSelected(false);
    	repaint();
    }
    
    private boolean stackUnits(int row, int col){
    	System.out.println("stack units");
    	BoardComponent bc = board.pieceAt(row, col);
    	BoardComponent selectedBc = board.getSelectedBoardComponent();
    	Player p = selectedBc.getCharacter().getPlayer();
    	if( bc.isOccupied() && selectedBc.isOccupied() ){ //make sure both are occupied
    		if( bc.getCharacter().getPlayer() == p ){ //and belong to the same player
    			if( !(bc.getCharacter().getRank() == Ranks.LEVEL_THREE || selectedBc.getCharacter().getRank() == Ranks.LEVEL_THREE) ){ //&& //not level three 
    					if( !(bc.getCharacter().getRank() == Ranks.LEVEL_TWO && selectedBc.getCharacter().getRank() == Ranks.LEVEL_TWO) ){// ){ //or both are not level two
    				if( bc.getCharacter().getRank() != Ranks.TAICHO && selectedBc.getCharacter().getRank() != Ranks.TAICHO ){			//neither are a taicho
    					MovableObject selectedChar = selectedBc.removeCharachter();
    					MovableObject joiningChar = bc.removeCharachter();
    					MovableObject newChar = new EmptyObject();
    					if( selectedChar.getRank() == Ranks.LEVEL_ONE && joiningChar.getRank() == Ranks.LEVEL_ONE ){
    						newChar = new TwoUnit(p, selectedChar, joiningChar);
    					}else if( (selectedChar.getRank() == Ranks.LEVEL_ONE && joiningChar.getRank() == Ranks.LEVEL_TWO) ||
    							(selectedChar.getRank() == Ranks.LEVEL_TWO && joiningChar.getRank() == Ranks.LEVEL_ONE) ){
    						newChar = new ThreeUnit(p, selectedChar, joiningChar);
    					}
    					bc.setCharacter(newChar);
    					selectedBc.setSelected(false);
    					repaint();
    					return true;
    				}
    			}
    			}
    		}
    	}else{
    		return false;
    	}
    	repaint();
    	return false;    	
    }
    
    /**
     * if the param bc coordinate member is equal to a coordinate in the valid moves array return true, else false
     * @param bc
     * @return
     */
    private boolean validSelection(BoardComponent bc){
//    	System.out.println("find if " + bc.getCoordinate().toString() + "  is valid");
    	for(BoardComponent vbc : validMoves){
//    		System.out.println("find if " + vbc.getCoordinate().toString() + " is the selected move position");
    		if(vbc.getCoordinate().equals(bc.getCoordinate())){
//    			System.out.println("found a match at " + bc.getCoordinate().toString());
    			return true;
    		}
    	}
		return false;
    	
    }
    
	public BoardDimensions getBoardProperties() {
		return boardProperties;
	}
}
