package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity3 extends AppCompatActivity {
    private EditText etHUid, etPosicion, etFuncion, etObjetivo;
    private CheckBox cbxComercial, cbxFinanzas, cbxProduccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Se traen los datos que se colocan en el Layout
        etHUid = (EditText) findViewById(R.id.txtHUCreate);
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
        //Se crea la base de datos bd y se establece para escritura con método getWritableDatabase()
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Id_HU = etHUid.getText().toString();
        String Posicion = etPosicion.getText().toString();
        String Funcion = etFuncion.getText().toString();
        String Objetivo = etObjetivo.getText().toString();
        String Estado = "Pendiente";

        //Se declara un tipo de dato COntentValues para contener los valores
        ContentValues registro = new ContentValues();
        //A este se le pasa lo que se obtuvo del activity

        registro.put("Id_Hu", Id_HU);
        registro.put("Posicion", Posicion);
        registro.put("Funcion",Funcion);
        registro.put("Objetivo",Objetivo);
        registro.put("Estado", Estado);

        //Hacemos un insert a la bd
        bd.insert("UserHistory", null, registro);
        //Cerramos la bd
        bd.close();

        //Se limpian los editText para dejarlos listos para el siguiente registro
        etHUid.setText(""); etPosicion.setText(""); etFuncion.setText(""); etObjetivo.setText("");

        //Se muestra el msj de éxito
        Toast.makeText(this, "HU guardada exitosamente", Toast.LENGTH_SHORT).show();
    }
}