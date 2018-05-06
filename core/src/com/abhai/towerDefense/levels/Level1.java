package com.abhai.towerDefense.levels;

import com.google.gson.Gson;

class Level1 extends LevelBase {

    Level1() {
        super();
    }


    @Override
    public void saveLevel() {
        Gson gson = new Gson();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + gson.toJson(mapMask) + "' WHERE id = 1;";
        super.saveLevel();
    }


    @Override
    public void loadLevel(int levelID) {
        loadQuery = "SELECT content FROM Levels WHERE id = 1";
        super.loadLevel(1);
    }
}
