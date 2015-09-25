package remington.productions.defendyourcastle.screen;

import remington.productions.defendyourcastle.MainGame;
import remington.productions.defendyourcastle.SoundManager;
import remington.productions.defendyourcastle.camera.OrthoCamera;
import remington.productions.defendyourcastle.screen.GameScreen.GAMESTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelScreen extends Screen {

	private OrthoCamera camera;
	public static GAMESTATE gamestate;
	public static long elapsedTime;
	protected BitmapFont font;
	

	public LevelScreen() {
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"),
				Gdx.files.internal("myfont.png"), false);
		font.scale(3);
		elapsedTime = 0;
		//Gdx.app.log("levelscreen", "called");
		// music = SoundManager.DREAM;
	}

	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
		elapsedTime = 0;
		//Gdx.app.log("create", "called");
		SoundManager.DREAM.play();
		
	}

	@Override
	public void update() {
		camera.update();
		goToGame();
	}

	@Override
	public void render(SpriteBatch sb) {
		elapsedTime++;
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		font.draw(
				sb,
				"Level " + String.valueOf(MainGame.GAME_LEVEL),
				MainGame.WIDTH
						/ 2
						- (font.getBounds(String.valueOf(MainGame.GAME_LEVEL)
								+ "Level ").width / 2), MainGame.HEIGHT / 2
						+ (font.getBounds("L").height));
		sb.end();
		// iterate timer
		Gdx.app.log("elapsed", String.valueOf(elapsedTime));
		
		
	}

	private void goToGame() {
		if (elapsedTime >= 650  || Gdx.input.justTouched()) {
			elapsedTime = 0;
			GameScreen.gamestate = GAMESTATE.PLAY;
			dispose();
			ScreenManager.setScreen(new GameScreen());
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {
		SoundManager.DREAM.stop();
		SoundManager.DREAM.dispose();
	}

	@Override
	public void pause() {
		SoundManager.DREAM.pause();
	}

	@Override
	public void resume() {
		SoundManager.DREAM.play();
	}

}
