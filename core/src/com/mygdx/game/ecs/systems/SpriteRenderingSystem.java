package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ecs.ECSEngine;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.animation.RenderableComponent;
import com.mygdx.game.ecs.components.animation.SpriteComponent;

public class SpriteRenderingSystem extends IteratingSystem {
    SpriteBatch batch;
    public SpriteRenderingSystem(SpriteBatch batch) {
        super(Family.all(RenderableComponent.class,SpriteComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpriteComponent sc = ECSEngine.SpriteCM.get(entity);
        PositionComponent pc = ECSEngine.PositionCM.get(entity);

        batch.draw(sc.spriteStates.get(sc.state)[sc.animationStep],pc.x,pc.y);
    }
}
