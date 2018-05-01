package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.gameObjects.IGameObject;
import com.abhai.towerDefense.gameObjects.bullets.BulletBase;
import com.abhai.towerDefense.gameObjects.bullets.GunBullet;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;
import com.abhai.towerDefense.gameObjects.towers.GunTower;
import com.abhai.towerDefense.gameObjects.towers.TowerBase;

import java.util.Stack;

public class Cache {
    private Stack<IGameObject> gameObjects;

    private int currentIndex;
    private short typeOfGameObject;



    public Cache(short typeOfGameObject, int capacity) {
        this.typeOfGameObject = typeOfGameObject;
        currentIndex = capacity - 1;
        gameObjects = new Stack<IGameObject>();

        for (int i = 0; i < capacity; i++)
           gameObjects.push(newInstance());
    }



    public IGameObject get() {
        if (currentIndex >= 0) {
            currentIndex--;
            return gameObjects.pop();
        } else
            return newInstance();
    }


    public void set(IGameObject gameObject) {
        currentIndex++;
        gameObjects.push(gameObject);
    }


    private IGameObject newInstance() {
        switch  (typeOfGameObject) {
            case EnemyBase.ENEMY_SOLDER:
                return new EnemySoldier();
            case BulletBase.GUN_BULLET:
                return new GunBullet();
            case TowerBase.GUN_TOWER:
                return new GunTower();
            default:
                return null;
        }
    }
}
