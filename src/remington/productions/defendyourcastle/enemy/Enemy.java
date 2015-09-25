package remington.productions.defendyourcastle.enemy;

import remington.productions.defendyourcastle.TextureManager;
import remington.productions.defendyourcastle.castle.Castle;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends EnemyEntity {
	public Vector2 direction;
	public Vector2 enemyCoordinates;
	public double velocity;
	double healthPercentage;
	public volatile  Vector2 fling;

	public Enemy(Vector2 pos, Vector2 direction) {
		super(TextureManager.STICKMAN_SPRITE, pos, direction);
		enemyCoordinates = pos;
		this.direction = direction;
		velocity = 0;
		fling = new Vector2(0,0);
	}

	@Override
	public void update() {
		pos.add(direction);
		if (pos.x <= 305 && pos.y == enemyCoordinates.y) {
			pos.set(299, pos.y);
			attackCastle();
		}else{
		EntityManager.ATTACK = false;	
		}
		if(fling.x != 0 && fling.y !=0){
			fling.x = -1*fling.x;
			if(fling.y <= Math.abs(100)){
			pos.add(fling);
			}else{
				fling.y = 100;
			}
		}
		if(pos.y < enemyCoordinates.y){
			pos.y = enemyCoordinates.y;
		}
		//fling.set(0,0);

		//Gdx.app.log("fling.x", String.valueOf(fling.x)+"fling.y "+String.valueOf(fling.y));
	}

	private void attackCastle() {
		Castle.CURRENT_HEALTH--;
		EntityManager.ATTACK = true; 
	}
}
