package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Character extends Sprite {

    public enum State{FALLING,JUMPING,STANDING,RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    TextureRegion marioStand;


    Animation chaRun;
    Animation chaJump;

    float stateTimer;
    boolean runningRight;


    private static final int FRAME_COLS = 4, FRAME_ROWS = 8;

    // Objects used
    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    Texture walkSheet;
    SpriteBatch spriteBatch;
    float stateTime;

    TextureRegion[] walkFrames;



    public Character(World world, GameScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;


        // Load the sprite sheet as a Texture
        walkSheet = new Texture("stella_walk.png");


        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        walkFrames = new TextureRegion[4];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                if(i == 2)
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        stateTime = 0f;





        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        TextureRegion[] frames = new TextureRegion[7];

        for(int i = 1; i < 4; i++){
          frames[i] = (new TextureRegion(getTexture(),i*16,0,16,16));



        }

        chaRun = new Animation(0.1f,frames);



        for(int i = 4; i < 6; i++){
            frames[i] = (new TextureRegion(getTexture(),i*16,10,16,16));;
        }

        chaJump = new Animation(0.1f,frames);

        defineCharacter();

        // get character texture
        marioStand = new TextureRegion(getTexture(),0,10,16,16);
        setBounds(0,10,16,16);
        setRegion(marioStand);



    }

    public void update(float f,SpriteBatch batch){

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        stateTime += f; // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame;


        //spriteBatch.begin();
        //spriteBatch.draw(currentFrame, b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2); // Draw current frame at (50, 50)
        //spriteBatch.end();

        // my animation for character
        currentState = getState();

        if(currentState == State.JUMPING)
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        else if(currentState == State.RUNNING)
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        else{
            currentFrame = walkFrames[0];

        }

        // flip x when left
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !currentFrame.isFlipX() ){
            currentFrame.flip(true,false);
            runningRight = false;
        }else if( (b2body.getLinearVelocity().x > 0 || runningRight) && currentFrame.isFlipX()   ){
            currentFrame.flip(true,false);
            runningRight = true;
        }


        setRegion(currentFrame);
        //setRegion(getFrame(f));
    }

    private TextureRegion getFrame(float f) {
        currentState = getState();

        TextureRegion region;


            if(currentState == State.JUMPING)
                region = (TextureRegion) chaJump.getKeyFrame(stateTimer);

            else if(currentState == State.RUNNING)
                region = (TextureRegion) chaRun.getKeyFrame(stateTimer,true);

            else{
                region = marioStand;

        }

        // flip x when left
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX() ){
            region.flip(true,false);
            runningRight = false;
        }else if( (b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()   ){
            region.flip(true,false);
            runningRight = true;
        }

        // if currentState == previousstate, statetimer += f else = 0
        stateTimer = currentState == previousState ? stateTimer + f : 0;
        previousState = currentState;
        return region;

    }

    private State getState() {
        if(b2body.getLinearVelocity().y > 0 || b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING){
            return State.JUMPING;
        }
        else if(b2body.getLinearVelocity().y < 0){
            return State.FALLING;
        }else if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }else{
            return State.STANDING;
        }

    }


    // create bounder for main character
    private void defineCharacter() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MyGdxGame.PPM,32 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MyGdxGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2,7), new Vector2(2,7));
        fdef.shape = head;
        fdef.isSensor = true;

        // in future head is mario head
        b2body.createFixture(fdef).setUserData("head");
    }
}
