package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.editor.Brush;
import com.abhai.towerDefense.gameObjects.Button;
import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;
import com.abhai.towerDefense.states.State;
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
    private ArrayList<State> statesCache;



    private GameWorld() {
        instance = this;
        isEdit = false;

        makeGrid();
        startPoints = new ArrayList<Vector2>();
        finishPoints = new ArrayList<Vector2>();
        enemies = new ArrayList<EnemyBase>();
        buttons = new ArrayList<Button>();
        statesCache = new ArrayList<State>();
    }



    private void addButton(short state, String image) {
        if (buttons.isEmpty())
            buttons.add(new Button(new Texture("images/buttons/" + image), Button.BUTTON_MARGIN, (int) (Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.5), Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT, state));
        else
            buttons.add(new Button(new Texture("images/buttons/" + image), (int) buttons.get(buttons.size() - 1).getX() + Button.BUTTON_WIDTH + Button.BUTTON_MARGIN, (int) (Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.5), Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT, state));
    }


    public void addEnemy(EnemyBase enemyBase) {
        enemies.add(enemyBase);
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


    public void clearGrid() {
        for (int ay = 0; ay < MAP_HEIGHT_MAX; ay++)
            for (int ax = 0; ax < MAP_WITH_MAX; ax++)
                grid.get(ay).get(ax).setState(Cell.STATE_CELL_FREE);
    }


    public void createButtons() {
        if (!buttons.isEmpty())
            buttons.clear();

        if (isEdit) {
            addButton(Button.SAVE_BUTTON_STATE, "saveButton.PNG");
            addButton(Button.CLEAR_BUTTON_STATE, "clearButton.PNG");
            addButton(Button.BUSY_BUTTON_STATE, "busyButton.PNG");
            addButton(Button.BUILD_ONLY_BUTTON_STATE, "buildOnlyButton.PNG");
            addButton(Button.START_POINT_BUTTON_STATE, "startPointButton.PNG");
            addButton(Button.FINISH_POINT_BUTTON_STATE, "finishPointButton.PNG");
        } else {
            //buttons.add(new Sprite(new Texture("tower.jpg"), 50, 50));
        }
    }


    public void deleteEnemy(EnemyBase enemyBase) {
        enemies.remove(enemyBase);
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


    public void newEnemy() {
        EnemySoldier enemySoldier = new EnemySoldier();
        if (startPoints.isEmpty() || finishPoints.isEmpty())
            System.out.println("EnemySoldier.init() - нет стартовой или финишной точки");
        else {
            Vector2 startPoint = startPoints.get((int) (Math.random() * (startPoints.size())));
            Vector2 finishPoint = finishPoints.get((int) (Math.random() * (finishPoints.size())));
            enemySoldier.init(startPoint.x, startPoint.y, finishPoint.x, finishPoint.y);
        }
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


    public void update(float delta) {
        for (EnemyBase enemy: enemies)
            enemy.update(delta);
    }



    public ArrayList<Button> getButtons() {
        return buttons;
    }


    public ArrayList<State> getStatesCache() {
        return statesCache;
    }


    ArrayList<EnemyBase> getEnemies() {
        return enemies;
    }


    public ArrayList<ArrayList<Cell>> getGrid() {
        return grid;
    }


    public static GameWorld getInstance() {
        return (instance == null) ? new GameWorld() : instance;
    }


    public boolean isEdit() {
        return isEdit;
    }


    private void setCellState(int ax, int ay, int state) {
        if (ax >= 0 && ax < MAP_WITH_MAX && ay >= 0 && ay < MAP_HEIGHT_MAX)
            grid.get(ay).get(ax).setState(state);
    }


    public void setEdit(boolean value) {
        isEdit = value;
    }


    public float toPix(float value) {
        return value * Cell.CELL_SIZE;
    }


    public int toTile(double value) {
        return (int)value / Cell.CELL_SIZE;
    }
}