package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.mygdx.game.ecs.ECSEngine;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;


    public MovementSystem() {}

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            PositionComponent position = ECSEngine.PositionCM.get(entity);
            VelocityComponent velocity = ECSEngine.VelocityCM.get(entity);

            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;

            if(Math.abs(velocity.x) > Math.abs(velocity.y) ){

            }
        }
    }

}