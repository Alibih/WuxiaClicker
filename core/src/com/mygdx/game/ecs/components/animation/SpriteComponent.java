package com.mygdx.game.ecs.components.animation;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

import java.util.HashMap;
import java.util.HashSet;

public class SpriteComponent implements Component, Pool.Poolable{
    public enum SpriteState{
        IDLE,
        POSE,
        MOVE_DOWN,
        MOVE_RIGHT,
        MOVE_LEFT,
        MOVE_UP,
        ATTACK_DOWN,
        ATTACK_RIGHT,
        ATTACK_LEFT,
        ATTACK_UP,
        JUMP,
        SLIDE,
        DIE
    }
    public HashMap<SpriteState, TextureRegion[]> spriteStates = new HashMap<>();
    public int animationStep;
    public SpriteState state;
    public float updateRate = 0.25f;
    public float updateCounter = 0f;
    @Override
    public void reset() {

    }
}
