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
    private LevelBase currentLevel;


    BaseGameState(boolean isEdit, int levelId) {
        gameWorld = GameWorld.getInstance();
        gameWorld.setEdit(isEdit);
        gameWorld.createButtons();

        levelManager = new LevelManager();
        currentLevel = levelManager.getLevel(levelId);
        currentLevel.load();

        renderer = new GameRenderer(gameWorld);
        Gdx.input.setInputProcessor(new InputHandler());
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
        gameWorld = null;
        renderer = null;
        currentLevel = null;
        levelManager = null;
    }
}
