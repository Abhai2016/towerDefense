package com.abhai.towerDefense.levels;

public class LevelManager {
    public static final int CUSTOM_LEVEL = 0;
    public static final int FIRST_STORY_LEVEL = 1;
    public static final int SECOND_STORY_LEVEL = 2;

    private static final int TOTAL_LEVELS = 10;



    public static LevelBase getLevel(int id) {
        if (id < CUSTOM_LEVEL || id > TOTAL_LEVELS) {
            System.out.println("LevelManager::getLevel() - Уровня" + id + "не существует!");
            return null;
        }

        switch (id) {
            case CUSTOM_LEVEL:
                return new LevelBase();
            case FIRST_STORY_LEVEL:
                return new Level1();
            case SECOND_STORY_LEVEL:
                return new Level2();
            default:
                return null;
        }
    }

}
