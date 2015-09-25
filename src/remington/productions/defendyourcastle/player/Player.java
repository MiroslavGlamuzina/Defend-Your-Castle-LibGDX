package remington.productions.defendyourcastle.player;

import remington.productions.defendyourcastle.MainGame;
import remington.productions.defendyourcastle.TextureManager;
import remington.productions.defendyourcastle.camera.OrthoCamera;
import remington.productions.defendyourcastle.enemy.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Player extends PlayerEntity {
	public boolean enemySelected; 
	public Vector2 touch;
	public boolean isTouched; 


	public Player(Vector2 pos, Vector2 direction, EntityManager entityManager, OrthoCamera camera) {
		super(TextureManager.PLAYER, pos, direction);

		//setEnemySelected(false); 
		touch = new Vector2();
	}

	@Override
	public void update() {
		if (Gdx.input.isTouched()) {
			//android
			touch.set(Gdx.input.getX()/((Gdx.graphics.getWidth()/MainGame.WIDTH))-((TextureManager.PLAYER.getWidth()/2)), (Gdx.graphics.getHeight()- Gdx.input.getY())/(Gdx.graphics.getHeight()/MainGame.HEIGHT)-((TextureManager.PLAYER.getHeight()/2)));
			DRAW = true;
			setPosition(touch);
			 setTouched(true);
		}
		else{
			setTouched(false);
			DRAW= false;
			touch = new Vector2(0,0);
			setPosition(touch);
		}
	}

	public boolean isEnemySelected() {
		return enemySelected;
	}

	public void setEnemySelected(boolean enemySelected) {
		this.enemySelected = enemySelected;
	}
	
	public boolean isTouched() {
		return isTouched;
	}

	public void setTouched(boolean isTouched) {
		this.isTouched = isTouched;
	}
	

}
