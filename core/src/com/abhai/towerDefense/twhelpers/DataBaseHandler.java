package com.abhai.towerDefense.twhelpers;

import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class DataBaseHandler {
    private static final String DATABASE_NAME = "towerDefense";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHandler instance;
    private Database database;



    private DataBaseHandler() {
        database =  DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, null, null);
        database.setupDatabase();
    }


    public static DataBaseHandler getInstance() {
        return (instance == null) ? new DataBaseHandler() : instance;
    }


    public void openDatabase() throws SQLiteGdxException {
        database.openOrCreateDatabase();
    }


    public void closeDatabase() throws SQLiteGdxException {
        database.closeDatabase();
    }


    public void execSQL(String s) throws SQLiteGdxException {
        database.execSQL(s);
    }


    public DatabaseCursor rawQuery(String query) {
        try {
            return database.rawQuery(query);
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
