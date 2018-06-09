package com.abhai.towerDefense.gameObjects.bullets;

public class GunBullet extends BulletBase {

    public GunBullet() {
        super("images/bullets/gunBullet.PNG");
        damage = 0.5;
        bulletSpeed = 800;
    }


    @Override
    public void init(float x, float y, double angle) {
        super.init(x, y, angle);
        gameWorld.getGunBullets().add(this);
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if (isDead)
            gameWorld.getCacheGunBullets().set(this);
    }
}
