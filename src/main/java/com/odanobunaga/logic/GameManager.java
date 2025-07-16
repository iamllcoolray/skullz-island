package com.odanobunaga.logic;

import com.odanobunaga.entities.Player;
import com.odanobunaga.entities.Skulls;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.LocationLockCamera;

public class GameManager {
    private GameManager(){

    }

    public static void init(){
        Camera camera = new LocationLockCamera(Player.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);

        Game.world().onLoaded(e -> {
            Spawnpoint enter = e.getSpawnpoint("enter");

            if(enter != null){
                enter.spawn(Player.instance());
            }
        });

        CreatureMapObjectLoader.registerCustomCreatureType(Skulls.class);
    }

}
