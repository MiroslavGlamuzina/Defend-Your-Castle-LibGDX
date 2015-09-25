package remington.productions.defendyourcastle.screen;

import remington.productions.defendyourcastle.MainGame;
import remington.productions.defendyourcastle.SoundManager;
import remington.productions.defendyourcastle.TextureManager;
import remington.productions.defendyourcastle.camera.OrthoCamera;
import remington.productions.defendyourcastle.enemy.EntityManager;
import remington.productions.defendyourcastle.screen.GameScreen.GAMESTATE;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class EntityScreen extends Screen {

	private OrthoCamera camera;
	public static GAMESTATE gamestate;
	private EntityManager entityManager;
	private Texture texture;
	public static Music death_one = SoundManager.DEATH_ONE;
	public static Music death_two = SoundManager.DEATH_TWO;
	public static Music death_three = SoundManager.DEATH_THREE;
	public static Music death_four = SoundManager.DEATH_FOUR;
	public static Music death_five = SoundManager.DEATH_FIVE;
	public static Music death_six = SoundManager.DEATH_SIX;
	public static Music death_seven = SoundManager.DEATH_SEVEN;
	private int rand;
	// load music and sound effects
							// Gdx.audio.newMusic(Gdx.files.internal("hthree.mp3"));

	public EntityScreen() {
		camera = new OrthoCamera();
		entityManager = new EntityManager(MainGame.GAME_LEVEL * 5, camera);
		texture = TextureManager.GAME;
		 rand = MathUtils.random(0, 3);
		switch (rand) {
		case 0:
			SoundManager.GAME_MUSIC_ONE.play();
			break;
		case 1:
			SoundManager.GAME_MUSIC_TWO.play();

			break;
		case 2:
			SoundManager.GAME_MUSIC_THREE.play();
			break;
		case 3:
			SoundManager.GAME_MUSIC_FOUR.play();
			break;
		default:
			break;
		}
	}

	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
	}

	@Override
	public void update() {
		camera.update();
		entityManager.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(texture, MainGame.WIDTH / 2 - texture.getWidth() / 2,
				MainGame.HEIGHT / 2 - texture.getHeight() / 2);
		entityManager.render(sb);
		sb.end();

	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {
		switch (rand) {
		case 0:
			SoundManager.GAME_MUSIC_ONE.stop();
			SoundManager.GAME_MUSIC_ONE.dispose();
			break;
		case 1:
			SoundManager.GAME_MUSIC_TWO.stop();
			SoundManager.GAME_MUSIC_TWO.dispose();

			break;
		case 2:
			SoundManager.GAME_MUSIC_THREE.stop();
			SoundManager.GAME_MUSIC_THREE.dispose();
			break;
		case 3:
			SoundManager.GAME_MUSIC_FOUR.stop();
			SoundManager.GAME_MUSIC_FOUR.dispose();
			break;
		default:
			break;
		}
	}

	@Override
	public void pause() {
		switch (rand) {
		case 0:
			SoundManager.GAME_MUSIC_ONE.pause();
			break;
		case 1:
			SoundManager.GAME_MUSIC_TWO.pause();

			break;
		case 2:
			SoundManager.GAME_MUSIC_THREE.pause();
			break;
		case 3:
			SoundManager.GAME_MUSIC_FOUR.pause();
			break;
		default:
			break;
		}	
		}

	@Override
	public void resume() {
		switch (rand) {
		case 0:
			SoundManager.GAME_MUSIC_ONE.play();
			break;
		case 1:
			SoundManager.GAME_MUSIC_TWO.play();

			break;
		case 2:
			SoundManager.GAME_MUSIC_THREE.play();
			break;
		case 3:
			SoundManager.GAME_MUSIC_FOUR.play();
			break;
		default:
			break;
		}		}

	

}
