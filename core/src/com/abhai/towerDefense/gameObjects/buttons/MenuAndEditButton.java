package com.abhai.towerDefense.gameObjects.buttons;

import com.badlogic.gdx.graphics.Texture;

public class MenuAndEditButton extends BaseButton {
    public static final int MENU_AND_EDIT_BUTTON_WIDTH = 200;
    public static final int MENU_AND_EDIT_BUTTON_HEIGHT = 25;
    public static final int MENU_AND_EDIT_BUTTON_MARGIN = 11;



    public MenuAndEditButton(Texture texture, int x, int y, int width, int height, short state) {
        super(texture, x, y, width, height, state);
    }
}
