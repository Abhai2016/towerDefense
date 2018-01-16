package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameRenderer {
    private Stage stage;
    private GameWorld gameWorld;



    public GameRenderer(GameWorld world) {
        stage = new Stage();
        gameWorld = world;
    }


    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        for (int ay = 0; ay < gameWorld.getGrid().size(); ay++)
            for (int ax = 0; ax < gameWorld.getGrid().get(ay).size(); ax++)
                gameWorld.getGrid().get(ay).get(ax).draw(stage.getBatch());

        if (!gameWorld.isEdit())
            for (EnemyBase enemyBase:  gameWorld.getEnemies())
                enemyBase.draw(stage.getBatch());

        if (!gameWorld.getButtons().isEmpty())
            for (Sprite button : gameWorld.getButtons())
                button.draw(stage.getBatch());
        stage.getBatch().end();
    }
}
