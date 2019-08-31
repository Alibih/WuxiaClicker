package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ecs.ECSEngine;
import com.mygdx.game.ecs.actors.TileActor;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.dungeon.ChunkComponent;
import com.mygdx.game.ecs.components.dungeon.TileValues;

public class DungeonRenderingSystem extends IteratingSystem {
    SpriteBatch batch;
    public DungeonRenderingSystem(SpriteBatch batch) {
        super(Family.all(ChunkComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ChunkComponent cc = ECSEngine.ChunkCM.get(entity);
        PositionComponent pc = ECSEngine.PositionCM.get(entity);
        if(cc.tiles != null){
            for(int i = 0; i < cc.size; i ++){
                for(int j = 0; j < cc.size; j ++) {
                    int tc = cc.tiles[i][j];
                    PositionComponent offsetpc = TileActor.getTileMap(tc&TileValues.MASK_TILETYPE);

                    if(TileValues.isWall(tc)) {
                        PositionComponent toffsetpc = TileActor.offsetWall(tc);
                        batch.draw(TileActor.wallTexture, pc.x + i * TileActor.size, pc.y + j * TileActor.size, (int) (offsetpc.x + toffsetpc.x), (int) (offsetpc.y + toffsetpc.y), TileActor.size, TileActor.size);
                    }
                    else if (TileValues.isFloor(tc))
                    {
                        PositionComponent toffsetpc = TileActor.offsetFloor(tc);
                        batch.draw(TileActor.floorTexture, pc.x + i * TileActor.size, pc.y + j * TileActor.size, (int) (offsetpc.x + toffsetpc.x), (int) (offsetpc.y + toffsetpc.y), TileActor.size, TileActor.size);

                    }
                }
            }
        }
        //batch.draw(sc.spriteStates.get(sc.state)[sc.animationStep],pc.x,pc.y);
    }
}
