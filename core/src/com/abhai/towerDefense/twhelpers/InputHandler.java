package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

public class InputHandler implements InputProcessor {
    private String state;
    private ArrayList<ArrayList<Cell>> grid;


    public InputHandler(String state) {
        this.state = state;
        if (this.state.equals("GameState"))
            grid = GameWorld.getInstance().getGrid();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state.equals("GameState"))
            GameWorld.getInstance().newEnemy();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (state.equals("GameState")) {
            double kx = (double) Game.GAME_WITH / Gdx.graphics.getWidth();
            double ky = (double) Game.GAME_HEIGHT / Gdx.graphics.getHeight();

            double _screenX = screenX * kx;
            double _screenY = screenY * ky;
            for (int i = 0; i < grid.size(); i++)
                for (Cell cell : grid.get(i))
                    if (_screenX >= cell.getX() && _screenX <= cell.getX() + Cell.CELL_SIZE)
                        if (_screenY <= cell.getY() + Cell.CELL_SIZE && _screenY >= cell.getY()) {
                            cell.setState(Cell.STATE_CELL_BUSY);
                            return true;
                        }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
