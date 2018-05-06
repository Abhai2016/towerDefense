package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.google.gson.Gson;

class CustomLevel extends LevelBase {

    CustomLevel() {
        super();
    }


    @Override
    public void saveLevel() {
        for (int i = 0; i < GameWorld.MAP_HEIGHT_MAX; i++)
            for (int j = 0; j < GameWorld.MAP_WITH_MAX; j++)
                mapMask[i][j] = GameWorld.getInstance().getEditGrid().get(i).get(j).getState();

        Gson gson = new Gson();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + gson.toJson(mapMask) + "' WHERE id = 0;";
        GameWorld.getInstance().setShowSaveText(true);
        super.saveLevel();
    }


    @Override
    public void loadLevel(int levelID) {
        loadQuery = "SELECT content FROM Levels WHERE id = 0";
        super.loadLevel(0);
    }
}
