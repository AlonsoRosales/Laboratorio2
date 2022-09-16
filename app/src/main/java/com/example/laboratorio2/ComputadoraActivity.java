package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ComputadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora);

        Intent intent2 = getIntent();
        Lista listita = (Lista) intent2.getSerializableExtra("lista");

        List<Computadora> computadoraList = new ArrayList<>();


        for(Object objetito : listita.getListaEquipos()){
            if(objetito.getClass() == Computadora.class){
                computadoraList.add((Computadora) objetito);
            }
        }



        /*String textoListadoPCS = "";
        if(computadoraList.size() == 0){
            textoListadoPCS = "No hay computadoras ingresadas";

        }else{

        }*/






        FloatingActionButton botonAgregar = findViewById(R.id.botonagregar);
        botonAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(ComputadoraActivity.this,ComputadoraAgregarActivity.class);
            intent.putExtra("listaComputadoras",listita);
            startActivity(intent);

        });


    }



}