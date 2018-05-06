package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
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

    int[][] mapMask = {{1,1,1,1,1,1,0,3,0,1,1,1,1,1,1,1,1,1,0,3,0,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {3,0,0,0,0,0,0,0,0,2,1,1,2,1,2,1,1,2,0,0,0,0,0,0,0,0,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,2,0,0,0,2,1,1,1,1,1,1,1,2,0,0,0,2,1,1,1,1,1},
            {1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
            {1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
            {1,1,1,1,1,2,0,0,0,2,1,1,1,1,1,1,1,2,0,0,0,2,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,2,0,0,0,2,1,1,1,1,1,1,1,2,0,0,0,1,1,1,1,1,1},
            {1,1,1,1,1,1,0,4,0,1,1,1,1,1,1,1,1,1,0,4,0,1,1,1,1,1,1}};

    String insertOrUpdateQuery;
    String loadQuery;



    LevelBase() {
        gameWorld = GameWorld.getInstance();
        dataBaseHandler = DataBaseHandler.getInstance();
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL(createQuery);
            DatabaseCursor cursor = dataBaseHandler.rawQuery("SELECT * FROM Levels WHERE id = 1");
            if (!cursor.next()) {
                Gson gson = new Gson();

                int mask[][] = new int[GameWorld.MAP_HEIGHT_MAX][GameWorld.MAP_WITH_MAX];
                for (int i = 0; i < GameWorld.MAP_HEIGHT_MAX; i++)
                    for (int j = 0; j < GameWorld.MAP_WITH_MAX; j++)
                        mask[i][j] = Cell.STATE_CELL_FREE;

                dataBaseHandler.execSQL("INSERT INTO Levels VALUES(0, '" + gson.toJson(mask) + "');");
                dataBaseHandler.execSQL("INSERT INTO Levels VALUES(1, '" + gson.toJson(mapMask) + "');");
            }
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }


    public void loadLevel(int levelID) {
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
                    if (levelID == LevelManager.CUSTOM_LEVEL && gameWorld.isEdit())
                        gameWorld.getEditGrid().get(i).get(j).setState(mapMask[i][j]);
                    else
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


    public void saveLevel() {
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL(insertOrUpdateQuery);
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }
}
