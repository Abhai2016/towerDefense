package com.abhai.towerDefense.screens;

import com.abhai.towerDefense.gameWorld.GameRenderer;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;



    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler());
    }


    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }


    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {

    }

}
