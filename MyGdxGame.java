package com.mygdx.game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class MyGdxGame extends Game implements ApplicationListener {
	// The class with the menu
	public static MenuScreen menuScreen;
	// The class with the game
	public static GameScreen gameScreen;

    // The class with the game
    public static GameOver gameoverScreen;

    public static WinScreen winScreen;


	public static final float PPM = 1;

    public static final short DEA_BIT = 1;
	public static final short CHAR_BIT = 2;
    public static final short BRIC_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short PIKE_BIT = 32;
    public static final short DESTORY_BIT = 16;
	public static final short CHE_BIT = 64;

	//https://freesound.org/people/jalastram/sounds/458059/ fail music
	public Sound failSound;
	//https://freesound.org/people/ProjectsU012/sounds/341695/ coin
	public Music coinSound;

	//https://freesound.org/people/krytosss/sounds/167388/
	public Music bgm;

	@Override
	public void create() {
		Gdx.app.log("MyGdxGame: "," create");
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
        gameoverScreen = new GameOver(this);
        winScreen = new WinScreen(this);
		Gdx.app.log("MyGdxGame: ","about to change screen to menuScreen");
		// Change screens to the menu
		setScreen(menuScreen);
		Gdx.app.log("MyGdxGame: ","changed screen to menuScreen");


		coinSound = Gdx.audio.newMusic(Gdx.files.internal("coin.ogg"));


		failSound = Gdx.audio.newSound(Gdx.files.internal("fail.ogg"));


		bgm = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
		bgm.setLooping(true);

	}
	@Override
	public void dispose() {super.dispose();}
	@Override
	// this method calls the super class render
	// which in turn calls the render of the actual screen being used
	public void render() {super.render();}
	@Override
	public void resize(int width, int height) { super.resize(width, height);}
	@Override
	public void pause() {
		super.pause();
	}
	@Override
	public void resume() {
		super.resume();
	}
}