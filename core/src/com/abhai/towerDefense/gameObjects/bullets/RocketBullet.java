package com.abhai.towerDefense.gameObjects.bullets;

public class RocketBullet extends BulletBase {

    public RocketBullet() {
        super("images/bullets/rocketBullet.PNG");
        damage = 1;
        bulletSpeed = 200;
    }


    @Override
    public void init(float x, float y, double angle) {
        super.init(x, y, angle);
        gameWorld.getRocketBullets().add(this);
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        if (isDead)
            gameWorld.getCacheRocketBullets().set(this);
    }
}
