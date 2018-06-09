package com.abhai.towerDefense.gui.buttons;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameObjects.towers.TowerBase;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.levels.LevelBase;
import com.abhai.towerDefense.levels.LevelManager;
import com.abhai.towerDefense.states.GameStates.BaseGameState;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.GameStates.PlayState;
import com.abhai.towerDefense.states.MenuStates.GameMenuState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BaseButton extends Sprite {
    public static final short SAVE_BUTTON_STATE = 0;
    public static final short FREE_BUTTON_STATE = 1;
    public static final short BUSY_BUTTON_STATE = 2;
    public static final short BUILD_ONLY_BUTTON_STATE = 3;
    public static final short START_POINT_BUTTON_STATE = 4;
    public static final short FINISH_POINT_BUTTON_STATE = 5;
    public static final short CONTINUE_BUTTON_STATE = 6;
    public static final short NEW_GAME_BUTTON_STATE = 7;
    public static final short EDIT_BUTTON_STATE = 8;
    public static final short OPTIONS_BUTTON_STATE = 9;
    public static final short EXIT_BUTTON_STATE = 10;
    public static final short GUN_BUTTON_STATE = 11;
    public static final short DOUBLE_GUN_BUTTON_STATE = 12;
    public static final short ROCKET_BUTTON_STATE = 13;
    public static final short START_BUTTON_STATE = 14;
    public static final short NEW_STORY_LEVEL_BUTTON_STATE = 15;
    public static final short NEW_CUSTOM_LEVEL_BUTTON_STATE = 16;



    private short state;



    BaseButton(Texture texture, int x, int y, int width, int height, short state) {
        super(texture, 0, 0, width, height);
        setX(x);
        setY(y);
        this.state = state;
    }



    public void runEvent() {
        switch (state) {
            case SAVE_BUTTON_STATE:
                BaseGameState.saveLevel();
                break;
            case FREE_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_FREE;
                GameWorld.getInstance().getTypeOfCell().setRegion(0, 0, Cell.CELL_SIZE, Cell.CELL_SIZE);
                break;
            case BUSY_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_BUSY;
                GameWorld.getInstance().getTypeOfCell().setRegion( Cell.CELL_SIZE, 0, Cell.CELL_SIZE, Cell.CELL_SIZE);
                break;
            case BUILD_ONLY_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_BUILD_ONLY;
                GameWorld.getInstance().getTypeOfCell().setRegion( Cell.CELL_SIZE * 2, 0, Cell.CELL_SIZE, Cell.CELL_SIZE);
                break;
            case START_POINT_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_START;
                GameWorld.getInstance().getTypeOfCell().setRegion( Cell.CELL_SIZE * 3, 0, Cell.CELL_SIZE, Cell.CELL_SIZE);
                break;
            case FINISH_POINT_BUTTON_STATE:
                EditState.getBrush().kind = Cell.STATE_CELL_FINISH;
                GameWorld.getInstance().getTypeOfCell().setRegion( Cell.CELL_SIZE * 4, 0, Cell.CELL_SIZE, Cell.CELL_SIZE);
                break;

            case CONTINUE_BUTTON_STATE:
                if (!GameWorld.getInstance().isStart())
                    Game.gsm.pop();
                else if (GameWorld.getInstance().getLevelId() != 1)
                    Game.gsm.push(new PlayState(GameWorld.getInstance().getLevelId()));
                break;
            case NEW_GAME_BUTTON_STATE:
                Game.gsm.push(new GameMenuState());
                break;
            case EDIT_BUTTON_STATE:
                Game.gsm.push(new EditState());
                break;
            case OPTIONS_BUTTON_STATE:
                System.out.println("optionsButton touchDown");
                break;
            case EXIT_BUTTON_STATE:
                Gdx.app.exit();
                break;

            case GUN_BUTTON_STATE:
                GameWorld.getInstance().setTypeOfTower(TowerBase.GUN_TOWER);
                break;
            case DOUBLE_GUN_BUTTON_STATE:
                GameWorld.getInstance().setTypeOfTower(TowerBase.DOUBLE_GUN_TOWER);
                break;
            case ROCKET_BUTTON_STATE:
                GameWorld.getInstance().setTypeOfTower(TowerBase.ROCKET_TOWER);
                break;

            case START_BUTTON_STATE:
                GameWorld.getInstance().startWaves();
                break;

            case NEW_STORY_LEVEL_BUTTON_STATE:
                newGame(LevelManager.FIRST_STORY_LEVEL);
                break;
            case NEW_CUSTOM_LEVEL_BUTTON_STATE:
                newGame(LevelManager.CUSTOM_LEVEL);
                break;
        }
    }



    private void newGame(int levelId) {
        GameWorld.getInstance().newGame();
        GameWorld.getInstance().setStart(false);
        Game.gsm.clear();
        Game.gsm.push(new PlayState(levelId));
    }
}
