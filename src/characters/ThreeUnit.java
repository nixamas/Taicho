package characters;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import utilities.Utils;

import basecomponents.MovableObject;
import enums.Player;
import enums.Ranks;

public class ThreeUnit extends MovableObject {

	List<MovableObject> components;
	
	public ThreeUnit(Player p) {
		super(p, Ranks.LEVEL_THREE);
		combatValue = 3;
	}
	
	public ThreeUnit(Player p, MovableObject comp1, MovableObject comp2, MovableObject comp3){
		super(p, Ranks.LEVEL_THREE);
		components = new ArrayList<MovableObject>();
		components.add(comp1);
		components.add(comp2);
		components.add(comp3);
		combatValue = 3;
	}
	
	public ThreeUnit(Player p, MovableObject comp1, MovableObject comp2){
		super(p, Ranks.LEVEL_THREE);
		components = new ArrayList<MovableObject>();
		components.add(comp1);
		components.add(comp2);
		combatValue = 3;
	}
	
	@Override
	public void setPlayer(Player p) {
		player = p;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Color getColor(){
//		Color c = Color.RED;
//		if( this.player == Player.PLAYER_ONE ){
//			c = Color.YELLOW;
//		}else if( this.player == Player.PLAYER_TWO ){
//			c = Color.DARK_GRAY;
//		}
//		return c;
		return Utils.blendColor(this.getPlayer().getColor(), Color.DARK_GRAY, 0.4);
	}
	
//	@Override
//	public ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
