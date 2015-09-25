package remington.productions.defendyourcastle.screen;

import remington.productions.defendyourcastle.MainGame;
import remington.productions.defendyourcastle.SoundManager;
import remington.productions.defendyourcastle.TextureManager;
import remington.productions.defendyourcastle.camera.OrthoCamera;
import remington.productions.defendyourcastle.screen.GameScreen.GAMESTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameStartScreen extends Screen {

	private OrthoCamera camera;
	private Texture texture, snow1, snow2, snow3, snow4, snow5, help;
	private Vector2[] vsnow;
	public static GAMESTATE gamestate;
	public Vector2 touch;
	// Music song;
	private boolean delay;

	public GameStartScreen() {
		texture = TextureManager.START;
		help = TextureManager.HELP;
		snowFallingLoad();
		Gdx.app.log("gamestart", "called");
		delay = false;
		touch = new Vector2();
	}

	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
		SoundManager.INTRO.play();
		help = TextureManager.HELP;
	}

	public void snowFallingLoad() {
		snow1 = TextureManager.SNOWONE;
		snow2 = TextureManager.SNOWTWO;
		snow3 = TextureManager.SNOWTHREE;
		snow4 = TextureManager.SNOWFOUR;
		snow5 = TextureManager.SNOWFIVE;
		vsnow = new Vector2[30];
		for (int i = 0; i < 30; i++) {
			vsnow[i] = new Vector2(MathUtils.random(0, 800), MathUtils.random(
					0, 480));
		}
	}

	public void snowFallingRender(SpriteBatch sb) {

		for (int i = 0; i < 30; i++) {
			sb.draw(snow1, vsnow[i].x, vsnow[i].y, 5, 5);
			sb.draw(snow1, vsnow[i].x + 120, vsnow[i].y + 220, 6, 6);
			sb.draw(snow1, vsnow[i].x + 320, vsnow[i].y + 320, 5, 5);
			sb.draw(snow1, vsnow[i].x + 720, vsnow[i].y + 40, 4, 4);

			sb.draw(snow2, vsnow[i].x + 550, vsnow[i].y + 120, 7, 7);
			sb.draw(snow2, vsnow[i].x + 250, vsnow[i].y + 20, 6, 6);
			sb.draw(snow2, vsnow[i].x + 350, vsnow[i].y + 320, 5, 5);
			sb.draw(snow2, vsnow[i].x + 450, vsnow[i].y + 120, 4, 4);

			sb.draw(snow3, vsnow[i].x + 150, vsnow[i].y + 0, 8, 8);
			sb.draw(snow3, vsnow[i].x + 0, vsnow[i].y + 200, 7, 7);
			sb.draw(snow3, vsnow[i].x + 20, vsnow[i].y + 160, 6, 6);
			sb.draw(snow3, vsnow[i].x + 220, vsnow[i].y + 100, 5, 5);

			sb.draw(snow4, vsnow[i].x + 300, vsnow[i].y + 1600, 8, 8);
			sb.draw(snow4, vsnow[i].x + 200, vsnow[i].y + 200, 7, 7);
			sb.draw(snow4, vsnow[i].x + 100, vsnow[i].y + 100, 6, 6);
			sb.draw(snow4, vsnow[i].x + 50, vsnow[i].y + 50, 5, 5);
			sb.draw(snow4, vsnow[i].x + 0, vsnow[i].y + 0, 4, 4);

			sb.draw(snow5, vsnow[i].x + 220, vsnow[i].y + 90, 7, 7);
			sb.draw(snow5, vsnow[i].x + 100, vsnow[i].y + 250, 6, 6);
			sb.draw(snow5, vsnow[i].x + 110, vsnow[i].y + 110, 5, 5);
			sb.draw(snow5, vsnow[i].x + 50, vsnow[i].y + 20, 4, 4);
			sb.draw(snow5, vsnow[i].x + 0, vsnow[i].y + 0, 8, 8);

		}

	}

	public void snowfallAnimation() {
		for (int i = 0; i < 30; i++) {
			vsnow[i].x += MathUtils.random(-2, 2) / 2;
			vsnow[i].y -= MathUtils.random(3) / 2;
			if (vsnow[i].y < 0) {
				vsnow[i].y = 490;
				vsnow[i].x = MathUtils.random(0, 800);
				delay = true;
			}
		}
	}

	@Override
	public void update() {
		camera.update();
		delay = true;
		snowfallAnimation();
		setTouch();
		goToLevelID();
	}

	private void setTouch() {
		if (Gdx.input.justTouched()) {
			touch.set(
					Gdx.input.getX()
							/ ((Gdx.graphics.getWidth() / MainGame.WIDTH))
							- ((TextureManager.PLAYER.getWidth() / 2) / ((Gdx.graphics
									.getWidth() / MainGame.WIDTH))),
					(Gdx.graphics.getHeight() - Gdx.input.getY())
							/ (Gdx.graphics.getHeight() / MainGame.HEIGHT)
							- ((TextureManager.PLAYER.getHeight() / 2) / (Gdx.graphics
									.getHeight() / MainGame.HEIGHT)));
		}
	}

	private void goToLevelID() {
		if (Gdx.input.justTouched() && delay) {
			if (touch.x < 101 && touch.y < 101) {
				GameScreen.gamestate = GAMESTATE.HELP;
				delay = false;
				dispose();
				ScreenManager.setScreen(new GameScreen());
			} else {
				GameScreen.gamestate = GAMESTATE.LEVEL_ID;
				delay = false;
				dispose();
				ScreenManager.setScreen(new GameScreen());
			}
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(texture, MainGame.WIDTH / 2 - texture.getWidth() / 2,
				MainGame.HEIGHT / 2 - texture.getHeight() / 2);
		snowFallingRender(sb);
		sb.draw(help, 0, 0, 100, 100);
		sb.end();
		// change screens
		// Gdx.app.log("delay", String.valueOf(delay));

	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {
		SoundManager.INTRO.stop();
		SoundManager.INTRO.dispose();
	}

	@Override
	public void pause() {
		// song.pause();
		SoundManager.INTRO.pause();

	}

	@Override
	public void resume() {
		// song.play();
		SoundManager.INTRO.play();

	}

}
