package com.jvm.game.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.jvm.game.components.TilemapComponent;

public class Map {
    private Entity map;
    private TilemapComponent tileMapComp;

    public Map(Engine engine, String file_name) {
        map = engine.createEntity();

        tileMapComp = new TilemapComponent();
        tileMapComp.map = new TmxMapLoader().load(file_name);
        tileMapComp.mapRenderer = new OrthogonalTiledMapRenderer(tileMapComp.map);

        map.add(tileMapComp);

        //tileMapComp.mapRenderer.setView(camera);
    }

    public Entity getMapEntity() {
        return map;
    }

    public OrthogonalTiledMapRenderer getMapRenderer() {
        return tileMapComp.mapRenderer;
    }

}