package com.abhai.towerDefense.states.MenuStates;

import com.abhai.towerDefense.gameWorld.GameRenderer;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.State;

public class MenuState implements State {
    private static GameRenderer gameRenderer;


    MenuState() {
        if (gameRenderer == null)
            gameRenderer = new GameRenderer(GameWorld.getInstance());
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        gameRenderer.render();
    }

    @Override
    public void dispose() {

    }
}
