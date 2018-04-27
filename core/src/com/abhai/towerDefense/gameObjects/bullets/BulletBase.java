package com.abhai.towerDefense.gameObjects.bullets;

import com.abhai.towerDefense.gameObjects.IGameObject;
import com.abhai.towerDefense.gameObjects.controllers.ObjectController;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.Amath;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BulletBase extends Sprite implements IGameObject {
    private double damage;
    private GameWorld gameWorld;
    private Vector2 speed;

    private boolean isDead;


    BulletBase() {
        super(new Texture("images/bullets/gunBullet.PNG"));
        damage = 0.4;
        gameWorld = GameWorld.getInstance();
    }



    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }


    public void init(float x, float y, double speed, double angle) {
        setX(x + Cell.CELL_SIZE / 2);
        setY(y + Cell.CELL_SIZE / 2);

        this.speed = Amath.asSpeed(speed, Amath.toRadians(angle));
        gameWorld.getBullets().add(this);
    }


    @Override
    public boolean isDead() {
        return isDead;
    }


    public void setDamage(double damage) {
        this.damage = damage;
    }


    @Override
    public void update(float delta) {
        setX(getX() + speed.x * delta);
        setY(getY() + speed.y * delta);

        ObjectController enemies = gameWorld.getEnemies();
        EnemyBase enemyBase;
        int length = enemies.size();
        double distance;

        for (int i = 0; i < length; i++) {
            enemyBase = (EnemyBase) enemies.get(i);
            distance = Vector2.dst(getX(), getY(), enemyBase.getX(), enemyBase.getY());

            if (distance <= getWidth() * 0.5 + enemyBase.getWidth() * 0.5) {
                enemyBase.addDamage(damage);
                isDead = true;
                break;
            }
        }
    }
}
