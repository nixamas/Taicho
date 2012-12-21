package characters;

import basecomponents.MovableObject;
import enums.Player;
import enums.Ranks;

public class OneUnit extends MovableObject {
	
	public OneUnit(Player p) {
		super(p, Ranks.LEVEL_ONE);
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
