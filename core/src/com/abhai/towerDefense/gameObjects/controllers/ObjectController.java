package com.abhai.towerDefense.gameObjects.controllers;

import com.abhai.towerDefense.gameObjects.IGameObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class ObjectController {
    private ArrayList<IGameObject> gameObjects = new ArrayList<IGameObject>();


    public void add(IGameObject gameObject) {
        gameObjects.add(gameObject);
    }


    public void clear() {
        gameObjects.clear();
    }


    public void draw(SpriteBatch spriteBatch) {
        for (IGameObject gameObject : gameObjects)
            gameObject.draw(spriteBatch);
    }


    public boolean isEmpty() {
        return gameObjects.isEmpty();
    }


    public IGameObject get(int i) {
        return gameObjects.get(i);
    }


    public int size() {
        return gameObjects.size();
    }


    public void update(float delta) {
        for (IGameObject gameObject : gameObjects) {
            gameObject.update(delta);

            if (gameObject.isDead()) {
                gameObjects.remove(gameObject);
                break;
            }
        }
    }
}
