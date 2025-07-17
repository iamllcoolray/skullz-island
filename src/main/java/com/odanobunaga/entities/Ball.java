package com.odanobunaga.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.physics.Force;

import java.awt.geom.Point2D;

@EntityInfo(width = 36, height = 36)
@CollisionInfo(collisionBoxWidth = 36, collisionBoxHeight = 36, collision = false, valign = Valign.MIDDLE)
public class Ball extends Prop implements IUpdateable {
    private static final double BALL_SPEED = 500.0;
    private static final double FORCE_RADIUS = 5.0;
    private final Point2D direction;
    private final Force force;

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

        // Check for collisions with any enemy
        for (IEntity entity : Game.world().environment().getEntities()) {
            if (entity instanceof Skull && this.getBoundingBox().intersects(entity.getBoundingBox())) {
                System.out.println("Ball hit enemy: " + entity.getName());
                this.dispose(); // remove the ball
                // Optional: Damage enemy or trigger effect
                ((Skull) entity).dispose(); // You define this
                break;
            }
        }
    }
}
