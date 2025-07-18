package com.odanobunaga.entities;

import com.odanobunaga.abilities.ThrowAbility;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

import java.awt.event.KeyEvent;

@EntityInfo(width = 96, height = 96)
@MovementInfo(velocity = 240)
@CollisionInfo(collisionBoxWidth = 72, collisionBoxHeight = 96, collision = true, valign = Valign.MIDDLE_DOWN)
public class Player extends Creature implements IUpdateable {
    private static Player instance;
    private final Sound walking;
    private final ThrowAbility throwAbility;
    private int updateSoundTimer;

    private Player(){
        super("player");

        walking = Resources.sounds().get("walking-on-grass");
        this.throwAbility = new ThrowAbility(this);
        this.updateSoundTimer = 0;
    }

    public static Player instance() {
        if(instance == null){
            instance = new Player();
        }

        return instance;
    }

    @Override
    public void update() {
        if(this.isDead()){
            return;
        }

        if(!this.isIdle() && Game.screens().current().getName().equals("INGAME-SCREEN")){
            if (updateSoundTimer == 0) {
                Game.audio().playSound(walking);
                updateSoundTimer = 20;
            }
            updateSoundTimer--;
        }

        if(this.isIdle() && updateSoundTimer != 0){
            updateSoundTimer = 0;
        }

        if(Input.mouse().isLeftButtonPressed()){
            throwAbility();
        }

        // Check for collisions with any enemy
        for (IEntity entity : Game.world().environment().getEntities()) {
            if (entity instanceof Skull && this.getBoundingBox().intersects(entity.getBoundingBox())) {
                this.respawn();
                ((Skull) entity).dispose();
                break;
            }
        }
    }

    private void respawn() {
        Game.world().environment().getSpawnpoint("enter").spawn(Player.instance());
    }

    @Override
    protected IMovementController createMovementController() {
        return new KeyboardEntityController<>(this);
    }

    @Action(description = "This performs a throw projectile ability for the player's entity")
    public void throwAbility(){
        if(this.throwAbility.isOnCooldown()){
            return;
        }

        this.throwAbility.cast();
    }
}
