package com.abhai.towerDefense.states.MenuStates;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.GameStates.PlayState;
import com.abhai.towerDefense.states.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuState extends State {
    private Stage stage;
    private Texture background;

    private TextButton playButton;
    private TextButton editButton;
    private TextButton optionsButton;
    private TextButton exitButton;



    public MainMenuState() {
        stage = new Stage();

        if (Gdx.graphics.getWidth() > Game.GAME_WITH)
            background = new Texture("menu_background_fhd.jpg");
        else
            background = new Texture("menu_background_hd.jpg");

        createButtons();
        addListeners();
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
        playButton = null;
        editButton = null;
        optionsButton = null;
        exitButton = null;

        stage.dispose();
        background.dispose();
    }


    private void addListeners() {
        playButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Game.gsm.set(new PlayState());
                return true;
            }
        });

        editButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Game.gsm.set(new EditState());
                return true;
            }
        });

        optionsButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("optionsButton touchDown");
                return true;
            }
        });

        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
    }


    private void createButtons() {
        playButton = new TextButton("Play", new Skin(Gdx.files.internal("skins/star-soldier/star-soldier-ui.json")));
        playButton.setWidth(Gdx.graphics.getWidth() / 5);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2,
                (float)(Gdx.graphics.getHeight() / 1.1) - playButton.getHeight() / 2);
        stage.addActor(playButton);

        editButton = new TextButton("Edit", new Skin(Gdx.files.internal("skins/star-soldier/star-soldier-ui.json")));
        editButton.setWidth(Gdx.graphics.getWidth() / 5);
        editButton.setPosition(Gdx.graphics.getWidth() / 2 - editButton.getWidth() / 2,
                (float)(Gdx.graphics.getHeight() / 1.2) - editButton.getHeight() / 2);
        stage.addActor(editButton);

        optionsButton = new TextButton("Options", new Skin(Gdx.files.internal("skins/star-soldier/star-soldier-ui.json")));
        optionsButton.setWidth(Gdx.graphics.getWidth() / 5);
        optionsButton.setPosition(Gdx.graphics.getWidth() / 2 - optionsButton.getWidth() / 2,
                (float)(Gdx.graphics.getHeight() / 6) - optionsButton.getHeight() / 2);
        stage.addActor(optionsButton);

        exitButton = new TextButton("Exit", new Skin(Gdx.files.internal("skins/star-soldier/star-soldier-ui.json")));
        exitButton.setWidth(Gdx.graphics.getWidth() / 5);
        exitButton.setPosition(Gdx.graphics.getWidth() / 2 - exitButton.getWidth() / 2,
                (float)(Gdx.graphics.getHeight() / 10) - exitButton.getHeight() / 2);
        stage.addActor(exitButton);
    }
}
