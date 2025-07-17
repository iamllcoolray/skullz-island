package com.odanobunaga.entities;

import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;

@EntityInfo(width = 96, height = 96)
@MovementInfo(velocity = 120)
@CollisionInfo(collisionBoxWidth = 60, collisionBoxHeight = 96, collision = true, valign = Valign.MIDDLE)
public class Skull extends Creature {
    public Skull(){
        super("skull");

        this.addController(new EnemyController(this));
    }
}
