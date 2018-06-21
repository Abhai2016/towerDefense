package com.abhai.towerDefense.states.MenuStates;

import com.abhai.towerDefense.gameWorld.GameWorld;

public class OptionsMenuState extends MenuState {

    public OptionsMenuState() {
        super();
        GameWorld.getInstance().createOptionsMenuButtons();
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
