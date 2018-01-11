package com.abhai.towerDefense.gameObjects;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Cell extends Sprite {
    private int state;

    public Cell(int cell_state) {
        super(new Texture("cells.jpg"), 0, 0, GameWorld.MAP_CELL_SIZE, GameWorld.MAP_CELL_SIZE);
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
            case GameWorld.STATE_CELL_FREE:
                setRegion(0, 0, GameWorld.MAP_CELL_SIZE, GameWorld.MAP_CELL_SIZE);
                break;
            case GameWorld.STATE_CELL_BUSY:
                setRegion(32, 0, GameWorld.MAP_CELL_SIZE, GameWorld.MAP_CELL_SIZE);
                break;
            case GameWorld.STATE_CELL_BUILD_ONLY:
                setRegion(64, 0, GameWorld.MAP_CELL_SIZE, GameWorld.MAP_CELL_SIZE);
                break;
        }
    }
}
