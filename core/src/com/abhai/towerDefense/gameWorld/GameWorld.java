package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.editor.Brush;
import com.abhai.towerDefense.gameObjects.bullets.BulletBase;
import com.abhai.towerDefense.gameObjects.buttons.BaseButton;
import com.abhai.towerDefense.gameObjects.buttons.TowerButton;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameObjects.ObjectController;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;
import com.abhai.towerDefense.gameObjects.buttons.MenuAndEditButton;
import com.abhai.towerDefense.gameObjects.towers.DoubleGunTower;
import com.abhai.towerDefense.gameObjects.towers.GunTower;
import com.abhai.towerDefense.gameObjects.towers.RocketTower;
import com.abhai.towerDefense.gameObjects.towers.TowerBase;
import com.abhai.towerDefense.twhelpers.Cache;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class GameWorld {
    public static final int MAP_WITH_MAX = 40;
    public static final int MAP_HEIGHT_MAX = 20;
    private static GameWorld instance;

    private boolean isEdit;
    private boolean start;
    private boolean showSaveText;

    private int centerOfWidth;
    private double maxDeltaTime;
    private int typeOfTower;

    private Texture background;
    private Sprite typeOfCell;
    private Sprite saveText;

    private ArrayList<ArrayList<Cell>> grid;
    private ArrayList<ArrayList<Cell>> editGrid;

    private ObjectController enemies;

    private ObjectController gunBullets;
    private ObjectController doubleGunBullets;
    private ObjectController rocketBullets;

    private ObjectController gunTowers;
    private ObjectController doubleGunTowers;
    private ObjectController rocketTowers;

    private ArrayList<BaseButton> editButtons;
    private ArrayList<BaseButton> mainMenuButtons;
    private ArrayList<BaseButton> towerButtons;

    private ArrayList<Vector2> startPoints;
    private ArrayList<Vector2> finishPoints;

    private Cache cacheEnemySoldiers;
    private Cache cacheGunBullets;
    private Cache cacheDoubleGunBullets;
    private Cache cacheRocketBullets;

    private Cache cacheGunTowers;
    private Cache cacheDoubleGunTowers;
    private Cache cacheRocketTowers;





    private GameWorld() {
        instance = this;
        isEdit = false;
        start = true;
        showSaveText = false;

        if (Gdx.graphics.getWidth() > Game.GAME_WITH)
            centerOfWidth = Gdx.graphics.getWidth() / 3;
        else
            centerOfWidth = Game.GAME_WITH / 2 - MenuAndEditButton.MENU_AND_EDIT_BUTTON_WIDTH / 2;
        maxDeltaTime = 0.04;
        typeOfTower = TowerBase.GUN_TOWER;

        background = new Texture("images/backgrounds/menu_background_hd.jpg");
        typeOfCell = new Sprite(new Texture("images/cells.PNG"), 32,0, Cell.CELL_SIZE, Cell.CELL_SIZE);
        typeOfCell.setPosition(Game.GAME_WITH / 2 - Cell.CELL_SIZE / 2, Game.GAME_HEIGHT - Cell.CELL_SIZE * 2.3f);
        saveText = new Sprite(new Texture("images/saved.PNG"), 0, 0, 250, 30);
        saveText.setPosition(10, Game.GAME_HEIGHT - Cell.CELL_SIZE * 2.2f);

        makeGrid();
        enemies = new ObjectController();

        gunBullets = new ObjectController();
        doubleGunBullets = new ObjectController();
        rocketBullets = new ObjectController();

        gunTowers = new ObjectController();
        doubleGunTowers = new ObjectController();
        rocketTowers = new ObjectController();

        editButtons = new ArrayList<BaseButton>();
        mainMenuButtons = new ArrayList<BaseButton>();
        towerButtons = new ArrayList<BaseButton>();

        startPoints = new ArrayList<Vector2>();
        finishPoints = new ArrayList<Vector2>();

        cacheEnemySoldiers = new Cache(EnemyBase.ENEMY_SOLDER, 50);

        cacheGunBullets = new Cache(BulletBase.GUN_BULLET, 50);
        cacheDoubleGunBullets = new Cache(BulletBase.DOUBLE_GUN_BULLET, 50);
        cacheRocketBullets = new Cache(BulletBase.ROCKET_BULLET, 50);

        cacheGunTowers = new Cache(TowerBase.GUN_TOWER, 20);
        cacheDoubleGunTowers = new Cache(TowerBase.DOUBLE_GUN_TOWER, 20);
        cacheRocketTowers = new Cache(TowerBase.ROCKET_TOWER, 20);
    }





    private void addEditButton(short state, String image) {
        if (editButtons.isEmpty())
            editButtons.add(new MenuAndEditButton(new Texture("images/buttons/" + image),
                    MenuAndEditButton.MENU_AND_EDIT_BUTTON_MARGIN, (Game.GAME_HEIGHT - Cell.CELL_SIZE),
                    MenuAndEditButton.MENU_AND_EDIT_BUTTON_WIDTH, MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT, state));
        else
            editButtons.add(new MenuAndEditButton(new Texture("images/buttons/" + image),
                    (int) (editButtons.get(editButtons.size() - 1)).getX() +
                            MenuAndEditButton.MENU_AND_EDIT_BUTTON_WIDTH + MenuAndEditButton.MENU_AND_EDIT_BUTTON_MARGIN,
                            (Game.GAME_HEIGHT - Cell.CELL_SIZE), MenuAndEditButton.MENU_AND_EDIT_BUTTON_WIDTH,
                            MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT, state));
    }


    private void addMainMenuButton(short state, String image, int y) {
        mainMenuButtons.add(new MenuAndEditButton(new Texture("images/buttons/" + image), centerOfWidth, y,
                MenuAndEditButton.MENU_AND_EDIT_BUTTON_WIDTH, MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT, state));
    }


    private void addTowerButton(short state, String image) {
        if (towerButtons.isEmpty())
            towerButtons.add(new TowerButton(new Texture("images/buttons/" + image),
                    TowerButton.TOWER_BUTTON_MARGIN, (int)(Game.GAME_HEIGHT - Cell.CELL_SIZE * 2.25),
                    TowerButton.TOWER_BUTTON_WIDTH, TowerButton.TOWER_BUTTON_HEIGHT, state));
        else
            towerButtons.add(new TowerButton(new Texture("images/buttons/" + image),
                    (int) (towerButtons.get(towerButtons.size() - 1)).getX() +
                            TowerButton.TOWER_BUTTON_WIDTH + TowerButton.TOWER_BUTTON_MARGIN,
                    (int)(Game.GAME_HEIGHT - Cell.CELL_SIZE * 2.25), TowerButton.TOWER_BUTTON_WIDTH,
                    TowerButton.TOWER_BUTTON_HEIGHT, state));
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
            addEditButton(MenuAndEditButton.SAVE_BUTTON_STATE, "saveButton.PNG");
            addEditButton(MenuAndEditButton.FREE_BUTTON_STATE, "freeButton.PNG");
            addEditButton(MenuAndEditButton.BUSY_BUTTON_STATE, "busyButton.PNG");
            addEditButton(MenuAndEditButton.BUILD_ONLY_BUTTON_STATE, "buildOnlyButton.PNG");
            addEditButton(MenuAndEditButton.START_POINT_BUTTON_STATE, "startPointButton.PNG");
            addEditButton(MenuAndEditButton.FINISH_POINT_BUTTON_STATE, "finishPointButton.PNG");
        }
    }


    public void createMainMenuButtons() {
        if (mainMenuButtons.isEmpty()) {
            addMainMenuButton(MenuAndEditButton.CONTINUE_BUTTON_STATE, "continueButton.PNG",
                    (Game.GAME_HEIGHT / 15) - MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT / 2);
            addMainMenuButton(MenuAndEditButton.NEW_GAME_BUTTON_STATE, "newGameButton.PNG",
                    (int) (Game.GAME_HEIGHT / 7.75) -MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT / 2);
            addMainMenuButton(MenuAndEditButton.EDIT_BUTTON_STATE, "editButton.PNG",
                    (int) (Game.GAME_HEIGHT / 5.25) - MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT / 2);
            addMainMenuButton(MenuAndEditButton.OPTIONS_BUTTON_STATE, "optionsButton.PNG",
                    (int)(Game.GAME_HEIGHT / 1.12) - MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT / 2);
            addMainMenuButton(MenuAndEditButton.EXIT_BUTTON_STATE, "exitButton.PNG",
                    (int) (Game.GAME_HEIGHT / 1.05) - MenuAndEditButton.MENU_AND_EDIT_BUTTON_HEIGHT / 2);
        }
    }


    public void createTowerButtons() {
        if (towerButtons.isEmpty()) {
            addTowerButton(TowerButton.GUN_BUTTON_STATE, "gunTowerButton.PNG");
            addTowerButton(TowerButton.DOUBLE_GUN_BUTTON_STATE, "doubleGunTowerButton.PNG");
            addTowerButton(TowerButton.ROCKET_BUTTON_STATE, "rocketTowerButton.PNG");
        }
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
        EnemySoldier enemySoldier = (EnemySoldier) cacheEnemySoldiers.get();
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

        gunBullets.clear();
        doubleGunBullets.clear();
        rocketBullets.clear();

        gunTowers.clear();
        doubleGunTowers.clear();
        rocketTowers.clear();
    }


    public void newTower(double x, double y) {
        int tileX = toTile(x);
        int tileY = toTile(y);

        switch (typeOfTower) {
            case TowerBase.GUN_TOWER:
                GunTower gunTower = (GunTower) cacheGunTowers.get();
                gunTower.init(tileX, tileY);
                grid.get(tileY).get(tileX).setState(Cell.STATE_CELL_GUN_TOWER);
                break;
            case TowerBase.DOUBLE_GUN_TOWER:
                    DoubleGunTower doubleGunTower = (DoubleGunTower) cacheDoubleGunTowers.get();
                    doubleGunTower.init(tileX, tileY);
                    grid.get(tileY).get(tileX).setState(Cell.STATE_CELL_DOUBLE_GUN_TOWER);
                break;
               case TowerBase.ROCKET_TOWER:
                   RocketTower rocketTower = (RocketTower) cacheRocketTowers.get();
                   rocketTower.init(tileX, tileY);
                   grid.get(tileY).get(tileX).setState(Cell.STATE_CELL_ROCKET_TOWER);
                   break;
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
        enemies.update(delta);

        gunBullets.update(delta);
        doubleGunBullets.update(delta);
        rocketBullets.update(delta);

        gunTowers.update(delta);
        doubleGunTowers.update(delta);
        rocketTowers.update(delta);
    }



    public Texture getBackground() {
        return background;
    }


    public Cache getCacheEnemySoldiers() {
        return cacheEnemySoldiers;
    }


    public Cache getCacheGunBullets() {
        return cacheGunBullets;
    }


    public Cache getCacheDoubleGunBullets() {
        return cacheDoubleGunBullets;
    }


    public Cache getCacheRocketBullets() {
        return cacheRocketBullets;
    }


    public ObjectController getDoubleGunBullets() {
        return doubleGunBullets;
    }


    public ObjectController getDoubleGunTowers() {
        return doubleGunTowers;
    }


    public ObjectController getGunBullets() {
        return gunBullets;
    }


    public ObjectController getGunTowers() {
        return gunTowers;
    }


    public ArrayList<BaseButton> getEditButtons() {
        return editButtons;
    }


    public ArrayList<ArrayList<Cell>> getEditGrid() {
        return editGrid;
    }


    public ObjectController getEnemies() {
        return enemies;
    }


    public ArrayList<ArrayList<Cell>> getGrid() {
        return grid;
    }


    public static GameWorld getInstance() {
        return (instance == null) ? new GameWorld() : instance;
    }


    public ArrayList<BaseButton> getMainMenuButtons() {
        return mainMenuButtons;
    }


    public double getMaxDeltaTime() {
        return maxDeltaTime;
    }


    public ObjectController getRocketBullets() {
        return rocketBullets;
    }


    public ObjectController getRocketTowers() {
        return rocketTowers;
    }


    public Sprite getSaveText() {
        return saveText;
    }


    public ArrayList<BaseButton> getTowerButtons() {
        return towerButtons;
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


    public void setTypeOfTower(int typeOfTower) {
        this.typeOfTower = typeOfTower;
    }


    public float toPix(float value) {
        return value * Cell.CELL_SIZE;
    }


    public int toTile(double value) {
        return (int)value / Cell.CELL_SIZE;
    }
}