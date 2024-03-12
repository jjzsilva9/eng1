package com.jvm.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.jvm.game.components.PositionComponent;
import com.jvm.game.components.TextureComponent;
import com.jvm.game.components.TilemapComponent;

import java.util.Comparator;

public class MapRenderSystem extends EntitySystem {
    //SpriteBatch for batch rendering

    private final OrthographicCamera camera;

    //Sorted by z elements of PositionComponents
    private Array<Entity> renderQueue;
    private ImmutableArray<Entity> entities;

    public MapRenderSystem(OrthographicCamera camera) {

        this.camera = camera;

    }

    public void addedToEngine(Engine engine) {
        //Gets all entities that need rendering

        entities = getEngine().getEntitiesFor(Family.all(TilemapComponent.class).get());

        //Adds them to the rendering queue
        renderQueue = new Array<Entity>();
        for (Entity entity : entities) {
            renderQueue.add(entity);
        }
        //rm = ComponentMapper.getFor(TextureComponent.class);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);

        camera.update();

        for (Entity entity : renderQueue) {
            entity.getComponent(TilemapComponent.class).mapRenderer.render();
            entity.getComponent(TilemapComponent.class).mapRenderer.setView(camera);
        }

    }

    public void removedFromEngine() {

    }

}