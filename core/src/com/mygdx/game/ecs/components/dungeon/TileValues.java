package com.mygdx.game.ecs.components.dungeon;

public final class TileValues {
    public static final int SAME_NONE                  = (0b00000000000000);
    public static final int SAME_U___                  = (0b00000000000001);
    public static final int SAME__L__                  = (0b00000000000010);
    public static final int SAME_UL__                  = (0b00000000000011);
    public static final int SAME___R_                  = (0b00000000000100);
    public static final int SAME_U_R_                  = (0b00000000000101);
    public static final int SAME__LR_                  = (0b00000000000110);
    public static final int SAME_ULR_                  = (0b00000000000111);
    public static final int SAME____D                  = (0b00000000001000);
    public static final int SAME_U__D                  = (0b00000000001001);
    public static final int SAME__L_D                  = (0b00000000001010);
    public static final int SAME_UL_D                  = (0b00000000001011);
    public static final int SAME___RD                  = (0b00000000001100);
    public static final int SAME_U_RD                  = (0b00000000001101);
    public static final int SAME__LRD                  = (0b00000000001110);
    public static final int SAME_ULRD                  = (0b00000000001111);
    public static final int SAME_ALL                   = (0b00000000010000);
    public static final int MASK_TILESHAPE             = (0b00000000011111);
    public static final int MASK_WALL_EXCLUSIVE        = (0b00000000100000);
    public static final int MASK_WALL                  = (0b00000000111111);

    public static final int TILETYPE_ROCK              = (0b00000001000000);
    public static final int MASK_TILETYPE              = (0b00001111000000);
    public static final int MASK_TILE                  = (0b00001111111111);
    public static boolean isWall(int val){
        return ((MASK_WALL_EXCLUSIVE & val) == MASK_WALL_EXCLUSIVE);
    }
    public static boolean isFloor(int val){
        return (val % MASK_WALL) < MASK_WALL_EXCLUSIVE;
    }

    public static boolean comparePlacing(TileComponent a, TileComponent b){
        if ((a.id & MASK_TILE) == a.id && (b.id & MASK_TILE) == b.id) {
            if((isWall(a.id) && isWall(b.id)) || (isFloor(a.id) && isFloor(b.id)))
                return true;
        }

        return false;
    }
    public static boolean comparePlacing(int a, int b){
        if ((a & MASK_TILE) == a && (b & MASK_TILE) == b) {
            if((isWall(a) && isWall(b)) || (isFloor(a) && isFloor(b)))
                return true;
        }

        return false;
    }
}