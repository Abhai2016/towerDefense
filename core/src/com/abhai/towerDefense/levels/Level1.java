package com.abhai.towerDefense.levels;

import com.google.gson.Gson;

class Level1 extends LevelBase {

    Level1() {
        super();
    }


    @Override
    public void saveStoryLevel() {
        Gson gson = new Gson();
        insertOrUpdateQuery = "update Levels set content = '" + gson.toJson(mapMask) + "' where id = 1;";
        super.saveStoryLevel();
    }


    @Override
    public void loadStoryLevel() {
        loadQuery = "select content from Levels where id = 1";
        super.loadStoryLevel();
    }
}
