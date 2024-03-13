package com.jvm.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.jvm.game.components.ColliderComponent;
import com.jvm.game.components.PositionComponent;
import com.jvm.game.components.TilemapComponent;
import com.jvm.game.components.VelocityComponent;

public class CollisionSystem extends EntitySystem {

    private ImmutableArray<Entity> colliderEntities;
    private Entity map;
    private TiledMapTileLayer collisionLayer;

    public void addedToEngine(Engine engine) {
        colliderEntities = engine.getEntitiesFor(Family.all(ColliderComponent.class, PositionComponent.class).get());
        map = engine.getEntitiesFor(Family.all(TilemapComponent.class).get()).get(0);
        collisionLayer = map.getComponent(TilemapComponent.class).collisionLayer;
    }


}
