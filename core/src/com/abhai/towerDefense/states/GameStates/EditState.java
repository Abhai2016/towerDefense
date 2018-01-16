package com.abhai.towerDefense.states.GameStates;

import com.abhai.towerDefense.gameWorld.GameRenderer;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.State;
import com.abhai.towerDefense.twhelpers.InputHandler;
import com.badlogic.gdx.Gdx;

public class EditState extends State {
    private GameWorld gameWorld;
    private GameRenderer renderer;



    public EditState() {
        gameWorld = GameWorld.getInstance();
        gameWorld.setEdit(true);
        gameWorld.createButtons();

        renderer = new GameRenderer(gameWorld);
        Gdx.input.setInputProcessor(new InputHandler());
    }


    @Override
    public void update(float delta) {
        gameWorld.update(delta);
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void dispose() {
        gameWorld = null;
        renderer = null;
    }
}