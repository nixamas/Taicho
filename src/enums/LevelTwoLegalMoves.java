package enums;

public enum LevelTwoLegalMoves {
	MOVE_ONE(2), MOVE_TWO(18), MOVE_THREE(1), MOVE_FOUR(9),
		MOVE_FIVE(-2), MOVE_SIX(-18), MOVE_SEVEN(-1), MOVE_EIGHT(-9);

    private int numVal;

    LevelTwoLegalMoves(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
