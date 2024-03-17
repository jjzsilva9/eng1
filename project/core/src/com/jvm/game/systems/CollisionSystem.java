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

import java.awt.*;

public class CollisionSystem extends EntitySystem {

    private ImmutableArray<Entity> colliderEntities;
    private Entity map;
    private float tileWidth;
    private float tileHeight;
    private TiledMapTileLayer collisionLayer;

    private String[] BUILDINGS;

    public void addedToEngine(Engine engine) {
        colliderEntities = engine.getEntitiesFor(Family.all(ColliderComponent.class, PositionComponent.class).get());
        map = engine.getEntitiesFor(Family.all(TilemapComponent.class).get()).get(0);
        collisionLayer = map.getComponent(TilemapComponent.class).collisionLayer;
        tileWidth = map.getComponent(TilemapComponent.class).tileWidth;
        tileHeight = map.getComponent(TilemapComponent.class).tileHeight;
        BUILDINGS = new String[] {"CS", "Piazza", "Bus", "Constantine"};
    }

    //Returns true if the entity is colliding with a tile on the collision layer.
    public boolean isColliding(Entity e) {

            //The x valye
            float entity_x = e.getComponent(PositionComponent.class).x;
            float entity_y = e.getComponent(PositionComponent.class).y;
            if (collisionLayer.getCell((int) (entity_x / tileWidth),(int) (entity_y / tileHeight)) != null) {
                return true;
            }
            else {
                return false;
            }

    }

    public String buildingColliding(Entity e) {
        //The x valye
        float entity_x = e.getComponent(PositionComponent.class).x;
        float entity_y = e.getComponent(PositionComponent.class).y;
        if (collisionLayer.getCell((int) (entity_x / tileWidth),(int) (entity_y / tileHeight)) != null) {
            TiledMapTile currentCell = collisionLayer.getCell((int) (entity_x / tileWidth),(int) (entity_y / tileHeight)).getTile();

            if (currentCell.getProperties().containsKey("CS")) {
                return "CS";
            } else if (currentCell.getProperties().containsKey("Piazza")) {
                return "Piazza";
            } else if (currentCell.getProperties().containsKey("Constantine")) {
                return "Constantine";
            } else if (currentCell.getProperties().containsKey("Bus")) {
                return "Bus";
            }
            else {
                return "";
            }

        }
        else {
            return "";
        }
    }

}
