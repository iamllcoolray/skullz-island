package com.odanobunaga.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.physics.Force;

import java.awt.geom.Point2D;

@EntityInfo(width = 96, height = 96)
@CollisionInfo(collision = true, valign = Valign.MIDDLE)
public class Ball extends Prop implements IUpdateable {
    private static final double BALL_SPEED = 500.0;
    private static final double FORCE_RADIUS = 50.0;
    private Point2D direction;
    private Force force;

    public Ball(Point2D origin, Point2D target) {
        super("ball");

        this.setLocation(origin);
        direction = getDirection(origin, target);
        force = new Force(direction, (float) BALL_SPEED, (float) FORCE_RADIUS);
        Game.physics().add(this);

        Game.loop().attach(this);

        Game.loop().perform(2000, this::dispose);
    }

    private void dispose() {
        Game.loop().detach(this);
        Game.world().environment().remove(this);
    }

    private Point2D getDirection(Point2D origin, Point2D target) {
        double dx = target.getX() - origin.getX();
        double dy = target.getY() - origin.getY();
        double length = Math.sqrt(dx * dx + dy * dy);
        return length == 0 ? new Point2D.Double(0, 0) : new Point2D.Double(dx / length, dy / length);
    }

    @Override
    public void update() {
        double deltaTime = Game.loop().getDeltaTime() / 1000.0; // convert ms to seconds

        // Compute movement
        double moveX = direction.getX() * BALL_SPEED * deltaTime;
        double moveY = direction.getY() * BALL_SPEED * deltaTime;

        // Move the ball
        this.setLocation(this.getX() + moveX, this.getY() + moveY);
    }
}
