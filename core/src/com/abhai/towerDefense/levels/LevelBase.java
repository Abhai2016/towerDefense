package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.DataBaseHandler;

public class LevelBase {
    private DataBaseHandler dataBaseHandler;
    private GameWorld gameWorld;
    int[][] mapMask;



    LevelBase() {
        gameWorld = GameWorld.getInstance();
        dataBaseHandler = DataBaseHandler.getInstance();
    }


    void setCreateQuery() {

    }


    void save() {

    }


    public void load() {
        for (int ay = 0; ay < gameWorld.getGrid().size(); ay++)
            for (int ax = 0; ax < gameWorld.getGrid().get(0).size(); ax++)
                gameWorld.getGrid().get(ay).get(ax).setState(mapMask[ay][ax]);
    }
}
