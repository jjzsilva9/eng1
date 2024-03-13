package com.jvm.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jvm.game.entities.Player;
import com.jvm.game.entities.Map;
import com.jvm.game.systems.CollisionSystem;
import com.jvm.game.systems.MovementSystem;
import com.jvm.game.systems.RenderSystem;


//Handling of main game screen - processing and rendering
public class GameScreen implements Screen {

    public Engine engine;
    private final SpriteBatch batch;

    private OrthographicCamera camera;

    public GameScreen(GameController game) {

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.GAME_WIDTH, game.GAME_HEIGHT);

        //Create an ashley engine
        engine = new Engine();

        //Create player
        Player p = new Player(engine);
        engine.addEntity(p.getPlayer());

        //Create tilemap
        Map m = new Map(engine, "map/Final_Tilemap.tmx");
        engine.addEntity(m.getMapEntity());

        CollisionSystem collider = new CollisionSystem();
        engine.addSystem(collider);

        //Create the movement system for the player
        MovementSystem movementSystem = new MovementSystem();
        engine.addSystem(movementSystem);

        //Add the render system
        RenderSystem renderer = new RenderSystem(camera, batch);
        engine.addSystem(renderer);




    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1);
        engine.update(deltaTime);

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
