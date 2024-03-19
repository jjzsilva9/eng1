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

/**
 * Manages interactions between the player and buildings
 *
 * Reads key presses near buildings and calls the correct function
 */
public class InteractionSystem extends EntitySystem {

    private CollisionSystem collisionSystem;
    private ImmutableArray<Entity> entities;

    private Counters counters;

    public boolean endGame = false;

    /**
     * Initializer
     * Sets counters for the system
     * @param c Counters to be set
     */
    public InteractionSystem(Counters c) {
        counters = c;
    }

    /**
     * Gets all entities with a Position, Velocity Component and gets the CollisionSystem
     * @param engine The Ashley engine it is added to
     */
    public void addedToEngine(Engine engine) {

        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());

        collisionSystem = engine.getSystem(CollisionSystem.class);
    }

    /**
     * Update method for the system
     *
     * Sees which buildings are being collided with while E is pressed
     * @param deltaTime Time since the last frame
     */
    public void update(float deltaTime){
        //If the key "e" is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) ) {
            //For each entity with position and velocity (Player)
            for (Entity e: entities) {
                //Finds the name of the building it is colliding with (empty string if not colliding)
                String building = collisionSystem.buildingColliding(e);

                //Call the correct counter function depending on the building
                if (building == "CS") {
                    counters.increaseStudyCount();}
                else if (building == "Piazza") {
                    counters.increaseEatCount();
                }
                else if (building == "Bus") {
                    counters.increaseActivityCount();
                }
                else if (building == "Constantine") {
                    //End the game if days counter goes above 7
                    counters.increaseDayCount();
                    if (counters.getDay() >= 8) {
                        endGame = true;
                    }
                }

            }
        }


    }
}
