package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.DataBaseHandler;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;

import org.json.JSONArray;
import org.json.JSONException;

public class LevelBase {
    private static final String createQuery = "create table if not exists Levels (id integer primary key, content text not null);";

    private GameWorld gameWorld;
    private DataBaseHandler dataBaseHandler;

    int[][] mapMask;
    String insertOrUpdateQuery;
    String loadQuery;



    LevelBase() {
        gameWorld = GameWorld.getInstance();
        dataBaseHandler = DataBaseHandler.getInstance();
        mapMask = new int[GameWorld.MAP_HEIGHT_MAX][GameWorld.MAP_WITH_MAX];
    }


    public void saveStoryLevel() {
        try {
            dataBaseHandler.execSQL(createQuery);
            dataBaseHandler.execSQL(insertOrUpdateQuery);
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }


    public void loadStoryLevel() {
        try {
            DatabaseCursor cursor = dataBaseHandler.rawQuery(loadQuery);
            JSONArray jsonArray = new JSONArray(cursor.getString(0));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray jsonElements = jsonArray.getJSONArray(i);
                for (int j = 0; j < jsonElements.length(); j++)
                    mapMask[i][j] = jsonElements.getInt(j);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int ay = 0; ay < GameWorld.MAP_HEIGHT_MAX; ay++)
            for (int ax = 0; ax < GameWorld.MAP_WITH_MAX; ax++)
                gameWorld.getGrid().get(ay).get(ax).setState(mapMask[ay][ax]);
    }


    public void loadCustomLevel() {

    }



    public void saveCustomLevel() {

    }
}
