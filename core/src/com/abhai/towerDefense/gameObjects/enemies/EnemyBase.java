package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.PathFinder;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EnemyBase extends Sprite {
    public static final int KIND_NONE = -1;
    public static final int KIND_SOLDER = 0;

    ArrayList<Vector2> way;
    GameWorld gameWorld;

    Vector2 position;
    Vector2 target;
    Vector2 wayTarget;
    Vector2 speed;

    boolean isWay = false;
    int kind = KIND_NONE;
    float health = 0;
    int wayIndex = 0;
    int defSpeed = 0;




    EnemyBase() {
        super(new Texture("images/enemies/enemies.jpg"));
        gameWorld = GameWorld.getInstance();
    }


    public void init(int tileX, int tileY, int tileTargetX, int tileTargetY) {
        setX(gameWorld.toPix(tileX));
        setY(gameWorld.toPix(tileY));

        gameWorld.addEnemy(this);
        position = new Vector2(tileX, tileY);
        target = new Vector2(tileTargetX, tileTargetY);

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


    void setNextTarget() {
        if (wayIndex == way.size())
            isWay = false;
        else {
            wayTarget = way.get(wayIndex);
            speed = new Vector2(gameWorld.toPix(wayTarget.x), gameWorld.toPix(wayTarget.y)).sub(getX(), getY()).nor();
            speed.mulAdd(speed, defSpeed);
            setRotation(speed.angle());
        }
    }


    public void update(float delta) {

    }


    public void delete(){
        gameWorld.deleteEnemy(this);
    }


    public int getKind() {
        return kind;
    }
}
