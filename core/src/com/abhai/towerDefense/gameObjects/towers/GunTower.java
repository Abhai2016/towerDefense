package com.abhai.towerDefense.gameObjects.towers;

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
        setRotation(getRotation() + 0.5f);
    }
}
