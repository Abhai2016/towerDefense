package com.abhai.towerDefense;

import com.abhai.towerDefense.screens.GameScreen;

public class Game extends com.badlogic.gdx.Game {
	public static final int GAME_WITH = 1280;
	public static final int GAME_HEIGHT = 720;
	public static final String GAME_NAME = "Tower Defense";


	@Override
	public void create () {
		setScreen(new GameScreen());
	}

}