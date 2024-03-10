package com.jvm.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player {

    private Entity player;

    public Player(Engine engine) {
        player = engine.createEntity();

        //Add texture component
        Texture playerTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        TextureComponent pTextureC = new TextureComponent();
        pTextureC.texture = playerTexture;
        player.add(pTextureC);

        //Add position component
        PositionComponent playerPos = new PositionComponent();
        player.add(playerPos);

        //Add velocity component
        VelocityComponent playerVel = new VelocityComponent();
        playerVel.x = 50f; playerVel.y = 50f;
        player.add(playerVel);

    }

    public Player(Engine engine, int x, int y) {
        player = engine.createEntity();

        //Add texture component
        Texture playerTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        TextureComponent pTextureC = new TextureComponent();
        pTextureC.texture = playerTexture;
        player.add(pTextureC);

        //Add position component
        PositionComponent playerPos = new PositionComponent();
        playerPos.x = x;
        playerPos.y = y;
        player.add(playerPos);

        //Add velocity component
        VelocityComponent playerVel = new VelocityComponent();
        playerVel.x = 50f; playerVel.y = 50f;
        player.add(playerVel);
    }

    public Entity getPlayer() {
        return player;
    }

    public Texture getTexture() {
        return player.getComponent(TextureComponent.class).texture;
    }

    public void setTexture(Texture texture) {
        player.getComponent(TextureComponent.class).texture = texture;
    }

    public PositionComponent getPosition() {
        return player.getComponent(PositionComponent.class);
    }

    public void setPosition(int x, int y) {
        PositionComponent playerPos = player.getComponent(PositionComponent.class);
        playerPos.x = x;
        playerPos.y = y;
    }

    public VelocityComponent getVelocity() {
        return player.getComponent(VelocityComponent.class);
    }

    public void setVelocity(int x, int y) {
        VelocityComponent playerVel = player.getComponent(VelocityComponent.class);
        playerVel.x = x;
        playerVel.y = y;
    }

    public void render() {

    }

}
