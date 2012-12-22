package gameparts;

public class BoardDimensions {
	private int COMPONENT_DIMENSION;
	private int CHARACTER_DIMENSION;
	private int BOARD_LENGTH;
	private int BOARD_WIDTH;
	
	private int initialDimension = 20;
	
	public BoardDimensions(int i){
		if( i < 20 ){
			this.initialDimension = i;
		}
		this.COMPONENT_DIMENSION = this.initialDimension;
		this.BOARD_LENGTH = (this.initialDimension * 15);
		this.BOARD_WIDTH = (this.initialDimension * 9);
		this.CHARACTER_DIMENSION = (this.initialDimension - 5);
	}

	public int getInitialDimension() {
		return this.initialDimension;
	}
	
	public int getComponentSize(){
		return this.COMPONENT_DIMENSION;
	}
	
	public int getBoardLength(){
		return this.BOARD_LENGTH;
	}
	public int getBoardWidth(){
		return this.BOARD_WIDTH;
	}

	public int getCharacterDimension() {
		return this.CHARACTER_DIMENSION;
	}
	
	
}
