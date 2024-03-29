package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.math.Rectangle;

public class Brick extends InteractiveTileObject{

    public Brick(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);

        setCategoryFilter(MyGdxGame.BRIC_BIT);




    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick","Hit a brick");
    }
}
