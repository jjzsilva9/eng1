package com.jvm.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.ashley.utils.ImmutableArray;


//MovementSystem for player movement
public class MovementSystem  extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public MovementSystem() {}

    public void addedToEngine(Engine engine) {
        //Finds all entities that need movement handling
        //Should be just player
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void update(float deltaTime) {
        for (Entity player: entities) {


            PositionComponent position = player.getComponent(PositionComponent.class);
            VelocityComponent velocity = player.getComponent(VelocityComponent.class);

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                position.x -= velocity.x * deltaTime;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                position.x += velocity.x * deltaTime;
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                position.y += velocity.y * deltaTime;
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                position.y -= velocity.y * deltaTime;
            }

        }

    }


}
