package com.abhai.towerDefense.gameObjects.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseButton extends Sprite {
    short state;



    BaseButton(Texture texture, int x, int y, int width, int height, short state) {
        super(texture, 0, 0, width, height);
        setX(x);
        setY(y);
        this.state = state;
    }
}
