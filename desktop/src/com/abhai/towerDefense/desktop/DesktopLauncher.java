package com.abhai.towerDefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.abhai.towerDefense.Game;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Game.GAME_NAME;
		config.width = Game.GAME_WITH;
		config.height = Game.GAME_HEIGHT;
		new LwjglApplication(new Game(), config);
	}
}