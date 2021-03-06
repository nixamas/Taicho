package characters;

import basecomponents.MovableObject;
import enums.ComponentImages;
import enums.Player;
import enums.Ranks;

/**
 * fills BC's that are not occupied by other (actual game) characters
 * Empty, non null, values
 * Player = Player.None, Rank = Ranks.None, ComponentImages = ComponentImages.None
 * @author Ryan
 *
 */
public class EmptyObject extends MovableObject {

	public EmptyObject() {
		super(Player.NONE, Ranks.NONE, ComponentImages.NONE);
	}

}
