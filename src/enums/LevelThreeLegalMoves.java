package enums;

public enum LevelThreeLegalMoves {
	MOVE_ONE(30), MOVE_TWO(24), MOVE_THREE(16), MOVE_FOUR(8), MOVE_FIVE(10), MOVE_SIX(8), 
		MOVE_SEVEN(-30), MOVE_EIGHT(-24), MOVE_NINE(-16), MOVE_TEN(-8), MOVE_ELEVEN(-10), MOVE_TWELVE(-8);

    private int numVal;

    LevelThreeLegalMoves(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
