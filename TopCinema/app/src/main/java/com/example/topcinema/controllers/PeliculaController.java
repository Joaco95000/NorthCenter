package com.example.topcinema.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.topcinema.AyudanteBaseDeDatos;
import com.example.topcinema.modelos.Pelicula;

import java.util.ArrayList;

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
        valoresParaInsertar.put("foto",pelicula.getFoto());
        return baseDeDatos.insert(NOMBRE_TABLA,null,valoresParaInsertar);
    }

    public ArrayList listaDePeliculas() {
        try {
            SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
            ArrayList<Pelicula> array_list = new ArrayList<Pelicula>();
            Cursor res = baseDeDatos.rawQuery( "select * from " + NOMBRE_TABLA, null );
            res.moveToFirst();
            while(res.isAfterLast() == false) {
                String nombre = res.getString(res.getColumnIndex("nombre"));
                String compania = res.getString(res.getColumnIndex("compania"));
                Pelicula p = new Pelicula(nombre, compania);
                array_list.add(p);
                res.moveToNext();
            }
            return array_list;
        }
        catch (Exception ex){
            throw ex;
        }

    }
}
