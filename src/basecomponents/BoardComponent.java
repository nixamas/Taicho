package basecomponents;
import java.awt.Color;

import characters.EmptyObject;
import enums.Location;
import enums.Player;


public class BoardComponent {

	private final Coordinate coordinate;
	private final Location location;
	private Color color = Color.BLACK;
	private boolean occupied = false;
	private boolean stackable = false;
	private boolean selected = false;
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
		this.character = new EmptyObject();
	}
	public boolean isOccupied() {
		if(this.character.getPlayer() != Player.NONE){
			return true;
		}else{
			return false;
		}
//		return occupied;
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
	public MovableObject removeCharachter(){
		MovableObject tempChar= this.character;
		this.character = new EmptyObject();
		return tempChar;
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
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "BoardComponent [coordinate=" + coordinate + ", location="
				+ location + ", color=" + color + ", occupied=" + occupied
				+ ", highlight=" + highlight + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardComponent other = (BoardComponent) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (location != other.location)
			return false;
		return true;
	}
}
