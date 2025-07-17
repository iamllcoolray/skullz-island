package com.odanobunaga.abilities;

import com.odanobunaga.entities.Ball;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityExecution;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.entities.Creature;

@AbilityInfo(cooldown = 500, duration = 300, value = 240)
public class ThrowAbility extends Ability {
    public ThrowAbility(Creature executor) {
        super(executor);
    }

    @Override
    public AbilityExecution cast() {
        if(canCast()){
            System.out.println("Threw the ball.");
        }

        return super.cast();
    }
}
