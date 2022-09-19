package com.example.laboratorio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laboratorio2.Entity.Teclado;

import java.util.ArrayList;
import java.util.List;

public class Teclado_actualizar_activity extends AppCompatActivity {
    public Lista maquinas = null;
    public int ubicacion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_actualizar);
        getSupportActionBar().setTitle("Actualizar");
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Teclado teclado = (Teclado) intent.getSerializableExtra("teclado");
        Lista lista = (Lista) intent.getSerializableExtra("listaTeclado");
        int posicion = intent.getIntExtra("posicion",0);
        maquinas = lista;
        ubicacion = posicion;
        List<String> valuesOfSprinnerMarca = new ArrayList<>();
        valuesOfSprinnerMarca.add(0,"Marca");
        valuesOfSprinnerMarca.add("Dell");
        valuesOfSprinnerMarca.add("Logitech");
        valuesOfSprinnerMarca.add("Toshiba");
        valuesOfSprinnerMarca.add("HP");
        List<String> valores = new ArrayList<>();
        int position = 0;
        for(String marca : valuesOfSprinnerMarca){
            if(marca.equalsIgnoreCase(String.valueOf(teclado.getMarca()))){
                break;
            }
            position++;
        }
        valores.add(valuesOfSprinnerMarca.get(position));
        for(String m : valuesOfSprinnerMarca){
            if(!m.equalsIgnoreCase(String.valueOf(teclado.getMarca()))){
                valores.add(m);
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,valores);
        Spinner spinner = findViewById(R.id.spinnerMacarTecladoact);
        spinner.setAdapter(arrayAdapter);

        List<String> valuesOfSprinnerIdioma = new ArrayList<>();
        valuesOfSprinnerIdioma.add(0,"Idioma");
        valuesOfSprinnerIdioma.add("Ingles");
        valuesOfSprinnerIdioma.add("Español");

        List<String> valores2 = new ArrayList<>();
        int position2 = 0;
        for(String marca : valuesOfSprinnerIdioma){
            if(marca.equalsIgnoreCase(String.valueOf(teclado.getMarca()))){
                break;
            }
            position++;
        }

        valores2.add(valuesOfSprinnerIdioma.get(position2));

        for(String m : valuesOfSprinnerIdioma){
            if(!m.equalsIgnoreCase(String.valueOf(teclado.getMarca()))){
                valores2.add(m);
            }
        }
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,valores2);
        Spinner spinner2 = findViewById(R.id.spinneridiomaTecladoACT);
        spinner2.setAdapter(arrayAdapter2);

        if (lista.getListaEquipos().isEmpty()){
            Intent intent1 = new Intent(Teclado_actualizar_activity.this,MainActivity.class);
            intent1.putExtra("lista",lista);
            startActivity(intent1);
            this.finish();
        }else{
            //Debemos obtener la lista de computadoras para obtener el codigo de cada computadora para mandarlo al spinner
            List<Computadora> computadoraList = new ArrayList<>();
            for (Object obj: lista.getListaEquipos()) {
                if (obj.getClass() == Computadora.class){
                    computadoraList.add((Computadora) obj);
                }
            }
            List<String> valuesOfSprinnerComputadora = new ArrayList<>();
            valuesOfSprinnerComputadora.add(0,"PC");
            for (Computadora computadora:computadoraList) {
                valuesOfSprinnerComputadora.add(computadora.activo);
            }
            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,valuesOfSprinnerComputadora);
            Spinner spinnerComputadora = findViewById(R.id.spinnerPCtecladoact);
            spinnerComputadora.setAdapter(arrayAdapter3);
        }

        TextView campoActivo = findViewById(R.id.actActivoTeclado);
        campoActivo.setText(teclado.getActivo());

        TextView campoAnho = findViewById(R.id.anhoactteclado);
        campoAnho.setText(String.valueOf(teclado.getAnho()));

        TextView campoCPU = findViewById(R.id.modeloactualizarteclado);
        campoCPU.setText(teclado.getModelo());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbar_computadora_actualizar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.checksito){
            TextView textView1 = findViewById(R.id.actActivoTeclado);
            String activo = textView1.getText().toString();

            TextView textView2 = findViewById(R.id.anhoactteclado);
            String anho = textView2.getText().toString();

            TextView textView3 = findViewById(R.id.modeloactualizarteclado);
            String modelo = textView3.getText().toString();

            Spinner spinner = findViewById(R.id.spinnerPCtecladoact);
            String PC =spinner.getSelectedItem().toString();

            Spinner spinner1 = findViewById(R.id.spinneridiomaTecladoACT);
            String idioma = spinner1.getSelectedItem().toString();

            Spinner spinner2 = findViewById(R.id.spinnerMacarTecladoact);
            String marca = spinner2.getSelectedItem().toString();

            Teclado teclado = new Teclado();
            teclado.setMarca(marca);
            teclado.setIdioma(idioma);
            teclado.setActivo(activo);
            teclado.setPC(PC);
            teclado.setModelo(modelo);
            teclado.setAnho(anho);




            int coincidencias = 1;
            int lugarTeclado = 0;
            for(Object objetito : maquinas.getListaEquipos()){
                if(objetito.getClass() == Teclado.class){
                    if(coincidencias == (ubicacion+1)){
                        break;
                    }

                    coincidencias++;
                }
                lugarTeclado++;
            }

            List<Object> listaObjetos = maquinas.getListaEquipos();
            listaObjetos.set(lugarTeclado,teclado);
            maquinas.setListaEquipos(listaObjetos);

            Intent intent1 = new Intent(Teclado_actualizar_activity.this,Teclado_activity.class);
            intent1.putExtra("lista",maquinas);
            startActivity(intent1);

        }

        if(item.getItemId() == R.id.basurita){
            AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
            alerDialog.setMessage("Esta seguro que desea Borrar?");
            alerDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    int coincidencias = 1;
                    int lugarTeclado = 0;
                    for(Object objetito : maquinas.getListaEquipos()){
                        if(objetito.getClass() == Computadora.class){
                            if(coincidencias == (ubicacion+1)){
                                break;
                            }

                            coincidencias++;
                        }
                        lugarTeclado++;
                    }
                    List<Object> listaObjetos = maquinas.getListaEquipos();
                    listaObjetos.remove(lugarTeclado);
                    maquinas.setListaEquipos(listaObjetos);

                    Intent intent2 = new Intent(Teclado_actualizar_activity.this,Teclado_activity.class);
                    intent2.putExtra("lista",maquinas);
                    startActivity(intent2);

                }
            });
            alerDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            alerDialog.show();
        }


        if(item.getItemId() == android.R.id.home){
            Intent intent1 = new Intent(Teclado_actualizar_activity.this,Teclado_activity.class);
            intent1.putExtra("lista",maquinas);
            startActivity(intent1);
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}