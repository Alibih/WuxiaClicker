package com.mygdx.game.ecs.components.dungeon;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;


public class TileComponent implements Component, Pool.Poolable {
    public int id;
    @Override
    public void reset() {

    }
}