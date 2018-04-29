package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.badlogic.gdx.math.Vector2;

public class EnemySoldier extends EnemyBase {

    @Override
    public void init(float posX, float posY, float targetX, float targetY) {
        kind = EnemyBase.KIND_SOLDER;
        health = 1;
        defSpeed = 100;

        setRegion(64, 0, Cell.CELL_SIZE, Cell.CELL_SIZE);
        setSize(Cell.CELL_SIZE, Cell.CELL_SIZE);
        setOrigin(Cell.CELL_SIZE / 2, Cell.CELL_SIZE / 2);
        super.init(posX, posY, targetX, targetY);
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if (isWay) {
            setX(getX() + speed.x * delta);
            setY(getY() + speed.y * delta);

            Vector2 currentPoint = new Vector2(getX(), getY());
            Vector2 targetPoint = new Vector2(gameWorld.toPix(wayTarget.x), gameWorld.toPix(wayTarget.y));

            if (currentPoint.epsilonEquals(targetPoint, defSpeed / 50)) {
                position.x = gameWorld.toTile(getX());
                position.y = gameWorld.toTile(getY());

                wayIndex++;
                setNextTarget();
            }
        } else {
            gameWorld.getCacheEnemySoldiers().set(this);
            isDead = true;
        }
    }
}
