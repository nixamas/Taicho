package characters;

import basecomponents.MovableObject;
import enums.Player;
import enums.Ranks;

public class Captain extends MovableObject {

	public Captain(Player p) {
		super(p, Ranks.TAICHO);
	}

}
