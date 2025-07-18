package com.odanobunaga.logic;

import de.gurkenlabs.litiengine.entities.ICollisionEntity;
import de.gurkenlabs.litiengine.entities.behavior.AStarPathFinder;
import de.gurkenlabs.litiengine.environment.tilemap.IMap;

import java.awt.geom.Point2D;

public class NoCollisionAStarPathFinder extends AStarPathFinder {
    public NoCollisionAStarPathFinder(IMap map) {
        super(map);
    }

    @Override
    protected boolean intersectsWithAnyCollisionBox(ICollisionEntity entity, Point2D start, Point2D target) {
        return false;
    }
}
