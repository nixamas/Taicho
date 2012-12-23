package enums;

import interfaces.MoveManager;

public enum LevelThreeLegalMoves implements MoveManager {
	MOVE_ONE(30), MOVE_TWO(24), MOVE_THREE(16), MOVE_FOUR(20), MOVE_FIVE(10), MOVE_SIX(8), 
		MOVE_SEVEN(-30), MOVE_EIGHT(-24), MOVE_NINE(-16), MOVE_TEN(-20), MOVE_ELEVEN(-10), MOVE_TWELVE(-8);

    private int numVal;

    LevelThreeLegalMoves(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
    
    @Override
	public Object[] getMoves() {
		return values();
	}

    @Override
	public int getMove(int i) {
    	LevelThreeLegalMoves[] array = values();
		return array[i].getNumVal();
	}
    
	public static int getBufferValue() {
		return 3;
	}
}
