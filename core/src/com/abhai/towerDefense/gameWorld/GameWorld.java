package com.abhai.towerDefense.gameWorld;

import com.abhai.towerDefense.Game;
import com.abhai.towerDefense.editor.Brush;
import com.abhai.towerDefense.gameObjects.bullets.BulletBase;
import com.abhai.towerDefense.gameObjects.enemies.enemyHelpers.EnemyWaveController;
import com.abhai.towerDefense.gui.buttons.BaseButton;
import com.abhai.towerDefense.gui.buttons.MenuButton;
import com.abhai.towerDefense.gui.buttons.TowerButton;
import com.abhai.towerDefense.gameObjects.enemies.EnemyBase;
import com.abhai.towerDefense.gameObjects.enemies.EnemyHardSoldier;
import com.abhai.towerDefense.gameObjects.enemies.EnemyTank;
import com.abhai.towerDefense.gameObjects.simpleObjects.Cell;
import com.abhai.towerDefense.gameObjects.ObjectController;
import com.abhai.towerDefense.gameObjects.enemies.EnemySoldier;
import com.abhai.towerDefense.gui.buttons.EditButton;
import com.abhai.towerDefense.gameObjects.towers.DoubleGunTower;
import com.abhai.towerDefense.gameObjects.towers.GunTower;
import com.abhai.towerDefense.gameObjects.towers.RocketTower;
import com.abhai.towerDefense.gameObjects.towers.TowerBase;
import com.abhai.towerDefense.twhelpers.Cache;
import com.abhai.towerDefense.gameObjects.enemies.enemyHelpers.EnemyWave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class GameWorld {
    public static final int MAP_WITH_MAX = 27;
    public static final int MAP_HEIGHT_MAX = 13;

    private static GameWorld instance;

    private boolean isEdit;
    private boolean start;
    private boolean showSaveText;

    private double maxDeltaTime;
    private int typeOfTower;
    private int levelId;

    private Texture background;
    private Sprite typeOfCell;
    private Sprite saveText;

    private ArrayList<ArrayList<Cell>> grid;
    private ArrayList<ArrayList<Cell>> editGrid;

    private EnemyWaveController enemyWaveController;

    private ObjectController enemies;

    private ObjectController gunBullets;
    private ObjectController doubleGunBullets;
    private ObjectController rocketBullets;

    private ObjectController gunTowers;
    private ObjectController doubleGunTowers;
    private ObjectController rocketTowers;

    private ArrayList<BaseButton> editButtons;
    private ArrayList<BaseButton> guiButtons;
    private ArrayList<BaseButton> gameMenuButtons;
    private ArrayList<BaseButton> mainMenuButtons;
    private ArrayList<BaseButton> towerButtons;

    private ArrayList<Vector2> startPoints;
    private ArrayList<Vector2> finishPoints;

    private Cache cacheEnemySoldiers;
    private Cache cacheEnemyHardSoldiers;
    private Cache cacheEnemyTanks;

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
        maxDeltaTime = 0.04;
        typeOfTower = TowerBase.GUN_TOWER;

        background = new Texture("images/backgrounds/menu_background_hd.jpg");
        typeOfCell = new Sprite(new Texture("images/cells.PNG"), Cell.CELL_SIZE,0, Cell.CELL_SIZE, Cell.CELL_SIZE);
        typeOfCell.setPosition(Game.GAME_WITH / 2 - Cell.CELL_SIZE / 2, Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.8f);
        saveText = new Sprite(new Texture("images/saved.PNG"), 0, 0, 250, 30);
        saveText.setPosition(10, Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.6f);

        makeGrid();
        enemyWaveController = new EnemyWaveController();
        enemies = new ObjectController();

        gunBullets = new ObjectController();
        doubleGunBullets = new ObjectController();
        rocketBullets = new ObjectController();

        gunTowers = new ObjectController();
        doubleGunTowers = new ObjectController();
        rocketTowers = new ObjectController();

        editButtons = new ArrayList<BaseButton>();
        guiButtons = new ArrayList<BaseButton>();
        gameMenuButtons = new ArrayList<BaseButton>();
        mainMenuButtons = new ArrayList<BaseButton>();
        towerButtons = new ArrayList<BaseButton>();

        startPoints = new ArrayList<Vector2>();
        finishPoints = new ArrayList<Vector2>();

        cacheEnemySoldiers = new Cache(EnemyBase.ENEMY_SOLDER, 100);
        cacheEnemyHardSoldiers = new Cache(EnemyBase.ENEMY_HARD_SOLDER, 100);
        cacheEnemyTanks = new Cache(EnemyBase.ENEMY_TANK, 100);

        cacheGunBullets = new Cache(BulletBase.GUN_BULLET, 100);
        cacheDoubleGunBullets = new Cache(BulletBase.DOUBLE_GUN_BULLET, 100);
        cacheRocketBullets = new Cache(BulletBase.ROCKET_BULLET, 100);

        cacheGunTowers = new Cache(TowerBase.GUN_TOWER, 30);
        cacheDoubleGunTowers = new Cache(TowerBase.DOUBLE_GUN_TOWER, 30);
        cacheRocketTowers = new Cache(TowerBase.ROCKET_TOWER, 30);
    }





    private void addEditButton(short state, String image) {
        if (editButtons.isEmpty())
            editButtons.add(new EditButton(new Texture("images/gui/buttons/editButtons/" + image),
                    EditButton.EDIT_BUTTON_MARGIN, (int)(Game.GAME_HEIGHT - Cell.CELL_SIZE / 1.8),
                    EditButton.EDIT_BUTTON_WIDTH, EditButton.EDIT_BUTTON_HEIGHT, state));
        else
            editButtons.add(new EditButton(new Texture("images/gui/buttons/editButtons/" + image),
                    (int) (editButtons.get(editButtons.size() - 1)).getX() +
                            EditButton.EDIT_BUTTON_WIDTH + EditButton.EDIT_BUTTON_MARGIN,
                    (int)(Game.GAME_HEIGHT - Cell.CELL_SIZE / 1.8), EditButton.EDIT_BUTTON_WIDTH,
                            EditButton.EDIT_BUTTON_HEIGHT, state));
    }


    private void addGuiButton(short state, String image) {
        guiButtons.add(new MenuButton(new Texture("images/gui/buttons/playStateButtons/" + image),
                Gdx.graphics.getWidth() / 2 - MenuButton.MENU_BUTTON_WIDTH / 2,
                (int)(Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.5), MenuButton.MENU_BUTTON_WIDTH,
                MenuButton.MENU_BUTTON_HEIGHT, state));
    }


    private void addMainMenuButton(short state, String image, int y) {
        mainMenuButtons.add(new MenuButton(new Texture("images/gui/buttons/menuButtons/" + image),
                Gdx.graphics.getWidth() / 2 - MenuButton.MENU_BUTTON_WIDTH / 2, y, MenuButton.MENU_BUTTON_WIDTH,
                MenuButton.MENU_BUTTON_HEIGHT, state));
    }


    private void addGameMenuButton(short state, String image, int y) {
        gameMenuButtons.add(new MenuButton(new Texture("images/gui/buttons/menuButtons/" + image),
                Gdx.graphics.getWidth() / 2 - MenuButton.MENU_BUTTON_WIDTH / 2, y, MenuButton.MENU_BUTTON_WIDTH,
                MenuButton.MENU_BUTTON_HEIGHT, state));
    }


    private void addTowerButton(short state, String image) {
        if (towerButtons.isEmpty())
            towerButtons.add(new TowerButton(new Texture("images/gui/buttons/towerButtons/" + image),
                    TowerButton.TOWER_BUTTON_MARGIN, (int)(Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.75),
                    TowerButton.TOWER_BUTTON_WIDTH, TowerButton.TOWER_BUTTON_HEIGHT, state));
        else
            towerButtons.add(new TowerButton(new Texture("images/gui/buttons/towerButtons/" + image),
                    (int) (towerButtons.get(towerButtons.size() - 1)).getX() +
                            TowerButton.TOWER_BUTTON_WIDTH + TowerButton.TOWER_BUTTON_MARGIN,
                    (int)(Game.GAME_HEIGHT - Cell.CELL_SIZE * 1.75), TowerButton.TOWER_BUTTON_WIDTH,
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
            addEditButton(BaseButton.SAVE_BUTTON_STATE, "saveButton.PNG");
            addEditButton(BaseButton.FREE_BUTTON_STATE, "freeButton.PNG");
            addEditButton(BaseButton.BUSY_BUTTON_STATE, "busyButton.PNG");
            addEditButton(BaseButton.BUILD_ONLY_BUTTON_STATE, "buildOnlyButton.PNG");
            addEditButton(BaseButton.START_POINT_BUTTON_STATE, "startPointButton.PNG");
            addEditButton(BaseButton.FINISH_POINT_BUTTON_STATE, "finishPointButton.PNG");
        }
    }


    public void createGameMenuButtons() {
        if (gameMenuButtons.isEmpty()) {
            addGameMenuButton(BaseButton.NEW_STORY_LEVEL_BUTTON_STATE, "storyLevelButton.PNG",
                    (Game.GAME_HEIGHT / 15) - MenuButton.MENU_BUTTON_HEIGHT / 2);
            addGameMenuButton(BaseButton.NEW_CUSTOM_LEVEL_BUTTON_STATE, "customLevelButton.PNG",
                    (Game.GAME_HEIGHT / 5) - MenuButton.MENU_BUTTON_HEIGHT / 2);
        }
    }


    private void createGuiButtons() {
        if (guiButtons.isEmpty()) {
            addGuiButton(BaseButton.START_BUTTON_STATE, "startButton.PNG");
        }
    }


    public void createMainMenuButtons() {
        if (mainMenuButtons.isEmpty()) {
            addMainMenuButton(BaseButton.CONTINUE_BUTTON_STATE, "continueButton.PNG",
                    (Game.GAME_HEIGHT / 15) - MenuButton.MENU_BUTTON_HEIGHT / 2);
            addMainMenuButton(BaseButton.NEW_GAME_BUTTON_STATE, "newGameButton.PNG",
                    (Game.GAME_HEIGHT / 5) - MenuButton.MENU_BUTTON_HEIGHT / 2);
            addMainMenuButton(BaseButton.EDIT_BUTTON_STATE, "editModeButton.PNG",
                    (Game.GAME_HEIGHT / 3) - MenuButton.MENU_BUTTON_HEIGHT / 2);
            addMainMenuButton(BaseButton.OPTIONS_BUTTON_STATE, "optionsButton.PNG",
                    (int)(Game.GAME_HEIGHT / 1.2) - MenuButton.MENU_BUTTON_HEIGHT / 2);
            addMainMenuButton(BaseButton.EXIT_BUTTON_STATE, "exitButton.PNG",
                    (int) (Game.GAME_HEIGHT / 1.05) - MenuButton.MENU_BUTTON_HEIGHT / 2);
        }
    }


    public void createPlayStateButtons() {
        createTowerButtons();
        createGuiButtons();
    }


    private void createTowerButtons() {
        if (towerButtons.isEmpty()) {
            addTowerButton(BaseButton.GUN_BUTTON_STATE, "gunTowerButton.PNG");
            addTowerButton(BaseButton.DOUBLE_GUN_BUTTON_STATE, "doubleGunTowerButton.PNG");
            addTowerButton(BaseButton.ROCKET_BUTTON_STATE, "rocketTowerButton.PNG");
        }
    }



    private EnemyBase getTypeOfEnemy(int typeOfEnemy) {
        EnemyBase enemyBase;
        switch (typeOfEnemy) {
            case EnemyBase.ENEMY_SOLDER:
                enemyBase = (EnemySoldier) cacheEnemySoldiers.get();
                break;
            case EnemyBase.ENEMY_HARD_SOLDER:
                enemyBase = (EnemyHardSoldier) cacheEnemyHardSoldiers.get();
                break;
            case EnemyBase.ENEMY_TANK:
                enemyBase = (EnemyTank) cacheEnemyTanks.get();
                break;
            default:
                enemyBase = new EnemySoldier();
        }
        return enemyBase;
    }


    public void makeEnemyWaves(int levelId) {
        enemyWaveController.init(levelId);
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


    public void newEnemy(int typeOfEnemy) {
        EnemyBase enemyBase = getTypeOfEnemy(typeOfEnemy);

        if (startPoints.isEmpty() || finishPoints.isEmpty())
            System.out.println("EnemySoldier.init() - нет стартовой или финишной точки");
        else {
            Vector2 startPoint = startPoints.get((int) (Math.random() * (startPoints.size())));
            Vector2 finishPoint = finishPoints.get((int) (Math.random() * (finishPoints.size())));
            enemyBase.init(startPoint.x, startPoint.y, finishPoint.x, finishPoint.y);
        }
    }


    public void newEnemy(int typeOfEnemy, Vector2 startPoint, Vector2 finishPoint) {
        EnemyBase enemyBase = getTypeOfEnemy(typeOfEnemy);

        enemyBase.init(startPoint.x, startPoint.y, finishPoint.x, finishPoint.y);
    }


    public void newGame() {
        enemies.clear();

        gunBullets.clear();
        doubleGunBullets.clear();
        rocketBullets.clear();

        gunTowers.clear();
        doubleGunTowers.clear();
        rocketTowers.clear();

        startPoints.clear();
        finishPoints.clear();
        enemyWaveController.clear();
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


    public void startWaves() {
        enemyWaveController.startWave();
    }


    public void update(float delta) {
        enemies.update(delta);

        gunBullets.update(delta);
        doubleGunBullets.update(delta);
        rocketBullets.update(delta);

        gunTowers.update(delta);
        doubleGunTowers.update(delta);
        rocketTowers.update(delta);

        enemyWaveController.update(delta);
    }



    public Texture getBackground() {
        return background;
    }


    public Cache getCacheEnemySoldiers() {
        return cacheEnemySoldiers;
    }


    public Cache getCacheEnemyHardSoldiers() {
        return cacheEnemyHardSoldiers;
    }


    public Cache getCacheEnemyTanks() {
        return cacheEnemyTanks;
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


    public ArrayList<BaseButton> getGuiButtons() {
        return guiButtons;
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


    public ArrayList<BaseButton> getGameMenuButtons() {
        return gameMenuButtons;
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


    public void setLevelId(int levelId) {
        this.levelId = levelId;
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