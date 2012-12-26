package characters;

import java.awt.Color;

import basecomponents.MovableObject;
import enums.ComponentImages;
import enums.Player;
import enums.Ranks;
import enums.TaichoColors;

public class OneUnit extends MovableObject {
	
	public OneUnit(Player p) {
		super(p, Ranks.LEVEL_ONE, ComponentImages.LEVEL_ONE_IMAGE);
	}

	@Override
	public Color getColor(){
		if( this.player == Player.PLAYER_ONE ){
			return TaichoColors.PLAYER_ONE_LVL1.getColor();
		}else if( this.player == Player.PLAYER_TWO ){
			return TaichoColors.PLAYER_TWO_LVL1.getColor();
		}else{
			return Color.WHITE;
		}
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
