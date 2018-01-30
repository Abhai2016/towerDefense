package com.abhai.towerDefense.twhelpers;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class PathFinder {
    private static final int WATER_KEY = 999;
    private static final int MAX_ITERATIONS = 100;

    private int[][] grid;
    private ArrayList<ArrayList<Vector2>> mapDirs = new ArrayList<ArrayList<Vector2>>();

    private int mapHeight = 0;
    private int mapWidth = 0;
    private int freeCell = 0;



    public PathFinder() {
        mapHeight = GameWorld.MAP_HEIGHT_MAX;
        mapWidth = GameWorld.MAP_WITH_MAX;

        grid = new int[mapHeight][mapWidth];
        for (int y = 0; y < mapHeight; y++)
            for (int x = 0; x < mapWidth; x++)
                grid[y][x] = GameWorld.getInstance().getGrid().get(y).get(x).getState();

        for (int i = 0; i < mapHeight; i++) {
            mapDirs.add(new ArrayList<Vector2>());
            for (int j = 0; j < mapWidth; j++)
                mapDirs.get(i).add(new Vector2());
        }
    }


    private boolean inMap(int ax, int ay) {
        return (ax >= 0 && ax < mapWidth && ay >= 0 && ay < mapHeight);
    }


    private void goWater(int ax, int ay) {
        // Если клеточка сверху свободна
        if (inMap(ax, ay - 1) && grid[ay - 1][ax] == freeCell) {
            grid[ay - 1][ax] = WATER_KEY;
            mapDirs.get(ay - 1).get(ax).x = ax;
            mapDirs.get(ay - 1).get(ax).y = ay;
        }

        // Если клеточка слева свободна
        if (inMap(ax - 1, ay) && grid[ay][ax - 1] == freeCell) {
            grid[ay][ax - 1] = WATER_KEY;
            mapDirs.get(ay).get(ax - 1).x = ax;
            mapDirs.get(ay).get(ax - 1).y = ay;
        }

        // Если клеточка снизу свободна
        if (inMap(ax, ay + 1) && grid[ay + 1][ax] == freeCell) {
            grid[ay + 1][ax] = WATER_KEY;
            mapDirs.get(ay + 1).get(ax).x = ax;
            mapDirs.get(ay + 1).get(ax).y = ay;
        }

        // Есле клеточка справа свободна
        if (inMap(ax + 1, ay) && grid[ay][ax + 1] == freeCell) {
            grid[ay][ax + 1] = WATER_KEY;
            mapDirs.get(ay).get(ax + 1).x = ax;
            mapDirs.get(ay).get(ax + 1).y = ay;
        }
    }


    public ArrayList<Vector2> findWay(Vector2 start, Vector2 end) {
        grid[(int)end.y][(int)end.x] = WATER_KEY;
        int counter = 0;

        while (counter < MAX_ITERATIONS) {
            for (int y = 0; y < mapHeight; y++)
                for (int x = 0; x < mapWidth; x++)
                    if (grid[y][x] == WATER_KEY)
                        goWater(x, y);

            if (grid[(int)start.y][(int)start.x] == WATER_KEY)
                return getWay(start, end);
            counter++;
        }

        return new ArrayList<Vector2>();
    }


    private ArrayList<Vector2> getWay(Vector2 start, Vector2 end) {
        ArrayList<Vector2> way = new ArrayList<Vector2>();
        Vector2 p1 = new Vector2(start.x, start.y);
        Vector2 p2 = new Vector2();

        while (true) {
            p2.x = mapDirs.get((int)p1.y).get((int)p1.x).x;
            p2.y = mapDirs.get((int)p1.y).get((int)p1.x).y;

            way.add(new Vector2(p2.x, p2.y));

            p1.x = p2.x;
            p1.y = p2.y;

            if (p1.x == end.x && p1.y == end.y)
                break;
        }
        return way;
    }


    public void setFreeCell(int value) {
        freeCell = value;
    }
}