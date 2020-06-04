package com.example.topcinema.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.topcinema.AyudanteBaseDeDatos;
import com.example.topcinema.modelos.Usuario;

import java.util.ArrayList;

public class UsuarioController {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "usuarios";

    public UsuarioController(Context context) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
    }
    //metodo para nuevo usuario:
    public long nuevoUsuario(Usuario usuario){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("correo",usuario.getCorreo());
        valoresParaInsertar.put("nombre",usuario.getNombre());
        valoresParaInsertar.put("apellido1",usuario.getApellido1());
        valoresParaInsertar.put("apellido2",usuario.getApellido2());
        valoresParaInsertar.put("edad",usuario.getEdad());
        valoresParaInsertar.put("usuario",usuario.getUsuario());
        valoresParaInsertar.put("password",usuario.getPassword());
        return baseDeDatos.insert(NOMBRE_TABLA,null,valoresParaInsertar);
    }

    public ArrayList listaDeUsuarios() {
        SQLiteDatabase baseD = ayudanteBaseDeDatos.getWritableDatabase();
        ArrayList<Usuario> array_list = new ArrayList<Usuario>();
        Cursor cursor = baseD.rawQuery( "select * from " + NOMBRE_TABLA, null );
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            String correo = cursor.getString(cursor.getColumnIndex("correo"));
            String usuario = cursor.getString(cursor.getColumnIndex("usuario"));
            Usuario u = new Usuario(nombre,correo,usuario);
            array_list.add(u);
            cursor.moveToNext();
        }
        return array_list;
    }

    public ArrayList listaDeUsuarios(String buscar) {
        SQLiteDatabase baseD = ayudanteBaseDeDatos.getWritableDatabase();
        ArrayList<Usuario> array_list = new ArrayList<Usuario>();
        Cursor cursor = baseD.rawQuery( "SELECT * FROM " + NOMBRE_TABLA + " WHERE usuario LIKE '%" + buscar + "%';", null );
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            String correo = cursor.getString(cursor.getColumnIndex("correo"));
            String usuario = cursor.getString(cursor.getColumnIndex("usuario"));
            Usuario u = new Usuario(nombre,correo,usuario);
            array_list.add(u);
            cursor.moveToNext();
        }
        return array_list;
    }
}