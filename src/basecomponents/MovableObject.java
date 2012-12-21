package basecomponents;

import java.awt.Color;
import java.util.ArrayList;

import enums.LevelOneLegalMoves;
import enums.LevelThreeLegalMoves;
import enums.LevelTwoLegalMoves;
import enums.Location;
import enums.Player;
import gameparts.ObjectData;


public abstract class MovableObject {
	protected int COMBAT_VALUE;
	protected Player player;
	
	public abstract int getCombatValue();
//	public abstract ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc);
	
	public MovableObject(Player p) {
		player = p;
	}
	public int getCOMBAT_VALUE() {
		return COMBAT_VALUE;
	}

	public void setCOMBAT_VALUE(int cOMBAT_VALUE) {
		COMBAT_VALUE = cOMBAT_VALUE;
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
//	public abstract void setPlayer(Player p);
//	public abstract Player getPlayer();
	public ArrayList<BoardComponent> getPossibleMoves(ObjectData board, BoardComponent bc){
		ArrayList<BoardComponent> legalMoves = new ArrayList<BoardComponent>();
		switch(COMBAT_VALUE){
			case 0:
				break;
			case 1:
				LevelOneLegalMoves[] l1moves = LevelOneLegalMoves.values();
				for(int i = 0; i < LevelOneLegalMoves.values().length; i++){
					BoardComponent potentialPosition = board.getBoardComponentAtId(bc.getId() + l1moves[i].getNumVal());
					if( !potentialPosition.isOccupied() && potentialPosition.getLocation() != Location.OUT_OF_BOUNDS ){
						legalMoves.add(potentialPosition);
					}
				}
				break;
			case 2:
				LevelTwoLegalMoves[] l2moves = LevelTwoLegalMoves.values();
				for(int i = 0; i < LevelOneLegalMoves.values().length; i++){
					BoardComponent potentialPosition = board.getBoardComponentAtId(bc.getId() + l2moves[i].getNumVal());
					if( !potentialPosition.isOccupied() && potentialPosition.getLocation() != Location.OUT_OF_BOUNDS ){
						legalMoves.add(potentialPosition);
					}
				}
				break;
			case 3:
				LevelThreeLegalMoves[] l3moves = LevelThreeLegalMoves.values();
				for(int i = 0; i < LevelOneLegalMoves.values().length; i++){
					BoardComponent potentialPosition = board.getBoardComponentAtId(bc.getId() + l3moves[i].getNumVal());
					if( !potentialPosition.isOccupied() && potentialPosition.getLocation() != Location.OUT_OF_BOUNDS ){
						legalMoves.add(potentialPosition);
					}
				}
				break;
			default:
				break;
		}
		
		for(int i = 0; i < legalMoves.size(); i++){
			legalMoves.get(i).setHighlight(true);
		}
		
		return legalMoves;
	}
	
}
