package dan.fisher;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;

import dan.fisher.entity.Ship;
import dan.fisher.scene.MainScene;

public class Fisher extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 640;
	private static final int CAMERA_HEIGHT = 480;
	private static final int SURFACE = 180;

	private ZoomCamera zoomCamera;
	private MainScene mainScene;
	private SpriteBackground mainBackground;
	private TiledTextureRegion charactersTextureRegion;
	private PhysicsWorld physicsWorld;

	public Engine onLoadEngine() {
		zoomCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				zoomCamera));
	}

	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		final BitmapTextureAtlas backgroundTextureAtlas = new BitmapTextureAtlas(
				1024, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		final BitmapTextureAtlas characters = new BitmapTextureAtlas(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		final Sprite backgroundSprite = new Sprite(0, 0,
				BitmapTextureAtlasTextureRegionFactory.createFromAsset(
						backgroundTextureAtlas, Fisher.this, "bg.png", 0, 0));
		charactersTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(characters, Fisher.this, "ship.png", 0,
						0, 4, 4);
		this.mEngine.getTextureManager().loadTextures(backgroundTextureAtlas,
				characters);
		mainBackground = new SpriteBackground(backgroundSprite);
	}

	public Scene onLoadScene() {
		this.physicsWorld = new PhysicsWorld(new Vector2(
				SensorManager.GRAVITY_EARTH, 0), false);
		mainScene = MainScene.init(physicsWorld, mainBackground, CAMERA_WIDTH,
				CAMERA_HEIGHT);
		final Ship ship = new Ship(charactersTextureRegion.deepCopy());
		final AnimatedSprite shipSprite = ship.getShip();
		mainScene.attachChild(shipSprite);
		shipSprite.setPosition(CAMERA_WIDTH / 2 - shipSprite.getWidth() / 2,
				SURFACE - shipSprite.getHeight() + 18);
		shipSprite.animate(175);
		// mainScene.setBackground(mainBackground);
		return mainScene;
	}

	public void onLoadComplete() {

	}

}
