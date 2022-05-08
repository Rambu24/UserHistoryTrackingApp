package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal_luischinchilla.utilities.Utilities;

public class MainActivity3 extends AppCompatActivity {
    private EditText etHUid, etNombre, etPosicion, etFuncion, etObjetivo;
    private CheckBox cbxComercial, cbxFinanzas, cbxProduccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Se traen los datos que se colocan en el Layout
        etHUid = (EditText) findViewById(R.id.txtHUCreate);
        etNombre = (EditText) findViewById(R.id.txtNombreCreate);
        etPosicion = (EditText) findViewById(R.id.txtComoCreate);
        etFuncion = (EditText) findViewById(R.id.txtDetailCreate);
        etObjetivo = (EditText) findViewById(R.id.txtParaCreate);

        cbxComercial = (CheckBox) findViewById(R.id.cbxComercial);
        cbxFinanzas = (CheckBox) findViewById(R.id.cbxFinanzas);
        cbxProduccion = (CheckBox) findViewById(R.id.cbxProduccion);

    }
    //Se agrega el usuario a la base de datos
    public void Agregar(View v){
        //Se crea instancia de la clase AdminSQLiteOpenHelper
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);


        //Se abre la conexión
        SQLiteDatabase bd = admin.getReadableDatabase();

        //Se obtiene el texto ingresado por el usuario
        String[] parametros = {etHUid.getText().toString()};
        String[] campos = {Utilities.CAMPO_ID_HU};


        //Se utiliza el elemento Cursor para ubicar la fila en la bd mediante el método rawQuery
        //que recibe una sentencia SQL concatenada con el valor obtenido anteriormente
        Cursor fila = bd.query(Utilities.TABLA_USER_HISTORY, campos, Utilities.CAMPO_ID_HU+"=?", parametros, null, null, null);

        //Si la fila obtenida es verdadera o tiene un valor al menos en los campos etPosicion, etFuncion, etObjetivo, tvEstActual se muestra
        //lo que venga en las posiciones 0, 1 y 2 respectivamente con el método getString del cursor fila
        if(fila.moveToFirst()){
            Toast.makeText(this, "Ya existe una HU con ese número", Toast.LENGTH_SHORT).show();
            //Cerramos la bd
            fila.close();
        }else{

            String Id_HU = etHUid.getText().toString();
            String Nombre = etNombre.getText().toString();
            String Posicion = etPosicion.getText().toString();
            String Funcion = etFuncion.getText().toString();
            String Objetivo = etObjetivo.getText().toString();
            String Estado = "Pendiente";


            validarEditText(admin, Id_HU, Nombre, Posicion, Funcion, Objetivo, Estado, v);

        }

    }

    private void validarEditText(AdminSQLiteOpenHelper admin,String Id_HU, String Nombre, String Posicion, String Funcion, String Objetivo,String Estado, View v){
        if(Id_HU.length() == 0){
            Toast notificacion = Toast.makeText(this, "Ingrese el número de HU", Toast.LENGTH_LONG);
            notificacion.show();
            etHUid.setError("Campo vacío");
            etHUid.requestFocus();
        }else if(Nombre.length() == 0){
            Toast notificacion = Toast.makeText(this, "Ingrese un nombre para la HU", Toast.LENGTH_LONG);
            notificacion.show();
            etNombre.setError("Campo vacío");
            etNombre.requestFocus();
        }else if(Posicion.length() == 0){
            Toast notificacion = Toast.makeText(this, "Ingrese su posición", Toast.LENGTH_LONG);
            notificacion.show();
            etPosicion.setError("Campo vacío");
            etPosicion.requestFocus();
        }else if(Funcion.length() == 0){
            Toast notificacion = Toast.makeText(this, "Ingrese una Funcionalidad", Toast.LENGTH_LONG);
            notificacion.show();
            etFuncion.setError("Campo vacío");
            etFuncion.requestFocus();
        }else if(Objetivo.length() == 0){
            Toast notificacion = Toast.makeText(this, "Ingrese el objetivo de la HU", Toast.LENGTH_LONG);
            notificacion.show();
            etObjetivo.setError("Campo vacío");
            etObjetivo.requestFocus();
        }else{
            //Se crea la base de datos bd y se establece para escritura con método getWritableDatabase()
            SQLiteDatabase db = admin.getWritableDatabase();

            //Se declara un tipo de dato COntentValues para contener los valores
            ContentValues registro = new ContentValues();
            //A este se le pasa lo que se obtuvo del activity

            registro.put(Utilities.CAMPO_ID_HU, Id_HU);
            registro.put(Utilities.CAMPO_NOMBRE, Nombre);
            registro.put(Utilities.CAMPO_POSICION, Posicion);
            registro.put(Utilities.CAMPO_FUNCION,Funcion);
            registro.put(Utilities.CAMPO_OBJETIVO,Objetivo);
            registro.put(Utilities.CAMPO_ESTADO, Estado);

            //Hacemos un insert a la bd
            Long idResultante = db.insert(Utilities.TABLA_USER_HISTORY, Utilities.CAMPO_ID_HU, registro);



            //Se limpian los editText para dejarlos listos para el siguiente registro
            etHUid.setText(""); etNombre.setText(""); etPosicion.setText(""); etFuncion.setText(""); etObjetivo.setText("");

            //Se muestra el msj de éxito
            Toast.makeText(this, "HU guardada exitosamente. Registro: "+idResultante, Toast.LENGTH_SHORT).show();
            //Cerramos la bd
            db.close();
        }
    }


}