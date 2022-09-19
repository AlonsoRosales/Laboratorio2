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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.laboratorio2.Entity.Teclado;

import java.util.List;

public class Teclado_actualizar_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_actualizar);
        getSupportActionBar().setTitle("Actualizar");
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Teclado teclado = (Teclado) intent.getSerializableExtra("teclado");
        Lista lista = intent.getSerializableExtra("listaTeclado");
        int position = intent.getIntExtra("posicion",0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbar_computadora_actualizar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.checksito){
            TextView textView1 = findViewById(R.id.campoActivo2);
            String activo = textView1.getText().toString();

            TextView textView2 = findViewById(R.id.campoAnho2);
            String anhoStr = textView2.getText().toString();
            int anho = Integer.parseInt(anhoStr);

            TextView textView3 = findViewById(R.id.campoCPU2);
            String CPU = textView3.getText().toString();

            Spinner spinner = findViewById(R.id.spinner4);
            String marca =spinner.getSelectedItem().toString();

            Computadora compu = new Computadora(activo,marca,anho,CPU);


            int coincidencias = 1;
            int lugarComputadora = 0;
            for(Object objetito : computadoras.getListaEquipos()){
                if(objetito.getClass() == Computadora.class){
                    if(coincidencias == (ubicacion+1)){
                        break;
                    }

                    coincidencias++;
                }
                lugarComputadora++;
            }

            List<Object> listaObjetos = computadoras.getListaEquipos();
            listaObjetos.set(lugarComputadora,compu);
            computadoras.setListaEquipos(listaObjetos);

            Intent intent1 = new Intent(ComputadoraActualizarActivity.this,ComputadoraActivity.class);
            intent1.putExtra("lista",computadoras);
            startActivity(intent1);

        }

        if(item.getItemId() == R.id.basurita){
            AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
            alerDialog.setMessage("Esta seguro que desea Borrar?");
            alerDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    int coincidencias = 1;
                    int lugarComputadora = 0;
                    for(Object objetito : computadoras.getListaEquipos()){
                        if(objetito.getClass() == Computadora.class){
                            if(coincidencias == (ubicacion+1)){
                                break;
                            }

                            coincidencias++;
                        }
                        lugarComputadora++;
                    }
                    List<Object> listaObjetos = computadoras.getListaEquipos();
                    listaObjetos.remove(lugarComputadora);
                    computadoras.setListaEquipos(listaObjetos);

                    Intent intent2 = new Intent(ComputadoraActualizarActivity.this,ComputadoraActivity.class);
                    intent2.putExtra("lista",computadoras);
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
            Intent intent1 = new Intent(ComputadoraActualizarActivity.this,ComputadoraActivity.class);
            intent1.putExtra("lista",computadoras);
            startActivity(intent1);
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}