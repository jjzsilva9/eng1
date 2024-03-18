package com.jvm.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Acts as a screen handler for multiple screens
 */
public class GameController extends Game {


    public static int GAME_WIDTH = 960 ;
    public static int GAME_HEIGHT = 540;

    /**
     * Start, sets the menu screen
     */
    public void create() {

        this.setScreen(new MenuScreen(this));
    }

    /**
     * Renders the active screen
     */
    public void render() {
        super.render();
    }

    public void dispose() {

    }
}

