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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameScreen implements Screen {
    MyGdxGame game; // Note it’s "MyGdxGame" not "Game"
    // constructor to keep a reference to the main Game class

    TextureAtlas atlas;
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

     Hud hud;

    float cameraX;
    float cameraY;


    Texture character;

    OrthographicCamera camera;
    Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    World world;
    Box2DDebugRenderer b2dr;

    Character mainC;

    Enemy s1;

    public GameScreen(MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        gamePort = new FitViewport(400,208,camera);
        gamePort.setScreenSize(400,208);
        gamePort.setCamera(camera);

        atlas = new TextureAtlas("Mario_and_Enemies.pack");


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("game map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MyGdxGame.PPM);

        camera.position.set(gamePort.getScreenWidth() / 2 / MyGdxGame.PPM , gamePort.getScreenHeight() /2 / MyGdxGame.PPM ,0);

        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();

        new WorldCreator(this);


        mainC = new Character(world,this);

        world.setContactListener(new WorldContactListener());


        s1 = new Enemy(50,mainC.getY());
    }
    public void create() {
        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        stage = new Stage();
        batch = new SpriteBatch();

        hud = new Hud(batch);


    }

    public TextureAtlas getAtlas(){
        return this.atlas;
    }

    public void update(float f){
        handleInput(f);

        world.step(1/10f,6,2);

        mainC.update(f,batch);
        hud.update(f);
        camera.position.x = mainC.b2body.getPosition().x;
        camera.update();
        renderer.setView(camera);

    }

    private void handleInput(float f) {

        // hit the screen
        /*if (Gdx.input.isTouched()){
            camera.position.x += 100 * f;
        }*/

        // for jumpping
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            mainC.b2body.applyLinearImpulse(new Vector2(0,160f), mainC.b2body.getWorldCenter(),true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && mainC.b2body.getLinearVelocity().x >= -60){
            mainC.b2body.applyLinearImpulse(new Vector2(-30f,0),mainC.b2body.getWorldCenter(),true);
        }


        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mainC.b2body.getLinearVelocity().x <= 60){
           // move right code, always follow the player
            mainC.b2body.applyLinearImpulse(new Vector2(30f,0),mainC.b2body.getWorldCenter(),true);
        }


    }

    public void render(float f) {
        update(f);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );


        renderer.render();

        //reder map
        b2dr.render(world,camera.combined);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        mainC.draw(batch);

        batch.end();


        batch.setProjectionMatrix(hud.stage.getCamera().combined);


        hud.stage.draw();

        batch.begin();
        s1.update(batch);
        batch.end();

    }



    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();

    }
    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
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