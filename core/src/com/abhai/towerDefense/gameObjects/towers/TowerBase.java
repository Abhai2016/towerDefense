package com.abhai.towerDefense.gameObjects.towers;

import com.abhai.towerDefense.gameObjects.IGameObject;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TowerBase extends Sprite implements IGameObject {
    static final int IDLE_STATE = 0;
    static final int ATTACK_STATE = 1;

    private Vector2 position;

    GameWorld gameWorld;
    EnemyBase enemyTarget;

    int state;
    int idleDelay;
    int attackRadius;
    int attackInterval;
    int bulletSpeed;
    double attackDamage;



    TowerBase() {
        super(new Texture("images/towers/gunTower.PNG"));
        gameWorld = GameWorld.getInstance();
        position = new Vector2();
        enemyTarget = new EnemySoldier();

        state = IDLE_STATE;
        idleDelay = 0;
        attackRadius = 100;
        attackInterval = 8;
        attackDamage = 0.2;
        bulletSpeed = 100;
    }



    public void init(int tileX, int tileY) {
        position.x = tileX;
        position.y = tileY;

        setX(gameWorld.toPix(position.x));
        setY(gameWorld.toPix(position.y));

        gameWorld.getTowers().add(this);
    }


    @Override
    public boolean isDead() {
        return false;
    }


    public void update(float delta) {

    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
}
