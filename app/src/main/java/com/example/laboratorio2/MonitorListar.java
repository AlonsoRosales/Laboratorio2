package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class MonitorListar extends AppCompatActivity {

    ArrayList<Monitor> listaMonitores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_monitor_listar);
        //Colocar para cambiar texto de barra superior

        TextView monitores = findViewById(R.id.lista_monitores);
        if (listaMonitores.isEmpty()) {
            monitores.setText("No hay monitores ingresados");
        } else {
            //Iterar por la lista e ingresar los valores
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_monitor,menu);
        return true;
    }

    public void popupMenuMonitor(MenuItem menuItem) {
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
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}