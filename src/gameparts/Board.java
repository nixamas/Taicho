package gameparts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.coobird.thumbnailator.Thumbnails;
import utilities.BoardDimensions;
import utilities.ColorPanel;
import utilities.Utils;
import basecomponents.BoardComponent;
import basecomponents.Coordinate;
import basecomponents.MovableObject;
import characters.EmptyObject;
import characters.ThreeUnit;
import characters.TwoUnit;
import enums.ComponentImages;
import enums.Location;
import enums.Player;
import enums.Ranks;
import exceptions.BoardComponentNotFoundException;

/**
 * UI for game. Deals with how the game program interfaces with the user
 *
 */
public class Board extends JPanel implements ActionListener, MouseListener, ImageObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TaichoGameData board; // The data for the checkers board is kept here.
						// This board is also responsible for generating
						// lists of legal moves.

	boolean gameInProgress, unstackObjects, showIcons; // Is a game currently in progress?

	ArrayList<BoardComponent> validMoves; // An array containing the legal moves for the
								// current player.
	Player player1, player2, currentPlayer;

	BoardDimensions boardProperties;
	
	BufferedImage lvl1Img, lvl2Img, lvl3Img, TaichoImg;
	
	BoardComponent selectedBC;
	
	JButton unstackBtn;
	
	/**player
	 * Constructor. Create the buttons and label. Listens for mouse clicks and
	 * for clicks on the buttons. Create the board and start the first game.
	 */
	public Board(JButton unstckBtn) {
		System.out.println("Board constructor");
		try {	//get icons stored in memory
			lvl1Img = Thumbnails.of(new File(ComponentImages.LEVEL_ONE_IMAGE.getImageLocation()))
				.size(160, 160)
				.asBufferedImage();
			lvl2Img = Thumbnails.of(new File(ComponentImages.LEVEL_TWO_IMAGE.getImageLocation()))
		        .size(160, 160)
		        .asBufferedImage();
			lvl3Img = Thumbnails.of(new File(ComponentImages.LEVEL_THREE_IMAGE.getImageLocation()))
		        .size(160, 160)
		        .asBufferedImage();
			TaichoImg = Thumbnails.of(new File(ComponentImages.TAICHO_IMAGE.getImageLocation()))
		        .size(160, 160)
		        .asBufferedImage();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		showIcons = false;
		validMoves = new ArrayList<BoardComponent>();
		boardProperties = new BoardDimensions(45);          ///     <<<<<<<<<<<<<<<<<<<< CHANGE SCREEN SIZE
		setBackground(Color.BLACK);
		addMouseListener(this);
		currentPlayer = Player.NONE;
		unstackBtn = unstckBtn;
		unstackBtn.addActionListener(this);
		player1 = Player.PLAYER_ONE;
		player2 = Player.PLAYER_TWO;
		board = new TaichoGameData(player1, player2);
		selectedBC = new BoardComponent(Location.OUT_OF_BOUNDS, new Coordinate(-1, -1, -1));
		
//		simulateMouseClick();
		
		setButtonState();
		unstackObjects = false;
	}

	/**
	 * Respond to user's click on one of the two buttons.
	 */
	public void actionPerformed(ActionEvent evt) {
		System.out.println("****Button Clicked****");
		 Object src = evt.getSource();
		 if (src == this.unstackBtn){
			 System.out.println("****unstackBtn clicked****");
			 showValidUnstack();
		 }
	}

	/**
	 * Draw checkerboard pattern in gray and lightGray. Draw the checkers. If a
	 * game is in progress, hilite the legal moves.
	 */
	public void paintComponent(Graphics g) {
		int compSize = boardProperties.getComponentSize();
		int charSize = boardProperties.getCharacterDimension();
		int bL = boardProperties.getBoardLength();
		int bW = boardProperties.getBoardWidth();
		int charOffset = boardProperties.getCharacterOffset();
		BufferedImage tn1 = null, tn2 = null, tn3 = null, tnT = null; //lvl 1, 2, 3, and Taicho
		
		/* Draw a two-pixel black border around the edges of the canvas. Used as player turn indicator */
		System.out.println("Paint components");
		g.setColor(currentPlayer.getColor()); 		
		/* Draw player indicator box around  */
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
						if(showIcons){
							g.setColor(bc.getCharacter().getColor());
							g.fillRect(2 + charOffset + col * compSize, 2 + charOffset + row * compSize, charSize, charSize);

							Ranks r = bc.getCharacter().getRank();
							BufferedImage icon = null;
							switch(r){
								case LEVEL_ONE:
										icon = lvl1Img;
									break;
								case LEVEL_TWO:
										icon = lvl2Img;
									break;
								case LEVEL_THREE:
										icon = lvl3Img;
									break;
								case TAICHO:
										icon = TaichoImg;
									break;
								case NONE:
								default:
									break;
							}
							
							g.drawImage(icon, 2 + charOffset + col * compSize, 2 + charOffset + row * compSize, charSize - 15, charSize - 15, null);
						}else{
							g.setColor(bc.getCharacter().getColor());
							g.fillRect(2 + charOffset + col * compSize, 2 + charOffset + row * compSize, charSize, charSize);
							if(bc.getCharacter().getRank() == Ranks.TAICHO){
								g.setColor(Utils.blendColor(bc.getCharacter().getColor(), Color.BLACK, 0.3));
							}else{
								g.setColor(Utils.blendColor(bc.getCharacter().getColor(), Color.WHITE, 0.7));
							}
							g.fillRect(2 + charOffset + col * compSize, 2 + charOffset + row * compSize, charSize - charSize/2, charSize - charSize/2);
						}
						
					}
				}else{
					g.setColor(bc.getColor());
					g.fillRect(2 + col * compSize, 2 + row * compSize, compSize, compSize);
				}
			}
		}
	} // end paintComponent()
	
//	private void simulateMouseClick(){
//		/**
//		 * for some reason if you click on the gameboard first it will repaint 2 boards, one offset from the other
//		 * However if you click an empty square before that it will not repaint the board twice.
//		 * This function does that, hopefully can be taken out later
//		 */
////		MouseEvent me = new MouseEvent((Component) this, (int) 0, (long) 0, (int) 0, 10, 10, (int) 0, true);
////		mousePressed(me);
//	}

	private void setButtonState(){
		if( this.selectedBC.getLocation() != Location.OUT_OF_BOUNDS && this.selectedBC.isOccupied() ){
			Ranks r = this.selectedBC.getCharacter().getRank();
			if( r == Ranks.LEVEL_TWO || r == Ranks.LEVEL_THREE ){
				System.out.println("Selected BC Character is able to be unstacked");
				this.unstackBtn.setVisible(true);
			}else{
				System.out.println("Selected BC Character Rank is not unstackable");
				this.unstackBtn.setVisible(false);
			}
		}else{
			this.unstackBtn.setVisible(false);
			System.out.println("Selected component is not occupied or is out of bounds");
		}
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
	    	System.out.println("" + bc.toString());
	    	System.err.println("************* BOARD SQUARE DATE *************");
	    	
	    	
			if(bc.getLocation() != Location.OUT_OF_BOUNDS){	//if player clicks on the game board
				if(validMoves.isEmpty()){		//if there is no valid moves (no BC selected)
					System.out.println("valid moves is empty, first click");
					selectBoardComponent(row, col);
					currentPlayer = bc.getCharacter().getPlayer();
					selectedBC = bc;
				}else if( !validMoves.isEmpty() && validSelection(bc) ){
						// if there is a selected BC and user chose a valid BC
					System.out.println("make move to new VALID square");
					if(bc.isOccupied()){
							//stack units
						stackUnits(row, col);
					}else{
						if(unstackObjects){		//set true by clicking the 'Un-stack' button
								//unstack units
							unstackUnits(row, col);
							unstackObjects = false;
						}else{
								//move units
							makeMove(row, col);
						}
					}
					eraseValidMoves();
						//set the selectedBC to some out of bounds location, keep from being null
			    	selectedBC = new BoardComponent(Location.OUT_OF_BOUNDS, new Coordinate(-1, -1, -1));
				}else{
					//user clicked same BC twice, abort the BC selection
					System.out.println("checking if user clicked selected BC again. If so, Abort");
					try{
			    		BoardComponent selectedBc = board.getSelectedBoardComponent();
			    		if(bc.getCoordinate().equals( selectedBc.getCoordinate() )){
			    				//if there is a selected BC then and the user clicked it a second time, 
			    					//Then clear the valid moves array
			    			selectedBc.setSelected(false);
			    			eraseValidMoves();
			    		}
			    		selectedBC = new BoardComponent(Location.OUT_OF_BOUNDS, new Coordinate(-1, -1, -1));
			    	}catch(BoardComponentNotFoundException bcnfe){
			    		System.err.println(bcnfe.getMessage());
			    	}
				}
			}	
		}catch(BoardComponentNotFoundException bcnfe){
			System.err.println(bcnfe.getMessage());
		}
		
		setButtonState();
		repaint();
	}

	/**
     * This is called by mousePressed() when a player clicks on the
     * square in the specified row and col.  It has already been checked
     * that a game is, in fact, in progress.
     */
    private void selectBoardComponent(int row, int col) {
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
    		MovableObject temp = selectedBc.removeCharacter();
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
    					MovableObject selectedChar = selectedBc.removeCharacter();
    					MovableObject joiningChar = bc.removeCharacter();
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
    
    private boolean showValidUnstack(){
    	System.out.println("showValidUnstack method");
    	eraseValidMoves();
    	unstackObjects = true;
    	BoardComponent selectedBc = board.getSelectedBoardComponent();
    	validMoves = selectedBc.getCharacter().getPossibleUnstackLocations(board, selectedBc);
    	repaint();
    	return true;
    }
    
    private boolean unstackUnits(int row, int col){
    	System.out.println("UNSTACKING CHARACTER UNIT " + selectedBC.toString() + " @ " + selectedBC.getCoordinate().toString());
    	BoardComponent bc = board.pieceAt(row, col);
    	BoardComponent selectedBc = board.getSelectedBoardComponent();
    	Player p = selectedBc.getCharacter().getPlayer();
    	if(!bc.isOccupied() && bc.getLocation() != Location.OUT_OF_BOUNDS){
    		System.out.println("square IS NOT occupied");
    		Ranks r = selectedBc.getCharacter().getRank();
    		switch(r){
				case LEVEL_TWO:
					TwoUnit selectedTwoUnit = (TwoUnit) selectedBc.getCharacter();
					MovableObject mo21 = selectedTwoUnit.removeUnitFromStack();
					MovableObject mo20 = selectedTwoUnit.removeUnitFromStack();
					bc.setCharacter( mo21 );
					selectedBc.setCharacter( mo20 );
					break;
				case LEVEL_THREE:
					ThreeUnit selectedThreeUnit = (ThreeUnit) selectedBc.getCharacter();
					MovableObject mo32 = selectedThreeUnit.removeUnitFromStack();
					MovableObject mo31 = selectedThreeUnit.removeUnitFromStack();
					MovableObject mo30 = selectedThreeUnit.removeUnitFromStack();
					bc.setCharacter( mo32 );
					selectedBc.setCharacter( new TwoUnit( p, mo31, mo30 ) );
					break;
				case LEVEL_ONE:
				case TAICHO:
				case NONE:
				default:	//no need to do anything
					System.err.println("character is not able to be unstacked");
					break;
    		}
    	}
    	selectedBc.setSelected(false);
    	repaint();
    	return true;
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
	
	private void eraseValidMoves(){
		for(int i = 0; i < validMoves.size(); i++){
    		validMoves.get(i).setHighlight(false);
    		validMoves.get(i).setStackable(false);
    		validMoves.get(i).setSelected(false);
    	}
    	validMoves.clear();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void mouseReleased(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}
	
}
