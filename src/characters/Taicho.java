package characters;

import java.awt.Color;

import utilities.Utils;

import basecomponents.MovableObject;
import enums.ComponentImages;
import enums.Player;
import enums.Ranks;

/**
 * reference basecomponents/MovableObject.java for more information
 * @author Ryan
 */
public class Taicho extends MovableObject {

	public Taicho(Player p) {
		super(p, Ranks.TAICHO, ComponentImages.TAICHO_IMAGE);
	}
	
	@Override
	public Color getColor(){
		return Utils.blendColor(this.getPlayer().getColor(), Color.WHITE, 0.4);
	}

}
