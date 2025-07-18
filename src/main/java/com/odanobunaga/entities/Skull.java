package com.odanobunaga.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.graphics.IRenderable;

import java.awt.*;

@EntityInfo(width = 60, height = 60)
@MovementInfo(velocity = 120)
@CollisionInfo(collisionBoxWidth = 60, collisionBoxHeight = 60, collision = false, valign = Valign.MIDDLE)
public class Skull extends Creature {
    public Skull(){
        super("skull");
        this.addController(new EnemyController(this));
    }

    public void dispose(){
        Game.world().environment().remove(this);
    }
}
