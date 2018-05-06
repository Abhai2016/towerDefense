package com.abhai.towerDefense.levels;

import com.google.gson.Gson;

class Level2 extends LevelBase {

    Level2() {
        super();
    }


    @Override
    public void saveLevel() {
        Gson gson = new Gson();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + gson.toJson(mapMask) + "' WHERE id = 2;";
        super.saveLevel();
    }


    @Override
    public void loadLevel(int levelID) {
        loadQuery = "SELECT content FROM Levels WHERE id = 2";
        super.loadLevel(2);
    }
}
