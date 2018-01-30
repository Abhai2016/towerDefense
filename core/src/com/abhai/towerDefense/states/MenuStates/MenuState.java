package com.abhai.towerDefense.states.MenuStates;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.states.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuState implements State {
    private Texture background;
    Stage stage;



    MenuState() {
        stage = new Stage();

        if (Gdx.graphics.getWidth() > Game.GAME_WITH)
            background = new Texture("images/backgrounds/menu_background_fhd.jpg");
        else
            background = new Texture("images/backgrounds/menu_background_hd.jpg");
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
