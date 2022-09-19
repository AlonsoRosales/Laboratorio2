package com.example.laboratorio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.laboratorio2.Entity.Teclado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Teclado_activity extends AppCompatActivity {
    public Lista maquinas = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado);
        getSupportActionBar().setTitle("Teclado");
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Lista lista = (Lista) intent.getSerializableExtra("lista");
        maquinas = lista;

        ListView listView = (ListView) findViewById(R.id.listaTeclados);
        String listadoTeclado = "";


        if (lista.listaEquipos.isEmpty()){
            listadoTeclado += "No hay teclados ingresados";
            TextView textView = findViewById(R.id.teclado_textview_vacio);
            textView.setText(listadoTeclado);
            //Si esta vacio por defecto se pone No hay teclados ingresadas
        }else{
            List<Teclado> tecladoList = new ArrayList<>();
            for (Object obj:lista.listaEquipos) {
                if (obj.getClass() == Teclado.class){
                    tecladoList.add((Teclado) obj);
                }
            }
            if (tecladoList.isEmpty()){
                listadoTeclado += "No hay teclados ingresados";
                TextView textView = findViewById(R.id.teclado_textview_vacio);
                textView.setText(listadoTeclado);
            }else{
                ArrayList<String> listaTextos = new ArrayList<>();
                String texto = "";
                for (Teclado teclado:tecladoList) {
                    texto += "Activo: " + teclado.getActivo()+ "\n" +"PC: " + teclado.getPC() + "\n" + "marca: " + teclado.getMarca() + "\n" + "Año: " + teclado.getAnho() + "\n" + "idioma: " + teclado.getIdioma() + "\n" + "Modelo: " + teclado.getModelo() + "\n";
                    listaTextos.add(texto);
                    texto = "";
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaTextos);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        Teclado teclado = tecladoList.get(position);
                        Intent intent1 = new Intent(Teclado_activity.this,Teclado_actualizar_activity.class);
                        intent1.putExtra("teclado",teclado);
                        intent1.putExtra("posicion",position);
                        intent1.putExtra("listaTeclado",lista);
                        startActivity(intent1);
                    }
                });
            }
        }
        FloatingActionButton floatingActionButton = findViewById(R.id.teclados_floatingActionButton);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(this,Teclado_activity_agregar.class);
            intent1.putExtra("lista",lista);
            startActivity(intent1);
        });

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teclado_menu,menu);
        return true;
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.puntitos_appbar_computadora_principal,menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent1 = new Intent(Teclado_activity.this,MainActivity.class);
            intent1.putExtra("lista",maquinas);
            startActivity(intent1);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void presionarPuntitosComputadora(MenuItem item){
        View menuItemView = findViewById(R.id.puntitos);
        PopupMenu popupMenu = new PopupMenu(this,menuItemView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_computadora_principal,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.buscar:
                        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(Teclado_activity.this);
                        alertDialog2.setTitle("Teclado");
                        final EditText input = new EditText(Teclado_activity.this);
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        input.setHint("Activo");
                        alertDialog2.setView(input);

                        alertDialog2.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String activoInput = input.getText().toString();

                                ArrayList<String> listaMaquinasBuscador = new ArrayList<>();
                                String texto = "";
                                for(Object objetito : maquinas.getListaEquipos()){
                                    if(objetito.getClass() == Teclado.class){
                                        Teclado teclado = (Teclado) objetito;
                                        if(teclado.getActivo().equals(activoInput)){
                                            texto += "Activo: " + teclado.getActivo()+ "\n" +"PC: " + teclado.getPC() + "\n" + "marca: " + teclado.getMarca() + "\n" + "Año: " + teclado.getAnho() + "\n" + "idioma: " + teclado.getIdioma() + "\n" + "Modelo: " + teclado.getModelo() + "\n";
                                            listaMaquinasBuscador.add(texto);
                                            texto = "";
                                        }
                                    }
                                }

                                if(listaMaquinasBuscador.size() == 0){
                                    String textoListadoPCS = "No existe el equipo con Activo: "+ activoInput;
                                    TextView textView = findViewById(R.id.teclado_textview_vacio);
                                    textView.setText(textoListadoPCS);

                                    ListView simple = (ListView) findViewById(R.id.listaTeclados);
                                    simple.setAdapter(null);

                                }else{
                                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Teclado_activity.this, android.R.layout.simple_list_item_1,listaMaquinasBuscador);
                                    ListView simple = (ListView) findViewById(R.id.listaTeclados);
                                    simple.setAdapter(adapter2);

                                }

                            }
                        });

                        alertDialog2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog2.show();

                        return true;



                    case R.id.todo:
                        ArrayList<String> listaMaquinasBuscador = new ArrayList<>();
                        String texto = "";
                        for(Object objetito : maquinas.getListaEquipos()){
                            if(objetito.getClass() == Computadora.class){
                                Teclado teclado = (Teclado) objetito;
                                texto += "Activo: " + teclado.getActivo()+ "\n" +"PC: " + teclado.getPC() + "\n" + "marca: " + teclado.getMarca() + "\n" + "Año: " + teclado.getAnho() + "\n" + "idioma: " + teclado.getIdioma() + "\n" + "Modelo: " + teclado.getModelo() + "\n";
                                listaMaquinasBuscador.add(texto);
                                texto = "";
                            }
                        }

                        if(listaMaquinasBuscador.size() == 0){
                            String textoListadoPCS = "No hay computadoras ingresadas";
                            TextView textView = findViewById(R.id.teclado_textview_vacio);
                            textView.setText(textoListadoPCS);

                            ListView simple = (ListView) findViewById(R.id.listaTeclados);
                            simple.setAdapter(null);

                        }else{
                            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Teclado_activity.this, android.R.layout.simple_list_item_1,listaMaquinasBuscador);
                            ListView simple = (ListView) findViewById(R.id.listaTeclados);
                            simple.setAdapter(adapter3);
                        }


                        return  true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }
}