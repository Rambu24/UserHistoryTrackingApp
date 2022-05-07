package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
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
public class MainActivity4 extends AppCompatActivity {

    private EditText etHUid, etPosicion, etFuncion, etObjetivo;
    private TextView tvEstActual;
    private Spinner sListaEstados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //Se traen los datos que se colocan en el Layout
        etHUid = (EditText) findViewById(R.id.txtNumeroHU);
        etPosicion = (EditText) findViewById(R.id.txtComo);
        etFuncion = (EditText) findViewById(R.id.txtDetailUpdate);
        etObjetivo = (EditText) findViewById(R.id.txtParaUpdate);
        tvEstActual = (TextView) findViewById(R.id.tvEstadoActual);

        //Se declara la instancia para el objeto Spinner de Estados
        //Spinner listaProvincias = findViewById(R.id.sProvincias);
        sListaEstados = (Spinner)findViewById(R.id.sEstados);
        //Se crea el objeto array adapter con el método integrado createFromResource
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sListaEstados.setAdapter(adapter);

    }

    public void Consultar(View v){
        //Se crea la instancia de la clase AdminSQLiteOpenHelper
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Se abre la conexión

        SQLiteDatabase bd = admin.getWritableDatabase();
        //Se obtiene el texto ingresado por el usuario
        String HUid = etHUid.getText().toString();

        //Se utiliza el elemento Cursor para ubicar la fila en la bd mediante el método rawQuery
        //que recibe una sentencia SQL concatenada con el valor obtenido anteriormente
        Cursor fila = bd.rawQuery("select, Posicion, Funcion, Objetivo, Estado from UserHistory where Id_HU ="+ HUid, null);

        //Si la fila obtenida es verdadera o tiene un valor al menos en los campos etPosicion, etFuncion, etObjetivo, tvEstActual se muestra
        //lo que venga en las posiciones 0, 1 y 2 respectivamente con el método getString del cursor fila
        if(fila.moveToFirst()){
            etPosicion.setText(fila.getString(0));
            etFuncion.setText(fila.getString(1));
            etObjetivo.setText(fila.getString(2));
            tvEstActual.setText(fila.getString(3));
        }else{  //Si no, la HU no existe
            Toast.makeText(this,"No existe ninguna HU con ese número", Toast.LENGTH_SHORT).show();
        }//Se cierra la conexión
        bd.close();
    }

    public void Eliminar(View v){
        //Se crea la instancia de la clase AdminSQLiteOpenHelper
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Se abre la conexión
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Se obtiene el texto ingresado por el usuario
        String HUid = etHUid.getText().toString();

        //Se declara un entero que invoca de la base de datos el método delete(el cual por parámetro
        //recibe el nombre de la tabla, la validación y los posibles argumentos)
        int cant = bd.delete("UserHistory", "Id_HU="+ HUid, null);
        //Se cierra la conexión
        bd.close();


        //Si la variable es igual o mayor a 1, el usuario se eliminó correctamente
        if (cant == 1){
            Toast.makeText(this, "HU Eliminada", Toast.LENGTH_SHORT).show();
        }else{
            //Si el usuario no existe
            Toast.makeText(this, "No existe ninguna HU con ese número", Toast.LENGTH_SHORT).show();
        }

        //Se limpian todos los edittext para dejarlos listos para el siguiente registro
        etHUid.setText(""); etPosicion.setText(""); etFuncion.setText(""); etObjetivo.setText("");
        tvEstActual.setText("");

    }

    public void Actualizar(View v){
        //Se crea la instancia de la clase AdminSQLiteOpenHelper
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        //Se abre la conexión
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Se obtiene el texto ingresado por el usuario
        String HUid = etHUid.getText().toString();
        String Posicion = etPosicion.getText().toString();
        String Funcion = etFuncion.getText().toString();
        String Objetivo = etObjetivo.getText().toString();
        String Estado = sListaEstados.getSelectedItem().toString();

        //Se declara un nuevo tipo de dato para contener los valores llamado ContentValues
        ContentValues registro = new ContentValues();

        //se agrega a ese objeto lo que se trajo del activiy
        registro.put("Posicion", Posicion);
        registro.put("Funcion", Funcion);
        registro.put("Objetivo", Objetivo);
        registro.put("Estado", Estado);

        //Se declara un entero que invoca la bd el método update (el cuál por parámetros recibe el
        //nombre de la tabla, los datos a actualizar en un ContentValue, la condición de la cédula
        //a Actualizar y los posibles argumentos)
        int cant = bd.update("UserHistory", registro, "Id_HU="+ HUid, null);
        //Se cierra la conexión
        bd.close();

        //Si la variable cantidad es mayor o igual a 1, el usuario se actualizó correctamente
        if(cant ==1){
            Toast.makeText(this, "Datos actualizados con éxito",Toast.LENGTH_SHORT).show();
        }else {
            //Si no, la HU no existe
            Toast.makeText(this, "No existe ninguna HU con ese número", Toast.LENGTH_SHORT).show();
        }
        //Limpiamos todos los edittext para dejarlos listos para el siguiente registro

        //Se limpian todos los edittext para dejarlos listos para el siguiente registro
        etHUid.setText(""); etPosicion.setText(""); etFuncion.setText(""); etObjetivo.setText("");
        tvEstActual.setText("");

    }
}