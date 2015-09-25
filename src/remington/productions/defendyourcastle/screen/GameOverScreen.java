package remington.productions.defendyourcastle.screen;

import remington.productions.defendyourcastle.MainGame;
import remington.productions.defendyourcastle.SoundManager;
import remington.productions.defendyourcastle.TextureManager;
import remington.productions.defendyourcastle.camera.OrthoCamera;
import remington.productions.defendyourcastle.castle.Castle;
import remington.productions.defendyourcastle.screen.GameScreen.GAMESTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameOverScreen extends Screen {

	private OrthoCamera camera;
	private Texture texture;
	protected BitmapFont gameover, highscore;
	private Vector2 touch;

	public GameOverScreen() {
		gameover = new BitmapFont(Gdx.files.internal("myfont.fnt"),
				Gdx.files.internal("myfont.png"), false);
		highscore = new BitmapFont(Gdx.files.internal("myfont.fnt"),
				Gdx.files.internal("myfont.png"), false);
		texture = TextureManager.CONTINUE;
		touch = new Vector2(0, 0);
		SoundManager.GAME_OVER.play();

	}

	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
	}

	@Override
	public void update() {
		camera.update();
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
		goToStartScreen();
	}

	private void goToStartScreen() {
		if (touch.x > (MainGame.WIDTH / 2) - (texture.getWidth() / 2)
				&& touch.x < (MainGame.WIDTH / 2) - (texture.getWidth() / 2)
						+ 50 + 180 && touch.y > 40 && touch.y < 160
				&& Gdx.input.justTouched()) {
			MainGame.GAME_LEVEL = 1;
			Castle.CURRENT_HEALTH = 1500;
			Castle.MAX_HEALTH = 1500;
			Castle.GOLD = 200;
			Castle.KILLS = 0;
			GameScreen.gamestate = GAMESTATE.START;
			ScreenManager.setScreen(new GameScreen());
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		gameover.draw(sb, "GAME OVER",
				MainGame.WIDTH / 2
						- (gameover.getBounds("GAME OVER").width / 2),
				MainGame.HEIGHT / 2 + (gameover.getBounds("G").height));
		gameover.draw(
				sb,
				"You have destroyed " + String.valueOf(MainGame.KILLCOUNT)
						+ " stickmen",
				MainGame.WIDTH
						/ 2
						- (gameover.getBounds("You have destroyed "
								+ String.valueOf(MainGame.KILLCOUNT)
								+ " stickmen").width / 2),
				(MainGame.HEIGHT / 2) - 50 + (gameover.getBounds("G").height));

		sb.draw(texture, (MainGame.WIDTH / 2) - (texture.getWidth() / 2) + 50,
				40, 180, 120);

		sb.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {
		SoundManager.GAME_OVER.stop();
		SoundManager.GAME_OVER.dispose();
	}

	@Override
	public void pause() {
		SoundManager.GAME_OVER.pause();
	}

	@Override
	public void resume() {
		SoundManager.GAME_OVER.play();
	}

}
