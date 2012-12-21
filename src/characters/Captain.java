package characters;

import java.util.ArrayList;

import basecomponents.BoardComponent;
import basecomponents.MovableObject;
import basecomponents.ObjectMove;
import enums.Player;
import gameparts.ObjectData;

public class Captain extends MovableObject {

	public Captain(Player p) {
		super(p);
	}

	@Override
	public int getCombatValue() {
		return 1;
	}

//	@Override
//	public ArrayList<ObjectMove> getPossibleMoves(ObjectData board, BoardComponent bc) {
//		ArrayList<ObjectMove> moves = new ArrayList<ObjectMove>();
//		return moves;
//	}

}
