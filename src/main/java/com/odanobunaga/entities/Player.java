package com.odanobunaga.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

@EntityInfo(width = 96, height = 96)
@MovementInfo(velocity = 240)
@CollisionInfo(collisionBoxWidth = 72, collisionBoxHeight = 96, collision = true, valign = Valign.MIDDLE_DOWN)
public class Player extends Creature implements IUpdateable {
    private static Player instance;
    private final Sound walking;

    private Player(){
        super("player");

        walking = Resources.sounds().get("walking-on-grass");
    }

    public static Player instance() {
        if(instance == null){
            instance = new Player();
        }

        return instance;
    }

    @Override
    public void update() {

    }

    @Override
    protected IMovementController createMovementController() {
        return new KeyboardEntityController<>(this);
    }
}
