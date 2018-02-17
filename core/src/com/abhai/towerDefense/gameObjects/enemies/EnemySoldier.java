package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class EnemySoldier extends EnemyBase {
    private static final int SOLDIER_SIZE = 32;

    @Override
    public void init(int posX, int posY, int targetX, int targetY) {
        kind = EnemyBase.KIND_SOLDER;
        health = 100;
        defSpeed = 100;

        setRegion(64, 0, SOLDIER_SIZE, SOLDIER_SIZE);
        setSize(SOLDIER_SIZE, SOLDIER_SIZE);
        setOrigin(SOLDIER_SIZE / 2, SOLDIER_SIZE / 2);
        super.init(posX, posY, targetX, targetY);
    }


    @Override
    public void update(float delta) {
        if (isWay) {
            setX(getX() + speed.x * delta);
            setY(getY() + speed.y * delta);

            Vector2 currentPoint = new Vector2(getX(), getY());
            Vector2 targetPoint = new Vector2(gameWorld.toPix(wayTarget.x), gameWorld.toPix(wayTarget.y));

            int epsilon;
            if (Gdx.graphics.getWidth() > Game.GAME_WITH)
                epsilon = defSpeed / 50;
            else
                epsilon = defSpeed / 100;

            if (currentPoint.epsilonEquals(targetPoint, epsilon)) {
                position.x = gameWorld.toTile(getX());
                position.y = gameWorld.toTile(getY());

                wayIndex++;
                setNextTarget();
            }
        }
    }
}
