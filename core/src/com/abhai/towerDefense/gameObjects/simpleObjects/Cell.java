package com.abhai.towerDefense.gameObjects.simpleObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Cell extends Sprite {
    public static final short STATE_CELL_FREE = 0;
    public static final short STATE_CELL_BUSY = 1;
    public static final short STATE_CELL_BUILD_ONLY = 2;
    public static final short STATE_CELL_START = 3;
    public static final short STATE_CELL_FINISH = 4;
    public static final short STATE_CELL_GUN_TOWER = 5;
    public static final short STATE_CELL_DOUBLE_GUN_TOWER = 6;
    public static final short STATE_CELL_ROCKET_TOWER = 7;

    public static final int CELL_SIZE = 48;

    private int state;

    public Cell(int cell_state) {
        super(new Texture("images/cells.PNG"), 0, 0, CELL_SIZE, CELL_SIZE);
        state = cell_state;
    }



    private void changeRegion() {
        switch (state) {
            case STATE_CELL_FREE:
                setRegion(0, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_BUSY:
                setRegion(CELL_SIZE, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_BUILD_ONLY:
                setRegion(CELL_SIZE * 2, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_START:
                setRegion(CELL_SIZE * 3, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_FINISH:
                setRegion(CELL_SIZE * 4, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_GUN_TOWER:
                setRegion(CELL_SIZE * 5, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_DOUBLE_GUN_TOWER:
                setRegion(CELL_SIZE * 5, CELL_SIZE, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_ROCKET_TOWER:
                setRegion(CELL_SIZE * 5, CELL_SIZE, CELL_SIZE, CELL_SIZE);
                break;
            default:
                setRegion(0, 0, CELL_SIZE, CELL_SIZE);
                break;
        }
    }


    public int getState() {
        return state;
    }


    public void setState(int cell_state) {
        state = cell_state;
        changeRegion();
    }
}
