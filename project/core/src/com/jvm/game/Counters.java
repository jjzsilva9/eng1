package com.jvm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class Counters extends Actor {

    private Stage stage;
    private Table dayCounterTable;
    private Table statsCounterTable;

    private int dayCounter;
    private static int studyCounter;
    private int eatCounter;
    private int activityCounter;

    Label.LabelStyle dayStyle;
    Label.LabelStyle statStyle;

    Label dayLabel;
    Label studyLabel;
    Label eatLabel;
    Label relaxLabel;

    public Counters(Stage stage) {
        this.stage = stage;

        BitmapFont dayFont = FontGenerator.GenerateFont(Gdx.files.internal("fonts/KodeMono-VariableFont_wght.ttf"), 25);
        BitmapFont statFont = FontGenerator.GenerateFont(Gdx.files.internal("fonts/KodeMono-VariableFont_wght.ttf"), 15);
        dayStyle = new Label.LabelStyle(dayFont, Color.WHITE);
        statStyle = new Label.LabelStyle(statFont, Color.WHITE);
        createDayTable();
        createStatsTable();
    }

    public void createDayTable() {
        dayCounterTable = new Table();
        stage.addActor(dayCounterTable);
        dayCounterTable.setFillParent(true);
        dayCounterTable.align(Align.topLeft);
        addDayTracker();

    }

    public void createStatsTable() {
        statsCounterTable = new Table();
        stage.addActor(statsCounterTable);
        statsCounterTable.setFillParent(true);
        statsCounterTable.align(Align.topRight);
        addStudyCounter();
        statsCounterTable.row();
        addEatCounter();
        statsCounterTable.row();
        addActivityCounter();
    }

    public void addDayTracker() {
        dayLabel = new Label("Day: " + dayCounter, dayStyle);
        dayCounterTable.add(dayLabel).padLeft(10);
    }

    public void addEatCounter() {
        eatLabel = new Label("Times eaten: " + eatCounter, statStyle);
        statsCounterTable.add(eatLabel).align(Align.topLeft).padRight(10);
    }

    public void addStudyCounter() {
        studyLabel = new Label("Times studied: " + studyCounter, statStyle);
        statsCounterTable.add(studyLabel).align(Align.topLeft).padRight(10);
    }

    public void addActivityCounter() {
        relaxLabel = new Label("Times relaxed: " + activityCounter, statStyle);
        statsCounterTable.add(relaxLabel).align(Align.topLeft).padRight(10);
    }

    public void increaseDayCount() {
        dayCounter++;
        dayLabel.setText("Day: " + dayCounter);
    }

    public void increaseEatCount() {
        eatCounter++;
        eatLabel.setText("Times eaten: " + eatCounter);
    }

    public void increaseActivityCount() {
        activityCounter++;
        relaxLabel.setText("Times relaxed: " + activityCounter);
    }

    public void increaseStudyCount() {
        studyCounter++;
        studyLabel.setText("Times studied: " + studyCounter);
    }
}
