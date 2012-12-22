package gameparts;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import basecomponents.BoardComponent;
import basecomponents.MovableObject;
import enums.Location;
import enums.Player;

public class Board extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ObjectData board; // The data for the checkers board is kept here.
						// This board is also responsible for generating
						// lists of legal moves.

	boolean gameInProgress; // Is a game currently in progress?

	/* The next three variables are valid only when the game is in progress. */

	Player currentPlayer; // Whose turn is it now? The possible values
							// are CheckersData.RED and CheckersData.BLACK.

	int selectedRow, selectedCol; // If the current player has selected a piece
									// to
									// move, these give the row and column
									// containing that piece. If no piece is
									// yet selected, then selectedRow is -1.

	ArrayList<BoardComponent> validMoves; // An array containing the legal moves for the
								// current player.
	Player p1, p2;

	BoardDimensions boardProperties;
//	Taicho game;

	/**
	 * Constructor. Create the buttons and label. Listens for mouse clicks and
	 * for clicks on the buttons. Create the board and start the first game.
	 */
	public Board() {
		System.out.println("Board constructor");
		board = null;
		validMoves = new ArrayList<BoardComponent>();
		boardProperties = new BoardDimensions(45);          ///     <<<<<<<<<<<<<<<<<<<< CHANGE SCREEN SIZE
		int i = boardProperties.getComponentSize();
		i = boardProperties.getBoardLength();
		i = boardProperties.getBoardWidth();
//		game = taicho;
		setBackground(Color.BLACK);
		addMouseListener(this);
//		taicho.setResignButton(new JButton("Resign"));
//		taicho.getResignButton().addActionListener(this);
//		taicho.setNewGameButton(new JButton("New Game"));
//		taicho.getNewGameButton().addActionListener(this);
//		taicho.setMessage(new JLabel("", JLabel.CENTER));
//		taicho.getMessage().setFont(new Font("Serif", Font.BOLD, 14));
//		taicho.getMessage().setForeground(Color.green);
		p1 = Player.PLAYER_ONE;
		p2 = Player.PLAYER_TWO;
		board = new ObjectData(p1, p2);
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
		if (currentPlayer == p1)
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
		g.setColor(Color.BLACK);
		int compSize = boardProperties.getComponentSize();
		int charSize = boardProperties.getCharacterDimension();
		int brdLngth = boardProperties.getBoardLength();
		int brdWdth = boardProperties.getBoardWidth();
		
		g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
		g.drawRect(1, 1, getSize().width - 3, getSize().height - 3);
//		g.drawRect(0, 0, brdLngth - 1, brdWdth - 1);
//		g.drawRect(1, 1, brdLngth - 3, brdWdth - 3);

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
//					g.fillRect(2 + col * 20, 2 + row * 20, 20, 20);
					
					g.fillRect(2 + col * compSize, 2 + row * compSize, compSize, compSize);

					if(bc.isOccupied()){
						g.setColor(bc.getCharacter().getColor());
						g.fillOval(4 + col * compSize, 4 + row * compSize, charSize, charSize);
//						if (bc.getCharacter().getPlayer() == Player.PLAYER_ONE) {
//							g.setColor(bc.getCharacter().getColor());
//							g.fillOval(4 + col * compSize, 4 + row * compSize, charSize, charSize);
//						} else if (bc.getCharacter().getPlayer() == Player.PLAYER_TWO) {
//							g.setColor(bc.getCharacter().getColor());
//							g.fillOval(4 + col * compSize, 4 + row * compSize, charSize, charSize);
//						}
					}
				}else{
					g.setColor(bc.getColor());
					g.fillRect(2 + col * compSize, 2 + row * compSize, compSize, compSize);
				}
			}
		}

		/*
		 * If a game is in progress, hilite the legal moves. Note that
		 * legalMoves is never null while a game is in progress.
		 */

//		if (gameInProgress) {
//			/*
//			 * First, draw a 2-pixel cyan border around the pieces that can be
//			 * moved.
//			 */
//			g.setColor(Color.cyan);
//			for (int i = 0; i < legalMoves.length; i++) {
//				g.drawRect(2 + legalMoves[i].fromCol * 20,
//						2 + legalMoves[i].fromRow * 20, 19, 19);
//				g.drawRect(3 + legalMoves[i].fromCol * 20,
//						3 + legalMoves[i].fromRow * 20, 17, 17);
//			}
//			/*
//			 * If a piece is selected for moving (i.e. if selectedRow >= 0),
//			 * then draw a 2-pixel white border around that piece and draw green
//			 * borders around each square that that piece can be moved to.
//			 */
//			if (selectedRow >= 0) {
//				g.setColor(Color.white);
//				g.drawRect(2 + selectedCol * 20, 2 + selectedRow * 20, 19, 19);
//				g.drawRect(3 + selectedCol * 20, 3 + selectedRow * 20, 17, 17);
//				g.setColor(Color.green);
//				for (int i = 0; i < legalMoves.length; i++) {
//					if (legalMoves[i].fromCol == selectedCol
//							&& legalMoves[i].fromRow == selectedRow) {
//						g.drawRect(2 + legalMoves[i].toCol * 20,
//								2 + legalMoves[i].toRow * 20, 19, 19);
//						g.drawRect(3 + legalMoves[i].toCol * 20,
//								3 + legalMoves[i].toRow * 20, 17, 17);
//					}
//				}
//			}
//		}

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
		
		BoardComponent bc = board.pieceAt(row, col);
		System.err.println("************* BOARD SQUARE DATE *************");
    	System.err.println("" + bc.toString());
    	System.err.println("************* BOARD SQUARE DATE *************");
		if(bc.getLocation() != Location.OUT_OF_BOUNDS){
			if(validMoves.isEmpty()){
				System.out.println("valid moves is empty, first click");
				doClickSquare(row, col);
			}else if( validSelection(bc) ){
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
			}
		}
		
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
	    		for(int i = 0; i < validMoves.size(); i++){
	    			System.out.println("valid moves found at positions -- " + validMoves.get(i).getCoordinate().toString());
	    		}
	    	}
//	    	}else if( !validMoves.isEmpty() && validSelection(bc) ){
//	        	for(int i = 0; i < validMoves.size(); i++){
//	        		validMoves.get(i).setHighlight(false);
//	        		validMoves.get(i).setStackable(false);
//	        	}
//	    	}
    	}
       	
       board.getCoordinateOfId(bc.getId());
       board.getBoardComponentAtId(bc.getId());
       repaint();
       /* If the player clicked on one of the pieces that the player
        can move, mark this row and col as selected and return.  (This
        might change a previous selection.)  Reset the message, in
        case it was previously displaying an error message. */
       
//       for (int i = 0; i < legalMoves.length; i++)
//          if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
//             selectedRow = row;
//             selectedCol = col;
//             repaint();
//             return;
//          }
       
       
       /* If the user clicked on a square where the selected piece can be
        legally moved, then make the move and return. */
       
//       for (int i = 0; i < legalMoves.length; i++)
//          if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
//                && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
////             doMakeMove(legalMoves[i]);
//             return;
//          }
       
       /* If we get to this point, there is a piece selected, and the square where
        the user just clicked is not one where that piece can be legally moved.
        Show an error message. */
       
       
    }  // end doClickSquare()
    
    private void makeMove(int row, int col){
//    	for(int i = 0; i < validMoves.size(); i++){
//    		validMoves.get(i).setHighlight(false);
//    		validMoves.get(i).setStackable(false);
//    	}
    	System.out.println("doClickSquare");
    	BoardComponent bc = board.pieceAt(row, col);
    	BoardComponent selectedBc = board.getSelectedBoardComponent();
    	if(!bc.isOccupied() && bc.getLocation() != Location.OUT_OF_BOUNDS){
    		System.out.println("square IS NOT occupied");
    		MovableObject temp = selectedBc.removeCharachter();
    		bc.setCharacter( temp );
    	}else if( bc.isOccupied() ){
    		System.out.println("square IS occupied");
    		if(bc.getCharacter().getPlayer() == selectedBc.getCharacter().getPlayer()){
    			//characters are on the same team
    		}else if(bc.getCharacter().getPlayer() != selectedBc.getCharacter().getPlayer()){
    			//characters are on opposite teams
    		}
    	}
    	
    	selectedBc.setSelected(false);
    	repaint();
    }
    
    private void stackUnits(int row, int col){
    	System.out.println("stack units");
    }
    
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
