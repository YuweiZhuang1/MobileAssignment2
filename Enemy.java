package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {

    public float x;
    public float y;
    public boolean isAlive;

    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    Texture walkSheet;
    SpriteBatch spriteBatch;
    float stateTime;

    TextureRegion[] walkFrames;


    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
        this.isAlive = true;


        // Load the sprite sheet as a Texture
        walkSheet = new Texture("spritesheet_caveman.png");


        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / 4,
                walkSheet.getHeight() / 4);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        walkFrames = new TextureRegion[16];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                walkFrames[index++] = tmp[i][j];

            }

            // Initialize the Animation with the frame interval and array of frames
            walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);

            // Instantiate a SpriteBatch for drawing and reset the elapsed animation
            // time to 0
            stateTime = 0f;


        }
    }

    public void update(SpriteBatch batch){

        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        TextureRegion currentFrame;
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        // Get current frame of animation for the current stateTime



        //batch.draw(currentFrame,20,20);
    }

}
