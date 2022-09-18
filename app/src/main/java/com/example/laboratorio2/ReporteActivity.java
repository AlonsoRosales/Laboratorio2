package com.example.laboratorio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.laboratorio2.Entity.Monitor;
import com.example.laboratorio2.Entity.Teclado;

import java.util.ArrayList;
import java.util.List;

public class ReporteActivity extends AppCompatActivity {
    Lista listaDevolver = new Lista();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        getSupportActionBar().setTitle("Reporte");
        //Cuando se crea obten la lista existente
        Intent intent = getIntent();
        Lista lista = (Lista) intent.getSerializableExtra("lista");
        listaDevolver = lista;
        TextView textView = findViewById(R.id.reporteTextView);
        if(lista.getListaEquipos().size()==0){
            //No hay equipos
            textView.setText("No se han encontrado equipos añadidos :(");
        }else{
            textView.setText(this.buscarComputadoras(lista)+this.buscarTeclado(lista)+this.buscarMonitores(lista));
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
            result += "- Del año 2022: "+String.valueOf(del2022)+"\n";
        }else{
            result += "No se tienen computadoras dentro del sistema\n";
        }
        return result;
    }
    public String buscarMonitores(Lista lista){
        String result = "Monitores : ";
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
        String result = "Teclados : ";
        List<Teclado> teclados = new ArrayList<>();
        for (Object object: lista.getListaEquipos()) {
            if(object.getClass()==Teclado.class){
                teclados.add((Teclado) object);
            }
        }
        //Obtenido los teclados
        if(teclados.size()==0){
            result += "No se han encontrado teclados\n";
        }else{
            result += String.valueOf(teclados.size())+"\n";
        }
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent1 = new Intent(ReporteActivity.this,MainActivity.class);
            intent1.putExtra("lista",listaDevolver);
            startActivity(intent1);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}