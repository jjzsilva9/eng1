package com.jvm.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jvm.game.components.PositionComponent;
import com.jvm.game.components.VelocityComponent;


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

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
                position.x -= velocity.x * deltaTime;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                position.x += velocity.x * deltaTime;
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                position.y += velocity.y * deltaTime;
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                position.y -= velocity.y * deltaTime;
            }

        }

    }


}
