package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComputadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora);

        Intent intent2 = getIntent();
        Lista listita = (Lista) intent2.getSerializableExtra("lista");

        ArrayList<Computadora> computadoraList = new ArrayList<>();

        ListView simpleList = (ListView) findViewById(R.id.listaDeComputadoras);

        /*VERIFICAR  LA LINEA 47 48*/
        System.out.println("TAMAÑO --> "+listita.getListaEquipos().size());
        String textoListadoPCS = "";
        if(listita.getListaEquipos().size() == 0){
            textoListadoPCS = textoListadoPCS + "No hay computadoras ingresadas";
            TextView textView = findViewById(R.id.textoVacio);
            textView.setText(textoListadoPCS);
        }else{
            for(Object objetito : listita.getListaEquipos()){
                if(objetito.getClass() == Computadora.class){
                    computadoraList.add((Computadora) objetito);
                }
            }

            ArrayList<String> listaTextos = new ArrayList<>();
            String texto = "";
            for(Computadora pc : computadoraList){
                texto = texto + "Activo: "+pc.getActivo()+"\n"+"Marca: "+pc.getMarca()+"\n"+"Año: "+pc.getAnho()+"\n"+"CPU: "+pc.getCPU()+"\n";
                listaTextos.add(texto);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaTextos);
            simpleList.setAdapter(adapter);

            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(ComputadoraActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();

                }
            });

        }



        FloatingActionButton botonAgregar = findViewById(R.id.botonagregar);
        botonAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(ComputadoraActivity.this,ComputadoraAgregarActivity.class);
            intent.putExtra("listaComputadoras",listita);
            startActivity(intent);

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.puntitos_appbar_computadora_principal,menu);
        return true;
    }

    public void presionarPuntitosComputadora(MenuItem item){
        View menuItemView = findViewById(R.id.puntitos);
        PopupMenu popupMenu = new PopupMenu(this,menuItemView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_computadora_principal,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.buscar:
                        return true;
                    case R.id.todo:
                        return  true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }

}