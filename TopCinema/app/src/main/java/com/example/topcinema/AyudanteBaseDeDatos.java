package com.example.topcinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DE_DATOS = "topcinema",
            NOMBRE_TABLA_USUARIOS = "usuarios",
            NOMBRE_TABLA_PELICULA = "pelicula";

    private static final int VERSION_BASE_DE_DATOS =2;

    public AyudanteBaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("create table if not exists %s(id integer primary key autoincrement,correo text, nombre text,apellido1 text,apellido2 text, edad integer,usuario text,password text);",NOMBRE_TABLA_USUARIOS));
        sqLiteDatabase.execSQL(String.format("create table if not exists %s(id integer primary key autoincrement, nombre text, genero text, compania text, duracion integer, puntuacion integer,foto blob);",NOMBRE_TABLA_PELICULA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*public boolean insertImage(String x){
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            ContentValues contentValues=new ContentValues();
            contentValues.put("img",x);
            db.insert("imagenes",null,contentValues);
            db.close();
            return  true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/
}