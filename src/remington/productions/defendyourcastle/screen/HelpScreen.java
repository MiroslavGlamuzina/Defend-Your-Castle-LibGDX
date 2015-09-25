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

public class HelpScreen extends Screen {

	private OrthoCamera camera;
	private Texture texture;
	private Vector2 touch;
	private BitmapFont font; 
	public HelpScreen() {
		texture = TextureManager.HELPPAGE;
		touch = new Vector2(0, 0);
		SoundManager.SPARTA.play();
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"),
				Gdx.files.internal("myfont.png"), false);
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
		if ( Gdx.input.justTouched()) {
			MainGame.GAME_LEVEL = 1;
			Castle.CURRENT_HEALTH = 1500;
			Castle.MAX_HEALTH = 1500;
			Castle.GOLD = 200;
			Castle.KILLS = 0;
			counter = 0;
			GameScreen.gamestate = GAMESTATE.START;
			ScreenManager.setScreen(new GameScreen());
		}
	}
	public static int counter = 0;
	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		counter--;
		Gdx.app.log("counter", String.valueOf(counter));
		sb.draw(texture,counter,0,texture.getWidth(), MainGame.HEIGHT);
		if(counter < -3450 && counter%2==0)
		{
			font.draw(
					sb,
					"CLICK THE SCREEN TO CONTINUE" ,
					MainGame.WIDTH
							/ 2
							- (font.getBounds("CLICK THE SCREEN TO CONTINUE").width / 2),
					(MainGame.HEIGHT / 2) - 50 + (font.getBounds("G").height));
		}
		sb.end();
		
	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {
		SoundManager.SPARTA.stop();
		SoundManager.SPARTA.dispose();
	}

	@Override
	public void pause() {
		SoundManager.SPARTA.pause();
	}

	@Override
	public void resume() {
		SoundManager.SPARTA.play();
	}

}
