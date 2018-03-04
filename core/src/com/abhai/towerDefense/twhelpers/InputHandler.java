package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.Button;
import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.GameStates.PlayState;
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
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK)
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
        for (Button button1 : gameWorld.getButtons())
            if (_screenX >= button1.getX() && _screenX <= button1.getX() + Button.BUTTON_WIDTH)
                if (_screenY >= button1.getY() && _screenY <= button1.getY() + Button.BUTTON_HEIGHT)
                    button1.runEvent();

        if (gameWorld.isEdit()) {
            EditState.getBrush().drawMode = true;
            gameWorld.applyBrush(EditState.getBrush(), _screenX, _screenY);
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
