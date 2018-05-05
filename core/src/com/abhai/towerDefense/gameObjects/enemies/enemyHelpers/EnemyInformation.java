package com.abhai.towerDefense.gameObjects.enemies.enemyHelpers;

import com.badlogic.gdx.math.Vector2;

public class EnemyInformation {
    Vector2 startPoint;
    Vector2 finishPoint;

    int typeOfEnemy;
    int count;
    int index;



    EnemyInformation(int kind, int count, Vector2 startPoint, Vector2 finishPoint) {
        typeOfEnemy = kind;
        this.count = count;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        index = 0;
    }
}
