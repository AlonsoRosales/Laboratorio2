package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.laboratorio2.Entity.Teclado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Teclado_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado);
        ScrollView scrollView = (ScrollView) findViewById(R.id.lista_teclados);
        Intent intent = getIntent();
        Lista lista = (Lista) intent.getSerializableExtra("lista");
        TextView vacio = new TextView(Teclado_activity.this);
        vacio.setText("No hay teclados ingresados");
        if (lista.listaEquipos.isEmpty()){
            scrollView.addView(vacio);
            //Si esta vacio por defecto se pone No hay teclados ingresadas
        }else{
            List<Teclado> tecladoList = new ArrayList<>();
            for (Object obj:lista.listaEquipos) {
                if (obj.getClass() == Teclado.class){
                    tecladoList.add((Teclado) obj);
                }
            }
            if (tecladoList.isEmpty()){
                scrollView.addView(vacio);
                //Si esta vacio por defecto se pone No hay teclados ingresadas
            }else{
                //Se lista los teclados
            }
        }
        FloatingActionButton floatingActionButton = findViewById(R.id.teclados_floatingActionButton);
        //floatingActionButton.setOnClickListener(view -> );
        //scrollView.addView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teclado_menu,menu);
        return true;
    }
}