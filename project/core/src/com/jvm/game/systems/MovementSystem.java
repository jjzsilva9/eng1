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


//MovementSystem for player movement
public class MovementSystem  extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private CollisionSystem collisionSystem;

    public MovementSystem() {}

    public void addedToEngine(Engine engine) {
        //Finds all entities that need movement handling
        //Should be just player
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());

        collisionSystem = engine.getSystem(CollisionSystem.class);
    }

    public void update(float deltaTime) {
        for (Entity player: entities) {

            PositionComponent position = player.getComponent(PositionComponent.class);
            VelocityComponent velocity = player.getComponent(VelocityComponent.class);
            TextureComponent texture = player.getComponent(TextureComponent.class);

            float old_x = position.x;
            float old_y = position.y;

            int playerWidth = texture.texture.getWidth();
            int playerHeight = texture.texture.getHeight();

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
                if (position.x > 0) {
                    position.x -= velocity.x * deltaTime;
                }
                if (position.x < 0) {
                    position.x = 0;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (position.x + playerWidth < GameController.GAME_WIDTH) {
                    position.x += velocity.x * deltaTime;
                }
                if (position.x + playerWidth > GameController.GAME_WIDTH) {
                    position.x = GameController.GAME_WIDTH - playerWidth;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (position.y + playerHeight < GameController.GAME_HEIGHT) {
                    position.y += velocity.y * deltaTime;
                }
                if (position.y + playerHeight > GameController.GAME_HEIGHT) {
                    position.y = GameController.GAME_HEIGHT - playerHeight;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (position.y > 0) {
                    position.y -= velocity.y * deltaTime;
                }
                if (position.y < 0) {
                    position.y = 0;
                }
            }

            if (player.getComponent(ColliderComponent.class) != null) {
                if (collisionSystem.isColliding(player)) {
                    position.x = old_x;
                    position.y = old_y;
                }
            }

        }

    }


}
