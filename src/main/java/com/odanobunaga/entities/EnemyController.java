package com.odanobunaga.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.behavior.AStarPathFinder;
import de.gurkenlabs.litiengine.entities.behavior.EntityNavigator;
import de.gurkenlabs.litiengine.entities.behavior.IBehaviorController;

public class EnemyController implements IBehaviorController {
    private final Creature enemy;
    private final EntityNavigator navigator;
    private int distanceFromTarget;
    private final int CHASE_RADIUS;

    public EnemyController(Creature enemy) {
        this.enemy = enemy;
        this.distanceFromTarget = 0;
        this.CHASE_RADIUS = 250;
        this.navigator = new EntityNavigator(this.enemy, new AStarPathFinder(Game.world().environment().getMap()));
    }


    @Override
    public IEntity getEntity() {
        return this.enemy;
    }

    private void chaseTarget(){
        if (!this.navigator.isNavigating()) {
            this.navigator.navigate(Player.instance().getCenter());
        }
    }

    @Override
    public void update() {
        if(this.enemy.isDead()){
            return;
        }

        this.distanceFromTarget = (int) this.getEntity().getCenter().distance(Player.instance().getCenter());

        if (this.distanceFromTarget < this.CHASE_RADIUS) {
            this.chaseTarget();
        } else {
            this.navigator.stop();
        }
    }
}
