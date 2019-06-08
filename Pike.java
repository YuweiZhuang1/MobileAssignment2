package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Pike extends InteractiveTileObject  {
    public Pike(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Pike","Hit a pike");
    }
}
