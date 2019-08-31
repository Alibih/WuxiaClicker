package com.mygdx.game.ecs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.game.ecs.components.PlayerComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.animation.SpriteComponent;
import com.mygdx.game.ecs.components.animation.TextureComponent;
import com.mygdx.game.ecs.components.VelocityComponent;
import com.mygdx.game.ecs.components.dungeon.ChunkComponent;
import com.mygdx.game.ecs.components.dungeon.TileComponent;
import com.mygdx.game.ecs.components.dungeon.TileValues;


public class ECSEngine extends PooledEngine {
    public static final ComponentMapper<TextureComponent> TextureCM = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<PositionComponent> PositionCM = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> VelocityCM = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<PlayerComponent> PlayerCM = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<SpriteComponent> SpriteCM = ComponentMapper.getFor(SpriteComponent.class);
    public static final ComponentMapper<ChunkComponent>  ChunkCM = ComponentMapper.getFor(ChunkComponent.class);
    public ECSEngine(){
        super(10,5000,10,5000);
    }


    public void update(float delta){

        super.update(delta);

        //update funs
    }

}
