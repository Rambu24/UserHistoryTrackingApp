package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectofinal_luischinchilla.entities.UserHistory;
import com.example.proyectofinal_luischinchilla.utilities.Utilities;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    ListView listViewHistories;
    ArrayList<String> listaInformacion;
    ArrayList<UserHistory> listaHistorias;

    AdminSQLiteOpenHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        conn = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        listViewHistories = (ListView) findViewById(R.id.listViewHistories);

        consultarListaPersonas();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        listViewHistories.setAdapter(adapter);

        listViewHistories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "HU- "+listaHistorias.get(position).getId_HU()+"\n";
                informacion += "Nombre: "+listaHistorias.get(position).getNombre()+"\n";
                informacion += "Estado: "+listaHistorias.get(position).getEstado()+"\n";

                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_LONG).show();
                UserHistory history = listaHistorias.get(position);

                Intent intent5 = new Intent(MainActivity5.this, MainActivity6.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("historia", history);

                intent5.putExtras(bundle);
                startActivity(intent5);

            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        UserHistory history = null;
        listaHistorias = new ArrayList<UserHistory>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilities.TABLA_USER_HISTORY, null);

        while (cursor.moveToNext()){
            history = new UserHistory();
            history.setId_HU(cursor.getInt(0));
            history.setNombre(cursor.getString(1));
            history.setPosicion(cursor.getString(2));
            history.setFuncion(cursor.getString(3));
            history.setObjetivo(cursor.getString(4));
            history.setEstado(cursor.getString(5));

            listaHistorias.add(history);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for(int i = 0; i<listaHistorias.size();i++){
            listaInformacion.add("HU-"+listaHistorias.get(i).getId_HU()+" - "+listaHistorias.get(i).getNombre()+" - "+listaHistorias.get(i).getEstado());
        }


    }
}