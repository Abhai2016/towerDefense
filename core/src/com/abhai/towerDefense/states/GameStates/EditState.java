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
        world = GameWorld.getInstance();
        renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler());
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
    }
}
