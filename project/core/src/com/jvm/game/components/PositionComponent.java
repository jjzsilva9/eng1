package com.jvm.game.components;

import com.badlogic.ashley.core.Component;

/**
 * PositionComponent
 *
 * Holds:
 *  - An x coordinate
 *  - A y coordinate
 *  - A z coordinate (rendering preference)
 */
public class PositionComponent  implements Component {
    public float x = 0.0f;
    public float y = 0.0f;

    //z dictates rendering priority
    public int z = 1;
}
