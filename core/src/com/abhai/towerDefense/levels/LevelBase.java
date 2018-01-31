package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.DataBaseHandler;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;

import org.json.JSONArray;
import org.json.JSONException;

public class LevelBase {
    private static final String createQuery = "CREATE TABLE IF NOT EXISTS Levels (id INTEGER PRIMARY KEY, content TEXT NOT NULL);";

    private GameWorld gameWorld;
    private DataBaseHandler dataBaseHandler;

    int[][] mapMask;
    String insertOrUpdateQuery;
    String loadQuery;



    LevelBase() {
        gameWorld = GameWorld.getInstance();
        dataBaseHandler = DataBaseHandler.getInstance();
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL(createQuery);
            //saveStoryLevels();
            // cuz every device has on his own version of database
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        mapMask = new int[GameWorld.MAP_HEIGHT_MAX][GameWorld.MAP_WITH_MAX];
    }


    public void saveStoryLevel() {
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL(createQuery);
            dataBaseHandler.execSQL(insertOrUpdateQuery);
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }


    public void loadStoryLevel() {
        JSONArray jsonArray;
        try {
            dataBaseHandler.openDatabase();
            DatabaseCursor cursor = dataBaseHandler.rawQuery(loadQuery);
            if (cursor.next())
                jsonArray  = new JSONArray(cursor.getString(0));
            else
                jsonArray = new JSONArray();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray jsonElements = jsonArray.getJSONArray(i);
                for (int j = 0; j < jsonElements.length(); j++)
                    mapMask[i][j] = jsonElements.getInt(j);
            }
            dataBaseHandler.closeDatabase();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLiteGdxException e) {
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
