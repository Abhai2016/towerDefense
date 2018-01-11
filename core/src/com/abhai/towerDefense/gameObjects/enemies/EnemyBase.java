package com.abhai.towerDefense.gameObjects.enemies;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnemyBase extends Sprite {
    public static final int KIND_NONE = -1;
    public static final int KIND_SOLDER = 0;

    GameWorld gameWorld;
    int kind = KIND_NONE;

    float health = 0;
    float speedX = 0;
    float speedY = 0;



    public EnemyBase() {
        super(new Texture("cells.jpg"));
        gameWorld = GameWorld.getInstance();
    }


    public void init() {
        gameWorld.addEnemy(this);
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
