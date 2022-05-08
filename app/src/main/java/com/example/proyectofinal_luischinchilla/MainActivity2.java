package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }


    public void CrearHU(View v){
        //Se crea un nuevo intent para el MainActivity.class
        Intent intent2 = new Intent(v.getContext(), MainActivity3.class);



        //Se inicia y pasa el intent al Bundle del OS
        startActivity(intent2);
    }

    public void Buscar(View v){
        //Se crea un nuevo intent para el MainActivity.class
        Intent intent3 = new Intent(v.getContext(), MainActivity4.class);

        //Se inicia y pasa el intent al Bundle del OS
        startActivity(intent3);
    }

    public void VerLista(View v){
        Intent intent4 = new Intent(v.getContext(), MainActivity5.class);
        startActivity(intent4);
    }


}