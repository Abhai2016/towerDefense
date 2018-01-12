package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.Cell;

public class EnemySoldier extends EnemyBase {

    @Override
    public void init(int posX, int posY, int targetX, int targetY) {
        kind = EnemyBase.KIND_SOLDER;
        health = 100;
        speedX = 100;
        speedY = 100;

        setRegion(64, 0, 32,32);
        setSize(32, 32);
        super.init(posX, posY, targetX, targetY);
    }


    @Override
    public void update(float delta) {
        if (isWay) {
            setX(wayTarget.x * Cell.CELL_SIZE);
            setY(wayTarget.y * Cell.CELL_SIZE);

            position.x = (int) (getX() / Cell.CELL_SIZE);
            position.y = (int) (getY() / Cell.CELL_SIZE);

            wayIndex++;
            if (wayIndex == way.size())
                isWay = false;
            else
                wayTarget = way.get(wayIndex);
        }
    }
}
