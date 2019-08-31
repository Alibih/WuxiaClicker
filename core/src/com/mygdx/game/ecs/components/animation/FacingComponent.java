package com.mygdx.game.ecs.components.animation;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class FacingComponent implements Component, Pool.Poolable{
    enum Face{
        UP,DOWN,LEFT,RIGHT
    }

    public Face face = Face.DOWN;

    @Override
    public void reset() {

    }
}
