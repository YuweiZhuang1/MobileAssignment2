package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOver implements Screen {

    Viewport viewport;
    Stage stage;
    private SpriteBatch batch;
    private Skin skin;

    BitmapFont font;
    MyGdxGame game;


    public GameOver(MyGdxGame game){

        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        stage = new Stage();
        batch = new SpriteBatch();


        this.game = game;

        viewport = new FitViewport(400,208,new OrthographicCamera());
        stage = new Stage( viewport, batch);

        // Font color
        font = new BitmapFont();
        font.getData().setScale(2.5f,2.5f);
        font.setColor(Color.RED);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        batch.begin();
        font.draw(batch,"GAME OVER",Gdx.graphics.getWidth()/2 - 50,Gdx.graphics.getHeight() * 0.6f);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
