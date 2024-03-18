package com.jvm.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Handling for multiple screens
public class GameController extends Game {


    public static int GAME_WIDTH = 960 ;
    public static int GAME_HEIGHT = 540;

    public void create() {

        this.setScreen(new MenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {

    }
}

