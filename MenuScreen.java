package com.mygdx.game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {
    MyGdxGame game; // Note it’s "MyGdxGame" not "Game"
    // constructor to keep a reference to the main Game class

    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

    Texture bg;


    public MenuScreen(MyGdxGame game){
        this.game = game;
    }
    public void create() {

        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        stage = new Stage();


        final TextButton button = new TextButton("PLAY", skin, "default");
        button.setWidth(Gdx.graphics.getWidth() * 0.07f);
        button.setHeight(Gdx.graphics.getHeight() * 0.05f);
        button.setPosition(Gdx.graphics.getWidth() * 0.16f, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight() * 0.3f);

        button.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(game.gameScreen);

            }
        });
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);


        final TextButton quit = new TextButton("QUIT", skin, "default");
        quit.setWidth(Gdx.graphics.getWidth() * 0.07f);
        quit.setHeight(Gdx.graphics.getHeight() * 0.05f);
        quit.setPosition(Gdx.graphics.getWidth() * 0.16f, Gdx.graphics.getHeight()/2 -   Gdx.graphics.getHeight() * 0.4f);

        quit.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                System.exit(0);

            }
        });
        stage.addActor(quit);
        Gdx.input.setInputProcessor(stage);

        bg = new Texture("bg.jpg");

        game.gameScreen.mainC.isAlive = true;
        game.gameScreen.mainC.isWin = false;
        game.gameScreen.mainC.setPosition(0,50);
    }
    public void render(float f) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        batch.begin();
        batch.draw(bg,-5,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();


        batch.begin();
        stage.draw();
        batch.end();



    }
    @Override
    public void dispose() { }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void show() {
        Gdx.app.log("MenuScreen: ","menuScreen show called");
        create(); }
    @Override
    public void hide() {
        Gdx.app.log("MenuScreen: ","menuScreen hide called");
    }
}