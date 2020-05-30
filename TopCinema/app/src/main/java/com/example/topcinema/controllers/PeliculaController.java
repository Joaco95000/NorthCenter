package com.example.topcinema.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.topcinema.AyudanteBaseDeDatos;
import com.example.topcinema.modelos.Pelicula;

public class PeliculaController {

    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "pelicula";

    public PeliculaController(Context context) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
    }

    public long nuevaPelicula(Pelicula pelicula){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre",pelicula.getNombre());
        valoresParaInsertar.put("genero",pelicula.getGenero());
        valoresParaInsertar.put("compania",pelicula.getCompania());
        valoresParaInsertar.put("duracion",pelicula.getDuracion());
        valoresParaInsertar.put("puntuacion",pelicula.getPuntuacion());
        return baseDeDatos.insert(NOMBRE_TABLA,null,valoresParaInsertar);
    }
}
