package com.abhai.towerDefense.states.GameStates;

import com.abhai.towerDefense.gameWorld.GameRenderer;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.State;
import com.abhai.towerDefense.twhelpers.InputHandler;
import com.badlogic.gdx.Gdx;

public class EditState extends State {
    private GameWorld world;
    private GameRenderer renderer;



    public EditState() {
        state = "EditGameState";
        world = new GameWorld();
        renderer = new GameRenderer(world, state);
        Gdx.input.setInputProcessor(new InputHandler(state));
    }


    @Override
    public void update(float delta) {
        world.update(delta);
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void dispose() {
        world = null;
        renderer = null;
        state = "";
    }
}
