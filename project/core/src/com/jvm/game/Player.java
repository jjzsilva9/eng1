package com.jvm.game;

import com.badlogic.ashley.core.Entity;

public class Player extends Entity {


    public Player() {
        this.add(new PositionComponent());
    }

    public void render() {}

}
