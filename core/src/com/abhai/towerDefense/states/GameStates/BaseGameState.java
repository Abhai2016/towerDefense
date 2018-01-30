package com.abhai.towerDefense.states.GameStates;

import com.abhai.towerDefense.gameWorld.GameRenderer;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.levels.LevelBase;
import com.abhai.towerDefense.levels.LevelManager;
import com.abhai.towerDefense.states.State;
import com.abhai.towerDefense.twhelpers.InputHandler;
import com.badlogic.gdx.Gdx;

public class BaseGameState implements State{
    private GameWorld gameWorld;
    private GameRenderer renderer;

    private LevelManager levelManager;
    private static LevelBase currentLevel;


    BaseGameState(int levelId) {
        gameWorld = GameWorld.getInstance();
        levelManager = new LevelManager();
        currentLevel = levelManager.getLevel(levelId);

        if (levelId == 0) {
            gameWorld.setEdit(true);
            currentLevel.loadCustomLevel();
        } else {
            gameWorld.setEdit(false);
            currentLevel.loadStoryLevel();
        }

        gameWorld.createButtons();
        gameWorld.preparePoints();
        renderer = new GameRenderer(gameWorld);
        Gdx.input.setInputProcessor(new InputHandler());
    }


    public static void saveCustomLevel() {
        currentLevel.saveCustomLevel();
    }

    @Override
    public void update(float delta) {
        gameWorld.update(delta);
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
        renderer = null;
        currentLevel = null;
        levelManager = null;
    }
}
