package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Se agrega una Lista de objetos
    Lista lista = new Lista();
    List<Object> equipos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //lista.setListaEquipos(equipos);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista.setListaEquipos(equipos);
        // Obtengo la variable de los servicios de registro y lo actualizo
        Intent intent = getIntent();
        if(intent.hasExtra("lista")){
            lista = (Lista) intent.getSerializableExtra("lista");
        }
    }

    //Redireccion al registro de Computadora
    public void registroComputadora(View view){
        Intent intent = new Intent(this,ComputadoraActivity.class);
        intent.putExtra("lista",lista);
        startActivity(intent);
    }
    //Redireccion al registro de Teclado
    public void registroTeclado(View view){
        Intent intent = new Intent(this,Teclado_activity.class);
        intent.putExtra("lista",lista);
        startActivity(intent);
    }
    //Redireccion al registro de Monitor
    public void registroMonitor(View view){
        Intent intent = new Intent(this,MonitorListar.class);
        intent.putExtra("lista",lista);
        startActivity(intent);
    }
    //Redireccion al modulo de Reporte
    public void reporte(View view){
        Intent intent = new Intent(this,ReporteActivity.class);
        intent.putExtra("lista",lista);
        startActivity(intent);
    }

}