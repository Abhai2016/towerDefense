package com.abhai.towerDefense.levels;

import com.badlogic.gdx.utils.Json;

public class Level3 extends LevelBase {

    Level3() {
        super();
    }


    @Override
    public void saveLevel() {
        Json json = new Json();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + json.toJson(mapMask) + "' WHERE id = 3;";
        super.saveLevel();
    }


    @Override
    public void loadLevel(int levelID) {
        loadQuery = "SELECT content FROM Levels WHERE id = 3";
        super.loadLevel(3);
    }

}
