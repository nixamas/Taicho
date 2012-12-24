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

	//	public abstract void setPlayer(Player p);
//	public abstract Player getPlayer();
	public ArrayList<BoardComponent> getPossibleMoves(TaichoGameData board, BoardComponent bc){
		ArrayList<BoardComponent> legalMoves = new ArrayList<BoardComponent>();
		ArrayList<MoveManager> mm = new ArrayList<MoveManager>();
		int bufferZone = 0;
		boolean isTaicho = false;
		switch(rank){
			case NONE:
				break;
			case LEVEL_ONE:
				bufferZone = LevelOneLegalMoves.getBufferValue();
				LevelOneLegalMoves[] l1moves = LevelOneLegalMoves.values();
				for(int i = 0; i < l1moves.length; i++){
					mm.add(l1moves[i]);
				}
				break;
			case LEVEL_TWO:
				bufferZone = LevelTwoLegalMoves.getBufferValue();
				LevelTwoLegalMoves[] l2moves = LevelTwoLegalMoves.values();
				for(int i = 0; i < l2moves.length; i++){
					mm.add(l2moves[i]);
				}
				break;
			case LEVEL_THREE:
				bufferZone = LevelThreeLegalMoves.getBufferValue();
				LevelThreeLegalMoves[] l3moves = LevelThreeLegalMoves.values();
				for(int i = 0; i < l3moves.length; i++){
					mm.add(l3moves[i]);
				}
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
	
	private ArrayList<BoardComponent> getSamuraiMoves(ArrayList<MoveManager> mm,TaichoGameData board, BoardComponent bc, int bufferZone){
		ArrayList<BoardComponent> legalMoves = new ArrayList<BoardComponent>();
		for(int i = 0; i < mm.size(); i++){ 
			int changeVal = mm.get(i).getMove(i);
			try{
				BoardComponent potentialPosition = board.getBoardComponentAtId(bc.getId() + changeVal);
				if( board.isWithinBufferZone(bufferZone, bc, potentialPosition)){
					if( !potentialPosition.isOccupied() && potentialPosition.getLocation() != Location.OUT_OF_BOUNDS ){
						potentialPosition.setHighlight(true);
						legalMoves.add(potentialPosition);
					} else if (potentialPosition.isOccupied()){
						if( this.rank != Ranks.LEVEL_THREE && this.rank != Ranks.TAICHO ){
							if(potentialPosition.getCharacter().getPlayer() == bc.getCharacter().getPlayer() 
									&& ( potentialPosition.getCharacter().getRank() != Ranks.TAICHO && potentialPosition.getCharacter().getRank() != Ranks.LEVEL_THREE)){
								potentialPosition.setStackable(true);
								legalMoves.add(potentialPosition);
							}
						}
					}
				}	
			}catch(BoardComponentNotFoundException bcnfe){
				System.err.println(bcnfe.getMessage());
			}	
		}//end for loop
		return legalMoves;
	}

	@Override
	public String toString() {
		return "MovableObject [combatValue=" + combatValue + ", player="
				+ player + ", rank=" + rank + "]";
	}
	
}
