package com.abhai.towerDefense.states.GameStates;

import com.abhai.towerDefense.gameWorld.GameRenderer;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.levels.LevelBase;
import com.abhai.towerDefense.levels.LevelManager;
import com.abhai.towerDefense.states.State;
import com.badlogic.gdx.Gdx;

public class BaseGameState implements State {
    private static GameRenderer renderer;

    static LevelBase currentLevel;
    GameWorld gameWorld;



    BaseGameState(int levelId) {
        gameWorld = GameWorld.getInstance();
        currentLevel = LevelManager.getLevel(levelId);

        if (renderer == null)
            renderer = new GameRenderer(gameWorld);

        gameWorld.makeEnemyWaves(levelId);
        Gdx.input.setCatchBackKey(true);
    }



    public static void saveLevel() {
        currentLevel.saveLevel();
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
        currentLevel = null;
    }
}
