package com.abhai.towerDefense.gameObjects.towers;

import com.abhai.towerDefense.gameObjects.controllers.ObjectController;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.twhelpers.Amath;
import com.badlogic.gdx.math.Vector2;

public class GunTower extends TowerBase {

    public GunTower() {
        attackRadius = 60;
        attackInterval = 10;
        attackDamage = 0.1;
        bulletSpeed = 300;
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
                        if (Vector2.dst(getX(), getY(), ((EnemyBase)enemies.get(i)).getX(), ((EnemyBase)enemies.get(i)).getY()) <= attackRadius) {
                            enemyTarget = (EnemyBase) enemies.get(i);
                            state = ATTACK_STATE;
                            System.out.println("Attack");
                        }
                    }
                    idleDelay = 0;
                }
                idleDelay++;
                break;
            case ATTACK_STATE:
                if (enemyTarget != null) {
                    setRotation((float) Amath.angle(getX(), getY(), enemyTarget.getX(), enemyTarget.getY()));

                    // todo стрелять во врага
                    // todo проверять, не погиб ли враг

                    if (Vector2.dst(enemyTarget.getX(), enemyTarget.getY(), getX(), getY()) > attackRadius) {
                        enemyTarget = null;
                        state = IDLE_STATE;
                    }
                } else
                    state = IDLE_STATE;
                break;
        }
        setRotation(getRotation() + 0.5f);
    }
}
