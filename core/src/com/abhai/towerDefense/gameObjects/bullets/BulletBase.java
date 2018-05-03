package com.abhai.towerDefense.gameObjects.bullets;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.IGameObject;
import com.abhai.towerDefense.gameObjects.ObjectController;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.Amath;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BulletBase extends Sprite implements IGameObject {
    public static final short GUN_BULLET = 0;
    public static final short DOUBLE_GUN_BULLET = 1;
    public static final short ROCKET_BULLET = 2;

    GameWorld gameWorld;
    Vector2 speed;

    double damage;
    int bulletSpeed;
    boolean isDead;




    BulletBase(String image) {
        super(new Texture(image));
        gameWorld = GameWorld.getInstance();
    }



    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }


    public void init(float x, float y, double angle) {
        setX(x + Cell.CELL_SIZE / 2);
        setY(y + Cell.CELL_SIZE / 2);

        isDead = false;

        speed = Amath.asSpeed(bulletSpeed, Amath.toRadians(angle));
        setRotation((float)angle);
    }


    @Override
    public boolean isDead() {
        return isDead;
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
                return;
            }
        }

        if (getX() < 0 || getY() < 0 || getX() > Game.GAME_WITH || getY() > Game.GAME_HEIGHT) {
            isDead = true;
        }
    }
}
