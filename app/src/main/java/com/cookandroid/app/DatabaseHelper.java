package com.cookandroid.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "food.db";
    public static int VERSION = 1;
    public static String TABLE_FOOD_INFO = "FOOD_INFO";
    public static String TAG = "MyDB_TEST";
    public DatabaseHelper(@Nullable Context context) {
        super(context,NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_SQL = "create table " + TABLE_FOOD_INFO + "("
                    + " DATE TEXT PRIMARY KEY, "
                    + " CONTENT TEXT "
                    + ")";
            db.execSQL(CREATE_SQL);
            Log.d(TAG, "onCreate CREATE_SQL success");
        }catch (Exception e){
            Log.d(TAG, "onCreate error!!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_SQL = "DROP TABLE IF EXISTS " + TABLE_FOOD_INFO;
        if(newVersion > oldVersion){
            db.execSQL(DROP_SQL);
            Log.d(TAG,"onUpgrade");
            onCreate(db);
        }

    }
}
