package com.abhai.towerDefense.states.MenuStates;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.GameStates.PlayState;
import com.abhai.towerDefense.states.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuState extends MenuState {
    private TextButton playButton;
    private TextButton editButton;
    private TextButton optionsButton;
    private TextButton exitButton;

    private boolean isEditState;
    private boolean isPlayState;


    public MainMenuState() {
        super();

        isEditState = false;
        isPlayState = false;
        createButtons();
        addListeners();
        Gdx.input.setInputProcessor(stage);
    }



    private void addListeners() {
        playButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!isPlayState) {
                    Game.gsm.set(new PlayState());
                    isPlayState = true;
                } else
                    Game.gsm.pop();
                return true;
            }
        });

        editButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!isEditState) {
                    Game.gsm.set(new EditState());
                    isEditState = true;
                } else
                    for (State state: GameWorld.getInstance().getStatesCache())
                        if (state instanceof EditState) {
                            GameWorld.getInstance().setEdit(true);
                            Game.gsm.push(state);
                        }
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

        playButton = null;
        editButton = null;
        optionsButton = null;
        exitButton = null;
    }
}
