package com.example.laboratorio2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laboratorio2.Entity.Monitor;

import java.util.ArrayList;

public class MonitorListar extends AppCompatActivity {

    ArrayList<Monitor> listaMonitores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_monitor_listar);
        getSupportActionBar().setTitle("Monitor");
        Lista lista = (Lista) getIntent().getSerializableExtra("lista");

        String mensaje_exito = getIntent().getStringExtra("exito");
        if (mensaje_exito != null && !mensaje_exito.equals("")) {
            Toast.makeText(MonitorListar.this, mensaje_exito, Toast.LENGTH_SHORT).show();
        }

        //Datos de prueba
//        Monitor monitor = new Monitor();
//        monitor.setActivo("C042322");
//        monitor.setPc("C012322");
//        monitor.setMarca("Dell");
//        monitor.setPulgadas("24\"");
//        monitor.setAnio("2022");
//        monitor.setModelo("d1234");
//        lista.getListaEquipos().add(monitor);
//
//        Monitor monitor1 = new Monitor();
//        monitor1.setActivo("C042323");
//        monitor1.setPc("C012323");
//        monitor1.setMarca("LG");
//        monitor1.setPulgadas("20\"");
//        monitor1.setAnio("2021");
//        monitor1.setModelo("d1233");
//        lista.getListaEquipos().add(monitor1);
//
//        Monitor monitor2 = new Monitor();
//        monitor2.setActivo("C042324");
//        monitor2.setPc("C012324");
//        monitor2.setMarca("MSI");
//        monitor2.setPulgadas("17\"");
//        monitor2.setAnio("2021");
//        monitor2.setModelo("d1234");
//        lista.getListaEquipos().add(monitor2);
//
//        Monitor monitor3 = new Monitor();
//        monitor3.setActivo("C042325");
//        monitor3.setPc("C012325");
//        monitor3.setMarca("MSI");
//        monitor3.setPulgadas("17\"");
//        monitor3.setAnio("2021");
//        monitor3.setModelo("d1235");
//        lista.getListaEquipos().add(monitor3);
//
//        Monitor monitor4 = new Monitor();
//        monitor4.setActivo("C042326");
//        monitor4.setPc("C012326");
//        monitor4.setMarca("MSI");
//        monitor4.setPulgadas("17\"");
//        monitor4.setAnio("2021");
//        monitor4.setModelo("d1236");
//        lista.getListaEquipos().add(monitor4);

        for (Object o : lista.getListaEquipos()) {
            listaMonitores.add((Monitor) o);
        }
        listarMonitores("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_monitor,menu);
        return true;
    }

    public void agregarMonitores(View view) {
        Intent intent = new Intent(this,MonitorAnadir.class);
        intent.putExtra("accion",1); //1: Añadir    2: Actualizar
        //intent.putExtra("monitorAActualizar", );
        intent.putExtra("listaMonitores", listaMonitores);
        startActivity(intent);
    }

    public void actualizarMonitor(Monitor m) {
        Intent intent = new Intent(this,MonitorAnadir.class);
        intent.putExtra("accion",2); //1: Añadir    2: Actualizar
        intent.putExtra("monitorAActualizar", m);
        intent.putExtra("listaMonitores", listaMonitores);
        startActivity(intent);
    }

    public void listarMonitores(String busqueda) {

        ArrayList<Monitor> listaMonitoresAMostrar = new ArrayList<>();

        TextView lista_vacia = findViewById(R.id.lista_monitores_error);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scrollView_monitor);
        linearLayout.removeAllViews();

        if (busqueda != null && !busqueda.equals("")) {
            for (Monitor m : listaMonitores) {
                if (m.getActivo().equals(busqueda)) {
                    listaMonitoresAMostrar.add(m);
                }
            }
        } else {
            listaMonitoresAMostrar = listaMonitores;
        }

        if (listaMonitores.isEmpty()) {
            lista_vacia.setText("No hay monitores ingresados");
            lista_vacia.setVisibility(lista_vacia.VISIBLE);
        } else if (listaMonitoresAMostrar.isEmpty()) {
            lista_vacia.setText("No existe el equipo con activo: " + busqueda);
            lista_vacia.setVisibility(lista_vacia.VISIBLE);
        } else {
            lista_vacia.setVisibility(lista_vacia.INVISIBLE);

            boolean color = false;
            for (Monitor m : listaMonitoresAMostrar) {
                TextView textView1 = new TextView(this);
                textView1.setTextSize(20);
                textView1.setText("Activo: " + m.getActivo() + "\n" +
                        "PC: " + m.getPc() + "\n" +
                        "Marca: " + m.getMarca() + "\n" +
                        "Pulgadas: " + m.getPulgadas() + "\n" +
                        "Año: " + m.getAnio() + "\n" +
                        "Modelo: " + m.getModelo());
                if (color) {
                    textView1.setBackgroundColor(0xFFC5C5C5);
                }
                textView1.setPadding(100, 50, 50, 50);// in pixels (left, top, right, bottom)
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        actualizarMonitor(m);
                    }
                });
                linearLayout.addView(textView1);
                color = !color;
            }
        }
    }

    public void popupMenuMonitor(MenuItem menuItem) {
        Log.d("msg", "Abri el menu tres puntos");
        View dots_item = findViewById(R.id.menu_monitor_dots);
        PopupMenu popupMenu = new PopupMenu(this,dots_item);
        popupMenu.getMenuInflater().inflate(R.menu.menu_monitor_popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.buscar_popup:
                        return true;
                    case R.id.todo_popup:
                        listarMonitores("");
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }

    public void alertaBuscar(MenuItem menuItem) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Monitor");
        EditText input = new EditText(this);
        input.setHint("Activo");
        //Revisar input
        alertDialog.setView(input);
        alertDialog.setPositiveButton("BUSCAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listarMonitores(String.valueOf(input.getText()));
            }
        });
        alertDialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        alertDialog.show();

    }
}