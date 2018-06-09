package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;

public class EnemySoldier extends EnemyBase {


    public EnemySoldier() {
        super("images/enemies/enemySoldier.PNG");
    }


    @Override
    public void init(float posX, float posY, float targetX, float targetY) {
        kind = ENEMY_SOLDER;
        health = 1;
        maxHealth = (int)health;
        defSpeed = 100;

        setOrigin(Cell.CELL_SIZE / 2, Cell.CELL_SIZE / 2);
        super.init(posX, posY, targetX, targetY);
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if (!isWay) {
            gameWorld.getCacheEnemySoldiers().set(this);
            isDead = true;
        }
    }
}
