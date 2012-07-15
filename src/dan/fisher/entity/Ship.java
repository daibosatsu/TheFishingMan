package dan.fisher.entity;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Ship {

	private AnimatedSprite shipSprite;
	
	public Ship(final TiledTextureRegion ship){
		shipSprite = new AnimatedSprite(0, 0, ship);
	}
	
	public AnimatedSprite getShip(){
		return this.shipSprite;
	}
}
