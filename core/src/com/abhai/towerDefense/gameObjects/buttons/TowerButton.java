package com.abhai.towerDefense.gameObjects.buttons;

import com.badlogic.gdx.graphics.Texture;

public class TowerButton extends BaseButton {
    public static final int TOWER_BUTTON_WIDTH = 64;
    public static final int TOWER_BUTTON_HEIGHT = 64;
    public static final int TOWER_BUTTON_MARGIN = 20;


    public TowerButton(Texture texture, int x, int y, int width, int height, short state) {
        super(texture, x, y, width, height, state);
    }
}
