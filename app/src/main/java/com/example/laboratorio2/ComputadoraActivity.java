package com.example.laboratorio2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    public Lista maquinas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora);
        getSupportActionBar().setTitle("Computadora");

        Intent intent2 = getIntent();
        Lista listita = (Lista) intent2.getSerializableExtra("lista");
        maquinas = listita;

        ListView simpleList = (ListView) findViewById(R.id.listaDeComputadoras);

        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String textoListadoPCS = "";

        if(listita.getListaEquipos().size() == 0){
            textoListadoPCS = textoListadoPCS + "No hay computadoras ingresadas";
            TextView textView = findViewById(R.id.textoVacio);
            textView.setText(textoListadoPCS);
        }else{
            ArrayList<Computadora> computadoraList = new ArrayList<>();

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
                texto = "";
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaTextos);
            simpleList.setAdapter(adapter);




            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("TAMAÑO --> "+computadoraList.size());
                    System.out.println("POSITION --> "+position);

                    Computadora computadorita = computadoraList.get(position);

                    Intent intent = new Intent(ComputadoraActivity.this,ComputadoraActualizarActivity.class);
                    intent.putExtra("computadora",computadorita);
                    intent.putExtra("posicion",position);
                    intent.putExtra("listaComputadoras",listita);
                    startActivity(intent);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent1 = new Intent(ComputadoraActivity.this,MainActivity.class);
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
                        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(ComputadoraActivity.this);
                        alertDialog2.setTitle("Computadora");
                        final EditText input = new EditText(ComputadoraActivity.this);
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
                                    if(objetito.getClass() == Computadora.class){
                                        Computadora compa = (Computadora) objetito;
                                        if(compa.getActivo().equals(activoInput)){
                                            texto = texto + "Activo: "+compa.getActivo()+"\n"+"Marca: "+compa.getMarca()+"\n"+"Año: "+compa.getAnho()+"\n"+"CPU: "+compa.getCPU()+"\n";
                                            listaMaquinasBuscador.add(texto);
                                            texto = "";
                                        }
                                    }
                                }

                                if(listaMaquinasBuscador.size() == 0){
                                    String textoListadoPCS = "No existe el equipo con Activo: "+ activoInput;
                                    TextView textView = findViewById(R.id.textoVacio);
                                    textView.setText(textoListadoPCS);

                                    ListView simple = (ListView) findViewById(R.id.listaDeComputadoras);
                                    simple.setAdapter(null);

                                }else{
                                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ComputadoraActivity.this, android.R.layout.simple_list_item_1,listaMaquinasBuscador);
                                    ListView simple = (ListView) findViewById(R.id.listaDeComputadoras);
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
                                Computadora compa = (Computadora) objetito;
                                texto = texto + "Activo: "+compa.getActivo()+"\n"+"Marca: "+compa.getMarca()+"\n"+"Año: "+compa.getAnho()+"\n"+"CPU: "+compa.getCPU()+"\n";
                                listaMaquinasBuscador.add(texto);
                                texto = "";
                            }
                        }

                        if(listaMaquinasBuscador.size() == 0){
                            String textoListadoPCS = "No hay computadoras ingresadas";
                            TextView textView = findViewById(R.id.textoVacio);
                            textView.setText(textoListadoPCS);

                            ListView simple = (ListView) findViewById(R.id.listaDeComputadoras);
                            simple.setAdapter(null);

                        }else{
                            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(ComputadoraActivity.this, android.R.layout.simple_list_item_1,listaMaquinasBuscador);
                            ListView simple = (ListView) findViewById(R.id.listaDeComputadoras);
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