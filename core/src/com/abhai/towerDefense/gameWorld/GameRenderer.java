package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
        for (int ay = 0; ay < GameWorld.MAP_HEIGHT_MAX; ay++)
            for (int ax = 0; ax < GameWorld.MAP_WITH_MAX; ax++)
                gameWorld.getGrid().get(ay).get(ax).draw(spriteBatch);

        if (!gameWorld.isEdit())
            for (EnemyBase enemyBase:  gameWorld.getEnemies())
                enemyBase.draw(spriteBatch);

        if (!gameWorld.getButtons().isEmpty())
            for (Sprite button : gameWorld.getButtons())
                button.draw(spriteBatch);
        spriteBatch.end();
    }
}
