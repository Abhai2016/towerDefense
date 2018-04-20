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
    public static final int IDLE_STATE = 0;
    public static final int ATTACK_STATE = 1;

    private GameWorld gameWorld;
    private Vector2 position;
    private EnemyBase enemyTarget;

    private int state;
    private int idleDelay;

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



    public void delete() {
        gameWorld.getTowers().delete(this);
    }



    public void init(int tileX, int tileY) {
        position.x = tileX;
        position.y = tileY;

        setX(gameWorld.toPix(position.x));
        setY(gameWorld.toPix(position.y));

        gameWorld.getTowers().add(this);
    }


    public void update(float delta) {

    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
}
