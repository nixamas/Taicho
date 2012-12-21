package basecomponents;

import java.awt.Color;
import java.util.ArrayList;

import enums.LevelOneLegalMoves;
import enums.LevelThreeLegalMoves;
import enums.LevelTwoLegalMoves;
import enums.Location;
import enums.MoveManager;
import enums.Player;
import enums.Ranks;
import gameparts.ObjectData;


public abstract class MovableObject {
	
	protected int COMBAT_VALUE;
	protected Player player;
	protected Ranks rank;
	
	
	public MovableObject(Player p, Ranks r) {
		player = p;
		rank = r;
	}

	public int getCombatValue(){
		return this.COMBAT_VALUE;
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
				this.COMBAT_VALUE = -1;
				break;
			case LEVEL_ONE:
				this.COMBAT_VALUE = 1;
				break;
			case LEVEL_TWO:
				this.COMBAT_VALUE = 2;
				break;
			case LEVEL_THREE:
				this.COMBAT_VALUE = 3;
				break;
			case TAICHO:
				this.COMBAT_VALUE = 1;
				break;
			default:
				this.COMBAT_VALUE = -1;
				break;
		}
		this.rank = rank;
	}

	//	public abstract void setPlayer(Player p);
//	public abstract Player getPlayer();
	public ArrayList<BoardComponent> getPossibleMoves(ObjectData board, BoardComponent bc){
		ArrayList<BoardComponent> legalMoves = new ArrayList<BoardComponent>();
		ArrayList<MoveManager> mm = new ArrayList<MoveManager>();
		switch(rank){
			case NONE:
				break;
			case LEVEL_ONE:
				LevelOneLegalMoves[] l1moves = LevelOneLegalMoves.values();
				for(int i = 0; i < l1moves.length; i++){
					mm.add(l1moves[i]);
				}
				break;
			case LEVEL_TWO:
				LevelTwoLegalMoves[] l2moves = LevelTwoLegalMoves.values();
				for(int i = 0; i < l2moves.length; i++){
					mm.add(l2moves[i]);
				}
				break;
			case LEVEL_THREE:
				LevelThreeLegalMoves[] l3moves = LevelThreeLegalMoves.values();
				for(int i = 0; i < l3moves.length; i++){
					mm.add(l3moves[i]);
				}
				break;
			default:
				break;
		}
		
		for(int i = 0; i < mm.size(); i++){
			int changeVal = mm.get(i).getMove(i);
			BoardComponent potentialPosition = board.getBoardComponentAtId(bc.getId() + changeVal);
			if( !potentialPosition.isOccupied() && potentialPosition.getLocation() != Location.OUT_OF_BOUNDS ){
				potentialPosition.setHighlight(true);
				legalMoves.add(potentialPosition);
			} else if (potentialPosition.isOccupied()){
				if(potentialPosition.getCharacter().getPlayer() == bc.getCharacter().getPlayer()){
					potentialPosition.setStackable(true);
					legalMoves.add(potentialPosition);
				}
			}
		}
		
//		for(int i = 0; i < legalMoves.size(); i++){
//			legalMoves.get(i).setHighlight(true);
//		}
		
		return legalMoves;
	}
	
}
