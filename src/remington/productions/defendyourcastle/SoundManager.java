package remington.productions.defendyourcastle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundManager{

	public static  Music GAME_MUSIC_ONE; 
	public static  Music GAME_MUSIC_TWO;
	public static  Music GAME_MUSIC_THREE;
	public static  Music GAME_MUSIC_FOUR; 

	public static Music DEATH_ONE; 
	public static Music DEATH_TWO; 
	public static Music DEATH_THREE;
	public static Music DEATH_FOUR;
	public static Music DEATH_FIVE; 
	public static Music DEATH_SIX; 
	public static Music DEATH_SEVEN;
	public static Music REPAIR; 
	public static Music UPGRADE;
	public static Music DREAM;
	public static Music INTRO;
	public static Music GAME_OVER;
	public static Music SPARTA;

	public SoundManager(){};
	
	public static void load(){
		 GAME_MUSIC_ONE = Gdx.audio.newMusic(Gdx.files.internal("hone.mp3"));
		 GAME_MUSIC_TWO = Gdx.audio.newMusic(Gdx.files.internal("htwo.mp3"));
		 GAME_MUSIC_THREE = Gdx.audio.newMusic(Gdx.files.internal("hthree.mp3"));
		 GAME_MUSIC_FOUR = Gdx.audio.newMusic(Gdx.files.internal("hfour.mp3"));

		 DEATH_ONE = Gdx.audio.newMusic(Gdx.files.internal("death_one.mp3"));
		 DEATH_TWO = Gdx.audio.newMusic(Gdx.files.internal("death_two.mp3"));
		 DEATH_THREE = Gdx.audio.newMusic(Gdx.files.internal("death_three.mp3"));
		 DEATH_FOUR = Gdx.audio.newMusic(Gdx.files.internal("death_four.mp3"));
		 DEATH_FIVE = Gdx.audio.newMusic(Gdx.files.internal("death_five.mp3"));
		 DEATH_SIX = Gdx.audio.newMusic(Gdx.files.internal("death_six.mp3"));
		 DEATH_SEVEN = Gdx.audio.newMusic(Gdx.files.internal("death_seven.mp3"));
		 REPAIR = Gdx.audio.newMusic(Gdx.files.internal("repair.mp3"));
		 UPGRADE = Gdx.audio.newMusic(Gdx.files.internal("upgrade.mp3"));
		 DREAM = Gdx.audio.newMusic(Gdx.files.internal("dream.mp3"));
		 INTRO = Gdx.audio.newMusic(Gdx.files.internal("rogue.mp3"));
		 GAME_OVER = Gdx.audio.newMusic(Gdx.files.internal("gameover.mp3"));
		 SPARTA = Gdx.audio.newMusic(Gdx.files.internal("sparta.mp3"));

	}
}
