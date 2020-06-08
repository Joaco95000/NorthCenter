package com.example.topcinema.modelos;

import android.graphics.Bitmap;

public class Pelicula {

    private String nombre, genero, compania; //TODO: ver si necesitamos director, recaudacion, presupuesto, fecha de estreno, premios :v
    private int id, puntuacion, duracion; //TODO: esto es un double?
    private Bitmap foto;


    public Pelicula(){}

    public Pelicula(int id, String nombre, String compania, Bitmap foto)
    {
        this.id = id;
        this.nombre = nombre;
        this.compania = compania;
        this.foto = foto;
    }

    public Pelicula(String nombre, String genero, String compania, int duracion, int puntuacion,Bitmap foto)
    {
        this.nombre = nombre;
        this.genero = genero;
        this.compania = compania;
        this.duracion = duracion;
        this.puntuacion = puntuacion;
        this.foto = foto;
    }

    public Pelicula(int id, String nombre, String genero, String compania, int duracion, int puntuacion)
    {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.compania = compania;
        this.duracion = duracion;
        this.puntuacion = puntuacion;
    }

    public Pelicula(int id, String nombre, String genero, String compania, int duracion, int puntuacion,Bitmap foto)
    {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.compania = compania;
        this.duracion = duracion;
        this.puntuacion = puntuacion;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCompania() {
        return compania;
    }
    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }


    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}
