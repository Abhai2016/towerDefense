package com.abhai.towerDefense.levels;


import com.badlogic.gdx.utils.Json;

class Level1 extends LevelBase {

    Level1() {
        super();
    }


    @Override
    public void saveLevel() {
        Json json = new Json();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + json.toJson(mapMask) + "' WHERE id = 1;";
        super.saveLevel();
    }


    @Override
    public void loadLevel(int levelID) {
        loadQuery = "SELECT content FROM Levels WHERE id = 1";
        super.loadLevel(1);
    }
}
