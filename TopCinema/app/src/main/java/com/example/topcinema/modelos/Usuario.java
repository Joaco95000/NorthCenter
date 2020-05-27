package com.example.topcinema.modelos;

public class Usuario {
    private String correo,nombre,apellido1,apellido2,usuario,password;
    private int edad,id;


    public Usuario() {
    }

    public Usuario(String correo, String nombre, String apellido1,String apellido2, String usuario, String password) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.usuario = usuario;
        this.password = password;
    }
    public Usuario(String correo, String nombre, String apellido1,String apellido2, String usuario, String password, int edad) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.usuario = usuario;
        this.password = password;
        this.edad = edad;
    }
    public Usuario(String correo, String nombre, String apellido1,String apellido2, String usuario, String password, int edad, int id) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.usuario = usuario;
        this.password = password;
        this.edad = edad;
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() {
        return apellido2;
    }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}