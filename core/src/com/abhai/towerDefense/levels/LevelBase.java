package com.abhai.towerDefense.levels;

import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.DataBaseHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;
import com.badlogic.gdx.utils.Json;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

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
                Json json = new Json();
                FileHandle handle = Gdx.files.internal("data/storyLevels.dat");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(handle.read()));

                int mask[][] = new int[GameWorld.MAP_HEIGHT_MAX][GameWorld.MAP_WITH_MAX];
                for (int i = 0; i < GameWorld.MAP_HEIGHT_MAX; i++)
                    for (int j = 0; j < GameWorld.MAP_WITH_MAX; j++)
                        mask[i][j] = Cell.STATE_CELL_FREE;

                dataBaseHandler.execSQL("INSERT INTO Levels VALUES(0, '" + json.toJson(mask) + "');");
                dataBaseHandler.execSQL("INSERT INTO Levels VALUES(1, '" + bufferedReader.readLine() + "');");
            }
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
                    if (!gameWorld.isEdit())
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
