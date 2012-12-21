package characters;

import java.util.ArrayList;

import basecomponents.BoardComponent;
import basecomponents.MovableObject;
import basecomponents.ObjectMove;
import enums.Player;
import gameparts.ObjectData;

public class OneUnit extends MovableObject {
	
	public OneUnit(Player p) {
		super(p);
		COMBAT_VALUE = 1;
	}

	@Override
	public int getCombatValue() {
		return COMBAT_VALUE;
	}

//	@Override
//	public ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc) {
//		ArrayList<ObjectMove> moves = new ArrayList<ObjectMove>();
//		
//		for(int row = 0; row < 9; row++){
//			for(int col = 0; col < 15; col++){
//				
//			}
//		}
//		return moves;
//	}

}
