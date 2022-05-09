package com.example.proyectofinal_luischinchilla;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;

import com.example.proyectofinal_luischinchilla.entities.UserHistory;
import com.example.proyectofinal_luischinchilla.utilities.Utilities;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    private EditText etHUid,etNombre, etPosicion, etFuncion, etObjetivo;
    private TextView tvEstActual;
    private Spinner sListaEstados;
    private Spinner sListaSeleccionHU;
    ArrayList<String> listaSelecHU;
    ArrayList<UserHistory> selecHUListCLS;

    //Se instancia la conexión a SQLite
    AdminSQLiteOpenHelper conn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        conn = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        //Se traen los datos que se colocan en el Layout
        etHUid = (EditText) findViewById(R.id.txtHUDetail);
        etNombre = (EditText) findViewById(R.id.txtNombreDetail);
        etPosicion = (EditText) findViewById(R.id.txtPuestoDetail);
        etFuncion = (EditText) findViewById(R.id.txtDetailHU);
        etObjetivo = (EditText) findViewById(R.id.txtObjetivoDetail);
        tvEstActual = (TextView) findViewById(R.id.tvEstadoActualDetail);


        //Se declara la instancia para los objetos Spinner
        sListaEstados = (Spinner)findViewById(R.id.sEstados);
        sListaSeleccionHU = (Spinner)findViewById(R.id.sSeleccionHU);

        //Se crea el objeto array adapter con el método integrado createFromResource
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sListaEstados.setAdapter(adapter);

        consultarListaSelecHU();



        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaSelecHU);
        sListaSeleccionHU.setAdapter(adaptador);

            sListaSeleccionHU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position!=0){
                        etHUid.setText(selecHUListCLS.get(position-1).getId_HU().toString());
                        etNombre.setText(selecHUListCLS.get(position-1).getNombre());
                        etPosicion.setText(selecHUListCLS.get(position-1).getPosicion());
                        etFuncion.setText(selecHUListCLS.get(position-1).getFuncion());
                        etObjetivo.setText(selecHUListCLS.get(position-1).getObjetivo());
                        tvEstActual.setText(selecHUListCLS.get(position-1).getEstado());
                    }else{
                        etHUid.setText("");
                        etNombre.setText("");
                        etPosicion.setText("");
                        etFuncion.setText("");
                        etObjetivo.setText("");
                        tvEstActual.setText("");
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

    }


    public void Consultar( View v){

        //Se abre la conexión
        SQLiteDatabase bd = conn.getReadableDatabase();


        //Se obtiene el texto ingresado por el usuario

        String[] parametros = {etHUid.getText().toString()};


        String[] campos = {Utilities.CAMPO_NOMBRE, Utilities.CAMPO_POSICION, Utilities.CAMPO_FUNCION, Utilities.CAMPO_OBJETIVO, Utilities.CAMPO_ESTADO};

        try {
            //Se utiliza el elemento Cursor para ubicar la fila en la bd mediante el método rawQuery
            //que recibe una sentencia SQL concatenada con el valor obtenido anteriormente
            Cursor fila = bd.query(Utilities.TABLA_USER_HISTORY, campos, Utilities.CAMPO_ID_HU+"=?", parametros, null, null, null);

            //Si la fila obtenida es verdadera o tiene un valor al menos en los campos etPosicion, etFuncion, etObjetivo, tvEstActual se muestra
            //lo que venga en las posiciones 0, 1 y 2 respectivamente con el método getString del cursor fila
            fila.moveToFirst();
            etNombre.setText(fila.getString(0));
            etPosicion.setText(fila.getString(1));
            etFuncion.setText(fila.getString(2));
            etObjetivo.setText(fila.getString(3));
            tvEstActual.setText(fila.getString(4));
            fila.close();

        }catch (Exception e){
            //Si no, la HU no existe
            Toast.makeText(this,"No existe ninguna HU con ese número", Toast.LENGTH_SHORT).show();
            limpiar();
            consultarListaSelecHU();
        }

    }







    public void Actualizar(View v){
        try{
            //Se abre la conexión
            SQLiteDatabase bd = conn.getWritableDatabase();
            //Se obtiene el texto ingresado por el usuario


            String[] parametros = {etHUid.getText().toString()};

            //Se obtiene el texto ingresado por el usuario
            String Nombre = etNombre.getText().toString();
            String Posicion = etPosicion.getText().toString();
            String Funcion = etFuncion.getText().toString();
            String Objetivo = etObjetivo.getText().toString();
            String Estado = sListaEstados.getSelectedItem().toString();

            //Se declara un nuevo tipo de dato para contener los valores llamado ContentValues
            ContentValues registro = new ContentValues();

            //se agrega a ese objeto lo que se trajo del activiy
            registro.put(Utilities.CAMPO_NOMBRE, Nombre);
            registro.put(Utilities.CAMPO_POSICION, Posicion);
            registro.put(Utilities.CAMPO_FUNCION, Funcion);
            registro.put(Utilities.CAMPO_OBJETIVO, Objetivo);
            registro.put(Utilities.CAMPO_ESTADO, Estado);

            //Se declara un entero que invoca la bd el método update (el cuál por parámetros recibe el
            //nombre de la tabla, los datos a actualizar en un ContentValue, la condición de la cédula
            //a Actualizar y los posibles argumentos)
            int cant = bd.update(Utilities.TABLA_USER_HISTORY, registro, Utilities.CAMPO_ID_HU+"=?", parametros);


            //Si la variable cantidad es mayor o igual a 1, el usuario se actualizó correctamente
            if(cant ==1){
                Toast.makeText(this, "Datos actualizados con éxito",Toast.LENGTH_SHORT).show();
            }else {
                //Si no, la HU no existe
                Toast.makeText(this, "No existe ninguna HU con ese número", Toast.LENGTH_SHORT).show();
            }
            //Se cierra la conexión
            bd.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error: "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        };


        //Limpiamos todos los edittext para dejarlos listos para el siguiente registro
        limpiar();
        consultarListaSelecHU();



        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaSelecHU);
        sListaSeleccionHU.setAdapter(adaptador);

    }



    public void Eliminar(View v){
        try{
            //Se abre la conexión
            SQLiteDatabase bd = conn.getWritableDatabase();



            //Se obtiene el texto ingresado por el usuario
            String[] parametros = {etHUid.getText().toString()};


            //Se declara un entero que invoca de la base de datos el método delete(el cual por parámetro
            //recibe el nombre de la tabla, la validación y los posibles argumentos)
            int cant = bd.delete(Utilities.TABLA_USER_HISTORY, Utilities.CAMPO_ID_HU+"=?", parametros);

            //Si la variable es igual o mayor a 1, el usuario se eliminó correctamente
            if (cant == 1){
                Toast.makeText(this, "HU Eliminada", Toast.LENGTH_SHORT).show();
            }else{
                //Si el usuario no existe
                Toast.makeText(this, "No existe ninguna HU con ese número", Toast.LENGTH_SHORT).show();
            }

            //Se cierra la conexión
            bd.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error: "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        };



        //Se limpian todos los edittext para dejarlos listos para el siguiente registro
        limpiar();
        consultarListaSelecHU();



        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaSelecHU);
        sListaSeleccionHU.setAdapter(adaptador);


    }


    private void limpiar(){
        //Se limpian todos los edittext para dejarlos listos para el siguiente registro
        etHUid.setText(""); etNombre.setText(""); etPosicion.setText(""); etFuncion.setText(""); etObjetivo.setText("");
        tvEstActual.setText(""); sListaSeleccionHU.setSelection(0);
    }

    private void consultarListaSelecHU(){
        SQLiteDatabase bd = conn.getReadableDatabase();

        UserHistory historia = null;
        selecHUListCLS = new ArrayList<UserHistory>();
        Cursor cursor = bd.rawQuery("SELECT * FROM "+Utilities.TABLA_USER_HISTORY, null);

        while (cursor.moveToNext()){
            historia = new UserHistory();
            historia.setId_HU(cursor.getInt(0));
            historia.setNombre(cursor.getString(1));
            historia.setPosicion(cursor.getString(2));
            historia.setFuncion(cursor.getString(3));
            historia.setObjetivo(cursor.getString(4));
            historia.setEstado(cursor.getString(5));


            selecHUListCLS.add(historia);
        }
        obtenerListaHUs();

    }

    private void obtenerListaHUs(){
        listaSelecHU = new ArrayList<String>();
        listaSelecHU.add("Seleccione HU:");

        for(int i=0; i<selecHUListCLS.size(); i++){
            listaSelecHU.add("HU-"+selecHUListCLS.get(i).getId_HU().toString()+" "+selecHUListCLS.get(i).getNombre());

        }
    }


}