package characters;

import basecomponents.MovableObject;
import enums.ComponentImages;
import enums.Player;
import enums.Ranks;

public class EmptyObject extends MovableObject {

	public EmptyObject() {
		super(Player.NONE, Ranks.NONE, ComponentImages.NONE);
	}

}
