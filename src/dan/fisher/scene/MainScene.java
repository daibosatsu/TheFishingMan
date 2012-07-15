package dan.fisher.scene;

import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

public class MainScene extends Scene {

	private static MainScene mainScene;

	private MainScene() {
		super();
	}

	public static MainScene init(final PhysicsWorld physicsWorld,
			final SpriteBackground spriteBackground, final int CAMERA_WIDTH,
			final int CAMERA_HEIGHT) {
		mainScene = new MainScene();
		mainScene.registerUpdateHandler(physicsWorld);
		mainScene.setBackground(spriteBackground);
//		final Rectangle base = new Rectangle(0, 170, CAMERA_WIDTH,
//				CAMERA_HEIGHT - 170);
//		base.setColor(.1f, .43f, .99f);
//		mainScene.attachChild(base);

		return mainScene;
	}
}
