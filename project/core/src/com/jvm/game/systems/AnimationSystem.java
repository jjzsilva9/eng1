package com.jvm.game.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.jvm.game.components.AnimationComponent;
import com.jvm.game.components.TextureComponent;
import com.jvm.game.entities.Player;

/**
 * Manages the Player's texture based on movement
 *
 * Provides functions for setting direction and walking.
 */
public class AnimationSystem extends EntitySystem {

    private Player player;
    private float interval = 0;
    private AnimationComponent playerAnim;

    /**
     * Initializer
     * @param player The player entity the animation system should apply to
     */
    public AnimationSystem(Player player) {
        this.player = player;
        playerAnim = player.getAnimationComponent();
    }

    /**
     * Sets the player's texture to a specific direction
     * @param direction The direction to be set to
     */
    public void setDirection(String direction) {
        switch (direction) {
            case "left":
                playerAnim.direction = 0;
                break;
            case "right":
                playerAnim.direction = 1;
                break;
            case "up":
                playerAnim.direction = 2;
                break;
            case "down":
                playerAnim.direction = 3;
                break;
            default:break;
        }
    }

    /**
     * Sets the player to cycle animation or not
     * @param isWalking Is the player walking
     */
    public void setWalking(boolean isWalking) {
        playerAnim.isWalking = isWalking;
    }

    /**
     * Update method for the animation system
     * Calculates new textures
     * @param deltaTime Time since last frame
     */
    public void update(float deltaTime) {
        //Update interval since last changed walk cycle if walking
        if (playerAnim.isWalking) {
            interval += deltaTime;
        } else {
            interval = 0;
            playerAnim.cycle = 0;
        }

        //If animation cycle needs changing, update and reset interval
        if (interval > 0.2f) {
            playerAnim.cycle += 1;
            if (playerAnim.cycle == 4) {
                playerAnim.cycle = 0;
            }
            interval -= 0.2f;
        }

        //Set the player texture to the correct texture for the animation
        player.setTexture(playerAnim.animationMap[playerAnim.cycle][playerAnim.direction]);
    }
}
