package characters;

import java.util.ArrayList;
import java.util.List;

import basecomponents.BoardComponent;
import basecomponents.MovableObject;
import basecomponents.ObjectMove;
import enums.Player;
import gameparts.ObjectData;

public class TwoUnit extends MovableObject {

	List<MovableObject> components;
	
	public TwoUnit(Player p) {
		super(p);
		COMBAT_VALUE = 2;
	}
	
	public TwoUnit(Player p, MovableObject comp1, MovableObject comp2){
		super(p);
		components = new ArrayList<MovableObject>();
		components.add(comp1);
		components.add(comp2);
		COMBAT_VALUE = 2;
	}

	@Override
	public int getCombatValue() {
		return COMBAT_VALUE;
	}

//	@Override
//	public ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
