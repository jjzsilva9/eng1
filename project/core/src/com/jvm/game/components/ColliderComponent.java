package com.jvm.game.components;

import com.badlogic.ashley.core.Component;

/**
 * ColliderComponent
 *
 * Holds:
 *  - Width of collider
 *  - Height of collider
 *  - Flag indicating if colliding
 */
public class ColliderComponent implements Component {
    //The collision box.
    public float width = 0f;
    public float height = 0f;

}
