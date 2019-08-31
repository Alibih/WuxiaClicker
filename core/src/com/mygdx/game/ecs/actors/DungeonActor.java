package com.mygdx.game.ecs.actors;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.dungeon.ChunkComponent;
import com.mygdx.game.ecs.components.dungeon.TileValues;

public final class DungeonActor {

    public static void createChunk(PooledEngine e, int x , int y){
        Entity chunk = e.createEntity();
        PositionComponent pc = e.createComponent(PositionComponent.class);
        ChunkComponent cc = e.createComponent(ChunkComponent.class);

        pc.x = x;
        pc.y = y;

        chunk.add(pc);
        chunk.add(cc);
        e.addEntity(chunk);
    }

    public static byte[]  getDrawOffset(ChunkComponent cc, int x, int y){
        int tcm = cc.tiles[x][y];
        byte total = 0;
        if(x>0){
            int ltcm = cc.tiles[x-1][y];

            if(TileValues.comparePlacing(ltcm,tcm)) {
                total += TileValues.SAME__L__;
            }
        }
        if(x<cc.size-1){
            int rtcm = cc.tiles[x+1][y];
            if(TileValues.comparePlacing(rtcm,tcm)) {
                total += TileValues.SAME___R_;
            }
        }
        if(y>0){
            int utcm = cc.tiles[x][y-1];
            if(TileValues.comparePlacing(utcm, tcm)) {
                total += TileValues.SAME____D;
            }
        }
        if(y<cc.size-1){
            int dtcm =  cc.tiles[x][y+1];
            if(TileValues.comparePlacing(dtcm,tcm)) {
                total += TileValues.SAME_U___ ;
            }
        }
        byte redundancy = 0b0;
        // removes redudant right
        if((total & (TileValues.SAME___R_ + TileValues.SAME____D+ TileValues.SAME_U___)) == TileValues.SAME___R_ + TileValues.SAME____D+ TileValues.SAME_U___){
            int dtcm = cc.tiles[x+1][y-1];
            int utcm = cc.tiles[x+1][y+1];
            if(TileValues.comparePlacing(dtcm,tcm) && TileValues.comparePlacing(utcm , tcm)){
                redundancy += TileValues.SAME___R_;
            }

        }
        // removes redundant left
        if((total & (TileValues.SAME__L__ + TileValues.SAME____D+ TileValues.SAME_U___)) == TileValues.SAME__L__ + TileValues.SAME____D+ TileValues.SAME_U___){
            int dtcm =  cc.tiles[x-1][y-1];
            int utcm = cc.tiles[x-1][y+1];
            if(TileValues.comparePlacing(dtcm,tcm) && TileValues.comparePlacing(utcm , tcm)){
                redundancy += TileValues.SAME__L__;
            }
        }

        // removes redundant up
        if((total & (TileValues.SAME__L__ + TileValues.SAME_U___+ TileValues.SAME___R_)) == TileValues.SAME__L__ + TileValues.SAME_U___+ TileValues.SAME___R_){
            int dtcm= cc.tiles[x-1][y+1];
            int utcm = cc.tiles[x+1][y+1];
            if(TileValues.comparePlacing(dtcm,tcm) && TileValues.comparePlacing(utcm , tcm)){
                redundancy += TileValues.SAME_U___;
            }

        }

        // removes redundant down
        if((total & (TileValues.SAME__L__ + TileValues.SAME____D+ TileValues.SAME___R_)) == TileValues.SAME__L__ + TileValues.SAME____D+ TileValues.SAME___R_){
            int dtcm  = cc.tiles[x-1][y-1];
            int utcm = cc.tiles[x+1][y-1];
            if(TileValues.comparePlacing(dtcm,tcm) && TileValues.comparePlacing(utcm , tcm)){
                redundancy += TileValues.SAME____D;
            }

        }

        if(redundancy==15)
            total = TileValues.SAME_ALL;
        //System.out.println(((total&TileValues.SAME_U___),TileValues.SAME_U___?"SAME_UP ":" ")+
        //        ((total&TileValues.SAME____D),TileValues.SAME____D?"SAME_DOWN ":" ")+
        //        ((total&TileValues.SAME__L__),TileValues.SAME__L__?"SAME_LEFT ":" ")+
        //        ((total&TileValues.SAME___R_),TileValues.SAME___R_?"SAME_RIGHT ":" "));
        return new byte[]{total,redundancy};
    }
}
