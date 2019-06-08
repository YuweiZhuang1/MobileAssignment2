package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Rectangle;
public abstract class InteractiveTileObject {
    World world;
    TiledMap map;
    TiledMapTile title;
    Rectangle bounds;
    Body body;
    GameScreen screen;

    Fixture fixture;

    public InteractiveTileObject(GameScreen screen, Rectangle bounds){
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        this.screen =screen;

        BodyDef bdef = new BodyDef();


        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(bounds.getX() + bounds.getWidth() / 2  / MyGdxGame.PPM, bounds.getY() + bounds.getHeight() /2/ MyGdxGame.PPM );

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth()/2 / MyGdxGame.PPM,bounds.getHeight()/2 / MyGdxGame.PPM) ;
        fdef.shape = shape;
        body.createFixture(fdef);



        fixture = body.createFixture(fdef);



    }


    public abstract void onHeadHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits=filterBit;
        fixture.setFilterData(filter);
    }


    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(1);
        return  layer.getCell((int)(body.getPosition().x / 16),
                (int)(body.getPosition().y / 16));
    }
}
