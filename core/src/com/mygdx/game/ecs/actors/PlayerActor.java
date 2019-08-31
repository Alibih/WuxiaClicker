package com.mygdx.game.ecs.actors;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ecs.components.PlayerComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.VelocityComponent;
import com.mygdx.game.ecs.components.animation.RenderableComponent;
import com.mygdx.game.ecs.components.animation.SpriteComponent;

import java.util.HashMap;

public final class PlayerActor {
    private static HashMap<SpriteComponent.SpriteState,TextureRegion[]> animations = null;
    public static HashMap<SpriteComponent.SpriteState, TextureRegion[]> getAnimations(Texture texture){
        if(animations != null)
            return animations;

        HashMap<SpriteComponent.SpriteState,TextureRegion[]> animations = new HashMap<SpriteComponent.SpriteState,TextureRegion[]>();

        //idle
        animations.put(SpriteComponent.SpriteState.IDLE,new TextureRegion[]{
                new TextureRegion(texture,0,0,48,48),
                new TextureRegion(texture,48,0,48,48),
                new TextureRegion(texture,48*2,0,48,48)});
        //pose
        animations.put(SpriteComponent.SpriteState.POSE,new TextureRegion[]{
                new TextureRegion(texture,0,48,48,48),
                new TextureRegion(texture,48,48,48,48),
                new TextureRegion(texture,48*2,48,48,48)});
        //wdown
        animations.put(SpriteComponent.SpriteState.MOVE_DOWN,new TextureRegion[]{
                new TextureRegion(texture,0,48*2,48,48),
                new TextureRegion(texture,48,48*2,48,48),
                new TextureRegion(texture,48*2,48*2,48,48)});
        //wsideright
        animations.put(SpriteComponent.SpriteState.MOVE_RIGHT,new TextureRegion[]{
                new TextureRegion(texture,0,48*3,48,48),
                new TextureRegion(texture,48,48*3,48,48),
                new TextureRegion(texture,48*2,48*3,48,48)});

        //wsideleft
        animations.put(SpriteComponent.SpriteState.MOVE_LEFT,new TextureRegion[]{
                new TextureRegion(texture,0,48*3,48,48),
                new TextureRegion(texture,48,48*3,48,48),
                new TextureRegion(texture,48*2,48*3,48,48)});
        //MOVE_UP
        animations.put(SpriteComponent.SpriteState.MOVE_UP,new TextureRegion[]{
                new TextureRegion(texture,0,48*4,48,48),
                new TextureRegion(texture,48,48*4,48,48),
                new TextureRegion(texture,48*2,48*4,48,48)});

        //ATTACK_DOWN
        animations.put(SpriteComponent.SpriteState.ATTACK_DOWN,new TextureRegion[]{
                new TextureRegion(texture,0,48*5,48,48),
                new TextureRegion(texture,48,48*5,48,48),
                new TextureRegion(texture,48*2,48*5,48,48)});

        //ATTACK_RIGHT
        animations.put(SpriteComponent.SpriteState.ATTACK_RIGHT,new TextureRegion[]{
                new TextureRegion(texture,0,48*6,48,48),
                new TextureRegion(texture,48,48*6,48,48),
                new TextureRegion(texture,48*2,48*6,48,48)});

        //ATTACK_LEFT
        animations.put(SpriteComponent.SpriteState.ATTACK_LEFT,new TextureRegion[]{
                new TextureRegion(texture,0,48*6,48,48),
                new TextureRegion(texture,48,48*6,48,48),
                new TextureRegion(texture,48*2,48*6,48,48)});
        //ATTACK_DOWN
        animations.put(SpriteComponent.SpriteState.ATTACK_UP,new TextureRegion[]{
                new TextureRegion(texture,0,48*7,48,48),
                new TextureRegion(texture,48,48*7,48,48),
                new TextureRegion(texture,48*2,48*7,48,48)});
        //JUMP
        animations.put(SpriteComponent.SpriteState.JUMP,new TextureRegion[]{
                new TextureRegion(texture,0,48*8,48,48),
                new TextureRegion(texture,48,48*8,48,48),
                new TextureRegion(texture,48*2,48*8,48,48)});
        //SLIDE
        animations.put(SpriteComponent.SpriteState.SLIDE,new TextureRegion[]{
                new TextureRegion(texture,0,48*9,48,48),
                new TextureRegion(texture,48,48*9,48,48),
                new TextureRegion(texture,48*2,48*9,48,48)});
        //DIE
        animations.put(SpriteComponent.SpriteState.DIE,new TextureRegion[]{
                new TextureRegion(texture,0,48*10,48,48),
                new TextureRegion(texture,48,48*10,48,48),
                new TextureRegion(texture,48*2,48*10,48,48)});
        for(TextureRegion t : animations.get(SpriteComponent.SpriteState.MOVE_LEFT)){
            t.flip(true,false);
        }

        for(TextureRegion t : animations.get(SpriteComponent.SpriteState.ATTACK_LEFT)){
            t.flip(true,false);
        }

        return animations;


    }




    public static void createPlayer(PooledEngine e, float x, float y, HashMap<SpriteComponent.SpriteState,TextureRegion[]> sprite){
        Entity player = e.createEntity();
        PositionComponent pc = e.createComponent(PositionComponent.class);
        VelocityComponent vc = e.createComponent(VelocityComponent.class);
        SpriteComponent sc = e.createComponent(SpriteComponent.class);

        pc.x = pc.y = 0;
        vc.y = vc.x = 0;
        sc.spriteStates = sprite;
        sc.animationStep = 0;
        sc.state = SpriteComponent.SpriteState.IDLE;

        player.add(pc);
        player.add(vc);
        player.add(sc);


        player.add( e.createComponent(PlayerComponent.class));
        player.add( e.createComponent(RenderableComponent.class));

        e.addEntity(player);
    }


}
