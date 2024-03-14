package com.jvm.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InteractionSystem extends EntitySystem {
    public InteractionSystem() {}

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Input.Keys.E) || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

        }
    }
}
