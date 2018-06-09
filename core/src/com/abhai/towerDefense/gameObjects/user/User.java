package com.abhai.towerDefense.gameObjects.user;

import com.abhai.towerDefense.gameWorld.GameWorld;
import com.abhai.towerDefense.twhelpers.DataBaseHandler;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class User {
    private static final String createQuery = "CREATE TABLE IF NOT EXISTS User (id INTEGER PRIMARY KEY, health INTEGER, " +
            "money INTEGER, levelId INTEGER);";

    private DataBaseHandler dataBaseHandler;

    private int hp;
    private int money;





    public User() {
        dataBaseHandler = DataBaseHandler.getInstance();
        try {
            dataBaseHandler.openDatabase();

            dataBaseHandler.execSQL(createQuery);
            DatabaseCursor cursor = dataBaseHandler.rawQuery("SELECT * FROM User");
            if (!cursor.next()) {
                GameWorld.getInstance().setLevelId(1);
                hp = 10;
                money = 100;
                dataBaseHandler.execSQL("INSERT INTO User VALUES(1, '" + hp + "', '" + money + "', '" + GameWorld.getInstance().getLevelId() + "');");
            } else {
                hp = cursor.getInt(1);
                money = cursor.getInt(2);
                GameWorld.getInstance().setLevelId(cursor.getInt(3));
            }
            dataBaseHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }




    public void newGame() {
        try {
            dataBaseHandler.openDatabase();
            dataBaseHandler.execSQL("UPDATE User SET health = 100, money = 100, levelId = 1 WHERE id = 1");
            dataBaseHandler.closeDatabase();
            GameWorld.getInstance().setLevelId(1);
            hp = 100;
            money = 100;
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }


    public int getHp() {
        return hp;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }


    public int getMoney() {
        return money;
    }


    public void setMoney(int money) {
        this.money = money;
    }


    public void saveGame() {
       try {
           dataBaseHandler.openDatabase();
           dataBaseHandler.execSQL("UPDATE User SET health = '" + hp + "', money = '" + money +
                   "', levelId = '" + GameWorld.getInstance().getLevelId() + "' WHERE id = 1");
           dataBaseHandler.closeDatabase();
       } catch (SQLiteGdxException e) {
           e.printStackTrace();
       }
    }
}
