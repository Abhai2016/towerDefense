package com.abhai.towerDefense.gameObjects.towers;

import com.abhai.towerDefense.gameObjects.bullets.GunBullet;
import com.abhai.towerDefense.gameObjects.ObjectController;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.twhelpers.Amath;
import com.badlogic.gdx.math.Vector2;

public class GunTower extends TowerBase {
    private int shootDelay;

    public GunTower() {
        attackRadius = 80;
        attackInterval = 20;
        attackDamage = 0.4;
        bulletSpeed = 400;
        shootDelay = 0;
    }


    @Override
    public void init(int tileX, int tileY) {
        super.init(tileX, tileY);
    }


    @Override
    public void update(float delta) {
        switch (state) {
            case IDLE_STATE:
                if (idleDelay >= 5) {
                    ObjectController enemies = gameWorld.getEnemies();
                    int size = enemies.size();
                    for (int i = 0; i < size; i++) {
                        if (Vector2.dst(getX(), getY(),
                                ((EnemyBase)enemies.get(i)).getX(), ((EnemyBase)enemies.get(i)).getY()) <= attackRadius) {
                            enemyTarget = (EnemyBase) enemies.get(i);
                            state = ATTACK_STATE;
                        }
                    }
                    idleDelay = 0;
                }
                idleDelay++;
                setRotation(getRotation() + 0.5f);
                break;
            case ATTACK_STATE:
                if (enemyTarget != null) {
                    setRotation((float) Amath.angle(getX(), getY(), enemyTarget.getX(), enemyTarget.getY()));

                    if (enemyTarget.isDead()) {
                        enemyTarget = null;
                        state = IDLE_STATE;
                    } else if (Vector2.dst(enemyTarget.getX(), enemyTarget.getY(), getX(), getY()) > attackRadius) {
                        enemyTarget = null;
                        state = IDLE_STATE;
                    } else
                        shoot();
                } else
                    state = IDLE_STATE;
                break;
        }
    }


    private void shoot() {
        shootDelay--;
        if (shootDelay <= 0) {
            GunBullet bullet = (GunBullet) gameWorld.getCacheGunBullets().get();
            bullet.setDamage(attackDamage);
            bullet.init(getX(), getY(), bulletSpeed, getRotation());
            shootDelay = attackInterval;
        }
    }
}
