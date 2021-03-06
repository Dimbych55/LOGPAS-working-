package com.example.logpas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    final String CREATE_TABLE = "Create table accounts(id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, password TEXT);";
    static DataBase instance;
    Context mContext;

    private DataBase(Context context, int dbVer){
        super(context, "app.db", null, dbVer);
        mContext = context;
    }

    private SQLiteDatabase GetDb(){
        return instance.getWritableDatabase();
    }

    public void set(String request, String[] protected_values){
        GetDb().execSQL(request, protected_values);
    }

    public Cursor get(String request, String[] protected_values){
        return GetDb().rawQuery(request, protected_values);
    }

    public static DataBase toInstance(Context context){
        if( instance == null){
            instance = new DataBase(context, 1);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tableName");
        onCreate(db);
    }}


