package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.Button;
import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.MenuStates.MainMenuState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
    private GameWorld gameWorld;


    public InputHandler() {
        gameWorld = GameWorld.getInstance();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE)
            Game.gsm.set(new MainMenuState());
        else if (!gameWorld.isEdit())
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
        double _screenY = screenY * ky;
        for (int i = 0; i < gameWorld.getGrid().size(); i++)
            for (Cell cell : gameWorld.getGrid().get(i))
                if (_screenX >= cell.getX() && _screenX <= cell.getX() + Cell.CELL_SIZE)
                    if (_screenY >= cell.getY() && _screenY <= cell.getY() + Cell.CELL_SIZE) {
                        cell.setState(Cell.STATE_CELL_BUSY);
                        return true;
                    }

        for (Button button1 : gameWorld.getButtons())
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
