package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //Botón para ir al Tercer Activity
        Button btnAgregar = (Button)findViewById(R.id.btnAgregarHU);
        //Se usa el llamado para escuchar evento on click
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se crea un nuevo intent para el MainActivity.class
                Intent intent2 = new Intent(v.getContext(), MainActivity3.class);

                //Se pasan los parámetros de los Edit Text
                //intent2.putExtra("paramEstudiante", usuarioImportado);
                //intent2.putExtra("paramIdEstudiante", idImportada);

                //Se inicia y pasa el intent al Bundle del OS
                startActivityForResult(intent2, 0);
            }
        });


        //Botón para ir al Tercer Activity
        Button btnBuscar = (Button)findViewById(R.id.btnBuscarHU);
        //Se usa el llamado para escuchar evento on click
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se crea un nuevo intent para el MainActivity.class
                Intent intent3 = new Intent(v.getContext(), MainActivity4.class);

                //Se pasan los parámetros de los Edit Text
                //intent2.putExtra("paramEstudiante", usuarioImportado);
                //intent2.putExtra("paramIdEstudiante", idImportada);

                //Se inicia y pasa el intent al Bundle del OS
                startActivityForResult(intent3, 0);
            }
        });

    }
}