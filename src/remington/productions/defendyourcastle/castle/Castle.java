package remington.productions.defendyourcastle.castle;

import remington.productions.defendyourcastle.MainGame;

import com.badlogic.gdx.math.Vector2;

public class Castle extends CastleEntity{

	public static double MAX_HEALTH = 1500;
	public static double CURRENT_HEALTH = 1500;
	public static double HEALTH_PERCENTAGE;
	public static int GOLD =2000 ;
	public static int KILLS = 0; 

	public Castle(){
		super(new Vector2(20,420), new Vector2(300,50));
		HEALTH_PERCENTAGE = ((Castle.CURRENT_HEALTH/Castle.MAX_HEALTH)*100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		MainGame.KILLCOUNT = KILLS;
		HEALTH_PERCENTAGE = ((Castle.CURRENT_HEALTH/Castle.MAX_HEALTH)*100);
//		Gdx.app.log("castle health", String.valueOf((double)HEALTH_PERCENTAGE/100));
	}
	
	
}
