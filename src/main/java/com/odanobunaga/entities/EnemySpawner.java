package com.odanobunaga.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.environment.tilemap.IMap;

import java.util.Random;

public class EnemySpawner implements IUpdateable {
    private final Random random = new Random();
    private int timer = 0;
    private final int interval = 300;
    public final int MAX_SPAWN = 10;

    @Override
    public void update() {
        timer--;
        if (timer <= 0) {
            long count = Game.world().environment().getEntities().stream()
                    .filter(e -> e instanceof Skull).count();

            if (count >= MAX_SPAWN) return;

            spawnEnemy();
            timer = interval;
        }
    }

    private void spawnEnemy() {
        // Get environment bounds
        IMap map = Game.world().environment().getMap();
        if (map == null) return;

        int mapWidth = map.getSizeInPixels().width;
        int mapHeight = map.getSizeInPixels().height;

        double x = random.nextInt(mapWidth);
        double y = random.nextInt(mapHeight);

        Skull enemy = new Skull();
        enemy.setLocation(x, y);
        Game.world().environment().add(enemy);
        System.out.println("Spawned enemy at (" + x + ", " + y + ")");
    }
}
