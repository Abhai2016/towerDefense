package com.abhai.towerDefense.levels;

public class LevelManager {
    public static final int TOTAL_LEVELS = 10;
    private int completed;


    public LevelBase getLevel(int id) {
        if (id < 0 || id > TOTAL_LEVELS) {
            System.out.println("LevelManager::getLevel() - Уровня" + id + "не существует!");
            return null;
        }

        switch (id) {
            case 1:
                return new Level1();

            case 2:
                return new Level2();

            default:
                return null;
        }
    }

}
