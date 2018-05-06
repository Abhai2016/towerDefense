package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gui.buttons.BaseButton;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.GameStates.PlayState;
import com.abhai.towerDefense.states.MenuStates.GameMenuState;
import com.abhai.towerDefense.states.MenuStates.MainMenuState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRenderer {
    private GameWorld gameWorld;
    private SpriteBatch spriteBatch;



    public GameRenderer(GameWorld world) {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, Game.GAME_WITH, Game.GAME_HEIGHT);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
        gameWorld = world;
    }


    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        if (Game.gsm.peek() instanceof EditState) {
            for (int ay = 0; ay < GameWorld.MAP_HEIGHT_MAX; ay++)
                for (int ax = 0; ax < GameWorld.MAP_WITH_MAX; ax++)
                    gameWorld.getEditGrid().get(ay).get(ax).draw(spriteBatch);

            for (BaseButton button : gameWorld.getEditButtons())
                button.draw(spriteBatch);

            gameWorld.getTypeOfCell().draw(spriteBatch);
            if (gameWorld.isShowSaveText())
                gameWorld.getSaveText().draw(spriteBatch);
        } else if (Game.gsm.peek() instanceof PlayState) {
            for (int ay = 0; ay < GameWorld.MAP_HEIGHT_MAX; ay++)
                for (int ax = 0; ax < GameWorld.MAP_WITH_MAX; ax++)
                    gameWorld.getGrid().get(ay).get(ax).draw(spriteBatch);

            for (BaseButton button : gameWorld.getTowerButtons())
                button.draw(spriteBatch);
            for (BaseButton button : gameWorld.getGuiButtons())
                button.draw(spriteBatch);

            gameWorld.getEnemies().draw(spriteBatch);

            gameWorld.getGunTowers().draw(spriteBatch);
            gameWorld.getDoubleGunTowers().draw(spriteBatch);
            gameWorld.getRocketTowers().draw(spriteBatch);

            gameWorld.getGunBullets().draw(spriteBatch);
            gameWorld.getDoubleGunBullets().draw(spriteBatch);
            gameWorld.getRocketBullets().draw(spriteBatch);
        } else if (Game.gsm.peek() instanceof MainMenuState) {
            spriteBatch.draw(gameWorld.getBackground(), 0, 0);
            for (BaseButton button: gameWorld.getMainMenuButtons())
                    button.draw(spriteBatch);
        } else if (Game.gsm.peek() instanceof GameMenuState) {
            spriteBatch.draw(gameWorld.getBackground(), 0, 0);
            for (BaseButton button: gameWorld.getGameMenuButtons())
                button.draw(spriteBatch);
        }
        spriteBatch.end();
    }
}
