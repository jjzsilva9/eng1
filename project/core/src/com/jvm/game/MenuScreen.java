package com.jvm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

//Handling of menu screen - processing and rendering
public class MenuScreen implements Screen {

    private Stage stage;
    private Table table;

    final GameController game;

    public MenuScreen(final GameController game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //create a table for the UI to be put in
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setDebug(false);

        //add header to the UI
        BitmapFont headerFont = FontGenerator.GenerateFont(Gdx.files.internal("fonts/KodeMono-VariableFont_wght.ttf"), 50);
        Label.LabelStyle headerStyle = new Label.LabelStyle(headerFont, Color.WHITE);
        Label header = new Label("Heslington Hustle", headerStyle);
        table.add(header);

        //enter a new row on the table
        table.row();

        //add start text to UI
        BitmapFont startFont = FontGenerator.GenerateFont(Gdx.files.internal("fonts/KodeMono-VariableFont_wght.ttf"), 25);
        Label.LabelStyle startStyle = new Label.LabelStyle(startFont, Color.WHITE);
        Label start = new Label("Press Enter to Start", startStyle);
        table.add(start);
    }

    public void create() {

    }

    public void show() {

    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void pause() {}

    public void resume() {}

    public void hide() {}

    @Override
    public void render(float delta) {
        //Sets background to a dark blue
        ScreenUtils.clear(0, 0, 0.2f, 1);

        //Calculates updates to the scene then draws
        stage.act(delta);
        stage.draw();

        //If Enter is pressed, set the screen to the game screen
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    public void dispose() {
        stage.dispose();
    }



}
