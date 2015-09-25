package remington.productions.defendyourcastle;

import remington.productions.defendyourcastle.screen.GameScreen;
import remington.productions.defendyourcastle.screen.GameScreen.GAMESTATE;
import remington.productions.defendyourcastle.screen.ScreenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class MainGame implements ApplicationListener {
	
	public static int WIDTH = 800, HEIGHT = 480;
	public static final String GAME_NAME ="Defend Your Castle";
	private SpriteBatch batch;
	public static int GAME_LEVEL; 
	public static boolean IS_PUASED; 
	public static int KILLCOUNT;
	public static SoundManager sm;
	
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		GameScreen.gamestate = GAMESTATE.START;
		GAME_LEVEL = 1; 
		KILLCOUNT = 0; 
		IS_PUASED = false;
		SoundManager.load();
		ScreenManager.setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().dispose();
		batch.dispose();
	}

	@Override
	public void render() throws IndexOutOfBoundsException, GdxRuntimeException, RuntimeException{		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().update();
		
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().render(batch);
	

	}

	@Override
	public void resize(int width, int height) {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resize(width, height);
	}

	@Override
	public void pause() {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().pause();
	}

	@Override
	public void resume() {
		if (ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resume();
	}
}
