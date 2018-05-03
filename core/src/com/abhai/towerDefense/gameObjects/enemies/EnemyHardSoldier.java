package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;

public class EnemyHardSoldier extends EnemyBase {

    public EnemyHardSoldier() {
        super("images/enemies/enemyHardSoldier.PNG");
    }


    @Override
    public void init(float posX, float posY, float targetX, float targetY) {
        kind = ENEMY_HARD_SOLDER;
        health = 2;
        defSpeed = 100;

        setOrigin(Cell.CELL_SIZE / 2, Cell.CELL_SIZE / 2);
        super.init(posX, posY, targetX, targetY);
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if (!isWay) {
            gameWorld.getCacheEnemyHardSoldiers().set(this);
            isDead = true;
        }
    }
}
