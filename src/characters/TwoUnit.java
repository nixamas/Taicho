package characters;

import java.util.ArrayList;
import java.util.List;

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

//	@Override
//	public ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
