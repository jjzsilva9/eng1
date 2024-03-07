package com.jvm.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

//Handling of main game screen - processing and rendering
public class GameScreen implements Screen {
    public GameScreen(GameController game) {
        //Create an ashley engine
        Engine engine = new Engine();

        //Add the player entity
        Player player = new Player();
        engine.addEntity(player);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
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

    }
}
