package com.jvm.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jvm.game.entities.Player;
import com.jvm.game.entities.Map;
import com.jvm.game.systems.CollisionSystem;
import com.jvm.game.systems.AnimationSystem;
import com.jvm.game.systems.MovementSystem;
import com.jvm.game.systems.RenderSystem;


//Handling of main game screen - processing and rendering
public class GameScreen implements Screen {

    private final Stage stage;

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
        Player p = new Player(engine, 50, 100, 100, 100);
        engine.addEntity(p.getPlayer());

        //Create tilemap
        Map m = new Map(engine, "map/Final_Tilemap.tmx", "Buildings");
        engine.addEntity(m.getMapEntity());


        CollisionSystem collider = new CollisionSystem();
        engine.addSystem(collider);

        AnimationSystem animationSystem = new AnimationSystem(p);
        engine.addSystem(animationSystem);

        //Create the movement system for the player
        MovementSystem movementSystem = new MovementSystem();
        engine.addSystem(movementSystem);

        //Add the render system
        RenderSystem renderer = new RenderSystem(camera, batch);
        engine.addSystem(renderer);

        stage = new Stage(new ScreenViewport(camera));
        Counters counters = new Counters(stage);




    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1);
        engine.update(deltaTime);
        stage.act(deltaTime);
        stage.draw();

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
