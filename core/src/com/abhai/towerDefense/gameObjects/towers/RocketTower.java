package com.abhai.towerDefense.gameObjects.towers;


import com.abhai.towerDefense.gameObjects.bullets.RocketBullet;

public class RocketTower extends GunTower {

    public RocketTower() {
        super("images/towers/rocketTower.PNG");
        attackRadius = 140;
        attackInterval = 60;
        shootDelay = 0;
    }



    @Override
    public void init(int tileX, int tileY) {
        super.initForSubClasses(tileX, tileY);
        gameWorld.getRocketTowers().add(this);
    }


    @Override
    void shoot() {
        shootDelay--;
        if (shootDelay <= 0) {
            RocketBullet bullet = (RocketBullet) gameWorld.getCacheRocketBullets().get();
            bullet.init(getX(), getY(), getRotation(), enemyTarget);
            shootDelay = attackInterval;
        }
    }
}