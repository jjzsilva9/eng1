package com.jvm.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class AnimationComponent implements Component {

    //0: left, 1: right, 2: up, 3: down
    public int direction = 0;
    public int cycle = 0;
    public Texture[][] animationMap;
}
