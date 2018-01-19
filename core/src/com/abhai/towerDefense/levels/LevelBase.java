package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameWorld.GameWorld;

public class LevelBase {
    private GameWorld gameWorld;
    int[][] mapMask;



    LevelBase() {
        gameWorld = GameWorld.getInstance();
    }


    public void load() {
        for (int ay = 0; ay < gameWorld.getGrid().size(); ay++)
            for (int ax = 0; ax < gameWorld.getGrid().get(0).size(); ax++)
                gameWorld.getGrid().get(ay).get(ax).setState(mapMask[ay][ax]);
    }
}
