package com.abhai.towerDefense.editor;

import com.abhai.towerDefense.gameObjects.Cell;

public class Brush {
    public boolean drawMode = false;
    public int kind = Cell.STATE_CELL_BUSY;

    public int tileX = -1;
    public int tileY = -1;


    public Brush() {

    }
}
