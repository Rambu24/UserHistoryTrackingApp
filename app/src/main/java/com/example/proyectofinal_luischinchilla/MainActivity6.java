package com.example.proyectofinal_luischinchilla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectofinal_luischinchilla.entities.UserHistory;

public class MainActivity6 extends AppCompatActivity {

    TextView campoId, campoNombre, campoPuesto, campoEstado;
    EditText campoFuncion, campoObjetivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        campoId = (TextView) findViewById(R.id.tvHUDetail1);
        campoNombre = (TextView) findViewById(R.id.tvNombreDetail1);
        campoPuesto = (TextView) findViewById(R.id.tvSolicitanteDetail1);
        campoEstado = (TextView) findViewById(R.id.tvEstadoActualDetail);
        campoFuncion = (EditText) findViewById(R.id.txtDetailHU);
        campoObjetivo = (EditText) findViewById(R.id.txtObjetivoDetail);

        Bundle objetoEnviado = getIntent().getExtras();
        UserHistory history = null;

        if(objetoEnviado!=null){
            history = (UserHistory) objetoEnviado.getSerializable("historia");

            campoId.setText("HU-"+history.getId_HU().toString());
            campoNombre.setText(history.getNombre());
            campoPuesto.setText(history.getPosicion());
            campoEstado.setText(history.getEstado());
            campoFuncion.setText(history.getFuncion());
            campoObjetivo.setText(history.getObjetivo());
        }
    }
}