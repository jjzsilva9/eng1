package com.jvm.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jvm.game.Counters;
import com.jvm.game.components.PositionComponent;
import com.jvm.game.components.VelocityComponent;

public class InteractionSystem extends EntitySystem {

    private CollisionSystem collisionSystem;
    private ImmutableArray<Entity> entities;

    private Counters counters;

    public InteractionSystem(Counters c) {
        counters = c;
    }

    public void addedToEngine(Engine engine) {

        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());

        collisionSystem = engine.getSystem(CollisionSystem.class);
    }

    public void update(float deltaTime){

        if (Gdx.input.isKeyPressed(Input.Keys.E) || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            for (Entity e: entities) {
                String building = collisionSystem.buildingColliding(e);
                switch (building){
                    case "CS":
                        counters.increaseStudyCount();
                    case "Piazza":
                        counters.increaseEatCount();
                    case "Bus":
                        counters.increaseActivityCount();
                    case "Constantine":
                        counters.increaseDayCount();
                }

            }
        }


    }
}
