package com.abhai.towerDefense.gameObjects;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.GameStates.BaseGameState;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Button extends Sprite {
    public static final String SAVE_BUTTON_NAME = "Save";
    public static final String CLEAR_BUTTON_NAME = "Clear";
    public static final String BUSY_BUTTON_NAME = "Busy";
    public static final String BUILD_ONLY_BUTTON_NAME = "Build Only";
    public static final String START_POINT_BUTTON_NAME = "Start Point";
    public static final String FINISH_POINT_BUTTON_NAME = "Finish Point";

    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 25;
    public static final int BUTTON_MARGIN = 11;

    private String name;




    public Button(String name, Texture texture, int x, int y, int width, int height) {
        super(texture, 0, 0, width, height);
        setX(x);
        setY(y);
        this.name = name;
    }


    public void runEvent() {
       if (name.equals(SAVE_BUTTON_NAME))
           BaseGameState.saveCustomLevel();
       else if (name.equals(CLEAR_BUTTON_NAME))
           GameWorld.getInstance().clearGrid();
       else if (name.equals(BUSY_BUTTON_NAME))
           EditState.getBrush().kind = Cell.STATE_CELL_BUSY;
       else if (name.equals(BUILD_ONLY_BUTTON_NAME))
           EditState.getBrush().kind = Cell.STATE_CELL_BUILD_ONLY;
       else if (name.equals(START_POINT_BUTTON_NAME))
           EditState.getBrush().kind = Cell.STATE_CELL_START;
       else if (name.equals(FINISH_POINT_BUTTON_NAME))
           EditState.getBrush().kind = Cell.STATE_CELL_FINISH;
    }
}
