package com.example.proyectofinal_luischinchilla.entities;

public class UserHistory {

    private Integer Id_HU;
    private String Nombre;
    private String Posicion;
    private String Funcion;
    private String Objetivo;
    private String Estado;

    public UserHistory(Integer Id_HU, String Posicion, String Funcion, String Objetivo, String Estado){
        this.Id_HU = Id_HU;
        this.Nombre = Nombre;
        this.Posicion = Posicion;
        this.Funcion = Funcion;
        this.Objetivo = Objetivo;
        this.Estado = Estado;
    }

    public UserHistory(){

    }


    public Integer getId_HU() {
        return Id_HU;
    }

    public void setId_HU(Integer id_HU) {
        Id_HU = id_HU;
    }

    public String getPosicion() {
        return Posicion;
    }

    public void setPosicion(String posicion) {
        Posicion = posicion;
    }

    public String getFuncion() {
        return Funcion;
    }

    public void setFuncion(String funcion) {
        Funcion = funcion;
    }

    public String getObjetivo() {
        return Objetivo;
    }

    public void setObjetivo(String objetivo) {
        Objetivo = objetivo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) { Nombre = nombre; }
}
