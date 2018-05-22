package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gui.buttons.BaseButton;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.GameStates.PlayState;
import com.abhai.towerDefense.states.MenuStates.GameMenuState;
import com.abhai.towerDefense.states.MenuStates.MainMenuState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRenderer {
    private GameWorld gameWorld;

    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private SpriteBatch guiSpriteBatch;



    public GameRenderer(GameWorld world) {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, Game.GAME_WITH, Game.GAME_HEIGHT);

        font = new BitmapFont();
        font.setColor(Color.RED);
        if (Gdx.graphics.getWidth() > Game.GAME_WITH)
            font.getData().setScale(2);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
        guiSpriteBatch = new SpriteBatch();

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

            if (gameWorld.isShowNotEnoughMoneyText())
                gameWorld.getNotEnoughMoneyText().draw(spriteBatch);
            if (gameWorld.isShowGameOverText())
                gameWorld.getGameOverText().draw(spriteBatch);

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

        if (Game.gsm.peek() instanceof PlayState) {
            guiSpriteBatch.begin();
            font.draw(guiSpriteBatch, String.valueOf(gameWorld.getUser().getMoney()),
                    Gdx.graphics.getWidth() - Cell.CELL_SIZE, Cell.CELL_SIZE * 1.4f);
            guiSpriteBatch.draw(gameWorld.getMoney(), Gdx.graphics.getWidth() - Cell.CELL_SIZE * 2.5f, Cell.CELL_SIZE * 0.8f);

            font.draw(guiSpriteBatch, String.valueOf(gameWorld.getUser().getHp()),
                    Gdx.graphics.getWidth() - Cell.CELL_SIZE, Cell.CELL_SIZE / 2);
            guiSpriteBatch.draw(gameWorld.getHealth(), Gdx.graphics.getWidth() - Cell.CELL_SIZE * 2.5f, 0);
            guiSpriteBatch.end();
        }
    }
}
