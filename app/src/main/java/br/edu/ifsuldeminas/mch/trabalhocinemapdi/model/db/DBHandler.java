package br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DROP_SQL = "DROP TABLE IF EXISTS movies;";
    private static final String TABLE_TASKS_CREATE_SQL =
            "CREATE TABLE movies ( " +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name varchar(30), " +
                    " genre varchar(255), " +
                    " director varchar (255));";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_DROP_SQL);
        sqLiteDatabase.execSQL(TABLE_TASKS_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // não vamos atualizar
    }
}