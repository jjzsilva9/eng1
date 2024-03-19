package com.jvm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Creates, holds and updates the values and GUI elements for the counters.
 *
 * <p>Values and GUI elements are created with the class, must be updated through the
 * provided increment functions.
 * </p>
 */
public class Counters extends Actor {

    private Stage stage;

    //UI Tables for positioning counters
    private Table dayCounterTable;
    private Table statsCounterTable;

    //Counter variables
    private int dayCounter = 1;
    private int studyCounter = 0;
    private int eatCounter = 0;
    private int activityCounter = 0;


    //Label styles and label objects
    Label.LabelStyle dayStyle;
    Label.LabelStyle statStyle;
    Label dayLabel;
    Label studyLabel;
    Label eatLabel;
    Label relaxLabel;

    /**
     * Constructor
     *
     * @param stage Scene2D stage that the counters are being added to
     */
    public Counters(Stage stage) {
        this.stage = stage;

        //Generate different sized fonts for the labels
        BitmapFont dayFont = FontGenerator.GenerateFont(Gdx.files.internal("fonts/KodeMono-VariableFont_wght.ttf"), 25);
        BitmapFont statFont = FontGenerator.GenerateFont(Gdx.files.internal("fonts/KodeMono-VariableFont_wght.ttf"), 15);

        //Create styles for the counters
        dayStyle = new Label.LabelStyle(dayFont, Color.WHITE);
        statStyle = new Label.LabelStyle(statFont, Color.WHITE);

        //Create and populate both of the UI tables
        createDayTable();
        createStatsTable();
    }

    /**
     * Returns the value of the day count
     *
     * @return The value of day count
     */
    public int getDay() {
        return dayCounter;
    }

    /**
     * Creates the day table and adds it to the top left
     */
    public void createDayTable() {
        //Create a table element and add it to the stage in the top left
        dayCounterTable = new Table();
        stage.addActor(dayCounterTable);
        dayCounterTable.setFillParent(true);
        dayCounterTable.align(Align.topLeft);

        //Add the actual tracker into the table
        addDayTracker();

    }

    /**
     * Creates the stats table and adds it to the top left
     */
    public void createStatsTable() {
        //Create a table element and add it to the stage in the top right
        statsCounterTable = new Table();
        stage.addActor(statsCounterTable);
        statsCounterTable.setFillParent(true);
        statsCounterTable.align(Align.topRight);

        //Add the counters into the table
        addStudyCounter();
        statsCounterTable.row();
        addEatCounter();
        statsCounterTable.row();
        addActivityCounter();
    }

    /**
     * Creates and adds the day tracker to it's table
     */
    public void addDayTracker() {
        //Create a new day tracker, add to table
        dayLabel = new Label("Day: " + dayCounter, dayStyle);
        dayCounterTable.add(dayLabel).padLeft(10);
    }

    /**
     * Creates and adds the eat counter to it's table
     */
    public void addEatCounter() {
        //Create a new eat counter, add to table
        eatLabel = new Label("Times eaten: " + eatCounter, statStyle);
        statsCounterTable.add(eatLabel).align(Align.topLeft).padRight(10);
    }

    /**
     * Creates and adds the study counter to it's table
     */
    public void addStudyCounter() {
        //Create a new study counter, add to table
        studyLabel = new Label("Times studied: " + studyCounter, statStyle);
        statsCounterTable.add(studyLabel).align(Align.topLeft).padRight(10);
    }

    /**
     * Creates and adds the activity counter to it's table
     */
    public void addActivityCounter() {
        //Create a new activity counter, add to table
        relaxLabel = new Label("Times relaxed: " + activityCounter, statStyle);
        statsCounterTable.add(relaxLabel).align(Align.topLeft).padRight(10);
    }

    /**
     * Increments day count and updates the GUI
     */
    public void increaseDayCount() {
        //Increase day count, update label
        dayCounter++;
        dayLabel.setText("Day: " + dayCounter);
    }

    /**
     * Increments eat count and updates the GUI
     */
    public void increaseEatCount() {
        //Increase eat count, update label
        eatCounter++;
        eatLabel.setText("Times eaten: " + eatCounter);
    }

    /**
     * Increments activity count and updates the GUI
     */
    public void increaseActivityCount() {
        //Increase activity count, update label
        activityCounter++;
        relaxLabel.setText("Times relaxed: " + activityCounter);
    }

    /**
     * Increments study count and updates the GUI
     */
    public void increaseStudyCount() {
        //Increase study count, update label
        studyCounter++;
        studyLabel.setText("Times studied: " + studyCounter);
    }
}
