package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.editor.Brush;
import com.abhai.towerDefense.gameObjects.Button;
import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class GameWorld {
    public static final int MAP_WITH_MAX = 40;
    public static final int MAP_HEIGHT_MAX = 20;

    private boolean isEdit;
    private static GameWorld instance;

    private ArrayList<ArrayList<Cell>> grid;
    private ArrayList<EnemyBase> enemies;
    private ArrayList<Button> buttons;
    private ArrayList<Vector2> startPoints;
    private ArrayList<Vector2> finishPoints;
    //private ArrayList<BaseTower> towers;
    //private ArrayList<BaseBullet> bullets;



    private GameWorld() {
        instance = this;
        isEdit = false;

        makeGrid();
        startPoints = new ArrayList<Vector2>();
        finishPoints = new ArrayList<Vector2>();
        enemies = new ArrayList<EnemyBase>();
        buttons = new ArrayList<Button>();
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


    public ArrayList<Button> getButtons() {
        return buttons;
    }


    private void makeGrid() {
        grid = new ArrayList<ArrayList<Cell>>();
        int posX = 0;
        int posY = 0;

        for (int ay = 0; ay < MAP_HEIGHT_MAX; ay++) {
            grid.add(new ArrayList<Cell>());

            for (int ax = 0; ax < MAP_WITH_MAX; ax++) {
                Cell cell = new Cell(Cell.STATE_CELL_FREE);
                cell.setX(posX);
                cell.setY(posY);
                grid.get(ay).add(cell);

                posX += Cell.CELL_SIZE;
            }

            posY += Cell.CELL_SIZE;
            posX = 0;
        }
    }


    public void clearGrid() {
        for (int ay = 0; ay < MAP_HEIGHT_MAX; ay++)
            for (int ax = 0; ax < MAP_WITH_MAX; ax++)
                grid.get(ay).get(ax).setState(Cell.STATE_CELL_FREE);
    }


    public void preparePoints() {
        startPoints.clear();
        finishPoints.clear();

        for (int i = 0; i < MAP_HEIGHT_MAX; i++)
            for (int j = 0; j < MAP_WITH_MAX; j++)
                switch (grid.get(i).get(j).getState()) {
                    case Cell.STATE_CELL_START:
                        startPoints.add(new Vector2(j, i));
                        break;
                    case Cell.STATE_CELL_FINISH:
                        finishPoints.add(new Vector2(j, i));
                        break;
                }
    }


    public void addEnemy(EnemyBase enemyBase) {
        enemies.add(enemyBase);
    }


    public void deleteEnemy(EnemyBase enemyBase) {
        enemies.remove(enemyBase);
    }


    public void createButtons() {
        if (!buttons.isEmpty())
            buttons.clear();

        if (isEdit) {
            addButton("Save", "saveButton.png", -1);
            addButton("Clear", "clearButton.png", 0);
            addButton("Busy", "busyButton.png", 1);
            addButton("Build Only", "buildOnlyButton.png", 2);
            addButton("Start Point", "startPointButton.png", 3);
            addButton("Finish Point", "finishPointButton.png", 4);
        } else {
            //buttons.add(new Sprite(new Texture("tower.jpg"), 50, 50));
        }
    }


    private void addButton(String title, String path, int x) {
        if (x != -1)
            buttons.add(new Button(title, new Texture("images/buttons/" + path),
                    (int)buttons.get(x).getX() + Button.BUTTON_WIDTH + Button.BUTTON_MARGIN,
                    (int) (Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.5), Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT));
        else
            buttons.add(new Button(title, new Texture("images/buttons/" + path), Button.BUTTON_MARGIN,
                    (int) (Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.5), Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT));
    }


    public void newEnemy() {
        EnemySoldier enemySoldier = new EnemySoldier();
        if (startPoints.isEmpty() || finishPoints.isEmpty())
            System.out.println("EnemySoldier.init() - нет стартовой или финишной точки");
        else {
            Vector2 startPoint = startPoints.get((int) (Math.random() * (startPoints.size())));
            Vector2 finishPoint = finishPoints.get((int) (Math.random() * (finishPoints.size())));
            enemySoldier.init((int)startPoint.x, (int)startPoint.y, (int)finishPoint.x, (int)finishPoint.y);
        }
    }


    public void setEdit(boolean value) {
        isEdit = value;
    }


    public boolean isEdit() {
        return isEdit;
    }


    public int toTile(double value) {
        return (int)value / Cell.CELL_SIZE;
    }


    public float toPix(float value) {
        return value * Cell.CELL_SIZE;
    }


    private void setCellState(int ax, int ay, int state) {
        if (ax >= 0 && ax < MAP_WITH_MAX && ay >= 0 && ay < MAP_HEIGHT_MAX)
            grid.get(ay).get(ax).setState(state);
    }


    public void applyBrush(Brush brush, double screenX, double screenY) {
        int cellPosX = toTile(screenX);
        int cellPosY = toTile(screenY);

        if (brush.tileX != cellPosX || brush.tileY != cellPosY) {
            setCellState(cellPosX, cellPosY, brush.kind);
            brush.tileX = cellPosX;
            brush.tileY = cellPosY;
        }
    }


    public void dispose() {
        instance = null;
    }
}