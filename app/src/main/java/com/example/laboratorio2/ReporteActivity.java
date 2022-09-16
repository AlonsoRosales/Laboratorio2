package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        List<Computadora> computadoras = new ArrayList<>();
        for (Object object: lista.getListaEquipos()) {
            if(object.getClass()==Computadora.class){
                computadoras.add((Computadora) object);
            }
        }
        //Una vez añadida la computadora
        if(computadoras.size()!=0){
            result += "- Total: "+String.valueOf(computadoras.size())+"\n";
            int del2022 = 0;
            for (Computadora c :computadoras) {
                if(c.getAnho()==2022){
                    del2022 += 1;
                }
            }
            result += "- Del año 2022"+String.valueOf(del2022)+"\n";
        }else{
            result += "No se tienen computadoras dentro del sistema\n";
        }
        return result;
    }
    public String buscarMonitores(Lista lista){
        String result = "Teclado : ";
        List<Monitor> monitores = new ArrayList<>();
        for (Object object: lista.getListaEquipos()) {
            if(object.getClass()==Monitor.class){
                monitores.add((Monitor) object);
            }
        }
        //Obtenido los monitores
        if(monitores.size()==0){
            result += "No se han encontrado monitores\n";
        }else{
            result += String.valueOf(monitores.size())+"\n";
        }
        return result;
    }
    public String buscarTeclado(Lista lista){
        String result = "Monitor : ";
        List<Tec> monitores = new ArrayList<>();
        for (Object object: lista.getListaEquipos()) {
            if(object.getClass()==Monitor.class){
                monitores.add((Monitor) object);
            }
        }
        //Obtenido los monitores
        if(monitores.size()==0){
            result += "No se han encontrado monitores\n";
        }else{
            result += String.valueOf(monitores.size())+"\n";
        }
        return result;
    }
}