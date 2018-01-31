package com.abhai.towerDefense.levels;

import com.google.gson.Gson;

class Level1 extends LevelBase {

    Level1() {
        super();
    }


    @Override
    public void saveStoryLevel() {
        Gson gson = new Gson();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + gson.toJson(mapMask) + "' WHERE id = 1;";
        super.saveStoryLevel();
    }


    @Override
    public void loadStoryLevel() {
        loadQuery = "SELECT content FROM Levels WHERE id = 1";
        super.loadStoryLevel();
    }
}
