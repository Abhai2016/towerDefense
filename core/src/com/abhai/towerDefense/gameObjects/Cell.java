package com.abhai.towerDefense.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Cell extends Sprite {
    public static final int STATE_CELL_FREE = 1;
    public static final int STATE_CELL_BUSY = 2;
    public static final int STATE_CELL_BUILD_ONLY = 3;
    public static final int STATE_CELL_START = 4;
    public static final int STATE_CELL_FINISH = 5;
    public static final int CELL_SIZE = 32;

    private int state;

    public Cell(int cell_state) {
        super(new Texture("images/cells.jpg"), 0, 0, CELL_SIZE, CELL_SIZE);
        state = cell_state;
    }


    public int getState() {
        return state;
    }


    public void setState(int cell_state) {
        state = cell_state;
        changeRegion();
    }


    private void changeRegion() {
        switch (state) {
            case STATE_CELL_FREE:
                setRegion(0, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_BUSY:
                setRegion(32, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_BUILD_ONLY:
                setRegion(64, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_START:
                setRegion(96, 0, CELL_SIZE, CELL_SIZE);
                break;
            case STATE_CELL_FINISH:
                setRegion(128, 0, CELL_SIZE, CELL_SIZE);
                break;
            default:
                setRegion(0, 0, CELL_SIZE, CELL_SIZE);
                break;
        }
    }
}
