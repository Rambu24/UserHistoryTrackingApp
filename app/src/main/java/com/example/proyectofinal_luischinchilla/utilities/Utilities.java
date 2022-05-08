package com.example.proyectofinal_luischinchilla.utilities;

public class Utilities {
    //Constantes campos tabla UserHistory
    public static final String TABLA_USER_HISTORY = "UserHistory";
    public static final String CAMPO_ID_HU = "Id_HU";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_POSICION = "Posicion";
    public static final String CAMPO_FUNCION = "Funcion";
    public static final String CAMPO_OBJETIVO = "Objetivo";
    public static final String CAMPO_ESTADO = "Estado";


    public static final String CREAR_TABLA_USER_HISTORY = "create table "+
            ""+TABLA_USER_HISTORY+ " ("+CAMPO_ID_HU+" integer primary key, "+
            ""+ CAMPO_NOMBRE+" text, "+CAMPO_POSICION+" text, "+CAMPO_FUNCION+ " text, "+CAMPO_OBJETIVO+" text, "+CAMPO_ESTADO+" text)";


}
