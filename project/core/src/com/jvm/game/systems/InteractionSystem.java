package com.jvm.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jvm.game.Counters;
import com.jvm.game.GameScreen;
import com.jvm.game.components.PositionComponent;
import com.jvm.game.components.VelocityComponent;

public class InteractionSystem extends EntitySystem {

    private CollisionSystem collisionSystem;
    private ImmutableArray<Entity> entities;

    private Counters counters;

    public boolean endGame = false;

    public InteractionSystem(Counters c) {
        counters = c;
    }

    public void addedToEngine(Engine engine) {

        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());

        collisionSystem = engine.getSystem(CollisionSystem.class);
    }

    public void update(float deltaTime){
    //|| Gdx.input.isKeyPressed(Input.Keys.ENTER)
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) ) {
            for (Entity e: entities) {
                String building = collisionSystem.buildingColliding(e);

                if (building == "CS") {counters.increaseStudyCount();}
                else if (building == "Piazza") {counters.increaseEatCount();}
                else if (building == "Bus") {counters.increaseActivityCount();}
                else if (building == "Constantine") {
                    counters.increaseDayCount();
                    if (counters.getDay() >= 8) {
                        endGame = true;
                    }
                }

            }
        }


    }
}
