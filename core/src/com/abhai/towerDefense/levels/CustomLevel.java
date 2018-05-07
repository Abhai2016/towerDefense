package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.utils.Json;

class CustomLevel extends LevelBase {

    CustomLevel() {
        super();
    }


    @Override
    public void saveLevel() {
        for (int i = 0; i < GameWorld.MAP_HEIGHT_MAX; i++)
            for (int j = 0; j < GameWorld.MAP_WITH_MAX; j++)
                mapMask[i][j] = GameWorld.getInstance().getEditGrid().get(i).get(j).getState();

        Json json = new Json();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + json.toJson(mapMask) + "' WHERE id = 0;";
        GameWorld.getInstance().setShowSaveText(true);
        super.saveLevel();
    }


    @Override
    public void loadLevel(int levelID) {
        loadQuery = "SELECT content FROM Levels WHERE id = 0";
        super.loadLevel(0);
    }
}
