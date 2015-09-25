package remington.productions.defendyourcastle.enemy;

import remington.productions.defendyourcastle.MainGame;
import remington.productions.defendyourcastle.TextureManager;
import remington.productions.defendyourcastle.camera.OrthoCamera;
import remington.productions.defendyourcastle.castle.Castle;
import remington.productions.defendyourcastle.player.Player;
import remington.productions.defendyourcastle.screen.EntityScreen;
import remington.productions.defendyourcastle.screen.GameScreen;
import remington.productions.defendyourcastle.screen.GameScreen.GAMESTATE;
import remington.productions.defendyourcastle.screen.ScreenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EntityManager {

	private Array<EnemyEntity> entities = new Array<EnemyEntity>();
	private Castle castle;
	private final Player player;
	public static Vector2 start, end;
	public static boolean getStartPos, getEndPos;

	// GRAVITY
	private final double GRAVITY = 0.1981;
	private final double KILL_VEL_LEVEL = 9.8;
	private final double MAX_VELOCITY = 150;
	// unit selection
	public int GRABBED;
	public int FLINGME;
	public static boolean ATTACK;
	public double elapsedFlingTime;
	// ABrick animation
//	private Texture brick1, brick2, brick3;
//	private static Vector2 brick1vector, brick2vector, brick3vector, dimension;
//	private static float rotation1, rotation2, rotation3; 

	public EntityManager(int amount, OrthoCamera camera) {
		castle = new Castle();
		player = new Player(new Vector2(0, 0), new Vector2(0, 0), this, camera);
		for (int i = 0; i < amount; i++) {
			float x = MathUtils.random(
					MainGame.WIDTH + TextureManager.ENEMY.getWidth(),
					MainGame.WIDTH + TextureManager.ENEMY.getWidth()
							+ ((MainGame.GAME_LEVEL/2) * 400));
			float y = MathUtils.random(TextureManager.ENEMY.getHeight()
					* (int) 1.5, MainGame.HEIGHT / 4);
			float speed = MathUtils.random(30, 80);
			addEntity(new Enemy(new Vector2(x, y), new Vector2(-speed
					/ (int) 50, 0)));
		}
		player.setEnemySelected(false);
		getStartPos = false;
		getEndPos = false;
		start = new Vector2();
		end = new Vector2();
		GRABBED = -1;
		FLINGME = -1;
		ATTACK = false; 
		elapsedFlingTime = 0;
//		fallingBrickLoad();
	}

	public void update() {
		// gameplay
		//for (EnemyEntity e : entities) {
			for(int i =0; i < entities.size ; i ++){
				if(entities.size > 0){
				entities.get(i).update();
			//e.update();
				}
		}
			if(end.x != 0 && end.y != 0){
				getEnemies().get(GRABBED).fling.add(end);
				end.set(0,0);
				elapsedFlingTime = 0;
			}
		player.update();
		enemySelected();
		gravity();
		// attack();
		removeEnemies();
		isLevelComplete();
		isGameOver();
		// castle
		castle.update();
//		fallingBrickUpdate();
		//Gdx.app.log("enemy selected", String.valueOf(GRABBED));

	}

	public void render(SpriteBatch sb) {
		//for (EnemyEntity e : entities)
		for(int i =0; i < entities.size ; i ++){
			if(entities.size > 0){
				//e.render(sb);
				entities.get(i).render(sb);
			}
			}
		player.render(sb);
		castle.render(sb);
//		fallingBrick(sb);
	}

//	private void fallingBrickLoad(){
//		brick1 = TextureManager.BRICKS;
//		brick2 = TextureManager.BRICKS;
//		brick3 = TextureManager.BRICKS;
//		rotation1 = 1;
//		rotation2 = 3;
//		rotation3 = 1; 
//		dimension = (new Vector2(25,11));
//		brick1vector = (new Vector2(100,100));
//		brick2vector = (new Vector2(140,140));
//		brick3vector = (new Vector2(270,170));
//	}
	

//	private void fallingBrick(SpriteBatch sb) {
//		if(ATTACK){
//		sb.draw(brick1, (float)brick1vector.x, (float)brick1vector.y, (float)5, (float)5, (float)dimension.x, (float)dimension.y, (float)1, (float)1);
//		sb.draw(brick2, (float)brick2vector.x, (float)brick2vector.y, (float)5, (float)5, (float)dimension.x, (float)dimension.y, (float)1, (float)1);
//		sb.draw(brick3, (float)brick3vector.x, (float)brick3vector.y, (float)5, (float)5, (float)dimension.x, (float)dimension.y, (float)1, (float)1);
//		
//		//sb.draw(brick3, brick3vector.x, brick3vector.y, dimension.x, dimension.y);
//
//		Gdx.app.log("BRICK", "ASDJKGHFAJB");
//		}
//		}
//	
//	private  void fallingBrickUpdate(){
//brick1vector.y -=1;
//brick2vector.y -=1;
//brick3vector.y -= 2;
//		if(brick3vector.y < -dimension.y)
//		{
//			brick1vector.y = 480;
//			brick2vector.y = 480;
//			brick3vector.y =100;		
//			}
//		
//	}

	// public static int number = 0;
	private void enemySelected() {
		for (int i = 0; i < getEnemies().size; i++) {
			if (!player.isTouched()) {
				GRABBED = -1;
				getStartPos = false;
				getEndPos = true;
				elapsedFlingTime = 0;
			}
			if (player.isTouched()
					&& getEnemies().get(i).getBounds()
							.overlaps(player.getBounds())) {
				getStartPos=true;
				if (GRABBED == -1) {
					GRABBED = i;
				}
				if (GRABBED != -1 && !getEnemies().get(GRABBED).DEAD && getEnemies().size > 0) {
					enemyGrabbed(getEnemies().get(GRABBED));
				}
			}

		}
	}

	private void enemyGrabbed(Enemy e) {
		// Gdx.app.log("enemy selected", String.valueOf(EnemyEntity.GRABBED));
		e.pos = new Vector2(player.touch);
		e.velocity = 0;
		if (start.x == 0 && start.y == 0) {
			start = new Vector2(e.pos.x, e.pos.y);
			elapsedFlingTime =1;
		}
		if (getEndPos) {
//			Gdx.app.log("enemygabbed", "called");
			elapsedFlingTime+= 9.8;
			Gdx.app.log("elapsed time", String.valueOf(elapsedFlingTime));
			end = new Vector2((e.pos.x - start.x) /675,     //(int)(10/elapsedFlingTime),
					(e.pos.y - start.y) / 675);                 //(int)(10/elapsedFlingTime));
		}
	}

	
	private void gravity() {
		for (Enemy e : getEnemies()) {
			if (e.getPosition().y > e.enemyCoordinates.y) {
				e.velocity = e.velocity + GRAVITY;
				e.pos.y -= (float) e.velocity;
				//enemy starting y pos
				if (e.getPosition().y < e.enemyCoordinates.y + e.velocity) {
					e.pos.y = e.enemyCoordinates.y;
					e.fling.set(0,0);
				}
				if (e.velocity >= MAX_VELOCITY) {
					e.velocity = MAX_VELOCITY;
				}
				if (e.velocity > KILL_VEL_LEVEL
						&& e.getPosition().y == e.enemyCoordinates.y) {
					e.velocity = 0;
					killEnemy(e);
				} 
				// Gdx.app.log("vel", String.valueOf(e.velocity));
			}
		}
	}

	public void addEntity(EnemyEntity entity) {
		entities.add(entity);
	}

	private Array<Enemy> getEnemies() {
		Array<Enemy> ret = new Array<Enemy>();
		for (EnemyEntity e : entities)
			if (e instanceof Enemy)
				ret.add((Enemy) e);
		return ret;
	}

	private void isLevelComplete() {
		// Gdx.app.log("entities left ", String.valueOf(entities.size));
		if (entities.size == 0) {
			MainGame.GAME_LEVEL++;
			GameScreen.gamestate = GAMESTATE.UPGRADE;
			ScreenManager.setScreen(new GameScreen());
		}
	}

	private void isGameOver() {
		// Gdx.app.log("entities left ", String.valueOf(entities.size));
		if (Castle.CURRENT_HEALTH <= 0) {
			for (EnemyEntity e : entities) {
				entities.removeValue(e, false);
			}
			GameScreen.gamestate = GAMESTATE.GAME_OVER;
			ScreenManager.setScreen(new GameScreen());
		}
	}

	private void killEnemy(Enemy e) {
		playDeathSoundEffect();
		e.DEAD = true;
		e.fling.set(0,0);
		e.bloodSpatter();
		e.setDirection(0, 0);
		Castle.KILLS++;
		Castle.GOLD += 10;
	}

	public void removeEnemies() {
		for (EnemyEntity e : entities) {
			if (e.REMOVE_ENEMY && GRABBED <0) {
				entities.removeValue(e, false);
			}
		}
	}


	

	public void playDeathSoundEffect() {
		int rand = MathUtils.random(1, 7);
		switch (rand) {
		case 1:
			EntityScreen.death_one.play();
			break;
		case 2:
			EntityScreen.death_two.play();
			break;
		case 3:
			EntityScreen.death_three.play();
			break;
		case 4:
			EntityScreen.death_four.play();
			break;
		case 5:
			EntityScreen.death_five.play();
			break;
		case 6:
			EntityScreen.death_six.play();
			break;
		case 7:
			EntityScreen.death_seven.play();
			break;
		default:
			break;
		}
	}

	public void dispose() {
		EntityScreen.death_one.stop();
		EntityScreen.death_two.stop();
		EntityScreen.death_three.stop();
		EntityScreen.death_four.stop();
		EntityScreen.death_five.stop();
		EntityScreen.death_six.stop();
		EntityScreen.death_seven.stop();
		EntityScreen.death_one.dispose();
		EntityScreen.death_two.dispose();
		EntityScreen.death_three.dispose();
		EntityScreen.death_four.dispose();
		EntityScreen.death_five.dispose();
		EntityScreen.death_six.dispose();
		EntityScreen.death_seven.dispose();
		

	}

}
