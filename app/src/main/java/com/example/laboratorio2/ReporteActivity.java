package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ReporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        //Cuando se crea obten la lista existente
        Intent intent = getIntent();
        Lista lista = (Lista) intent.getSerializableExtra("lista");
        if(lista.getListaEquipos().size()==0){

        }else{

        }
    }
}