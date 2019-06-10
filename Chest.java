package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Chest extends InteractiveTileObject {

    public Chest(GameScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MyGdxGame.CHE_BIT);

    }
    @Override
    public void onHeadHit() {

        //Gdx.app.log("Coin","Hit a coin");
        setCategoryFilter(MyGdxGame.DES_BIT);
        getCell().setTile(null);

        this.screen.mainC.isWin = true;

    }
}
