package com.mygdx.game.ecs.actors;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.dungeon.TileValues;

import java.util.HashMap;

public final class TileActor {
    public static Texture wallTexture;
    public static Texture floorTexture;
    public static final int size = 16;
    static HashMap<Integer, PositionComponent> textures = new HashMap<>();

    public static void setTexture(Texture wall, Texture floor){
        wallTexture = wall; floorTexture = floor;
    };

    public static void createTileMapPreset(Texture wall,Texture floor){
        setTexture(wall,floor);
        PositionComponent ROCK_WALL = new PositionComponent();
        ROCK_WALL.x = size*7*0;
        ROCK_WALL.y = size*3*1;
        PositionComponent ROCK_FLOOR = new PositionComponent();
        ROCK_FLOOR.x = size*7*0;
        ROCK_FLOOR.y = size*3*5;

        createTileMap(TileValues.TILETYPE_ROCK,ROCK_WALL);
    }
    public static PositionComponent offsetWall(boolean UP, boolean DOWN, boolean LEFT, boolean RIGHT){
        Byte b = (byte)(0b0000+(UP? TileValues.SAME_U___:0b0000) + (DOWN? TileValues.SAME____D:0b0000) + (LEFT? TileValues.SAME__L__:0b0000)+(RIGHT? TileValues.SAME___R_:0b0000));
        return offsetWall(b);
    }
    public static PositionComponent offsetWall(int b){
        PositionComponent pc = new PositionComponent();
        switch (b & (TileValues.MASK_WALL-TileValues.MASK_WALL_EXCLUSIVE)){
            case TileValues.SAME_NONE:
                pc.x = size;
                pc.y = size;
                break;
            case TileValues.SAME_U___:
                pc.x = size;
                pc.y = size;
                break;
            case TileValues.SAME____D:
                pc.x = 0;
                pc.y = size;
                break;
            case TileValues.SAME___R_:
                pc.x = 0;
                pc.y = size*2;
                break;
            case TileValues.SAME__L__:
                pc.x = size*2;
                pc.y = size*2;
                break;
            case TileValues.SAME_U__D:
                pc.x = 0;
                pc.y = size;
                break;
            case TileValues.SAME__LR_:
                pc.x = size*1;
                pc.y = 0;
                break;
            case TileValues.SAME___RD:
                pc.x = 0;
                pc.y = 0;
                break;
            case TileValues.SAME_U_R_:
                pc.x = size*0;
                pc.y = size*2;
                break;
            case TileValues.SAME__L_D:
                pc.x = size*2;
                pc.y = size*0;
                break;
            case TileValues.SAME_UL__:
                pc.x = size*2;
                pc.y = size*2;
                break;
            case TileValues.SAME_ULR_:
                pc.x = size*4;
                pc.y = size*2;
                break;
            case TileValues.SAME_UL_D:
                pc.x = size*5;
                pc.y = size*1;
                break;
            case TileValues.SAME__LRD:
                pc.x = size*4;
                pc.y = size*0;
                break;
            case TileValues.SAME_U_RD:
                pc.x = size*3;
                pc.y = size*1;
                break;
            case TileValues.SAME_ULRD:
                pc.x = size*4;
                pc.y = size*1;
            case TileValues.SAME_ALL:
                pc.x = size*3;
                pc.y = 0;
        }
        return pc;

    }

    public static PositionComponent offsetFloor(int b){
        PositionComponent pc = new PositionComponent();
        switch (b& (TileValues.MASK_WALL-TileValues.MASK_WALL_EXCLUSIVE)){
            case TileValues.SAME_NONE:
                pc.x = size*5;
                pc.y = size*0;
                break;
            case TileValues.SAME_U___:
                pc.x = size*3;
                pc.y = size*2;
                break;
            case TileValues.SAME____D:
                pc.x = size*3;
                pc.y = size*0;
                break;
            case TileValues.SAME___R_:
                pc.x = size*4;
                pc.y = size*1;
                break;
            case TileValues.SAME__L__:
                pc.x = size*6;
                pc.y = size*1;
                break;
            case TileValues.SAME_U__D:
                pc.x = size*3;
                pc.y = size*1;
                break;
            case TileValues.SAME__LR_:
                pc.x = size*5;
                pc.y = size*1;
                break;
            case TileValues.SAME___RD:
                pc.x = size*0;
                pc.y = size*0;
                break;
            case TileValues.SAME_U_R_:
                pc.x = size*0;
                pc.y = size*2;
                break;
            case TileValues.SAME__L_D:
                pc.x = size*2;
                pc.y = size*0;
                break;
            case TileValues.SAME_UL__:
                pc.x = size*2;
                pc.y = size*2;
                break;
            case TileValues.SAME_ULR_:
                pc.x = size*1;
                pc.y = size*2;
                break;
            case TileValues.SAME_UL_D:
                pc.x = size*2;
                pc.y = size*1;
                break;
            case TileValues.SAME__LRD:
                pc.x = size*1;
                pc.y = size*0;
                break;
            case TileValues.SAME_U_RD:
                pc.x = size*0;
                pc.y = size*1;
                break;
            case TileValues.SAME_ULRD:
            case TileValues.SAME_ALL:
                pc.x = size*1;
                pc.y = size*1;
        }
        return pc;

    }
    public static void createTileMap(int key , PositionComponent position){
        textures.put(key,position);
    }

    public static PositionComponent getTileMap(int key){
        return textures.get(key);
    }

}
