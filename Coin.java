package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.math.Rectangle;

public class Coin extends InteractiveTileObject {
    public Coin(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MyGdxGame.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin","Hit a coin");

        setCategoryFilter(MyGdxGame.DESTORY_BIT);

        if(getCell()!=null) {
            if (getCell().getTile() != null) {
                this.screen.game.coinSound.play();
            }


            getCell().setTile(null);
        }

        this.screen.hud.increaseScore();
    }


}
