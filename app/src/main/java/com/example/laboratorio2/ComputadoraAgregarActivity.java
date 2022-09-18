package com.example.laboratorio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ComputadoraAgregarActivity extends AppCompatActivity {

    private Computadora computadora = null;
    public Lista computadoras = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora_agregar);
        getSupportActionBar().setTitle("Nuevo");

        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        List<String> valuesSpinner = new ArrayList<>();
        valuesSpinner.add(0,"Marca");
        valuesSpinner.add("Dell");
        valuesSpinner.add("Logitech");
        valuesSpinner.add("Toshiba");
        valuesSpinner.add("HP");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,valuesSpinner);
        Spinner spinner = findViewById(R.id.spinner_pc);
        spinner.setAdapter(arrayAdapter);


        Intent intent = getIntent();
        Lista listita = (Lista) intent.getSerializableExtra("listaComputadoras");
        computadoras = listita;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbar_computadora_agregar,menu);
        return true;
    }

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

            Spinner spinner = findViewById(R.id.spinner_pc);
            String marca =spinner.getSelectedItem().toString();

            Computadora computadora = new Computadora(activo,marca,anho,CPU);

            computadoras.getListaEquipos().add(computadora);

            Intent intent1 = new Intent(ComputadoraAgregarActivity.this,ComputadoraActivity.class);
            intent1.putExtra("lista",computadoras);
            startActivity(intent1);

        }

        if(item.getItemId() == android.R.id.home){
            Intent intent1 = new Intent(ComputadoraAgregarActivity.this,ComputadoraActivity.class);
            intent1.putExtra("lista",computadoras);
            startActivity(intent1);
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}




