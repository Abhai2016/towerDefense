package com.abhai.towerDefense.states;

import com.abhai.towerDefense.gameWorld.GameRenderer;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.InputHandler;
import com.badlogic.gdx.Gdx;

public class GameState extends State {
    private GameWorld world;
    private GameRenderer renderer;



    public GameState() {
        state = "GameState";
        world = new GameWorld();
        renderer = new GameRenderer(world);
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

    }
}
