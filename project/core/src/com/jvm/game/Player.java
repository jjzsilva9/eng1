package com.jvm.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity {


    public Player() {
        this.add(new PositionComponent());
        this.add(new TextureComponent());
        Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        this.getComponent(TextureComponent.class).texture = texture;

    }

    public void render() {

    }

}
