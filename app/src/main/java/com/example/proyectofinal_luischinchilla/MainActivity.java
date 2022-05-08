package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText etUser;
    private EditText etPassword;
    String nombreUsuario = "Elsi";
    String claveIngreso = "123*";
    //String identificacionUsuario = "7-0241-0326";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se declara la instancia para el objeto del Usuario
        etUser = (EditText)findViewById(R.id.txtUser);
        //Se declara la instancia para el objeto de la contraseña
        etPassword = (EditText)findViewById(R.id.txtPassword);

        etUser.requestFocus();
    }

    public void ValidacionControles(View v){
        try{
            String usuario = etUser.getText().toString();
            String password = etPassword.getText().toString();


            //Se llama función que valida los campos y pasa al segundo activiy
            boolean estado = false;

            validarEditText(usuario, password, v);


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error: "+ e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        };
    }


    private void validarEditText(String usuario, String password, View v){
        if(usuario.length() == 0){
            Toast notificacion = Toast.makeText(this, "Ingrese un Usuario", Toast.LENGTH_LONG);
            notificacion.show();
            etUser.setError("Campo vacío");
            etUser.requestFocus();
        }else if(password.length() == 0){
            Toast notificacion = Toast.makeText(this, "Ingrese una Contraseña", Toast.LENGTH_LONG);
            notificacion.show();
            etPassword.setError("Campo vacío");
            etPassword.requestFocus();
        }else if (usuario.equals(this.nombreUsuario) && password.equals(this.claveIngreso)){
            //Pasar al segundo Activity
            Toast notificacion = Toast.makeText(this, "Iniciando Sesión...", Toast.LENGTH_LONG);
            notificacion.show();

            //Intent = Intento con parámetro View y nombre de la clase del MainActivity2
            Intent intent = new Intent(v.getContext(), MainActivity2.class);

            //Se pasan los parámetros de los Edit Text
            //intent.putExtra("paramUsuario", etUsuario.getText().toString());
            //intent.putExtra("paramIdentificacion", identificacionUsuario.toString());

            //Se inicia y pasa el intent al Bundle del OS
            startActivity(intent);
        }else{
            Toast notificacion = Toast.makeText(this, "Contraseña o Usuario Incorrectos", Toast.LENGTH_LONG);
            notificacion.show();
            etUser.setError("Contraseña o Usuario Incorrectos");
            etPassword.setError("Contraseña o Usuario Incorrectos");
            etUser.requestFocus();
        }
    }

}