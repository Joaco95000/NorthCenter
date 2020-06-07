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

    public long actualizarPelicula(Pelicula pelicula) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre",pelicula.getNombre());
        valoresParaInsertar.put("genero",pelicula.getGenero());
        valoresParaInsertar.put("compania",pelicula.getCompania());
        valoresParaInsertar.put("duracion",pelicula.getDuracion());
        valoresParaInsertar.put("puntuacion",pelicula.getPuntuacion());
        valoresParaInsertar.put("foto",pelicula.getFoto());
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaInsertar, "id="+pelicula.getId(), null);
    }

    public long eliminarPelicula(int id){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        return baseDeDatos.delete(NOMBRE_TABLA, "id="+id, null);
    }

    public ArrayList listaDePeliculas() {
        try {
            SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
            ArrayList<Pelicula> array_list = new ArrayList<Pelicula>();
            Cursor res = baseDeDatos.rawQuery( "select * from " + NOMBRE_TABLA, null );
            res.moveToFirst();
            while(res.isAfterLast() == false) {
                int id = Integer.parseInt(res.getString(res.getColumnIndex("id")));
                String nombre = res.getString(res.getColumnIndex("nombre"));
                String compania = res.getString(res.getColumnIndex("compania"));
                Pelicula p = new Pelicula(id, nombre, compania);
                array_list.add(p);
                res.moveToNext();
            }
            return array_list;
        }
        catch (Exception ex){
            throw ex;
        }

    }

    public ArrayList listaDePeliculas(String buscar) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ArrayList<Pelicula> array_list = new ArrayList<Pelicula>();
        Cursor res = baseDeDatos.rawQuery( "SELECT * FROM " + NOMBRE_TABLA + " WHERE nombre LIKE '%" + buscar + "%';", null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            int id = Integer.parseInt(res.getString(res.getColumnIndex("id")));
            String nombre = res.getString(res.getColumnIndex("nombre"));
            String compania = res.getString(res.getColumnIndex("compania"));
            Pelicula p = new Pelicula(id, nombre, compania);
            array_list.add(p);
            res.moveToNext();
        }
        return array_list;
    }

    public Pelicula peliculaEspecifica(int MovieID) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        Cursor res = baseDeDatos.rawQuery( "SELECT * FROM " + NOMBRE_TABLA + " WHERE id = " + MovieID + ";", null );
        Pelicula p = new Pelicula();
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            String nombre = res.getString(res.getColumnIndex("nombre"));
            String genero = res.getString(res.getColumnIndex("genero"));
            String compania = res.getString(res.getColumnIndex("compania"));
            int duracion = Integer.parseInt(res.getString(res.getColumnIndex("duracion")));
            int puntuacion = Integer.parseInt(res.getString(res.getColumnIndex("puntuacion")));
            String foto = res.getString(res.getColumnIndex("foto"));
            p = new Pelicula(nombre, genero, compania, duracion, puntuacion, foto);
            res.moveToNext();
        }
        return p;
    }
}
