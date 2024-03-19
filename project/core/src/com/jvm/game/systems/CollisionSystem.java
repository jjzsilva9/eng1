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

/**
 * Mangages collisions between entities with colliders.
 *
 */
public class CollisionSystem extends EntitySystem {

    //An array of all the entities which have a ColliderComponent
    private ImmutableArray<Entity> colliderEntities;
    private Entity map;
    private float tileWidth;
    private float tileHeight;
    private TiledMapTileLayer collisionLayer;

    //Indicates how far the player can be standing from a building while still being able to interact with it.
    private int OFFSET;

    private String[] BUILDINGS;

    /**
     * Initializer when added to engine.
     *
     * Finds all entities with a Collider and Position and the map
     * @param engine The Ashley engine it is added to
     */
    public void addedToEngine(Engine engine) {
        //Find all the appropriate entities
        colliderEntities = engine.getEntitiesFor(Family.all(ColliderComponent.class, PositionComponent.class).get());
        map = engine.getEntitiesFor(Family.all(TilemapComponent.class).get()).get(0);
        collisionLayer = map.getComponent(TilemapComponent.class).collisionLayer;
        tileWidth = map.getComponent(TilemapComponent.class).tileWidth;
        tileHeight = map.getComponent(TilemapComponent.class).tileHeight;
        OFFSET = 35;
    }


    /**
     * Checks collisions between an entity and tiles on the collision layer
     * @param e The entity we are checking collision for
     * @return Boolean If the entity is colliding with a tile on the collision layer
     */
    public boolean isColliding(Entity e) {

            //The x position value of the entity
            float entity_x = e.getComponent(PositionComponent.class).x;
            //The y position value of the entity
            float entity_y = e.getComponent(PositionComponent.class).y;
            //The width of the entity's collision box
            float entity_width  = e.getComponent(ColliderComponent.class).width;
            //The height of the entity's collision box
            float entity_height  = e.getComponent(ColliderComponent.class).height;

            //True if the bottom left corner of the entity is in a cell belonging to the collision layer.
            Boolean bottom_left = collisionLayer.getCell((int) (entity_x / tileWidth),(int) (entity_y / tileHeight)) != null;
            //True if the bottom right corner of the entity is in a cell belonging to the collision layer.
            Boolean bottom_right = collisionLayer.getCell((int) ((entity_x + entity_width) / tileWidth),(int) (entity_y / tileHeight)) != null;
            //True if the top left corner of the entity is in a cell belonging to the collision layer.
            Boolean top_left = collisionLayer.getCell((int) ((entity_x) / tileWidth),(int) ((entity_y + entity_height) / tileHeight)) != null;
            //True if the top right corner of the entity is in a cell belonging to the collision layer.
            Boolean top_right = collisionLayer.getCell((int) ((entity_x + entity_width) / tileWidth),(int) ((entity_y + entity_height) / tileHeight)) != null;

            //Return true if the entity is colliding with anything in any of its corners.
            if (top_left || top_right || bottom_left || bottom_right) {
                return true;
            }
            //Return false if there are no collisions.
            else {
                return false;
            }

    }

    /**
     * Checks collisions between an entity and buildings
     * @param e The entity we are checking collision for
     * @return Boolean If the entity is colliding with a building on the collision layer
     */
    public String buildingColliding(Entity e) {
        //The x position value of the entity
        float entity_x = e.getComponent(PositionComponent.class).x;
        //The y position value of the entity
        float entity_y = e.getComponent(PositionComponent.class).y;

        //Gets the cell to the left of the player (plus the OFFSET)
        TiledMapTileLayer.Cell left = collisionLayer.getCell((int) ((entity_x - OFFSET) / tileWidth), (int) (entity_y / tileHeight));
        //Gets the cell to the right of the player (plus the OFFSET)
        TiledMapTileLayer.Cell right = collisionLayer.getCell((int) ((entity_x + OFFSET) / tileWidth), (int) (entity_y / tileHeight));
        //Gets the cell above the player (plus the OFFSET)
        TiledMapTileLayer.Cell up = collisionLayer.getCell((int) (entity_x / tileWidth), (int) ((entity_y + OFFSET) / tileHeight));
        //Gets the cell below the player (plus the OFFSET)
        TiledMapTileLayer.Cell down = collisionLayer.getCell((int) (entity_x / tileWidth), (int) ((entity_y - OFFSET) / tileHeight));

        //The current cell is set to null as its default
        TiledMapTile collisionCell = null;

        //Checks if the cells are null first to avoid exceptions
        //Aims to select an appropriate cell for which the properties can be checked
        if (left != null) {
            collisionCell = left.getTile();
        } else if (right != null) {
            collisionCell = right.getTile();
        } else if (up != null) {
            collisionCell = up.getTile();
        } else if (down != null) {
            collisionCell = down.getTile();
        } else {
            collisionCell = null;
        }

        //Returns the name of the building you are colliding with (if you are colliding with one)
        //Each building tile contains a property which identitfies itself
        if (collisionCell != null){

            if (collisionCell.getProperties().containsKey("CS")) {
                return "CS";
            } else if (collisionCell.getProperties().containsKey("Piazza")) {
                return "Piazza";
            } else if (collisionCell.getProperties().containsKey("Constantine")) {
                return "Constantine";
            } else if (collisionCell.getProperties().containsKey("Bus")) {
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

