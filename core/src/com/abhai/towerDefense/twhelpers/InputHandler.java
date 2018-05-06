package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gui.buttons.BaseButton;
import com.abhai.towerDefense.gui.buttons.EditButton;
import com.abhai.towerDefense.gui.buttons.MenuButton;
import com.abhai.towerDefense.gui.buttons.TowerButton;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.GameStates.PlayState;
import com.abhai.towerDefense.states.MenuStates.GameMenuState;
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
            if (Game.gsm.peek() instanceof PlayState)
                Game.gsm.push(new MainMenuState());
            else if (Game.gsm.peek() instanceof GameMenuState)
                Game.gsm.pop();
            else if (Game.gsm.peek() instanceof EditState){
                gameWorld.setEdit(false);
                Game.gsm.pop();
            }
        } else if (!gameWorld.isEdit())
            GameWorld.getInstance().newEnemy((int)(Math.random() * 3) + EnemyBase.ENEMY_SOLDER);
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

        if (Game.gsm.peek() instanceof EditState) {
            checkButton("EditButtons", _screenX, _screenY);
            EditState.getBrush().drawMode = true;
            gameWorld.applyBrush(EditState.getBrush(), _screenX, _screenY);

        } else if (Game.gsm.peek() instanceof MainMenuState) {
            checkButton("MainMenuButtons", _screenX, _screenY);

        } else if (Game.gsm.peek() instanceof GameMenuState)
            checkButton("GameMenuButtons", _screenX, _screenY);

        else if (Game.gsm.peek() instanceof PlayState) {
            checkButton("TowerButtons", _screenX, _screenY);
            checkButton("GuiButtons", _screenX, _screenY);
            if (gameWorld.toTile(_screenY) < GameWorld.MAP_HEIGHT_MAX)
                if (gameWorld.getGrid().get(gameWorld.toTile(_screenY)).
                        get(gameWorld.toTile(_screenX)).getState() == Cell.STATE_CELL_BUILD_ONLY)
                    GameWorld.getInstance().newTower(_screenX, _screenY);
            else
                    GameWorld.getInstance().newEnemy((int)(Math.random() * 3) + EnemyBase.ENEMY_SOLDER);
        }
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
        int width = MenuButton.MENU_BUTTON_WIDTH;
        int height = MenuButton.MENU_BUTTON_HEIGHT;

        if (string.equals("EditButtons")) {
            buttons = gameWorld.getEditButtons();
            width = EditButton.EDIT_BUTTON_WIDTH;
            height = EditButton.EDIT_BUTTON_HEIGHT;

        } else if (string.equals("MainMenuButtons"))
            buttons = gameWorld.getMainMenuButtons();

        else if (string.equals("TowerButtons")) {
            buttons = gameWorld.getTowerButtons();
            width = TowerButton.TOWER_BUTTON_WIDTH;
            height = TowerButton.TOWER_BUTTON_HEIGHT;

        } else if (string.equals("GameMenuButtons"))
            buttons = gameWorld.getGameMenuButtons();
        else
            buttons = gameWorld.getGuiButtons();

        BaseButton button1;
        for (int i = 0; i < buttons.size(); i++) {
            button1 = buttons.get(i);
                if (x >= button1.getX() && x <= button1.getX() + width)
                    if (y >= button1.getY() && y <= button1.getY() + height)
                        button1.runEvent();
        }
    }
}
