package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRenderer {
    private GameWorld gameWorld;
    private OrthographicCamera cam;
    private SpriteBatch spriteBatch;



    public GameRenderer(GameWorld world) {
        gameWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Game.GAME_WITH, Game.GAME_HEIGHT);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);
    }


    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        for (int ay = 0; ay < gameWorld.getGrid().size(); ay++)
            for (int ax = 0; ax < gameWorld.getGrid().get(ay).size(); ax++)
                gameWorld.getGrid().get(ay).get(ax).draw(spriteBatch);

        for (EnemyBase enemyBase: gameWorld.getEnemies())
            enemyBase.draw(spriteBatch);

        spriteBatch.end();
    }
}
