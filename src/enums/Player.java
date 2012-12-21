package enums;



import java.awt.Color;

public enum Player {
	NONE{
		Color color;
		public void setColor(Color col){
			color = col;
		}
		public Color getColor(){
			return color;
		}
	},
	PLAYER_ONE{
		Color color = Color.RED;
		public void setColor(Color col){
			color = col;
		}
		public Color getColor(){
			color = Color.RED;
			return color;
		}
	},
	PLAYER_TWO{
		Color color = Color.BLUE;
		public void setColor(Color col){
			color = col;
		}
		public Color getColor(){
			return color;
		}
	};
	
	public abstract void setColor(Color col);
	public abstract Color getColor();
}
