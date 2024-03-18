package com.jvm.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * TilemapComponent
 *
 * Holds:
 *  - A map renderer
 *  - A tilemap
 *  - Width of tiles
 *  - Height of tiles
 *  - Collision Layer
 */
public class TilemapComponent implements Component {

    public OrthogonalTiledMapRenderer mapRenderer;
    public TiledMap tilemap;

    public float tileWidth;
    public float tileHeight;
    public TiledMapTileLayer collisionLayer;

}