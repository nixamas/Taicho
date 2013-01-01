package basecomponents;

import interfaces.MoveManager;

import java.awt.Color;
import java.util.ArrayList;

import enums.ComponentImages;
import enums.LevelOneLegalMoves;
import enums.LevelThreeLegalMoves;
import enums.LevelTwoLegalMoves;
import enums.Location;
import enums.Player;
import enums.Ranks;
import exceptions.BoardComponentNotFoundException;
import gameparts.TaichoGameData;

/**
 * All Characters will extend this object. 
 * Extended by OneUnit.java, TwoUnit.java, ThreeUnit.java, Taicho.java, EmptyObject.java
 * The getPossibleMoves method is used for all characters
 * @author Ryan
 *
 */
public abstract class MovableObject {
	
	protected int combatValue;
	protected Player player;
	protected Ranks rank;
	protected final ComponentImages imageLocation;
	
	/**
	 * Constructor for MovableObject sets the player, rank, and componentImages members
	 * the combat values is set based on what ever the rank is 
	 * @param p
	 * @param r
	 * @param ci
	 */
	public MovableObject(Player p, Ranks r, ComponentImages ci) {
		player = p;
		rank = r;
		this.imageLocation = ci;
		switch(rank){
			case NONE:
				this.combatValue = -1;
				break;
			case LEVEL_ONE:
				this.combatValue = 1;
				break;
			case LEVEL_TWO:
				this.combatValue = 2;
				break;
			case LEVEL_THREE:
				this.combatValue = 3;
				break;
			case TAICHO:
				this.combatValue = 1;
				break;
			default:
				this.combatValue = -1;
				break;
		}
	}

	public int getCombatValue(){
		return this.combatValue;
	}
	public Color getColor() {
		return player.getColor();
	}

	public void setColor(Color color) {
		this.player.setColor(color);
	}
	public void setPlayer(Player p) {
		player = p;
	}
	public Player getPlayer() {
		return player;
	}
	
	public Ranks getRank() {
		return rank;
	}

	/**
	 * Sets the ranks and combatValue based on rank
	 * @param rank
	 */
	public void setRank(Ranks rank) {
		this.rank = rank;
		switch(rank){
			case NONE:
				this.combatValue = -1;
				break;
			case LEVEL_ONE:
				this.combatValue = 1;
				break;
			case LEVEL_TWO:
				this.combatValue = 2;
				break;
			case LEVEL_THREE:
				this.combatValue = 3;
				break;
			case TAICHO:
				this.combatValue = 1;
				break;
			default:
				this.combatValue = -1;
				break;
		}
		this.rank = rank;
	}

	public ComponentImages getImageLocation() {
		return imageLocation;
	}
	
	public ArrayList<BoardComponent> getPossibleUnstackLocations(TaichoGameData board, BoardComponent bc){
		ArrayList<BoardComponent> legalMoves = new ArrayList<BoardComponent>();
		ArrayList<MoveManager> mm = new ArrayList<MoveManager>();
		LevelOneLegalMoves[] l1moves = LevelOneLegalMoves.values();
		for(int i = 0; i < LevelOneLegalMoves.values().length; i++){
			mm.add(l1moves[i]);
		}
		for(int i = 0; i < mm.size(); i++){
			int changeVal = mm.get(i).getMove(i);
			try{
				BoardComponent potentialPosition = board.getBoardComponentAtId(bc.getId() + changeVal);
				if( !potentialPosition.isOccupied() && potentialPosition.getLocation() != Location.OUT_OF_BOUNDS ){
					potentialPosition.setHighlight(true);
					legalMoves.add(potentialPosition);
				}
			}catch(BoardComponentNotFoundException bcnfe){
				System.err.println(bcnfe.getMessage());
			}
		}
		return legalMoves;
	}
	
	/**
	 * getPossibleMoves is called for all character objects that extend MovableObject.
	 * 
	 * based on the rank of 'this' object it will create an ArrayList<ArrayList<MoveManager>>
	 * 		that will contain all possible moves for that ranked object.
	 * 
	 * This double array is used to calculate whether objects are blocked by other objects. 
	 * The first set of arrays is the different paths that an object can take. Each array 
	 * of each path is made up of moves in order along the path radiating out from the object. 
	 * This method then looks through the double array and if a path is found to be blocked, a 
	 * boolean flag is set and the rest of the path is ignored. 
	 * @param board
	 * @param bc
	 * @return
	 */
	public ArrayList<BoardComponent> getPossibleMoves(TaichoGameData board, BoardComponent bc){
		ArrayList<BoardComponent> legalMoves = new ArrayList<BoardComponent>();
		ArrayList<ArrayList<MoveManager>> mm = new ArrayList<ArrayList<MoveManager>>();
		int bufferZone = 0;
		boolean isTaicho = false;
		switch(rank){
			case NONE:
				break;
			case LEVEL_ONE:
				bufferZone = LevelOneLegalMoves.getBufferValue();
				mm = LevelOneLegalMoves.getBlockablePathsOfMoves();
				break;
			case LEVEL_TWO:
				bufferZone = LevelTwoLegalMoves.getBufferValue();
				mm = LevelTwoLegalMoves.getBlockablePathsOfMoves();
				break;
			case LEVEL_THREE:
				bufferZone = LevelThreeLegalMoves.getBufferValue();
				mm = LevelThreeLegalMoves.getBlockablePathsOfMoves();
				break;
			case TAICHO:
				isTaicho = true;
				break;
			default:
				break;
		}
		
		if( !isTaicho ){
			System.out.println("you clicked a samurai");
			legalMoves = getSamuraiMoves(mm, board, bc, bufferZone);
		}else if( isTaicho ){
			System.out.println("you clicked a taicho");
			legalMoves = getTaichoMoves(board, bc);
		}
		
		return legalMoves;
	}
	
	/**
	 * called if the chosen object is a Taicho ranked object
	 * returns all unoccupied BC's within the players castle area
	 * @param board
	 * @param bc
	 * @return
	 */
	private ArrayList<BoardComponent> getTaichoMoves(TaichoGameData board, BoardComponent bc){
		ArrayList<BoardComponent> legalMoves = new ArrayList<BoardComponent>();
		ArrayList<BoardComponent> castle = board.getCastleBoardComponents( bc.getCharacter().getPlayer() );
		for(BoardComponent potentialBc : castle ){
			if( !potentialBc.equals(bc) && !potentialBc.isOccupied() ){
				potentialBc.setHighlight(true);
				legalMoves.add(potentialBc);
			}
		}
		return legalMoves;
	}
	
	/**
	 * called if the chosen object is a lvl1, lvl2, or lvl3 object
	 * @param mm
	 * @param board
	 * @param bc
	 * @param bufferZone
	 * @return
	 */
	private ArrayList<BoardComponent> getSamuraiMoves(ArrayList<ArrayList<MoveManager>> mm,TaichoGameData board, BoardComponent bc, int bufferZone){
		ArrayList<BoardComponent> legalMoves = new ArrayList<BoardComponent>();
		boolean blockedPath = false;
		for(ArrayList<MoveManager> path : mm){		//for each path in the list
			blockedPath = false;
			for(MoveManager move : path){			//for each move in the path
				int changeVal = move.getNumVal();	//get change value (+/-X)
				if( !blockedPath ){					//if current path is not yet blocked 
					try{
						BoardComponent potentialPosition = board.getBoardComponentAtId(bc.getId() + changeVal);
						if( board.isWithinBufferZone(bufferZone, bc, potentialPosition)){ //if potPos is within bufferZone
							if( !potentialPosition.isOccupied() && potentialPosition.getLocation() != Location.OUT_OF_BOUNDS ){
									//unoccupied BC
								potentialPosition.setHighlight(true);
								legalMoves.add(potentialPosition);
							} else if (potentialPosition.isOccupied()){
								//occupied BC
								if( this.rank != Ranks.LEVEL_THREE && this.rank != Ranks.TAICHO ){
									//must be rank lvl1 or lvl2 to stack
									if(potentialPosition.getCharacter().getPlayer() == bc.getCharacter().getPlayer() 
											&& ( potentialPosition.getCharacter().getRank() != Ranks.TAICHO && potentialPosition.getCharacter().getRank() != Ranks.LEVEL_THREE)){
										//BC is a stackable position
										potentialPosition.setStackable(true);
										legalMoves.add(potentialPosition);
									}
								}
								if(this.player != potentialPosition.getCharacter().getPlayer()){
									MovableObject potentialOpponent = potentialPosition.getCharacter();
									if(this.combatValue >= potentialOpponent.getCombatValue()){
										//BC is a attackable position
										System.out.println("Found a potential enemy of " + this.toString() + " at -- " + potentialPosition.getCoordinate().toString());
										potentialPosition.setAttackable(true);
										legalMoves.add(potentialPosition);
									}
								}
								blockedPath = true;
							}
						}	
					}catch(BoardComponentNotFoundException bcnfe){
						System.err.println(bcnfe.getMessage());
					}	
				}//if( !blockedPath)
			}//for(MoveManager move : path)
		}//for(ArrayList<MoveManager> path : mm)
		return legalMoves;
	}
	
	@Override
	public String toString() {
		return "MovableObject [combatValue=" + combatValue + ", player="
				+ player + ", rank=" + rank + "]";
	}
	
}
