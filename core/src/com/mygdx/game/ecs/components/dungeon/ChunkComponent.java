package com.mygdx.game.ecs.components.dungeon;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ChunkComponent implements Component, Pool.Poolable{
    public final int size = 128;
    public int[][] tiles;
    @Override
    public void reset() {

    }
}
