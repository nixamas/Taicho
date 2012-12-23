package characters;

import java.awt.Color;

import basecomponents.MovableObject;
import enums.Player;
import enums.Ranks;
import gameparts.Utils;

public class Taicho extends MovableObject {

	public Taicho(Player p) {
		super(p, Ranks.TAICHO);
	}
	
	@Override
	public Color getColor(){
		return Utils.blendColor(this.getPlayer().getColor(), Color.WHITE, 0.4);
	}

}
