package remington.productions.defendyourcastle.enemy;

import remington.productions.defendyourcastle.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class EnemyEntity {

	protected Vector2 pos, direction;
	private static int FRAME_COLS = 9; // #1
	private static int FRAME_ROWS = 8; // #2
	private static int STICKMAN_SCALEX = 30;
	private static int STICKMAN_SCALEY = 55;
	Animation walkAnimation; // #3
	Texture texture; // #4
	TextureRegion[] walkFrames; // #5
	SpriteBatch spriteBatch; // #6
	TextureRegion currentFrame; // #7
	float stateTime; // #8
	public boolean ATTACK;
//	public static int GRABBED ;
	public boolean DEAD;
	public boolean REMOVE_ENEMY;



	public EnemyEntity(Texture texture, Vector2 pos, Vector2 direction) {
		this.texture = texture;
		this.pos = pos;
		this.direction = direction;
		 running();
	}

	public void running() {
		FRAME_COLS = 9;
		FRAME_ROWS = 8;
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()
				/ FRAME_COLS, texture.getHeight() / FRAME_ROWS); // #10
		walkFrames = new TextureRegion[(FRAME_COLS * FRAME_ROWS)];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = FRAME_COLS - 1; j >= 0; j--) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.02f, walkFrames); // #11
		spriteBatch = new SpriteBatch(); // #12
		stateTime = MathUtils.random(10); // #13
	}

	public void bloodSpatter() {
		texture = TextureManager.STICKMAN_BLOOD_SPATTER;
		FRAME_COLS = 6;
		FRAME_ROWS = 1;
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()
				/ FRAME_COLS, texture.getHeight() / FRAME_ROWS); // #10
		walkFrames = new TextureRegion[(FRAME_COLS * FRAME_ROWS)];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.075f, walkFrames); // #11
		spriteBatch = new SpriteBatch(); // #12
		stateTime = 0f; // #13
	}

//	public void necropolis_skeleton_walk() {
//		FRAME_COLS = 6;
//		FRAME_ROWS = 4;
//		STICKMAN_SCALEX = STICKMAN_SCALEX;
//		STICKMAN_SCALEY = STICKMAN_SCALEY;
//		texture = TextureManager.NECROPOLIS_SKELETON;
//		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()
//				/ FRAME_COLS, texture.getHeight() / FRAME_ROWS); // #10
//		walkFrames = new TextureRegion[12];
//		int index = 0;
//
//		for (int i = 0; i < FRAME_ROWS; i++) {
//			for (int j = FRAME_COLS - 1; j >= 0; j--) {
//
//				if (i == 0 || i == 1) {
//					walkFrames[index++] = tmp[i][j];
//				}
//
//			}
//		}
//		walkAnimation = new Animation(0.045f, walkFrames); // #11
//		spriteBatch = new SpriteBatch(); // #12
//		stateTime = 0f; // #13
//	}

//	public void necropolis_skeleton_death() {
//		texture = TextureManager.NECROPOLIS_SKELETON;
//		FRAME_COLS = 6;
//		FRAME_ROWS = 4;
//		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()
//				/ FRAME_COLS, texture.getHeight() / FRAME_ROWS); // #10
//		walkFrames = new TextureRegion[6];
//		int index = 0;
//		for (int i = 0; i < FRAME_ROWS; i++) {
//			for (int j = FRAME_COLS - 1; j >= 0; j--) {
//				if (i == 3) {
//					walkFrames[index++] = tmp[i][j];
//				}
//			}
//		}
//		walkAnimation = new Animation(0.35f, walkFrames); // #11
//		spriteBatch = new SpriteBatch(); // #12
//		stateTime = 0f; // #13
//	}

//	public void necropolis_skeleton_attack() {
//		texture = TextureManager.NECROPOLIS_SKELETON;
//		FRAME_COLS = 6;
//		FRAME_ROWS = 4;
//		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()
//				/ FRAME_COLS, texture.getHeight() / FRAME_ROWS); // #10
//		walkFrames = new TextureRegion[6];
//		int index = 0;
//		for (int i = 0; i < FRAME_ROWS; i++) {
//			for (int j = FRAME_COLS - 1; j >= 0; j--) {
//				if (i == 2) {
//					walkFrames[index++] = tmp[i][j];
//				}
//			}
//		}
//		walkAnimation = new Animation(0.065f, walkFrames); // #11
//		spriteBatch = new SpriteBatch(); // #12
//		stateTime = 0f; // #13
//	}

	public abstract void update();

	public void render(SpriteBatch spriteBatch) {
		stateTime += Gdx.graphics.getDeltaTime(); // #15
		currentFrame = walkAnimation.getKeyFrame(stateTime, true); // #16
		spriteBatch.draw(currentFrame, pos.x, pos.y, STICKMAN_SCALEX * 2,
				STICKMAN_SCALEY * 2);
		if (DEAD) {
			stateTime += Gdx.graphics.getDeltaTime(); // #15
			currentFrame = walkAnimation.getKeyFrame(stateTime, false); // #16
			spriteBatch.draw(currentFrame, pos.x, pos.y, STICKMAN_SCALEX * 2,
					STICKMAN_SCALEY * 2);
			if (walkAnimation.isAnimationFinished(stateTime)) {
				REMOVE_ENEMY = true;
			}
		}
	}

	public Vector2 getPosition() {
		return pos;
	}

	public void setPosition(Vector2 pos) {
		this.pos = pos;
	}

	public Rectangle getBounds() {
		// return new Rectangle(pos.x, pos.y, texture.getWidth()/FRAME_COLS,
		// texture.getHeight()/FRAME_ROWS);
		return new Rectangle(pos.x, pos.y, STICKMAN_SCALEX, STICKMAN_SCALEY);
	}

	public int getWidth() {
		return STICKMAN_SCALEX;
	}

	public int getHeight() {
		return STICKMAN_SCALEY;
	}

	public void setDirection(float x, float y) {
		direction.set(x, y);
		direction.scl(Gdx.graphics.getDeltaTime());
	}

}
