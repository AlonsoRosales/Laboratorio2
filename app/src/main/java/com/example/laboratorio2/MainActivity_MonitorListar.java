package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity_MonitorListar extends AppCompatActivity {

    ArrayList<Monitor> listaMonitores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_monitor_listar);
        //Colocar para cambiar texto de barra superior

        TextView monitores = findViewById(R.id.lista_monitores);
        if (listaMonitores.isEmpty()) {
            monitores.setText("No hay monitores ingresados");
        } else {
            //Iterar por la lista e ingresar los valores
        }
    }
}