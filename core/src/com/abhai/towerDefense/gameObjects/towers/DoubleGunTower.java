package com.abhai.towerDefense.gameObjects.towers;

import com.abhai.towerDefense.gameObjects.bullets.DoubleGunBullet;

public class DoubleGunTower extends GunTower {

    public DoubleGunTower() {
        super("images/towers/doubleGunTower.PNG");
    }



    @Override
    public void init(int tileX, int tileY) {
        super.init(tileX, tileY);
        gameWorld.getDoubleGunTowers().add(this);
    }


    @Override
    void shoot() {
        shootDelay--;
        if (shootDelay <= 0) {
            DoubleGunBullet bullet = (DoubleGunBullet) gameWorld.getCacheDoubleGunBullets().get();
            DoubleGunBullet bullet2 = (DoubleGunBullet) gameWorld.getCacheDoubleGunBullets().get();

            bullet.init(getX() - 5, getY(), getRotation());
            bullet2.init(getX() + 5, getY(),getRotation());
            shootDelay = attackInterval;
        }
    }
}
