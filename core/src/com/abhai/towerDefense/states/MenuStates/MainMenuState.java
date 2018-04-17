package com.abhai.towerDefense.states.MenuStates;

import com.abhai.towerDefense.gameWorld.GameWorld;

public class MainMenuState extends MenuState {

    public MainMenuState() {
        super();
        GameWorld.getInstance().createMainMenuButtons();
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
