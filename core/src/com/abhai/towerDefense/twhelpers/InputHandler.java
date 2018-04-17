package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.Button;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.MenuStates.MainMenuState;
import com.abhai.towerDefense.states.State;
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
        for (Button button1 : gameWorld.getEditButtons())
            if (_screenX >= button1.getX() && _screenX <= button1.getX() + Button.BUTTON_WIDTH)
                if (_screenY >= button1.getY() && _screenY <= button1.getY() + Button.BUTTON_HEIGHT)
                    button1.runEvent();

            EditState.getBrush().drawMode = true;
            gameWorld.applyBrush(EditState.getBrush(), _screenX, _screenY);
        } else if (Game.gsm.peek() instanceof MainMenuState) {
            for (Button button1 : gameWorld.getMainMenuButtons())
                if (_screenX >= button1.getX() && _screenX <= button1.getX() + Button.BUTTON_WIDTH)
                    if (_screenY >= button1.getY() && _screenY <= button1.getY() + Button.BUTTON_HEIGHT)
                        button1.runEvent();
        } else
            GameWorld.getInstance().newEnemy();
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

}
