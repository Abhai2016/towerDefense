package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.Game;

public class EnemySoldier extends EnemyBase {

    @Override
    public void init() {
        kind = EnemyBase.KIND_SOLDER;
        health = 100;
        speedX = 100;
        speedY = 100;

        setRegion(64, 0, 32,32);
        setSize(32, 32);
        super.init();
    }


    @Override
    public void update(float delta) {
        setX(getX() + speedX * delta);
        setY(getY() + speedY * delta);

        // Инвертируем скорость движения врага
        // если он достиг границ экрана
        if (getX() >= Game.GAME_WITH || getX() <= 0)
            speedX *= -1;
        if (getY() >= Game.GAME_HEIGHT || getY() <= 0)
            speedY *= -1;
    }
}
