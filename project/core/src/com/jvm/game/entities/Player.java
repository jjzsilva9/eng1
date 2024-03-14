package com.jvm.game.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jvm.game.components.*;

public class Player {

    private Entity player;

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

        ColliderComponent playerCollider = new ColliderComponent();
        player.add(playerCollider);
    }

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

        ColliderComponent playerCollider = new ColliderComponent();
        player.add(playerCollider);
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

    public AnimationComponent getAnimationComponent() {
        return player.getComponent(AnimationComponent.class);
    }

    public void setAnimationComponent(AnimationComponent anim) {
        AnimationComponent animationComponent = player.getComponent(AnimationComponent.class);
        animationComponent.animationMap = anim.animationMap;
        animationComponent.cycle = anim.cycle;
        animationComponent.direction = anim.direction;
    }

    public void render() {

    }

}
