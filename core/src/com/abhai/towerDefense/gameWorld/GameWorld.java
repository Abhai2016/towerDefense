package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;

import java.util.ArrayList;


public class GameWorld {
    public static final int STATE_CELL_FREE = 1;
    public static final int STATE_CELL_BUSY = 2;
    public static final int STATE_CELL_BUILD_ONLY = 3;

    public static final int MAP_WITH_MAX = 40;
    public static final int MAP_HEIGHT_MAX = 20;
    public static final int MAP_CELL_SIZE = 32;

    private static GameWorld instance;
    private ArrayList<ArrayList<Cell>> grid;
    private ArrayList<EnemyBase> enemies = new ArrayList<EnemyBase>();
    //private ArrayList<BaseTower> towers;
    //private ArrayList<BaseBullet> bullets;



    public GameWorld() {
        if (instance != null)
            return; //log error or exception

        instance = this;
        makeDebugGrid();

        EnemySoldier enemySoldier = new EnemySoldier();
        enemySoldier.init();
    }


    public void update(float delta) {
        for (EnemyBase enemy: enemies)
            enemy.update(delta);
    }


    public ArrayList<ArrayList<Cell>> getGrid() {
        return grid;
    }


    ArrayList<EnemyBase> getEnemies() {
        return enemies;
    }


    public static GameWorld getInstance() {
        return (instance == null) ? new GameWorld() : instance;
    }


    private void makeDebugGrid() {
        grid = new ArrayList<ArrayList<Cell>>();
        int posX = 0;
        int posY = 0;

        for (int ay = 0; ay < MAP_HEIGHT_MAX; ay++) {
            grid.add(new ArrayList<Cell>());

            for (int ax = 0; ax < MAP_WITH_MAX; ax++) {
                Cell cell = new Cell(STATE_CELL_FREE);
                cell.setX(posX);
                cell.setY(posY);
                grid.get(ay).add(cell);

                posX += MAP_CELL_SIZE;
            }

            posY += MAP_CELL_SIZE;
            posX = 0;
        }

        grid.get(1).get(1).setState(STATE_CELL_BUSY);
        grid.get(2).get(1).setState(STATE_CELL_BUSY);

        grid.get(1).get(28).setState(STATE_CELL_BUILD_ONLY);
        grid.get(2).get(28).setState(STATE_CELL_BUILD_ONLY);
    }


    private void clearMapMask() {
        for (int ay = 0; ay < MAP_HEIGHT_MAX; ay++)
            for (int ax = 0; ax < MAP_WITH_MAX; ax++)
                grid.get(ay).get(ax).setState(STATE_CELL_FREE);
    }


    public void addEnemy(EnemyBase enemyBase) {
        enemies.add(enemyBase);
    }


    public void deleteEnemy(EnemyBase enemyBase) {
        enemies.remove(enemyBase);
    }
}
