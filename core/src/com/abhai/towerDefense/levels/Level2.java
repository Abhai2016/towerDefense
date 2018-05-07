package com.abhai.towerDefense.levels;

import com.badlogic.gdx.utils.Json;

class Level2 extends LevelBase {

    Level2() {
        super();
    }


    @Override
    public void saveLevel() {
        Json json = new Json();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + json.toJson(mapMask) + "' WHERE id = 2;";
        super.saveLevel();
    }


    @Override
    public void loadLevel(int levelID) {
        loadQuery = "SELECT content FROM Levels WHERE id = 2";
        super.loadLevel(2);
    }
}
