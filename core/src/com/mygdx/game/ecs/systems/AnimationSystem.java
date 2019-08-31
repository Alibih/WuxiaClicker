package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.ecs.ECSEngine;
import com.mygdx.game.ecs.components.VelocityComponent;
import com.mygdx.game.ecs.components.animation.SpriteComponent;

public class AnimationSystem extends IteratingSystem {
    public AnimationSystem() {
        super(Family.all(SpriteComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpriteComponent sc = ECSEngine.SpriteCM.get(entity);
        VelocityComponent vc = ECSEngine.VelocityCM.get(entity);

        sc.updateCounter += deltaTime;
        if(sc.updateRate <= sc.updateCounter) {
            sc.updateCounter = 0f;
            sc.animationStep = (sc.animationStep + 1) % sc.spriteStates.get(sc.state).length;
        }
    }
}
