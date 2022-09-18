package com.example.laboratorio2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.laboratorio2.Entity.Teclado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Teclado_activity extends AppCompatActivity {
    public Lista maquinas = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado);
        getSupportActionBar().setTitle("Teclado");
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Lista lista = (Lista) intent.getSerializableExtra("lista");
        maquinas = lista;

        ListView listView = (ListView) findViewById(R.id.listaTeclados);
        String listadoTeclado = "";


        if (lista.listaEquipos.isEmpty()){
            listadoTeclado += "No hay teclados ingresados";
            TextView textView = findViewById(R.id.teclado_textview_vacio);
            textView.setText(listadoTeclado);
            //Si esta vacio por defecto se pone No hay teclados ingresadas
        }else{
            List<Teclado> tecladoList = new ArrayList<>();
            for (Object obj:lista.listaEquipos) {
                if (obj.getClass() == Teclado.class){
                    tecladoList.add((Teclado) obj);
                }
            }
            ArrayList<String> listaTextos = new ArrayList<>();
            String texto = "";
            for (Teclado teclado:tecladoList) {
                texto += "Activo: " + teclado.getActivo()+ "\n" +"PC: " + teclado.getPC() + "\n" + "marca: " + teclado.getMarca() + "\n" + "Año: " + teclado.getAnho() + "\n" + "idioma: " + teclado.getIdioma() + "\n" + "Modelo: " + teclado.getModelo() + "\n";
                listaTextos.add(texto);
                texto = "";
            }

            if (tecladoList.isEmpty()){
                listadoTeclado += "No hay teclados ingresados";
                TextView textView = findViewById(R.id.teclado_textview_vacio);
                textView.setText(listadoTeclado);
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