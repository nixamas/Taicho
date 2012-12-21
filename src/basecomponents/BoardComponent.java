package basecomponents;
import java.awt.Color;

import characters.EmptyObject;
import enums.Location;


public class BoardComponent {
@Override
	public String toString() {
		return "BoardComponent [coordinate=" + coordinate + ", color=" + color
				+ ", occupied=" + occupied + ", location=" + location
				+ ", highlight=" + highlight + "]";
	}
	//	int id;
//	private int posX, posY;
	private Coordinate coordinate;
	private Color color = Color.BLACK;
	private boolean occupied = false;
	private MovableObject character;
	private Location location;
	private boolean highlight;
	
	public BoardComponent(MovableObject character, Location loc, Coordinate coord) {
//		this.posX = posX;
//		this.posY = posY;
		this.occupied = true;
		this.character = character;
		this.location = loc;
		this.coordinate = coord;
//		this.id = id;
	}
	public BoardComponent(Location loc, Coordinate coord){
//		this.posX = posX;
//		this.posY = posY;
		this.occupied = false;
		this.location = loc;
		this.coordinate = coord;
//		this.id = id;
	}
//	public int getPosX() {
//		return posX;
//	}
//	public void setPosX(int posX) {
//		this.posX = posX;
//	}
//	public int getPosY() {
//		return posY;
//	}
//	public void setPosY(int posY) {
//		this.posY = posY;
//	}
	public boolean isOccupied() {
		return occupied;
	}
	public MovableObject getCharacter() {
		if(character != null){
			return character;
		}else{
			return new EmptyObject();
		}
	}
	public void setCharacter(MovableObject character) {
		this.character = character;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getId() {
		return coordinate.getId();
	}
	public void setId(int id) {
		this.coordinate.setId(id);
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	public Color getColor() {
		if(highlight){
			return Color.MAGENTA;
		}else{
			return color;
		}
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isHighlight() {
		return highlight;
	}
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
}
