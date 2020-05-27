package com.example.topcinema.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.topcinema.AyudanteBaseDeDatos;
import com.example.topcinema.modelos.Usuario;

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
}