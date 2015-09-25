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

public class UpgradeScreen extends Screen {

	private OrthoCamera camera;
	private Texture texture, gold;
	public static GAMESTATE gamestate;
	protected BitmapFont font, fonthealth;
	private Vector2 touch;
	private Castle castle;
	private boolean upgradeMaxHealth, repairwall;

	public UpgradeScreen() {
		texture = TextureManager.UPGRADE;
		gold = TextureManager.COINS;
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"),
				Gdx.files.internal("myfont.png"), false);
		fonthealth = new BitmapFont(Gdx.files.internal("myfont.fnt"),
				Gdx.files.internal("myfont.png"), false);
		fonthealth.scale((float) -0.4);

		touch = new Vector2(1, 1);
		castle = new Castle();
		SoundManager.UPGRADE.play();
	}

	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
	}

	@Override
	public void update() {
		camera.update();
		castle.update();
		touch();
		continueButton();
		if (repairwall) {
			upgradeHealth();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repairwall = false;
		}
		if (upgradeMaxHealth) {
			upgradeMaxHealth();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			upgradeMaxHealth = false;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(texture, MainGame.WIDTH / 2 - texture.getWidth() / 2,
				MainGame.HEIGHT / 2 - texture.getHeight() / 2);
		sb.draw(gold, 20, 10f, 50f, 50f);
		font.draw(
				sb,
				String.valueOf(Castle.GOLD),
				MainGame.WIDTH
						/ 2
						- (font.getBounds(String.valueOf(Castle.GOLD)).width / 2),
				50);
		fortifyWalls(sb);
		repairWalls(sb);
		castle.render(sb);
		// healthbar
		fonthealth.draw(sb, String.valueOf((int) Castle.CURRENT_HEALTH) + "/"
				+ String.valueOf((int) Castle.MAX_HEALTH), 290, 123);

		sb.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {
			SoundManager.UPGRADE.stop();
			SoundManager.UPGRADE.dispose();
		
			SoundManager.REPAIR.stop();
			SoundManager.REPAIR.dispose();
	}

	@Override
	public void pause() {
		SoundManager.UPGRADE.pause();
	//	SoundManager.REPAIR.pause();
	}

	@Override
	public void resume() {
		SoundManager.UPGRADE.play();
	//	SoundManager.REPAIR.play();
	}

	public void touch() {
		if (Gdx.input.isTouched()) {
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

	public void continueButton() {
		// Gdx.app.log("x",
		// String.valueOf(touch.x) + " y:" + String.valueOf(touch.y));
		if (touch.x > 560 && touch.y < 25 && Gdx.input.justTouched()) {
			GameScreen.gamestate = GAMESTATE.LEVEL_ID;
			ScreenManager.setScreen(new GameScreen());
			dispose();
		}
	}

	public void repairWalls(SpriteBatch sb) {
		if (touch.x < 232 && touch.y > 160 && Gdx.input.justTouched()) {
			if (Castle.GOLD >= 50 && Castle.CURRENT_HEALTH < Castle.MAX_HEALTH) {
				if (!repairwall) {
					repairwall = true;
				}
				font.draw(
						sb,
						"+50 Health",
						MainGame.WIDTH
								/ 2
								- (font.getBounds(String.valueOf("+50 Health")).width / 2),
						MainGame.HEIGHT / 2);
				SoundManager.REPAIR.play();
			} else {
				font.draw(
						sb,
						"You do not have enough gold!",
						MainGame.WIDTH
								/ 2
								- (font.getBounds(String
										.valueOf("You do not have enough gold!")).width / 2),
						MainGame.HEIGHT / 2);

			}
		}
	}

	private void upgradeHealth() {
		Castle.CURRENT_HEALTH += 50;
		Castle.GOLD -= 50;
	}

	private void upgradeMaxHealth() {
		Castle.GOLD -= 250;
		Castle.MAX_HEALTH += 200;
	}

	public void fortifyWalls(SpriteBatch sb) {
		if (touch.x > 476 && touch.y > 160 && Gdx.input.justTouched()) {
			if (Castle.GOLD >= 250) {
				if (!upgradeMaxHealth) {
					upgradeMaxHealth = true;
				}
				font.draw(
						sb,
						"+200 Max Health",
						MainGame.WIDTH
								/ 2
								- (font.getBounds(String
										.valueOf("+200 Max Health")).width / 2),
						MainGame.HEIGHT / 2);
				SoundManager.REPAIR.play();
			} else {
				font.draw(
						sb,
						"You do not have enough gold!",
						MainGame.WIDTH
								/ 2
								- (font.getBounds(String
										.valueOf("You do not have enough gold!")).width / 2),
						MainGame.HEIGHT / 2);

			}
		}
	}

}
