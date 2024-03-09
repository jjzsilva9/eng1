package com.jvm.game;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class RenderSystem extends EntitySystem {
    //SpriteBatch for batch rendering
    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    //Sorted by z elements of PositionComponents
    private Array<Entity> renderQueue;
    private ImmutableArray<Entity> entities;

    public RenderSystem(OrthographicCamera camera, SpriteBatch batch) {

        this.batch = batch;
        this.camera = camera;

    }

    public void addedToEngine(Engine engine) {
        //Gets all entities that need rendering
        entities = getEngine().getEntitiesFor(Family.all(PositionComponent.class, TextureComponent.class).get());

        //Adds them to the rendering queue
        renderQueue = new Array<Entity>();
        for (Entity entity : entities) {
            renderQueue.add(entity);
        }
        //rm = ComponentMapper.getFor(TextureComponent.class);
    }

    public void entityAdded(Entity entity) {
        //Adds an entity to be rendered if instantiated new
        entities = getEngine().getEntitiesFor(Family.all(PositionComponent.class, TextureComponent.class).get());
        renderQueue.add(entity);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //Sorts render queue
        renderQueue.sort(new ZComparator());

        //Batch renders all sprites
        batch.begin();
        for (Entity entity : renderQueue) {
            batch.draw(entity.getComponent(TextureComponent.class).texture, entity.getComponent(PositionComponent.class).x, entity.getComponent(PositionComponent.class).y);
        }
        batch.end();

    }

    public void removedFromEngine() {

    }

    private static class ZComparator implements Comparator<Entity> {
        private final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum(pm.get(e1).z - pm.get(e2).z);
        }

    }
}
