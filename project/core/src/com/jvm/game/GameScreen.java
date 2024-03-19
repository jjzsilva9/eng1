package com.jvm.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
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
import com.jvm.game.systems.*;


/**
 * Game Screen
 * Acts as a handler for all game logic and rendering
 * Creates an Ashley Engine and a Scene2D stage for UI
 */
public class GameScreen implements Screen {

    private final Stage stage;

    public Engine engine;
    private final SpriteBatch batch;

    private OrthographicCamera camera;

    public Counters counters;

    private GameController game;

    /**
     * Creates Ashley engine and adds:
     *   - Player entity
     *   - Map entity
     *   - CollisionSystem
     *   - InteractionSystem
     *   - AnimationSystem
     *   - MovementSystem
     *   - RenderSystem
     * Creates Scene2D stage for UI
     * @param game The Game instance
     */
    public GameScreen(GameController game) {
        this.game = game;
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

        //Create Scene2D stage and add counters
        stage = new Stage(new ScreenViewport(camera));
        Counters counters = new Counters(stage);

        //Create collision system and add
        CollisionSystem collider = new CollisionSystem();
        engine.addSystem(collider);

        //Create interaction system and add
        InteractionSystem interactionSystem = new InteractionSystem(counters);
        engine.addSystem(interactionSystem);

        //Create animation system and add
        AnimationSystem animationSystem = new AnimationSystem(p);
        engine.addSystem(animationSystem);

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

    /**
     * Render function for game screen
     *
     * Updates Ashley engine and Scene2D stage
     * If game is over, returns to Menu
     * @param deltaTime Time since last frame
     */
    @Override
    public void render(float deltaTime) {
        if (engine.getSystem(InteractionSystem.class).endGame) {
            game.setScreen(new MenuScreen(game));
        }
        ScreenUtils.clear(0, 0, 0, 1);
        engine.update(deltaTime);
        stage.act(deltaTime);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
