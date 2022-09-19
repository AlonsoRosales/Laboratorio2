package com.example.laboratorio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laboratorio2.Entity.Teclado;

import java.util.ArrayList;
import java.util.List;

public class Teclado_activity_agregar extends AppCompatActivity {
    private Teclado teclado = null;
    public Lista maquinas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_agregar);
        getSupportActionBar().setTitle("Nuevo");
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Lista listita = (Lista) intent.getSerializableExtra("lista");
        maquinas = listita;
        //Llenamos los sprinner con los datos requeridos
        List<String> valuesOfSprinnerMarca = new ArrayList<>();
        valuesOfSprinnerMarca.add(0,"Marca");
        valuesOfSprinnerMarca.add("Dell");
        valuesOfSprinnerMarca.add("Logitech");
        valuesOfSprinnerMarca.add("Toshiba");
        valuesOfSprinnerMarca.add("HP");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,valuesOfSprinnerMarca);
        Spinner spinnerMarca = findViewById(R.id.teclado_Marca_agregar);
        spinnerMarca.setAdapter(arrayAdapter);

        List<String> valuesOfSprinnerIdioma = new ArrayList<>();
        valuesOfSprinnerIdioma.add(0,"Idioma");
        valuesOfSprinnerIdioma.add("Ingles");
        valuesOfSprinnerIdioma.add("Español");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,valuesOfSprinnerIdioma);
        Spinner spinnerIdioma = findViewById(R.id.teclado_Idioma_agregar);
        spinnerIdioma.setAdapter(arrayAdapter1);
        if (maquinas.getListaEquipos().isEmpty()){
            Intent intent1 = new Intent(Teclado_activity_agregar.this,MainActivity.class);
            intent1.putExtra("lista",maquinas);
            startActivity(intent1);
            this.finish();
        }else{
            //Debemos obtener la lista de computadoras para obtener el codigo de cada computadora para mandarlo al spinner
            List<Computadora> computadoraList = new ArrayList<>();
            for (Object obj: maquinas.getListaEquipos()) {
                if (obj.getClass() == Computadora.class){
                    computadoraList.add((Computadora) obj);
                }
            }
            List<String> valuesOfSprinnerComputadora = new ArrayList<>();
            valuesOfSprinnerComputadora.add(0,"PC");
            for (Computadora computadora:computadoraList) {
                valuesOfSprinnerComputadora.add(computadora.activo);
            }
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,valuesOfSprinnerComputadora);
            Spinner spinnerComputadora = findViewById(R.id.teclado_PC_agregar);
            spinnerComputadora.setAdapter(arrayAdapter2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbar_computadora_agregar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Teclado teclado1 = new Teclado();
        if (item.getItemId() == R.id.check_agregar){
            TextView editTextActivo = findViewById(R.id.actionTeclado);
            String activo = editTextActivo.getText().toString();
            teclado1.setActivo(activo);
            TextView editTextAnho = findViewById(R.id.anhoTeclado);
            String anho = editTextAnho.getText().toString();
            teclado1.setAnho(anho);
            TextView editTextModelo = findViewById(R.id.mdeloTeclado);
            String modelo = editTextModelo.getText().toString();
            teclado1.setModelo(modelo);
            Spinner computadorSpinner = findViewById(R.id.teclado_PC_agregar);
            String PC = computadorSpinner.getSelectedItem().toString();
            teclado1.setPC(PC);
            Spinner idiomaSprinner = findViewById(R.id.teclado_Idioma_agregar);
            String idioma = idiomaSprinner.getSelectedItem().toString();
            teclado1.setIdioma(idioma);
            Spinner spinner = findViewById(R.id.teclado_Marca_agregar);
            String marca = spinner.getSelectedItem().toString();
            teclado1.setMarca(marca);
            maquinas.getListaEquipos().add(teclado1);
            Intent intent1 = new Intent(Teclado_activity_agregar.this,Teclado_activity.class);
            intent1.putExtra("lista",maquinas);
            startActivity(intent1);
            this.finish();
            return true;


        }
        if (item.getItemId() == android.R.id.home){
            Intent intent1 = new Intent(Teclado_activity_agregar.this,MainActivity.class);
            intent1.putExtra("lista",maquinas);
            startActivity(intent1);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}