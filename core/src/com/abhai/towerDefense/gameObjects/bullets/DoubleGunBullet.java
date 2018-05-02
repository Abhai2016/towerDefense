package com.abhai.towerDefense.gameObjects.bullets;

public class DoubleGunBullet extends BulletBase {

    public DoubleGunBullet() {
        super("images/bullets/doubleGunBullet.PNG");
        damage = 0.8;
        bulletSpeed = 500;
    }


    @Override
    public void init(float x, float y, double angle) {
        super.init(x, y, angle);
        gameWorld.getDoubleGunBullets().add(this);
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if (isDead)
            gameWorld.getCacheDoubleGunBullets().set(this);
    }
}
