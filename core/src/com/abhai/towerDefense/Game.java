package com.abhai.towerDefense;

import com.abhai.towerDefense.states.GameState;
import com.abhai.towerDefense.twhelpers.GameStateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Game extends ApplicationAdapter {
	public static final int GAME_WITH = 1280;
	public static final int GAME_HEIGHT = 720;
	public static final String GAME_NAME = "Tower Defense";

	public static GameStateManager gsm;


	@Override
	public void create () {
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new GameState());
	}


	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
	}


	@Override
	public void dispose () {

	}
}