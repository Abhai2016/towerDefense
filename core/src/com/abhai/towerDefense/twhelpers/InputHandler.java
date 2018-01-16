package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.Button;
import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

public class InputHandler implements InputProcessor {
    private ArrayList<ArrayList<Cell>> grid;
    private ArrayList<Button> buttons;
    private boolean isEdit;


    public InputHandler() {
        isEdit = GameWorld.getInstance().isEdit();
        grid = GameWorld.getInstance().getGrid();
        buttons = GameWorld.getInstance().getButtons();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!isEdit)
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
        double kx = (double) Game.GAME_WITH / Gdx.graphics.getWidth();
        double ky = (double) Game.GAME_HEIGHT / Gdx.graphics.getHeight();

        double _screenX = screenX * kx;
        double _screenY = Game.GAME_HEIGHT - screenY * ky;
        for (int i = 0; i < grid.size(); i++)
            for (Cell cell : grid.get(i))
                if (_screenX >= cell.getX() && _screenX <= cell.getX() + Cell.CELL_SIZE)
                    if (_screenY >= cell.getY() && _screenY <= cell.getY() + Cell.CELL_SIZE) {
                        cell.setState(Cell.STATE_CELL_BUSY);
                        return true;
                    }

        for (Button button1 : buttons)
            if (_screenX >= button1.getX() && _screenX <= button1.getX() + Button.BUTTON_WIDTH)
                if (_screenY >= button1.getY() && _screenY <= button1.getY() + Button.BUTTON_HEIGHT)
                    button1.runEvent();

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
