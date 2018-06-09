package com.abhai.towerDefense.states.GameStates;

public class PlayState extends BaseGameState {


    public PlayState(int levelId) {
        super(levelId);

        currentLevel.loadLevel(levelId);
        gameWorld.setLevelId(levelId);
        gameWorld.preparePoints();
        gameWorld.createPlayStateButtons();
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