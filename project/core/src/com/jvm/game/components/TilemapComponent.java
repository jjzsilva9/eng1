package com.jvm.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

//Stores tilemap
public class TilemapComponent implements Component {

    public OrthogonalTiledMapRenderer mapRenderer;
    public TiledMap tilemap;

    public TiledMapTileLayer collisionLayer;

}