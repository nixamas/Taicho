package characters;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import utilities.Utils;
import basecomponents.MovableObject;
import enums.Player;
import enums.Ranks;

public class TwoUnit extends MovableObject {

	List<MovableObject> components;
	
	public TwoUnit(Player p) {
		super(p, Ranks.LEVEL_TWO);
	}
	
	public TwoUnit(Player p, MovableObject comp1, MovableObject comp2){
		super(p, Ranks.LEVEL_TWO);
		components = new ArrayList<MovableObject>();
		components.add(comp1);
		components.add(comp2);
	}
	
	public ArrayList<MovableObject> getComponents(){
		return (ArrayList) this.components;
	}
	
	public MovableObject removeUnitFromStack(){
			//remove last unit in array from list
		return this.components.remove( this.components.size() - 1 );
	}
	
	@Override
	public Color getColor(){
//		Color c = Color.RED;
//		if( this.player == Player.PLAYER_ONE ){
//			c = Color.ORANGE;
//		}else if( this.player == Player.PLAYER_TWO ){
//			c = Color.GREEN;
//		}
//		return c;
		return Utils.blendColor(this.getPlayer().getColor(), Color.GRAY, 0.4);// (, 70);
	}

//	@Override
//	public ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
