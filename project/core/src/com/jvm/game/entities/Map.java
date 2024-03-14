package com.jvm.game.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.jvm.game.components.TilemapComponent;

public class Map {
    private Entity map;
    private TilemapComponent tileMapComp;

    public Map(Engine engine, String fileName, String collisionLayerName) {
        map = engine.createEntity();

        tileMapComp = new TilemapComponent();
        tileMapComp.tilemap = new TmxMapLoader().load(fileName);
        tileMapComp.mapRenderer = new OrthogonalTiledMapRenderer(tileMapComp.tilemap);
        tileMapComp.collisionLayer = (TiledMapTileLayer) tileMapComp.tilemap.getLayers().get(collisionLayerName);
        tileMapComp.tileWidth = tileMapComp.collisionLayer.getTileWidth();
        tileMapComp.tileHeight = tileMapComp.collisionLayer.getTileHeight();
        map.add(tileMapComp);
    }

    public Entity getMapEntity() {
        return map;
    }

}