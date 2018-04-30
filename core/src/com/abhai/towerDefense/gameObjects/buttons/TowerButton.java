package com.abhai.towerDefense.gameObjects.buttons;

import com.badlogic.gdx.graphics.Texture;

public class TowerButton extends BaseButton {
    public static final short GUN_BUTTON_STATE = 11;

    public static final int TOWER_BUTTON_WIDTH = 200;
    public static final int TOWER_BUTTON_HEIGHT = 25;
    public static final int TOWER_BUTTON_MARGIN = 11;


    public TowerButton(Texture texture, int x, int y, int width, int height, short state) {
        super(texture, x, y, width, height, state);
    }
}
