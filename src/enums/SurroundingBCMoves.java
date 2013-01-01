package enums;

import interfaces.MoveManager;

public enum SurroundingBCMoves implements MoveManager{
	MOVE_ONE(1), MOVE_TWO(10), MOVE_THREE(8), MOVE_FOUR(9), 
		MOVE_FIVE(-1), MOVE_SIX(-10), MOVE_SEVEN(-8), MOVE_EIGHT(-9);

    private int numVal;

    SurroundingBCMoves(int numVal) {
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
		SurroundingBCMoves[] array = values();
		return array[i].getNumVal();
	}

	public static int getBufferValue() {
		return 1;
	}
}
