package com.abhai.towerDefense.states.GameStates;

import com.abhai.towerDefense.levels.LevelManager;

public class PlayState extends BaseGameState {


    public PlayState(int kind) {
        super(kind);

        currentLevel.loadLevel(kind);
        gameWorld.preparePoints();
        gameWorld.createPlayStateButtons();

        if (kind != LevelManager.CUSTOM_LEVEL)
            gameWorld.makeEnemyWaves();
        gameWorld.setEdit(false);
    }


    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
       super.dispose();
    }
}