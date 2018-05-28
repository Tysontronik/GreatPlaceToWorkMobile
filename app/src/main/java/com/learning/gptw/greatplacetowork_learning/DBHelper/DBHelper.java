package com.learning.gptw.greatplacetowork_learning.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 28/03/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, nombre, factory, version);

    }



    @Override

    public void onCreate(SQLiteDatabase db) {

        //aquí creamos la tabla de usuario (dni, nombre, ciudad, numero)
        db.execSQL("create table usuario(dni integer primary key, nombre text, ciudad text, numero integer)");

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

        db.execSQL("drop table if exists usuario");

        db.execSQL("create table usuario(dni integer primary key, nombre text, ciudad text, numero integer)");

    }

}
