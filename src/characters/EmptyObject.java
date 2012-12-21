package characters;

import java.util.ArrayList;

import basecomponents.BoardComponent;
import basecomponents.MovableObject;
import basecomponents.ObjectMove;
import enums.Player;
import gameparts.ObjectData;

public class EmptyObject extends MovableObject {

	public EmptyObject() {
		super(Player.NONE);
	}

	@Override
	public int getCombatValue() {
		return -1;
	}

//	@Override
//	public ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc) {
//		return new ArrayList<ObjectMove>();
//	}

}
