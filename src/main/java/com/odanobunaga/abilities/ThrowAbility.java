package com.odanobunaga.abilities;

import com.odanobunaga.entities.Ball;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityExecution;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.input.Input;

import java.awt.geom.Point2D;

@AbilityInfo(cooldown = 500, duration = 300, value = 240)
public class ThrowAbility extends Ability {
    public ThrowAbility(Creature executor) {
        super(executor);
    }

    @Override
    public AbilityExecution cast() {
        if(!this.canCast()){
            return null;
        }

        Point2D origin = this.getExecutor().getLocation();

        Point2D target = Input.mouse().getMapLocation();

        Ball ball = new Ball(origin, target);
        Game.world().environment().add(ball);

        return super.cast();
    }
}
