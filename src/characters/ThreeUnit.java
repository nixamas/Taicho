package characters;

import java.util.ArrayList;
import java.util.List;

import basecomponents.BoardComponent;
import basecomponents.MovableObject;
import basecomponents.ObjectMove;
import enums.Player;
import gameparts.ObjectData;

public class ThreeUnit extends MovableObject {

	List<MovableObject> components;
	
	public ThreeUnit(Player p) {
		super(p);
		COMBAT_VALUE = 3;
	}
	
	public ThreeUnit(Player p, MovableObject comp1, MovableObject comp2, MovableObject comp3){
		super(p);
		components = new ArrayList<MovableObject>();
		components.add(comp1);
		components.add(comp2);
		components.add(comp3);
		COMBAT_VALUE = 3;
	}
	
	public ThreeUnit(Player p, MovableObject comp1, MovableObject comp2){
		super(p);
		components = new ArrayList<MovableObject>();
		components.add(comp1);
		components.add(comp2);
		COMBAT_VALUE = 3;
	}
	
	@Override
	public int getCombatValue() {
		return COMBAT_VALUE;
	}
	
	@Override
	public void setPlayer(Player p) {
		player = p;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

//	@Override
//	public ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
