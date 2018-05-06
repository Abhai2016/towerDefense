package com.abhai.towerDefense.gameObjects.towers;

import com.abhai.towerDefense.gameObjects.bullets.GunBullet;
import com.abhai.towerDefense.gameObjects.ObjectController;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.twhelpers.Amath;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GunTower extends TowerBase {
    int shootDelay;



    public GunTower() {
        super("images/towers/gunTower.PNG");
        initConstructor();
    }


    GunTower(String image) {
        super(image);
        initConstructor();
    }



    private void initConstructor() {
        attackRadius = 100;
        attackInterval = 30;
        shootDelay = 0;
    }


    @Override
    public void init(int tileX, int tileY) {
        super.init(tileX, tileY);
        gameWorld.getGunTowers().add(this);
    }


    void initForSubClasses(int tileX, int tileY) {
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
                setRotation(getRotation() + 0.4f);
                break;
            case ATTACK_STATE:
                if (enemyTarget != null) {
                    setRotation((float) Amath.angle(getX(), getY(), enemyTarget.getX(), enemyTarget.getY()));
                    shootDelay--;

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


    void shoot() {
        if (shootDelay <= 0) {
            GunBullet bullet = (GunBullet) gameWorld.getCacheGunBullets().get();
            bullet.init(getX(), getY(), getRotation());
            shootDelay = attackInterval;
        }
    }
}
