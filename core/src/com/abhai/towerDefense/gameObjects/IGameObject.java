package com.abhai.towerDefense.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IGameObject {
    boolean isDead();

    void update(float delta);

    void draw(SpriteBatch spriteBatch);

    float getX();
}
