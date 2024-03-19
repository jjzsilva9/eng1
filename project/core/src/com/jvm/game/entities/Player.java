package com.jvm.game.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jvm.game.components.*;

/**
 * A wrapper class for creating and managing a Player entity.
 * Has components:
 *      - TextureComponent
*       - PositionComponent
*       - AnimationComponent
*       - VelocityComponent
*       - ColliderComponent
 * Provides setter and getter methods also for components.
 */
public class Player {

    private Entity player;

    /**
     * Creates the new entity with components:
     *   - TextureComponent
     *   - PositionComponent
     *   - AnimationComponent
     *   - VelocityComponent
     *   - ColliderComponent
     * Creates the player at position 0,0 with a default Velocity of 100f, 100f.
     * @param engine The Ashley engine to add the Player to
     */
    public Player(Engine engine) {
        player = engine.createEntity();

        //Add texture component
        Texture playerTexture = new Texture(Gdx.files.internal("player/idle-down.png"));
        TextureComponent pTextureC = new TextureComponent();
        pTextureC.texture = playerTexture;
        player.add(pTextureC);

        //Add animation component
        Texture[][] animMap = {{new Texture(Gdx.files.internal("player/idle-left.png")), new Texture(Gdx.files.internal("player/idle-right.png")), new Texture(Gdx.files.internal("player/idle-up.png")), new Texture(Gdx.files.internal("player/idle-down.png"))},
                {new Texture(Gdx.files.internal("player/walk-left-1.png")), new Texture(Gdx.files.internal("player/walk-right-1.png")), new Texture(Gdx.files.internal("player/walk-up-1.png")), new Texture(Gdx.files.internal("player/walk-down-1.png"))},
                {new Texture(Gdx.files.internal("player/idle-left.png")), new Texture(Gdx.files.internal("player/idle-right.png")), new Texture(Gdx.files.internal("player/idle-up.png")), new Texture(Gdx.files.internal("player/idle-down.png"))},
                {new Texture(Gdx.files.internal("player/walk-left-3.png")), new Texture(Gdx.files.internal("player/walk-right-3.png")), new Texture(Gdx.files.internal("player/walk-up-3.png")), new Texture(Gdx.files.internal("player/walk-down-3.png"))}};
        AnimationComponent playerAnim = new AnimationComponent();
        playerAnim.animationMap = animMap;
        player.add(playerAnim);

        //Add position component
        PositionComponent playerPos = new PositionComponent();
        player.add(playerPos);

        playerPos.x = 80f;
        playerPos.y = 50f;

        //Add velocity component
        VelocityComponent playerVel = new VelocityComponent();
        playerVel.x = 100f; playerVel.y = 100f;
        player.add(playerVel);

        //Add collider component
        ColliderComponent playerCollider = new ColliderComponent();
        playerCollider.width = playerTexture.getWidth();
        playerCollider.height = playerTexture.getHeight();
        player.add(playerCollider);
    }

    /**
     * Creates the new entity with components:
     *    - TextureComponent
     *    - PositionComponent
     *    - AnimationComponent
     *    - VelocityComponent
     *    - ColliderComponent
     * @param engine Ashley engine to add Player to
     * @param x X coordinate to spawn player at
     * @param y Y coordinate to spawn player at
     * @param vel_x Max speed of player in x direction
     * @param vel_y Max speed of player in y direction
     */
    public Player(Engine engine, float x, float y, float vel_x, float vel_y) {
        player = engine.createEntity();

        //Add texture component
        Texture playerTexture = new Texture(Gdx.files.internal("player/idle-down.png"));
        TextureComponent pTextureC = new TextureComponent();
        pTextureC.texture = playerTexture;
        player.add(pTextureC);

        Texture[][] animMap = {{new Texture(Gdx.files.internal("player/idle-left.png")), new Texture(Gdx.files.internal("player/idle-right.png")), new Texture(Gdx.files.internal("player/idle-up.png")), new Texture(Gdx.files.internal("player/idle-down.png"))},
                {new Texture(Gdx.files.internal("player/walk-left-1.png")), new Texture(Gdx.files.internal("player/walk-right-1.png")), new Texture(Gdx.files.internal("player/walk-up-1.png")), new Texture(Gdx.files.internal("player/walk-down-1.png"))},
                {new Texture(Gdx.files.internal("player/idle-left.png")), new Texture(Gdx.files.internal("player/idle-right.png")), new Texture(Gdx.files.internal("player/idle-up.png")), new Texture(Gdx.files.internal("player/idle-down.png"))},
                {new Texture(Gdx.files.internal("player/walk-left-3.png")), new Texture(Gdx.files.internal("player/walk-right-3.png")), new Texture(Gdx.files.internal("player/walk-up-3.png")), new Texture(Gdx.files.internal("player/walk-down-3.png"))}};
        AnimationComponent playerAnim = new AnimationComponent();
        playerAnim.animationMap = animMap;
        player.add(playerAnim);

        //Add position component
        PositionComponent playerPos = new PositionComponent();
        player.add(playerPos);

        playerPos.x = x;
        playerPos.y = y;

        //Add velocity component
        VelocityComponent playerVel = new VelocityComponent();
        playerVel.x = vel_x; playerVel.y = vel_y;
        player.add(playerVel);

        //Add collider component
        ColliderComponent playerCollider = new ColliderComponent();
        playerCollider.width = playerTexture.getWidth();
        playerCollider.height = playerTexture.getHeight();
        player.add(playerCollider);
    }

    /**
     * Returns the player entity
     * @return the player entity
     */
    public Entity getPlayer() {
        return player;
    }

    /**
     * Returns the texture of the player
     * @return The texture
     */
    public Texture getTexture() {
        return player.getComponent(TextureComponent.class).texture;
    }

    /**
     * Sets the texture of the player
     * @param texture Texture to set
     */
    public void setTexture(Texture texture) {
        player.getComponent(TextureComponent.class).texture = texture;
    }

    /**
     * Gets the PositionComponent of the player
     * @return the PositionComponent
     */
    public PositionComponent getPosition() {
        return player.getComponent(PositionComponent.class);
    }

    /**
     * Sets the position of the player
     * @param x X-coord to set
     * @param y Y-coord to set
     */
    public void setPosition(int x, int y) {
        PositionComponent playerPos = player.getComponent(PositionComponent.class);
        playerPos.x = x;
        playerPos.y = y;
    }

    /**
     * Getter for VelocityComponent
     * @return the VelocityComponent
     */
    public VelocityComponent getVelocity() {
        return player.getComponent(VelocityComponent.class);
    }

    /**
     * Setter for VelocityComponent
     * @param x Max-speed in x direction to be set
     * @param y Max-speed in y direction to be set
     */
    public void setVelocity(int x, int y) {
        VelocityComponent playerVel = player.getComponent(VelocityComponent.class);
        playerVel.x = x;
        playerVel.y = y;
    }

    /**
     * Getter for AnimationComponent
     * @return Player AnimationComponent
     */
    public AnimationComponent getAnimationComponent() {
        return player.getComponent(AnimationComponent.class);
    }

    /**
     * Setter for AnimationComponent
     * @param anim AnimationComponent to be set
     */
    public void setAnimationComponent(AnimationComponent anim) {
        AnimationComponent animationComponent = player.getComponent(AnimationComponent.class);
        animationComponent.animationMap = anim.animationMap;
        animationComponent.cycle = anim.cycle;
        animationComponent.direction = anim.direction;
    }

}
