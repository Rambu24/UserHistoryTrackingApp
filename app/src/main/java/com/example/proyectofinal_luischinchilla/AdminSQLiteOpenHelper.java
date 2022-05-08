package com.example.proyectofinal_luischinchilla;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.proyectofinal_luischinchilla.utilities.Utilities;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Utilities.CREAR_TABLA_USER_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2){
        db.execSQL("drop table if exists UserHistory");
        onCreate(db);
    }

}
