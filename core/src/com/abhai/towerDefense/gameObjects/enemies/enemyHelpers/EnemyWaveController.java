package com.abhai.towerDefense.gameObjects.enemies.enemyHelpers;

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
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EnemyWaveController {
    private static final String createQuery = "CREATE TABLE IF NOT EXISTS Waves (id INTEGER PRIMARY KEY, content TEXT NOT NULL);";

    private DataBaseHandler dataBaseHandler;
    private GameWorld gameWorld;
    private ArrayList<EnemyWave> enemyWaves;

    private int waveOfIndex;





    public EnemyWaveController() {
        enemyWaves = new ArrayList<EnemyWave>();
        gameWorld = GameWorld.getInstance();
        waveOfIndex = 0;

        dataBaseHandler = DataBaseHandler.getInstance();
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL(createQuery);
            DatabaseCursor cursor = dataBaseHandler.rawQuery("SELECT * FROM Waves WHERE id = 1");
            if (!cursor.next()) {
                Json json = new Json();
                FileHandle handle = Gdx.files.internal("data/enemyWaves.dat");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(handle.read()));
                JSONArray allLevels = new JSONArray(json.fromJson(ArrayList.class, bufferedReader));

                for (int levelID = 0; levelID < allLevels.length(); levelID++)
                    dataBaseHandler.execSQL("INSERT INTO Waves VALUES(" + levelID + ", '" + allLevels.getString(levelID) + "');");
            }
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





    public void init(int levelId) {
        JSONArray jsonArray;
        String loadQuery = "SELECT content FROM Waves WHERE id = " + levelId;
        try {
            Json json = new Json();
            dataBaseHandler.openDatabase();
            DatabaseCursor cursor = dataBaseHandler.rawQuery(loadQuery);
            if (cursor.next())
                jsonArray = new JSONArray(json.fromJson(ArrayList.class, cursor.getString(0)));
            else
                jsonArray = new JSONArray();

            for (int i = 0; i < jsonArray.length(); i++)
                enemyWaves.add(json.fromJson(EnemyWave.class, jsonArray.getString(i)));

            dataBaseHandler.closeDatabase();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }


    public void clear() {
        enemyWaves.clear();
        waveOfIndex = 0;
    }


    private boolean nextEnemy(EnemyWave enemyWave) {
        int size = enemyWave.typeOfEnemies.size();

        if (enemyWave.currentIndex < size) {
            for (int i = 0; i < size; i++) {
                EnemyInformation enemyInformation = enemyWave.typeOfEnemies.get(i);
                if (enemyInformation.index < enemyInformation.count) {
                    gameWorld.newEnemy(enemyInformation.typeOfEnemy, enemyInformation.startPoint, enemyInformation.finishPoint);
                    enemyInformation.index++;
                } else
                    enemyWave.currentIndex++;
            }
            return true;
        } else
            return false;
    }



    public void update(float delta) {
        if (waveOfIndex < enemyWaves.size()) {
            EnemyWave enemyWave = enemyWaves.get(waveOfIndex);

            if (enemyWave.isStarted) {
                enemyWave.interval -= EnemyWave.INTERVAL * delta;
                enemyWave.startDelay -= EnemyWave.INTERVAL * delta;

                if (enemyWave.interval <= 0 && enemyWave.startDelay <= 0) {
                    if (!nextEnemy(enemyWave)) {
                        enemyWave.isStarted = false;
                        waveOfIndex++;
                        startWave();
                    }
                    enemyWave.interval = enemyWave.respawnInterval;
                }
            }
        }
    }


    public void startWave() {
        if (waveOfIndex < enemyWaves.size()) {
            enemyWaves.get(waveOfIndex).currentIndex = 0;
            enemyWaves.get(waveOfIndex).isStarted = true;
        }
    }
}
