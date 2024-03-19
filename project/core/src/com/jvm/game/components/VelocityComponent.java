package com.jvm.game.components;

import com.badlogic.ashley.core.Component;

/**
 * VelocityComponent
 *
 * Holds:
 *  - A max speed in the x direction
 *  - A max speed in the y direction
 */
public class VelocityComponent implements Component {
    public float x = 0.0f;
    public float y = 0.0f;
}
