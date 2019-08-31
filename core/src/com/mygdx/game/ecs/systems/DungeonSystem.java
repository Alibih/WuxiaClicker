package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.ECSEngine;
import com.mygdx.game.ecs.actors.DungeonActor;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.dungeon.*;

import java.util.Random;

public class DungeonSystem extends IteratingSystem {
    public DungeonSystem() {
        super(Family.all(ChunkComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PooledEngine e = (PooledEngine)this.getEngine();
        ChunkComponent cc = ECSEngine.ChunkCM.get(entity);
        PositionComponent pc = ECSEngine.PositionCM.get(entity);
        if(cc.tiles == null){
            cc.tiles = new int[cc.size][cc.size];
            for(int i = 0; i < cc.size; i++){
                for (int j = 0; j < cc.size; j++){
                    int tc = 0;
                    tc |= TileValues.TILETYPE_ROCK;
                    Random random = new Random();
                    if(random.nextDouble()>0.6)
                        tc |= TileValues.MASK_WALL_EXCLUSIVE;
                    cc.tiles[i][j] = tc;
                }
            }
            for(int i = 0; i < cc.size; i++) {
                for (int j = 0; j < cc.size; j++) {
                    byte[] offsetCalc = DungeonActor.getDrawOffset(cc, i, j);

                    if(TileValues.isWall(cc.tiles[i][j])){
                        cc.tiles[i][j] |= offsetCalc[0]-offsetCalc[1];
                    }
                    else if(TileValues.isFloor(cc.tiles[i][j])){
                        cc.tiles[i][j] |= offsetCalc[0];
                    }
                }
            }
        }

    }
}
