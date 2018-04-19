package com.abhai.towerDefense;

import com.abhai.towerDefense.states.MenuStates.MainMenuState;
import com.abhai.towerDefense.twhelpers.GameStateManager;
import com.abhai.towerDefense.twhelpers.InputHandler;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class Game extends ApplicationAdapter {
	public static final int GAME_WITH = 1280;
	public static final int GAME_HEIGHT = 720;
	public static final String GAME_NAME = "Tower Defense";

	public static GameStateManager gsm;


	@Override
	public void create () {
		gsm = new GameStateManager();
		gsm.push(new MainMenuState());
		Gdx.input.setInputProcessor(new InputHandler());
	}


	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
	}


	@Override
	public void dispose () {
		gsm = null;
	}
}