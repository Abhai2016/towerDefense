package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.buttons.BaseButton;
import com.abhai.towerDefense.gameObjects.buttons.MenuAndEditButton;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.MenuStates.MainMenuState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

public class InputHandler implements InputProcessor {
    private GameWorld gameWorld;


    public InputHandler() {
        gameWorld = GameWorld.getInstance();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK) {
            if (gameWorld.isEdit()) {
                gameWorld.setEdit(false);
                Game.gsm.pop();
            }
            else
                Game.gsm.push(new MainMenuState());
        } else if (!gameWorld.isEdit())
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
        if (gameWorld.isShowSaveText())
            gameWorld.setShowSaveText(false);

        double kx = (double) Game.GAME_WITH / Gdx.graphics.getWidth();
        double ky = (double) Game.GAME_HEIGHT / Gdx.graphics.getHeight();

        double _screenX = screenX * kx;
        double _screenY = screenY * ky;

        if (gameWorld.isEdit()) {
            checkButton("EditButtons", _screenX, _screenY);
            EditState.getBrush().drawMode = true;
            gameWorld.applyBrush(EditState.getBrush(), _screenX, _screenY);
        } else if (Game.gsm.peek() instanceof MainMenuState) {
            checkButton("MainMenuButtons", _screenX, _screenY);
        } else if (gameWorld.getGrid().get(gameWorld.toTile(_screenY)).
                get(gameWorld.toTile(_screenX)).getState() == Cell.STATE_CELL_BUILD_ONLY)
            GameWorld.getInstance().newTower(_screenX, _screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (gameWorld.isEdit()) {
            EditState.getBrush().drawMode = false;
            EditState.getBrush().tileX = -1;
            EditState.getBrush().tileY = -1;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        double kx = (double) Game.GAME_WITH / Gdx.graphics.getWidth();
        double ky = (double) Game.GAME_HEIGHT / Gdx.graphics.getHeight();

        double _screenX = screenX * kx;
        double _screenY = screenY * ky;

        if (gameWorld.isEdit() && EditState.getBrush().drawMode)
            gameWorld.applyBrush(EditState.getBrush(), _screenX, _screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    private void checkButton(String string, double x, double y) {
        ArrayList<BaseButton> buttons;

        if (string.equals("EditButtons"))
            buttons = gameWorld.getEditButtons();
        else //if (string.equals("MainMenuButtons"))
            buttons = gameWorld.getMainMenuButtons();

        MenuAndEditButton button1;
        for (int i = 0; i < buttons.size(); i++) {
            button1 = (MenuAndEditButton)buttons.get(i);
            if (x >= button1.getX() && x <= button1.getX() + MenuAndEditButton.MENU_AND_EDIT_BUTTON_WIDTH)
                if (y >= button1.getY() && y <= button1.getY() + MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT)
                    button1.runEvent();
        }
    }
}
