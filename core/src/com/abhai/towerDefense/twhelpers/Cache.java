package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.gameObjects.IGameObject;
import com.abhai.towerDefense.gameObjects.bullets.GunBullet;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;
import com.abhai.towerDefense.gameObjects.towers.GunTower;
import com.abhai.towerDefense.gameWorld.GameWorld;

import java.util.ArrayList;

public class Cache {
    private ArrayList<IGameObject> gameObjects;

    private int currentIndex;
    private int typeOfGameObject;



    public Cache(int typeOfGameObject, int capacity) {
        this.typeOfGameObject = typeOfGameObject;
        currentIndex = capacity - 1;
        gameObjects = new ArrayList<IGameObject>();

        for (int i = 0; i < capacity; i++)
           gameObjects.add(newInstance());
    }



    public IGameObject get() {
        if (currentIndex >= 0) {
            currentIndex--;
            return gameObjects.get(currentIndex + 1);
        } else
            return newInstance();
    }


    public void set(IGameObject gameObject) {
        currentIndex++;

        if (currentIndex == gameObjects.size())
            gameObjects.add(gameObject);
        else
            gameObjects.set(currentIndex, gameObject);
    }


    private IGameObject newInstance() {
        switch  (typeOfGameObject) {
            case GameWorld.ENEMY_SOLDIER:
                return new EnemySoldier();
            case GameWorld.GUN_BULLET:
                return new GunBullet();
            case GameWorld.GUN_TOWER:
                return new GunTower();
            default:
                return null;
        }
    }
}
