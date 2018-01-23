package com.abhai.towerDefense.levels;

import com.google.gson.Gson;

class Level2 extends LevelBase {

    Level2() {
        super();
    }


    @Override
    public void saveStoryLevel() {
        Gson gson = new Gson();
        insertOrUpdateQuery = "update Levels set content = '" + gson.toJson(mapMask) + "' where id = 2;";
        super.saveStoryLevel();
    }


    @Override
    public void loadStoryLevel() {
        loadQuery = "select content from Levels where id = 2";
        super.loadStoryLevel();
    }
}
