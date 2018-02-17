package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.google.gson.Gson;

public class CustomLevel extends LevelBase {

    CustomLevel() {
        super();
    }


    @Override
    public void loadCustomLevel() {
        GameWorld.getInstance().clearGrid();
        super.loadCustomLevel();
    }


    @Override
    public void saveCustomLevel() {
        for (int i = 0; i < GameWorld.MAP_HEIGHT_MAX; i++)
            for (int j = 0; j < GameWorld.MAP_WITH_MAX; j++)
                mapMask[i][j] = GameWorld.getInstance().getGrid().get(i).get(j).getState();

        Gson gson = new Gson();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + gson.toJson(mapMask) + "' WHERE id = 1;";
        super.saveStoryLevel();
    }
}
