package com.abhai.towerDefense.gameObjects.towers;

import com.abhai.towerDefense.gameObjects.bullets.DoubleGunBullet;

public class DoubleGunTower extends GunTower {

    public DoubleGunTower() {
        super("images/towers/doubleGunTower.PNG");
        cost = 60;
    }



    @Override
    public void init(int tileX, int tileY) {
        super.initForSubClasses(tileX, tileY);
        gameWorld.getDoubleGunTowers().add(this);
    }


    @Override
    void shoot() {
        if (shootDelay <= 0) {
            DoubleGunBullet bullet = (DoubleGunBullet) gameWorld.getCacheDoubleGunBullets().get();
            bullet.init(getX(), getY(), getRotation());
            shootDelay = attackInterval;
        }
    }
}
