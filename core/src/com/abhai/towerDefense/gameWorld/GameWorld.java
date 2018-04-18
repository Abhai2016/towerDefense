package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.editor.Brush;
import com.abhai.towerDefense.gameObjects.Button;
import com.abhai.towerDefense.gameObjects.Cell;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;
import com.abhai.towerDefense.states.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;


public class GameWorld {
    public static final int MAP_WITH_MAX = 40;
    public static final int MAP_HEIGHT_MAX = 20;
    private static GameWorld instance;

    private boolean isEdit;
    private boolean start;
    private boolean showSaveText;

    private int centerOfWidth;

    private Texture background;
    private Sprite typeOfCell;
    private Sprite saveText;

    private ArrayList<ArrayList<Cell>> grid;
    private ArrayList<ArrayList<Cell>> editGrid;

    private ArrayList<EnemyBase> enemies;

    private ArrayList<Vector2> startPoints;
    private ArrayList<Vector2> finishPoints;

    private ArrayList<Button> editButtons;
    private ArrayList<Button> mainMenuButtons;
    //private ArrayList<BaseTower> towers;
    //private ArrayList<BaseBullet> bullets;



    private GameWorld() {
        instance = this;
        isEdit = false;
        start = true;
        showSaveText = false;

        if (Gdx.graphics.getWidth() > Game.GAME_WITH)
            centerOfWidth = Gdx.graphics.getWidth() / 3;
        else
            centerOfWidth = Game.GAME_WITH / 2 - Button.BUTTON_WIDTH / 2;

        background = new Texture("images/backgrounds/menu_background_hd.jpg");
        typeOfCell = new Sprite(new Texture("images/cells.jpg"), 32,0, Cell.CELL_SIZE, Cell.CELL_SIZE);
        typeOfCell.setPosition(Game.GAME_WITH / 2 - Cell.CELL_SIZE / 2, Game.GAME_HEIGHT - Cell.CELL_SIZE * 2.3f);
        saveText = new Sprite(new Texture("images/saved.PNG"), 0, 0, 250, 30);
        saveText.setPosition(10, Game.GAME_HEIGHT - Cell.CELL_SIZE * 2.2f);

        makeGrid();
        startPoints = new ArrayList<Vector2>();
        finishPoints = new ArrayList<Vector2>();
        enemies = new ArrayList<EnemyBase>();
        editButtons = new ArrayList<Button>();
        mainMenuButtons = new ArrayList<Button>();
    }



    private void addEditButton(short state, String image) {
        if (editButtons.isEmpty())
            editButtons.add(new Button(new Texture("images/buttons/" + image), Button.BUTTON_MARGIN, (Game.GAME_HEIGHT - Cell.CELL_SIZE), Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT, state));
        else
            editButtons.add(new Button(new Texture("images/buttons/" + image), (int) editButtons.get(editButtons.size() - 1).getX() + Button.BUTTON_WIDTH + Button.BUTTON_MARGIN, (Game.GAME_HEIGHT - Cell.CELL_SIZE), Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT, state));
    }


    public void addEnemy(EnemyBase enemyBase) {
        enemies.add(enemyBase);
    }


    private void addMainMenuButton(short state, String image, int y) {
        mainMenuButtons.add(new Button(new Texture("images/buttons/" + image), centerOfWidth, y, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT, state));
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


    public void createEditButtons() {
        if (editButtons.isEmpty()) {
            addEditButton(Button.SAVE_BUTTON_STATE, "saveButton.PNG");
            addEditButton(Button.FREE_BUTTON_STATE, "freeButton.PNG");
            addEditButton(Button.BUSY_BUTTON_STATE, "busyButton.PNG");
            addEditButton(Button.BUILD_ONLY_BUTTON_STATE, "buildOnlyButton.PNG");
            addEditButton(Button.START_POINT_BUTTON_STATE, "startPointButton.PNG");
            addEditButton(Button.FINISH_POINT_BUTTON_STATE, "finishPointButton.PNG");
        }
    }


    public void createMainMenuButtons() {
        if (mainMenuButtons.isEmpty()) {
            addMainMenuButton(Button.CONTINUE_BUTTON_STATE, "continueButton.PNG",   (Game.GAME_HEIGHT / 15) - Button.BUTTON_HEIGHT / 2);
            addMainMenuButton(Button.NEW_GAME_BUTTON_STATE, "newGameButton.PNG",  (int) (Game.GAME_HEIGHT / 7.75) - Button.BUTTON_HEIGHT / 2);
            addMainMenuButton(Button.EDIT_BUTTON_STATE, "editButton.PNG",   (int) (Game.GAME_HEIGHT / 5.25) - Button.BUTTON_HEIGHT / 2);
            addMainMenuButton(Button.OPTIONS_BUTTON_STATE, "optionsButton.PNG",  (int)(Game.GAME_HEIGHT / 1.12) - Button.BUTTON_HEIGHT / 2);
            addMainMenuButton(Button.EXIT_BUTTON_STATE, "exitButton.PNG",  (int) (Game.GAME_HEIGHT / 1.05) - Button.BUTTON_HEIGHT / 2);
        }
    }


    public void deleteEnemy(EnemyBase enemyBase) {
        enemies.remove(enemyBase);
    }


    private void makeGrid() {
        grid = new ArrayList<ArrayList<Cell>>();
        editGrid = new ArrayList<ArrayList<Cell>>();
        int posX = 0;
        int posY = 0;

        for (int ay = 0; ay < MAP_HEIGHT_MAX; ay++) {
            grid.add(new ArrayList<Cell>());
            editGrid.add(new ArrayList<Cell>());

            for (int ax = 0; ax < MAP_WITH_MAX; ax++) {
                Cell cell = new Cell(Cell.STATE_CELL_FREE);
                cell.setX(posX);
                cell.setY(posY);
                grid.get(ay).add(cell);

                Cell editCell = new Cell(Cell.STATE_CELL_FREE);
                editCell.setX(posX);
                editCell.setY(posY);
                editGrid.get(ay).add(editCell);

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


    public void newGame() {
        enemies.clear();
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



    public Texture getBackground() {
        return background;
    }


    public ArrayList<Button> getEditButtons() {
        return editButtons;
    }


    public ArrayList<ArrayList<Cell>> getEditGrid() {
        return editGrid;
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


    public ArrayList<Button> getMainMenuButtons() {
        return mainMenuButtons;
    }


    public Sprite getSaveText() {
        return saveText;
    }

    public Sprite getTypeOfCell() {
        return typeOfCell;
    }


    public boolean isEdit() {
        return isEdit;
    }


    public boolean isShowSaveText() {
        return showSaveText;
    }

    public boolean isStart() {
        return start;
    }


    private void setCellState(int ax, int ay, int state) {
        if (ax >= 0 && ax < MAP_WITH_MAX && ay >= 0 && ay < MAP_HEIGHT_MAX)
            editGrid.get(ay).get(ax).setState(state);
    }


    public void setEdit(boolean value) {
        isEdit = value;
    }


    public void setShowSaveText(boolean showSaveText) {
        this.showSaveText = showSaveText;
    }

    public void setStart(boolean start) {
        this.start = start;
    }


    public float toPix(float value) {
        return value * Cell.CELL_SIZE;
    }


    public int toTile(double value) {
        return (int)value / Cell.CELL_SIZE;
    }
}