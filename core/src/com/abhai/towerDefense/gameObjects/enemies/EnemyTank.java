package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;

public class EnemyTank extends EnemyBase {

    public EnemyTank() {
        super("images/enemies/enemyTank.PNG");
    }


    @Override
    public void init(float tileX, float tileY, float tileTargetX, float tileTargetY) {
        kind = ENEMY_TANK;
        health = 4;
        maxHealth = (int)health;
        defSpeed = 60;

        setOrigin(Cell.CELL_SIZE / 2, Cell.CELL_SIZE / 2);
        super.init(tileX, tileY, tileTargetX, tileTargetY);
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if (!isWay) {
            gameWorld.getCacheEnemyTanks().set(this);
            isDead = true;
        }
    }
}
