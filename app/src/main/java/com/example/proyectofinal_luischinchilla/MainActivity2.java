package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private RadioButton rbSuperv, rbDesarr, rbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Se guarda lo que el putExtra del intent anterior agregó al bundle
        //con el método getIntent y el submétodo getExtras
        Bundle bundle   = getIntent().getExtras();
        String usuarioImportado = bundle.getString("paramUsuario");
        TextView tvUsuario = (TextView)findViewById(R.id.tvUsuario);
        tvUsuario.setText("Bienvenido(a): "+usuarioImportado);

        rbSuperv = (RadioButton) findViewById(R.id.rbSupervisor);
        rbDesarr = (RadioButton) findViewById(R.id.rbDesarrollador);
        rbUser = (RadioButton) findViewById(R.id.rbUsuario);

    }


    public void CrearHU(View v){

        boolean supervisor = rbSuperv.isChecked();
        boolean desarrollador = rbDesarr.isChecked();
        boolean usuario = rbUser.isChecked();

        if(supervisor == false && desarrollador == false && usuario == false) {
            Toast notificacion = Toast.makeText(this, "Debe seleccionar el puesto", Toast.LENGTH_LONG);
            notificacion.show();
        }else{


            //Se crea un nuevo intent para el MainActivity.class
            Intent intent2 = new Intent(v.getContext(), MainActivity3.class);
            //Se pasan los parámetros de los RadioButton
            if(supervisor==true){
                intent2.putExtra("paramPuesto",rbSuperv.getText().toString());
            }
            if(desarrollador==true){
                intent2.putExtra("paramPuesto",rbDesarr.getText().toString());
            }
            if(usuario==true){
                intent2.putExtra("paramPuesto", rbUser.getText().toString());
            }


            //Se inicia y pasa el intent al Bundle del OS
            startActivity(intent2);
        }


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