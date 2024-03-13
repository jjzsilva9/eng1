package com.jvm.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.jvm.game.components.ColliderComponent;
import com.jvm.game.components.PositionComponent;
import com.jvm.game.components.TilemapComponent;
import com.jvm.game.components.VelocityComponent;

public class CollisionSystem extends EntitySystem {

    private ImmutableArray<Entity> colliderEntities;
    private Entity map;
    private float tileWidth;
    private float tileHeight;
    private TiledMapTileLayer collisionLayer;

    public void addedToEngine(Engine engine) {

        colliderEntities = engine.getEntitiesFor(Family.all(ColliderComponent.class, PositionComponent.class).get());
        map = engine.getEntitiesFor(Family.all(TilemapComponent.class).get()).get(0);
        collisionLayer = map.getComponent(TilemapComponent.class).collisionLayer;
        tileWidth = map.getComponent(TilemapComponent.class).tileWidth;
        tileHeight = map.getComponent(TilemapComponent.class).tileHeight;
    }

    public boolean isColliding(Entity e) {

            float object_x = e.getComponent(PositionComponent.class).x;
            float object_y = e.getComponent(PositionComponent.class).y;
            //System.out.println((int) (object_x / tileWidth));
            //System.out.println((int) (object_y / tileHeight));
            //System.out.println(collisionLayer.getCell((int) (object_x / tileWidth),(int) (object_y / tileHeight)));
            if (collisionLayer.getCell((int) (object_x / tileWidth),(int) (object_y / tileHeight)) != null) {
                TiledMapTile currentCell = collisionLayer.getCell((int) (object_x / tileWidth),(int) (object_y / tileHeight)).getTile();
                if (currentCell.getProperties().containsKey("Collider")) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }

        }

}
