package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.IGameObject;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.PathFinder;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EnemyBase extends Sprite implements IGameObject {
    static final short KIND_SOLDER = 0;

    private ArrayList<Vector2> way;
    GameWorld gameWorld;

    private Vector2 target;
    Vector2 position;
    Vector2 wayTarget;
    Vector2 speed;

    private boolean isAttacked = false;
    boolean isWay = false;
    boolean isDead = false;

    int kind = KIND_SOLDER;
    int wayIndex = 0;
    int defSpeed = 0;
    double health = 0;




    EnemyBase() {
        super(new Texture("images/enemies/enemies.jpg"));
        gameWorld = GameWorld.getInstance();

        position = new Vector2();
        target = new Vector2();
        speed = new Vector2();
    }



    public void addDamage(double damage) {
        health -= damage;

        if (health <= 0) {
            gameWorld.getCacheEnemySoldiers().set(this);
            isDead = true;
        }

        isAttacked = true;
        setRegion(32, 0, Cell.CELL_SIZE, Cell.CELL_SIZE);
    }


    public void init(float tileX, float tileY, float tileTargetX, float tileTargetY) {
        setX(gameWorld.toPix(tileX));
        setY(gameWorld.toPix(tileY));

        gameWorld.getEnemies().add(this);
        position.set(tileX, tileY);
        target.set(tileTargetX, tileTargetY);
        isDead = false;

        PathFinder pathFinder = new PathFinder();
        way = pathFinder.findWay(position, target);

        if (way.isEmpty())
            System.out.println("EnemyBase::init() - Путь не найден!");
        else {
            isWay = true;
            wayIndex = 0;
            setNextTarget();
        }
    }


    @Override
    public boolean isDead() {
        return isDead;
    }


    void setNextTarget() {
        if (wayIndex == way.size())
            isWay = false;
        else {
            wayTarget = way.get(wayIndex);

            speed.x = gameWorld.toPix(wayTarget.x);
            speed.y = gameWorld.toPix(wayTarget.y);

            speed.sub(getX(), getY()).nor();
            speed.mulAdd(speed, defSpeed);
            setRotation(speed.angle());
        }
    }


    public void update(float delta) {
        if (isAttacked) {
            setRegion(64, 0, Cell.CELL_SIZE, Cell.CELL_SIZE);
            isAttacked = false;
        }
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
}
