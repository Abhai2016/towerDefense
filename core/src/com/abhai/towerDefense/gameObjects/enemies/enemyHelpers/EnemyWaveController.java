package com.abhai.towerDefense.gameObjects.enemies.enemyHelpers;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.levels.LevelManager;
import com.abhai.towerDefense.twhelpers.DataBaseHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EnemyWaveController {
    private static final String createQuery = "CREATE TABLE IF NOT EXISTS Waves (id INTEGER PRIMARY KEY, content TEXT NOT NULL);";

    private DataBaseHandler dataBaseHandler;
    private GameWorld gameWorld;
    private ArrayList<EnemyWave> enemyWaves;

    private int waveOfIndex;
    private int numberOfWave;





    public EnemyWaveController() {
        enemyWaves = new ArrayList<EnemyWave>();
        gameWorld = GameWorld.getInstance();
        waveOfIndex = 0;
        numberOfWave = 0;

        dataBaseHandler = DataBaseHandler.getInstance();
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL(createQuery);
            DatabaseCursor cursor = dataBaseHandler.rawQuery("SELECT * FROM Waves WHERE id = " + LevelManager.FIRST_STORY_LEVEL);
            if (!cursor.next()) {
                Gson gson = new Gson();
                FileHandle handle = Gdx.files.internal("data/enemyWaves.dat");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(handle.read()));

                JsonArray jsonArray = new JsonArray();
                jsonArray.addAll(gson.fromJson(bufferedReader.readLine(), JsonArray.class));
                dataBaseHandler.execSQL("INSERT INTO Waves VALUES('" + LevelManager.FIRST_STORY_LEVEL + "', '" + jsonArray.get(LevelManager.FIRST_STORY_LEVEL) + "');");
                dataBaseHandler.execSQL("INSERT INTO Waves VALUES('" + LevelManager.SECOND_STORY_LEVEL + "', '" + jsonArray.get(LevelManager.SECOND_STORY_LEVEL) + "');");
                dataBaseHandler.execSQL("INSERT INTO Waves VALUES('" + LevelManager.THIRD_STORY_LEVEL + "', '" + jsonArray.get(LevelManager.THIRD_STORY_LEVEL) + "');");
            }
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void init(int levelId) {
        JsonArray jsonArray = new JsonArray();
        String loadQuery = "SELECT content FROM Waves WHERE id = " + levelId;
        try {
            Gson gson = new Gson();
            dataBaseHandler.openDatabase();
            DatabaseCursor cursor = dataBaseHandler.rawQuery(loadQuery);
            if (cursor.next())
                jsonArray.addAll(gson.fromJson(cursor.getString(0), JsonArray.class));

            for (int i = 0; i < jsonArray.size(); i++)
                enemyWaves.add(gson.fromJson(jsonArray.get(i), EnemyWave.class));

            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }


    public void clear() {
        enemyWaves.clear();
        waveOfIndex = 0;
        numberOfWave = 0;
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
        } else if (waveOfIndex != 0 && gameWorld.getEnemies().isEmpty())
            gameWorld.setShowLevelCompleteText(true);
    }


    public void startWave() {
        if (waveOfIndex < enemyWaves.size()) {
            enemyWaves.get(waveOfIndex).currentIndex = 0;
            enemyWaves.get(waveOfIndex).isStarted = true;
            numberOfWave++;
        }
    }


    public int getNumberOfWave() {
        return numberOfWave;
    }


    public int size() {
        return enemyWaves.size();
    }
}
