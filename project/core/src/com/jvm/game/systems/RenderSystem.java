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

/**
 * Renders all entities with a Position and Texture Component and Map
 */
public class RenderSystem extends EntitySystem {
    //SpriteBatch for batch rendering
    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    //Sorted by z elements of PositionComponents
    private Array<Entity> renderQueue;
    private ImmutableArray<Entity> entities;
    private Entity mapEntity;

    /**
     * Initializer
     *
     * Sets camera and SpriteBatch to use
     * @param camera Camera to use
     * @param batch SpriteBatch to use
     */
    public RenderSystem(OrthographicCamera camera, SpriteBatch batch) {

        this.batch = batch;
        this.camera = camera;

    }

    /**
     * Gets all entities that need rendering
     *
     * Adds them to render queue
     * @param engine Ashley engine to render entities for
     */
    public void addedToEngine(Engine engine) {
        //Gets all entities that need rendering
        entities = getEngine().getEntitiesFor(Family.all(PositionComponent.class, TextureComponent.class).get());

        mapEntity = getEngine().getEntitiesFor(Family.all(TilemapComponent.class).get()).get(0);

        //Adds them to the rendering queue
        renderQueue = new Array<Entity>();
        for (Entity entity : entities) {
            renderQueue.add(entity);
        }
        //rm = ComponentMapper.getFor(TextureComponent.class);
    }

    /**
     * Adds an entity to the render queue when added to the engine
     * @param entity The new entity
     */
    public void entityAdded(Entity entity) {
        //Adds an entity to be rendered if instantiated new
        entities = getEngine().getEntitiesFor(Family.all(PositionComponent.class, TextureComponent.class).get());
        renderQueue.add(entity);
    }

    /**
     * Update method for render system
     *
     * Sorts render queue and renders them
     * @param deltaTime Time since last frame
     */
    public void update(float deltaTime) {
        super.update(deltaTime);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //Renders map
        mapEntity.getComponent(TilemapComponent.class).mapRenderer.render();
        mapEntity.getComponent(TilemapComponent.class).mapRenderer.setView(camera);

        //Sorts render queue
        renderQueue.sort(new ZComparator());

        //Batch renders all sprites
        batch.begin();
        for (Entity entity : renderQueue) {
            batch.draw(entity.getComponent(TextureComponent.class).texture, entity.getComponent(PositionComponent.class).x, entity.getComponent(PositionComponent.class).y);
        }
        batch.end();

    }

    /**
     * Comparator
     *
     * Compares PositionComponents based on z index
     */
    private static class ZComparator implements Comparator<Entity> {
        private final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

        /**
         * Compares two entities on z of PositionComponent
         * @param e1 the first entity to be compared.
         * @param e2 the second entity to be compared.
         * @return entity with higher z
         */
        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum(pm.get(e1).z - pm.get(e2).z);
        }

    }
}
