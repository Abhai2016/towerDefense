package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.DataBaseHandler;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;
import com.google.gson.Gson;

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
        mapMask = new int[GameWorld.MAP_HEIGHT_MAX][GameWorld.MAP_WITH_MAX];
        gameWorld = GameWorld.getInstance();
        dataBaseHandler = DataBaseHandler.getInstance();
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL(createQuery);
            DatabaseCursor cursor = dataBaseHandler.rawQuery("SELECT * FROM Levels WHERE id = 1");
            if (!cursor.next()) {
                Gson gson = new Gson();
                dataBaseHandler.execSQL("INSERT INTO Levels VALUES(1, '" + gson.toJson(mapMask) + "');");
            }
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }



    public void loadCustomLevel() {

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
                for (int j = 0; j < jsonElements.length(); j++) {
                    mapMask[i][j] = jsonElements.getInt(j);
                    gameWorld.getGrid().get(i).get(j).setState(mapMask[i][j]);
                }
            }
            dataBaseHandler.closeDatabase();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }


    public void saveCustomLevel() {
        for (int i = 0; i < GameWorld.MAP_HEIGHT_MAX; i++)
            for (int j = 0; j < GameWorld.MAP_WITH_MAX; j++)
                mapMask[i][j] = GameWorld.getInstance().getEditGrid().get(i).get(j).getState();

        Gson gson = new Gson();
        insertOrUpdateQuery = "UPDATE Levels SET content = '" + gson.toJson(mapMask) + "' WHERE id = 1;";
        saveStoryLevel();
    }


    public void saveStoryLevel() {
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL(insertOrUpdateQuery);
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }
}
