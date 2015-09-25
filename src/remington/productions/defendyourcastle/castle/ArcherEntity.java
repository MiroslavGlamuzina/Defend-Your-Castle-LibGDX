package remington.productions.defendyourcastle.castle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class ArcherEntity {
	protected Texture archer;
	protected Vector2 pos;
	Animation walkAnimation; // #3
	Texture texture; // #4
	TextureRegion[] walkFrames; // #5
	SpriteBatch spriteBatch; // #6
	TextureRegion currentFrame; // #7
	float stateTime; // #8
	private static int STICKMAN_SCALEX = 30;
	private static int STICKMAN_SCALEY = 55;
	private int FRAME_COLS = 10;
	private int FRAME_ROWS = 1;
	
	public boolean DRAW;

	public ArcherEntity(Texture texture) {
		archer = texture;
		pos = new Vector2(100,100);
	
		archerLoad();
	}

	
	public abstract void update();
	
	public void render(SpriteBatch sb) {
		archerRender(sb);
	}
	
	public void archerLoad() {
		TextureRegion[][] tmp = TextureRegion.split(archer, archer.getWidth()
				/ FRAME_COLS, archer.getHeight() / FRAME_ROWS); // #10
		walkFrames = new TextureRegion[(FRAME_COLS * FRAME_ROWS)];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j< FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames); // #11
		spriteBatch = new SpriteBatch(); // #12
		stateTime = 0f; // #13
	}
	public void archerRender(SpriteBatch sb){
		stateTime += Gdx.graphics.getDeltaTime(); // #15
		currentFrame = walkAnimation.getKeyFrame(stateTime, true); // #16
		spriteBatch.draw(currentFrame, pos.x, pos.y, STICKMAN_SCALEX * 2,
				STICKMAN_SCALEY * 2);
		
	}
	
	
}
