package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    MyGdxGame game; // Note it’s "MyGdxGame" not "Game"
    // constructor to keep a reference to the main Game class

    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

    private Hud hud;

    float cameraX;
    float cameraY;

    Texture map;
    Texture character;

    OrthographicCamera camera;
    Viewport gamePort;



    public GameScreen(MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        gamePort = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);

    }
    public void create() {
        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        stage = new Stage();
        batch = new SpriteBatch();
        hud = new Hud(batch);

        cameraX = 0;
        cameraY = 0;


        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera.position.set(cameraX,cameraY,0);


        //batch.setProjectionMatrix(camera.combined);
        map = new Texture("1-1.png");
        character = new Texture("badlogic.jpg");

        /*
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("particles/star.p"),
                Gdx.files.internal("particles/"));
        */


    }
    public void render(float f) {

        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }
    @Override
    public void dispose() { }
    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void show() {

        create(); }
    @Override
    public void hide() {

    }
}