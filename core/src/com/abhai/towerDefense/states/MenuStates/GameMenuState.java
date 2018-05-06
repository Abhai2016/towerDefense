package com.abhai.towerDefense.states.MenuStates;

import com.abhai.towerDefense.gameWorld.GameWorld;

public class GameMenuState extends MenuState {

    public GameMenuState() {
        super();
        GameWorld.getInstance().createGameMenuButtons();
    }


    @Override
    public void update(float delta) {

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
