package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        //Gdx.app.log("Beg","Begin contact");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            //if fixA.getuserData == "head" fixA is head else fixB is head
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;

            // same
            Fixture object = head == fixA ? fixB : fixA;

            Gdx.app.log("Beg","data is: " + object.getUserData());

            if(object.getUserData() != null && object.getUserData().getClass().equals(Coin.class)){
                Gdx.app.log("Coin","cccccc is: " + object.getUserData());

            }
            // if object class is InteractiveTileObject class
            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){

                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("Beg","End contact");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
