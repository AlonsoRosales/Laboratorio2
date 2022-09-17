package com.example.laboratorio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ComputadoraAgregarActivity extends AppCompatActivity {

    private Computadora computadora = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora_agregar);

        List<String> valuesSpinner = new ArrayList<>();
        valuesSpinner.add(0,"Marca");
        valuesSpinner.add("Dell");
        valuesSpinner.add("Logitech");
        valuesSpinner.add("Toshiba");
        valuesSpinner.add("HP");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,valuesSpinner);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
        System.out.println("-----------------------------------------------------------1");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbar_computadora_agregar,menu);
        System.out.println("-----------------------------------------------------------2");
        return true;
    }


    /*public boolean checkMenuAgregar(MenuItem item){
        TextView textView1 = findViewById(R.id.campoActivo);
        String activo = textView1.getText().toString();

        TextView textView2 = findViewById(R.id.campoAnho);
        String anhoStr = textView2.getText().toString();
        int anho = Integer.parseInt(anhoStr);

        TextView textView3 = findViewById(R.id.campoCPU);
        String CPU = textView3.getText().toString();

        Spinner spinner = findViewById(R.id.spinner);
        String marca =spinner.getSelectedItem().toString();

        computadora = new Computadora(activo,marca,anho,CPU);

        if(computadora != null){
            System.out.println(computadora.activo);
            System.out.println(computadora.anho);
            System.out.println(computadora.marca);
            System.out.println(computadora.CPU);
            Intent intent = getIntent();
            Lista listita = (Lista) intent.getSerializableExtra("listaComputadoras");
            listita.getListaEquipos().add(computadora);

            Intent intent1 = new Intent(ComputadoraAgregarActivity.this,ComputadoraActivity.class);
            intent1.putExtra("lista",listita);
            startActivity(intent1);

        }
        Intent intent = getIntent();
        Lista listita = (Lista) intent.getSerializableExtra("listaComputadoras");
        listita.getListaEquipos().add(computadora);

        Intent intent1 = new Intent(ComputadoraAgregarActivity.this,ComputadoraActivity.class);
        intent1.putExtra("lista",listita);
        startActivity(intent1);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.check_agregar){
            TextView textView1 = findViewById(R.id.campoActivo);
            String activo = textView1.getText().toString();

            TextView textView2 = findViewById(R.id.campoAnho);
            String anhoStr = textView2.getText().toString();
            int anho = Integer.parseInt(anhoStr);

            TextView textView3 = findViewById(R.id.campoCPU);
            String CPU = textView3.getText().toString();

            Spinner spinner = findViewById(R.id.spinner);
            String marca =spinner.getSelectedItem().toString();

            Computadora computadora = new Computadora(activo,marca,anho,CPU);

            Intent intent = getIntent();
            Lista listita = (Lista) intent.getSerializableExtra("listaComputadoras");
            listita.getListaEquipos().add(computadora);

            Intent intent1 = new Intent(ComputadoraAgregarActivity.this,ComputadoraActivity.class);
            intent1.putExtra("lista",listita);
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }


}




