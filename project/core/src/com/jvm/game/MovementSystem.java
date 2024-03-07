package com.jvm.game;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class MovementSystem  extends EntitySystem {
    private Player player;

    public MovementSystem(Player p) {
        player = p;
    }

    public void update(float deltaTime) {
        PositionComponent position = player.getComponent(PositionComponent.class);
        VelocityComponent velocity = player.getComponent(VelocityComponent.class);
        if (Gdx.input.isKeyPressed(Input.Buttons.LEFT)) {

        }
    }


}
