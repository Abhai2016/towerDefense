package com.abhai.towerDefense.gameObjects.bullets;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.twhelpers.Amath;
import com.badlogic.gdx.math.Vector2;

public class RocketBullet extends BulletBase {
    private EnemyBase enemyTarget;

    public RocketBullet() {
        super("images/bullets/rocketBullet.PNG");
        damage = 2;
        bulletSpeed = 200;
    }


    public void init(float x, float y, double angle, EnemyBase enemyBase) {
        super.init(x, y, angle);
        enemyTarget = enemyBase;
        gameWorld.getRocketBullets().add(this);
    }


    @Override
    public void update(float delta) {
        if (!enemyTarget.isDead()) {
            double angle = Amath.angle(getX(), getY(), enemyTarget.getX(), enemyTarget.getY());
            speed = Amath.asSpeed(bulletSpeed, Amath.toRadians(angle));
            setRotation((float)angle);
        } else {
            isDead = true;
            return;
        }

        setX(getX() + speed.x * delta);
        setY(getY() + speed.y * delta);

        double distance = Vector2.dst(getX(), getY(), enemyTarget.getX(), enemyTarget.getY());
        if (distance <= getWidth() * 0.5 + enemyTarget.getWidth() * 0.5) {
            enemyTarget.addDamage(damage);
            isDead = true;
            return;
        }

        if (getX() < 0 || getY() < 0 || getX() > Game.GAME_WITH || getY() > Game.GAME_HEIGHT) {
            isDead = true;
        }

        if (isDead)
            gameWorld.getCacheRocketBullets().set(this);
    }
}
