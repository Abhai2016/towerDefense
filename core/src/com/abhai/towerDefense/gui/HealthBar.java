package com.abhai.towerDefense.gui;

import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HealthBar extends Sprite {
    private static final int HEALTH_BAR_HEIGHT = 2;

    private int fullHealth;





    public HealthBar() {
        super(new Texture("images/gui/healthBar.PNG"));
    }



    public void init(float x, float y, int fullHP) {
        setX(x);
        setY(y);

        fullHealth = fullHP;
    }



    public void update(float x, float y, double health) {
        setX(x);
        setY(y);

        setSize((float) health * Cell.CELL_SIZE / fullHealth, HEALTH_BAR_HEIGHT);
    }


    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }



    public int getFullHealth() {
        return fullHealth;
    }
}
