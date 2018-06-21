package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gui.buttons.BaseButton;
import com.abhai.towerDefense.gui.buttons.MenuButton;
import com.abhai.towerDefense.states.GameStates.EditState;
import com.abhai.towerDefense.states.GameStates.PlayState;
import com.abhai.towerDefense.states.MenuStates.GameMenuState;
import com.abhai.towerDefense.states.MenuStates.MainMenuState;
import com.abhai.towerDefense.states.MenuStates.OptionsMenuState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameRenderer {
    private GameWorld gameWorld;

    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private SpriteBatch guiSpriteBatch;
    private int center;



    public GameRenderer(GameWorld world) {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, Game.GAME_WITH, Game.GAME_HEIGHT);

        font = new BitmapFont();
        font.setColor(Color.RED);
        if (Gdx.graphics.getWidth() > Game.GAME_WITH) {
            font.getData().setScale(2);
            center = Game.GAME_WITH / 2 - Cell.CELL_SIZE;
        } else
            center = Game.GAME_WITH / 2;

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        OrthographicCamera camera2 = new OrthographicCamera();
        camera2.setToOrtho(false, Game.GAME_WITH, Game.GAME_HEIGHT);
        guiSpriteBatch = new SpriteBatch();
        guiSpriteBatch.setProjectionMatrix(camera2.combined);
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
            if (gameWorld.isShowLevelCompleteText())
                gameWorld.getLevelCompleteText().draw(spriteBatch);

        } else if (Game.gsm.peek() instanceof MainMenuState) {
            spriteBatch.draw(gameWorld.getBackground(), 0, 0);
            for (BaseButton button: gameWorld.getMainMenuButtons())
                    button.draw(spriteBatch);

        } else if (Game.gsm.peek() instanceof GameMenuState) {
            spriteBatch.draw(gameWorld.getBackground(), 0, 0);
            for (BaseButton button: gameWorld.getGameMenuButtons())
                button.draw(spriteBatch);

        } else if (Game.gsm.peek() instanceof OptionsMenuState) {
            spriteBatch.draw(gameWorld.getBackground(), 0, 0);
            for (BaseButton button: gameWorld.getOptionsMenuButtons())
                button.draw(spriteBatch);
        }

        spriteBatch.end();

        if (Game.gsm.peek() instanceof PlayState) {
            guiSpriteBatch.begin();
            font.draw(guiSpriteBatch, String.valueOf(gameWorld.getUser().getMoney()),
                    Game.GAME_WITH - Cell.CELL_SIZE, Cell.CELL_SIZE * 1.4f);
            guiSpriteBatch.draw(gameWorld.getMoney(), Game.GAME_WITH - Cell.CELL_SIZE * 2.5f, Cell.CELL_SIZE * 0.8f);

            font.draw(guiSpriteBatch, String.valueOf(gameWorld.getUser().getHp()),
                    Game.GAME_WITH - Cell.CELL_SIZE, Cell.CELL_SIZE / 2);
            guiSpriteBatch.draw(gameWorld.getHealth(), Game.GAME_WITH - Cell.CELL_SIZE * 2.5f, 0);

            font.draw(guiSpriteBatch, "Number of wave: " + String.valueOf(gameWorld.getEnemyWaveController().getNumberOfWave()
                    + " / " + gameWorld.getEnemyWaveController().size()), center,
                    Game.GAME_HEIGHT - gameWorld.getGuiButtons().get(0).getY() + Cell.CELL_SIZE / 1.8f);
            guiSpriteBatch.end();
        }
    }
}
