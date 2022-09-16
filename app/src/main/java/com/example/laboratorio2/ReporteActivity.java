package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        //Cuando se crea obten la lista existente
        Intent intent = getIntent();
        Lista lista = (Lista) intent.getSerializableExtra("lista");
        if(lista.getListaEquipos().size()==0){
            //No hay equipos
            TextView textView = findViewById(R.id.reporteTextView);
            textView.setText("No se han encontrado equipos añadidos :(");
        }else{

        }
    }
    public String buscarComputadoras(Lista lista){
        String result = "Computadoras : \n";

        return result;
    }
    public String buscarMonitores(Lista lista){
        String result = "Teclado : ";
        return result;
    }
    public String buscarTeclado(Lista lista){
        String result = "Monitor : ";
        return result;
    }
}