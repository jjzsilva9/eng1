package com.jvm.game.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.jvm.game.components.AnimationComponent;
import com.jvm.game.components.TextureComponent;

//TODO: Needs directional changing and cycling
public class AnimationSystem extends EntitySystem {

    private Entity player;
    private float interval = 0;
    private AnimationComponent playerAnim;
    private TextureComponent playerText;
    public AnimationSystem(Entity player) {
        this.player = player;
        playerAnim = player.getComponent(AnimationComponent.class);
        playerText = player.getComponent(TextureComponent.class);
    }

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

    public void update(float deltaTime) {
        interval += deltaTime;
        if (interval > 1) {
            playerAnim.cycle += 1;
            if (playerAnim.cycle == 4) {
                playerAnim.cycle = 0;
            }
            interval -= 1;
        }
        playerText.texture = playerAnim.animationMap[playerAnim.direction][playerAnim.cycle];
    }
}
