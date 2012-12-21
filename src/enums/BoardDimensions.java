package enums;

public class BoardDimensions {
	public enum Parts{
		COMPONENT_DIMENSION(initialDimension),
		CHARACTER_DIMENSION(initialDimension - 5),
		BOARD_LENGTH(initialDimension * 15),
		BOARD_WIDTH(initialDimension * 9);
		int val;
		Parts(int v){
			val = v;
		}
		public int getVal(){
			return val;
		}
	}
	private static int initialDimension = 20;
	
	public BoardDimensions(final int i){
		this.initialDimension = i;
	}

	public static int getInitialDimension() {
		return initialDimension;
	}
	
	public int getComponentSize(){
		return Parts.COMPONENT_DIMENSION.getVal();
	}
	
	public int getBoardLength(){
		return Parts.BOARD_LENGTH.getVal();
	}
	public int getBoardWidth(){
		return Parts.BOARD_WIDTH.getVal();
	}

	public int getCharacterDimension() {
		return Parts.CHARACTER_DIMENSION.getVal();
	}
	
	
}
