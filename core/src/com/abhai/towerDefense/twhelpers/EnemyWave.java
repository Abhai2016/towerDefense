package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EnemyWave {
    private static final int INTERVAL = 10;
    private int startDelay;

    private GameWorld gameWorld;
    private ArrayList<EnemyInformation> typeOfEnemies;

    private boolean isStarted;
    private int currentIndex;
    private int respawnInterval;
    private int interval;




    public EnemyWave(int startDelay, int respawnInterval) {
        gameWorld = GameWorld.getInstance();
        typeOfEnemies = new ArrayList<EnemyInformation>();

        this.startDelay = startDelay;
        this.respawnInterval = respawnInterval;
    }





    public void add(int kind, int count, Vector2 startPoint, Vector2 finishPoint) {
        typeOfEnemies.add(new EnemyInformation(kind, count, startPoint, finishPoint));
    }

    public boolean isFinished() {
        return !isStarted;
    }


    private boolean nextEnemy() {
        int size = typeOfEnemies.size();

        if (currentIndex < size) {
            for (int i = 0; i < size; i++) {
                EnemyInformation enemyInformation = typeOfEnemies.get(i);
                if (enemyInformation.index < enemyInformation.count) {
                    gameWorld.newEnemy(enemyInformation.typeOfEnemy, enemyInformation.startPoint, enemyInformation.finishPoint);
                    enemyInformation.index++;
                } else
                    currentIndex++;
            }
            return true;
        } else
             return false;
    }



    public void update(float delta) {
        startDelay -= INTERVAL * delta;

        if (isStarted && startDelay <= 0) {
            interval -= INTERVAL * delta;

            if (interval <= 0) {
                if (!nextEnemy())
                    isStarted = false;
                interval = respawnInterval;
            }
        }
    }


    public void startWave() {
        if (!isStarted) {
            currentIndex = 0;
            isStarted = true;
        }
    }
}
