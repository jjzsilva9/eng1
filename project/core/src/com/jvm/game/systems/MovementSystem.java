package com.jvm.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jvm.game.GameController;
import com.jvm.game.GameScreen;
import com.jvm.game.components.ColliderComponent;
import com.jvm.game.components.PositionComponent;
import com.jvm.game.components.TextureComponent;
import com.jvm.game.components.VelocityComponent;
import com.jvm.game.entities.Player;


/**
 * Manages movement for the Player entity
 *
 * Reads in movement and updates the animation system
 */
public class MovementSystem  extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private AnimationSystem animationSystem;

    private CollisionSystem collisionSystem;

    /**
     * Initializer
     */
    public MovementSystem() {}

    /**
     * Gets all entities with a position, velocity component (player)
     * Also gets references to the collision and animation system
     * @param engine The Ashley engine it is added to
     */
    public void addedToEngine(Engine engine) {
        //Finds all entities that need movement handling
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());

        collisionSystem = engine.getSystem(CollisionSystem.class);
        animationSystem = engine.getSystem(AnimationSystem.class);
    }

    /**
     * Update method
     *
     * Update position based on velocity and collision resolution
     * Updates the animation system
     * @param deltaTime Time since the last frame
     */
    public void update(float deltaTime) {
        for (Entity player: entities) {

            PositionComponent position = player.getComponent(PositionComponent.class);
            VelocityComponent velocity = player.getComponent(VelocityComponent.class);
            TextureComponent texture = player.getComponent(TextureComponent.class);

            float old_x = position.x;
            float old_y = position.y;

            int playerWidth = texture.texture.getWidth();
            int playerHeight = texture.texture.getHeight();

            //Move in pressed direction if in game window
            //Update animation system for this move
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
                if (position.x > 0) {
                    position.x -= velocity.x * deltaTime;
                }
                if (position.x < 0) {
                    position.x = 0;
                }
                animationSystem.setDirection("left");
                animationSystem.setWalking(true);

            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (position.x + playerWidth < GameController.GAME_WIDTH) {
                    position.x += velocity.x * deltaTime;
                }
                if (position.x + playerWidth > GameController.GAME_WIDTH) {
                    position.x = GameController.GAME_WIDTH - playerWidth;
                }
                animationSystem.setDirection("right");
                animationSystem.setWalking(true);

            } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (position.y + playerHeight < GameController.GAME_HEIGHT) {
                    position.y += velocity.y * deltaTime;
                }
                if (position.y + playerHeight > GameController.GAME_HEIGHT) {
                    position.y = GameController.GAME_HEIGHT - playerHeight;
                }
                animationSystem.setDirection("up");
                animationSystem.setWalking(true);

            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (position.y > 0) {
                    position.y -= velocity.y * deltaTime;
                }
                if (position.y < 0) {
                    position.y = 0;
                }
                animationSystem.setDirection("down");
                animationSystem.setWalking(true);
            } else {
                //If not walking, update animation system
                animationSystem.setWalking(false);
            }

            //If the player is colliding, revert changes and update animation system
            if (player.getComponent(ColliderComponent.class) != null) {
                if (collisionSystem.isColliding(player)) {
                    position.x = old_x;
                    position.y = old_y;
                    animationSystem.setWalking(false);
                }
            }

        }

    }


}
