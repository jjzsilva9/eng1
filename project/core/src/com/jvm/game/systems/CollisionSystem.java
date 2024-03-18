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
import java.util.Iterator;

public class CollisionSystem extends EntitySystem {

    private ImmutableArray<Entity> colliderEntities;
    private Entity map;
    private float tileWidth;
    private float tileHeight;
    private TiledMapTileLayer collisionLayer;

    private int OFFSET;

    private String[] BUILDINGS;

    public void addedToEngine(Engine engine) {
        colliderEntities = engine.getEntitiesFor(Family.all(ColliderComponent.class, PositionComponent.class).get());
        map = engine.getEntitiesFor(Family.all(TilemapComponent.class).get()).get(0);
        collisionLayer = map.getComponent(TilemapComponent.class).collisionLayer;
        tileWidth = map.getComponent(TilemapComponent.class).tileWidth;
        tileHeight = map.getComponent(TilemapComponent.class).tileHeight;
        OFFSET = 35;
    }

    //Returns true if the entity is colliding with a tile on the collision layer.
    public boolean isColliding(Entity e) {

            //The x valye
            float entity_x = e.getComponent(PositionComponent.class).x;
            float entity_y = e.getComponent(PositionComponent.class).y;
            float entity_width  = e.getComponent(ColliderComponent.class).width;
            float entity_height  = e.getComponent(ColliderComponent.class).height;

            Boolean bottom_left = collisionLayer.getCell((int) (entity_x / tileWidth),(int) (entity_y / tileHeight)) != null;
            Boolean bottom_right = collisionLayer.getCell((int) ((entity_x + entity_width) / tileWidth),(int) (entity_y / tileHeight)) != null;
            Boolean top_left = collisionLayer.getCell((int) ((entity_x) / tileWidth),(int) ((entity_y + entity_height) / tileHeight)) != null;
            Boolean top_right = collisionLayer.getCell((int) ((entity_x + entity_width) / tileWidth),(int) ((entity_y + entity_height) / tileHeight)) != null;

            if (top_left || top_right || bottom_left || bottom_right) {
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
        TiledMapTileLayer.Cell left = collisionLayer.getCell((int) ((entity_x - OFFSET) / tileWidth), (int) (entity_y / tileHeight));
        TiledMapTileLayer.Cell right = collisionLayer.getCell((int) ((entity_x + OFFSET) / tileWidth), (int) (entity_y / tileHeight));
        TiledMapTileLayer.Cell up = collisionLayer.getCell((int) (entity_x / tileWidth), (int) ((entity_y + OFFSET) / tileHeight));
        TiledMapTileLayer.Cell down = collisionLayer.getCell((int) (entity_x / tileWidth), (int) ((entity_y - OFFSET) / tileHeight));

        TiledMapTile currentCell = null;

        if (left != null) {
            currentCell = left.getTile();
        } else if (right != null) {
            currentCell = right.getTile();
        } else if (up != null) {
            currentCell = up.getTile();
        } else if (down != null) {
            currentCell = down.getTile();
        }

        if (currentCell != null){
            if (currentCell.getProperties().containsKey("CS")) {
                return "CS";
            } else if (currentCell.getProperties().containsKey("Piazza")) {
                return "Piazza";
            } else if (currentCell.getProperties().containsKey("Constantine")) {
                return "Constantine";
            } else if (currentCell.getProperties().containsKey("Bus")) {
                return "Bus";
            } else {
                return "";
            }
        }
        else {
            return "";
        }

    }

}

