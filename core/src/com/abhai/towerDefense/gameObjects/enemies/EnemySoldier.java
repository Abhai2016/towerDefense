package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.Cell;
import com.badlogic.gdx.math.Vector2;

public class EnemySoldier extends EnemyBase {

    @Override
    public void init(int posX, int posY, int targetX, int targetY) {
        kind = EnemyBase.KIND_SOLDER;
        health = 100;
        defSpeed = 100;

        setRegion(64, 0, 32,32);
        setSize(32, 32);
        super.init(posX, posY, targetX, targetY);
    }


    @Override
    public void update(float delta) {
        if (isWay) {
            setX(getX() + speed.x * delta);
            setY(getY() + speed.y * delta);

            Vector2 currentPoint = new Vector2(getX(), getY());
            Vector2 targetPoint = new Vector2(gameWorld.toPix(wayTarget.x), gameWorld.toPix(wayTarget.y));

            if (currentPoint.epsilonEquals(targetPoint, defSpeed / 100)) {
                position.x = gameWorld.toTile(getX());
                position.y = gameWorld.toTile(getY());

                wayIndex++;
                setNextTarget();
            }
        }
    }
}
