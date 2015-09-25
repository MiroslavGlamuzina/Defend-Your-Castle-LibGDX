package remington.productions.defendyourcastle.screen;

import remington.productions.defendyourcastle.camera.OrthoCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends Screen {

	private OrthoCamera camera;
	//private EntityManager entityManager;

	public static enum GAMESTATE {START, LEVEL_ID, PLAY, UPGRADE, GAME_OVER, PAUSE, RESUME, HELP}
	public static GAMESTATE gamestate;
	
	@Override
	public void create() {
		switch(gamestate){
		case START:
			ScreenManager.setScreen(new GameStartScreen());
			break;
		case LEVEL_ID:
			ScreenManager.setScreen(new LevelScreen());
			break;
		case PLAY:
			ScreenManager.setScreen(new EntityScreen());
			break;
		case UPGRADE:
			ScreenManager.setScreen(new UpgradeScreen());
			break;
		case GAME_OVER:
			ScreenManager.setScreen(new GameOverScreen());

			break;
		case PAUSE:
			//todo 
			break;
		case RESUME:
			//todo 
			break;
		case HELP:
			ScreenManager.setScreen(new HelpScreen());
			break; 
			default:
				break;
		}
		
	}

	@Override
	public void update() {
		
		switch(gamestate){
		case START:
			break;
		case LEVEL_ID:
			break;
		case PLAY:
//			camera.update();
//			entityManager.update();		
			break;
		case UPGRADE:
			//todo 
			break;
		case GAME_OVER:
			//todo 
			break;
		case PAUSE:
			//todo 
			break;
		case RESUME:
			//todo 
			break;
			default:
				break;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		switch(gamestate){
		case START:			
			break;
		case LEVEL_ID:
			break;
		case PLAY:
			break;
		case UPGRADE:
			//todo 
			break;
		case GAME_OVER:
			//todo 
			break;
		case PAUSE:
			//todo 
			break;
		case RESUME:
			//todo 
			break;
			default:
				break;
		}

	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

}
