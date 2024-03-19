package com.jvm.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

/**
 * AnimationComponent
 *
 * Holds:
 *  - A currently facing direction
 *  - A current walk cycle
 *  - An animation map of all needed textures
 *  - A flag indicating if walking
 */
public class AnimationComponent implements Component {

    //0: left, 1: right, 2: up, 3: down
    public int direction = 0;
    public int cycle = 0;
    public Texture[][] animationMap;
    public boolean isWalking = false;
}
