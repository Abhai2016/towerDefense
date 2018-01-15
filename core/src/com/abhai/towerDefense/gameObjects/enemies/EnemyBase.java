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

    GameWorld gameWorld;
    int kind = KIND_NONE;

    float health = 0;
    float speedX = 0;
    float speedY = 0;

    Vector2 position;
    Vector2 target;

    ArrayList<Vector2> way;
    boolean isWay = false;
    int wayIndex = 0;
    Vector2 wayTarget;




    EnemyBase() {
        super(new Texture("cells.jpg"));
        gameWorld = GameWorld.getInstance();
    }


    public void init(int posX, int posY, int targetX, int targetY) {
        gameWorld.addEnemy(this);

        position = new Vector2(posX, posY);
        target = new Vector2(targetX, targetY);

        PathFinder pathFinder = new PathFinder();
        pathFinder.setFreeCell(Cell.STATE_CELL_FREE);
        way = pathFinder.findWay(position, target);

        if (way.isEmpty())
            System.out.println("EnemyBase::init() - Путь не найден!");
        else {
            isWay = true;
            wayIndex = 0;
            wayTarget = way.get(wayIndex);
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
