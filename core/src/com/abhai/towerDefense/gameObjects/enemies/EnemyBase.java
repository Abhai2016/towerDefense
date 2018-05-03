package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.IGameObject;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.PathFinder;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EnemyBase extends Sprite implements IGameObject {
    public static final short ENEMY_SOLDER = 10;
    public static final short ENEMY_HARD_SOLDER = 11;
    public static final short ENEMY_TANK = 12;

    private ArrayList<Vector2> way;
    GameWorld gameWorld;

    private Vector2 target;
    private Vector2 position;
    private Vector2 wayTarget;
    private Vector2 speed;

    private boolean isAttacked;
    boolean isWay;
    boolean isDead;

    private int wayIndex;
    int kind;
    int defSpeed;
    double health;




    EnemyBase(String image) {
        super(new Texture(image));
        gameWorld = GameWorld.getInstance();

        position = new Vector2();
        target = new Vector2();
        speed = new Vector2();
    }



    public void addDamage(double damage) {
        health -= damage;

        if (health <= 0)
            isDead = true;

        isAttacked = true;
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


    private void setNextTarget() {
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
        if (isAttacked)
            isAttacked = false;

        if (isWay && !isDead) {
            setX(getX() + speed.x * delta);
            setY(getY() + speed.y * delta);

            Vector2 currentPoint = new Vector2(getX(), getY());
            Vector2 targetPoint = new Vector2(gameWorld.toPix(wayTarget.x), gameWorld.toPix(wayTarget.y));

            if (currentPoint.epsilonEquals(targetPoint, defSpeed / 50)) {
                position.x = gameWorld.toTile(getX());
                position.y = gameWorld.toTile(getY());

                wayIndex++;
                setNextTarget();
            }
        }
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
}
