package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class WorldCreator {

    public WorldCreator(GameScreen screen){

        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        // collision detection
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // create ground body from tile map
        // keep this!
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2  / MyGdxGame.PPM, rect.getY() + rect.getHeight() /2/ MyGdxGame.PPM );

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 / MyGdxGame.PPM,rect.getHeight()/2 / MyGdxGame.PPM) ;


            fdef.filter.categoryBits = MyGdxGame.DEA_BIT;
            fdef.shape = shape;

            body.createFixture(fdef);


        }

        //todo get(2) is ground, get(5) is brick, get(5) is sky background, get(3) is pike, get(6)????


        // create brick body from tile map
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            new Brick(screen,rect);
        }


        // create coin body from tile map

        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){

            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(screen,rect);

        }

        // create pike body from tile map

        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            new Pike(screen,rect);
        }



    }
}
