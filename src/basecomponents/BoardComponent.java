package basecomponents;
import java.awt.Color;

import characters.EmptyObject;
import enums.Location;


public class BoardComponent {
	private final Coordinate coordinate;
	private final Location location;
	private Color color = Color.BLACK;
	private boolean occupied = false;
	private boolean stackable = false;
	private MovableObject character;
	private boolean highlight;
	
	public BoardComponent(MovableObject character, Location loc, Coordinate coord) {
		this.occupied = true;
		this.character = character;
		this.location = loc;
		this.coordinate = coord;
	}
	public BoardComponent(Location loc, Coordinate coord){
		this.occupied = false;
		this.location = loc;
		this.coordinate = coord;
	}
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
	public int getId() {
		return coordinate.getId();
	}
	public void setId(int id) {
		this.coordinate.setId(id);
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
//	public void setCoordinate(Coordinate coordinate) {
//		this.coordinate = coordinate;
//	}
	public Color getColor() {
		if(highlight){
			return Color.MAGENTA;
		}else if(stackable){
			return Color.GREEN;
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
	public boolean isStackable() {
		return stackable;
	}
	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}
	@Override
	public String toString() {
		return "BoardComponent [coordinate=" + coordinate + ", location="
				+ location + ", color=" + color + ", occupied=" + occupied
				+ ", highlight=" + highlight + "]";
	}
	
	
}
