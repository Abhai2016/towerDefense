package com.abhai.towerDefense.gameObjects;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.GameStates.BaseGameState;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Button extends Sprite {
    public static final short SAVE_BUTTON_STATE = 0;
    public static final short CLEAR_BUTTON_STATE = 1;
    public static final short BUSY_BUTTON_STATE = 2;
    public static final short BUILD_ONLY_BUTTON_STATE = 3;
    public static final short START_POINT_BUTTON_STATE = 4;
    public static final short FINISH_POINT_BUTTON_STATE = 5;

    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 25;
    public static final int BUTTON_MARGIN = 11;

    private short state;



    public Button(Texture texture, int x, int y, int width, int height, short state) {
        super(texture, 0, 0, width, height);
        setX(x);
        setY(y);
        this.state = state;
    }


    public void runEvent() {
        switch (state) {
            case SAVE_BUTTON_STATE:
                BaseGameState.saveCustomLevel();
                break;
            case CLEAR_BUTTON_STATE:
                GameWorld.getInstance().clearGrid();
                break;
            case BUSY_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_BUSY;
                break;
            case BUILD_ONLY_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_BUILD_ONLY;
                break;
            case START_POINT_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_START;
                break;
            case FINISH_POINT_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_FINISH;
                break;
        }
    }
}
