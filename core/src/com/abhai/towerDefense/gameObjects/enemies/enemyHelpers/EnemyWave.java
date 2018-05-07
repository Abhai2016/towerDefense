package com.abhai.towerDefense.gameObjects.enemies.enemyHelpers;

import java.util.ArrayList;

public class EnemyWave {
    static final int INTERVAL = 10;

    ArrayList<EnemyInformation> typeOfEnemies;

    boolean isStarted;
    int currentIndex;
    int respawnInterval;
    int interval;
    int startDelay;
}
