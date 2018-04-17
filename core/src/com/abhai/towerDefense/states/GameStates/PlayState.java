package com.abhai.towerDefense.states.GameStates;

import com.abhai.towerDefense.levels.LevelManager;
import com.badlogic.gdx.Gdx;

public class PlayState extends BaseGameState {


    public PlayState() {
        super(LevelManager.FIRST_STORY_LEVEL);

        currentLevel.loadStoryLevel();
        gameWorld.preparePoints();
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