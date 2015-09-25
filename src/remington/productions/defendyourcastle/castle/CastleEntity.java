package remington.productions.defendyourcastle.castle;

import remington.productions.defendyourcastle.MainGame;
import remington.productions.defendyourcastle.TextureManager;
import remington.productions.defendyourcastle.screen.GameScreen;
import remington.productions.defendyourcastle.screen.GameScreen.GAMESTATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class CastleEntity {

	protected Texture healthbar, health, gold;
	protected Vector2 pos, dimensions;
	protected BitmapFont font;
	
	public boolean DRAW;
	public CastleEntity(Vector2 pos, Vector2 dimensions) {
		healthbar = TextureManager.HEALTHBAR;
		health = TextureManager.HEALTH;
		if(GameScreen.gamestate == GAMESTATE.PLAY){
		this.pos = pos;
		}
		if(GameScreen.gamestate == GAMESTATE.UPGRADE){
//		this.pos = new Vector2((MainGame.WIDTH/2)-(healthbar.getWidth()),100);
			this.pos = new Vector2(250,90);
		}
		this.dimensions = dimensions;
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
	}

	
	public abstract void update();
	
	public void render(SpriteBatch sb) {
		//sb.draw(texture, pos.x, pos.y);
		//sb.draw(healthbar, 20f, 420f, 300f, 50f);
		sb.draw(healthbar, pos.x, pos.y, dimensions.x, dimensions.y);

		sb.draw(health, pos.x+32, pos.y+15, 235f*((float)Castle.HEALTH_PERCENTAGE/100), 20f);
		font.draw(sb, String.valueOf(Castle.KILLS), MainGame.WIDTH/2-(font.getBounds(String.valueOf(Castle.KILLS)).width/2), 450);
	}
	
	
}