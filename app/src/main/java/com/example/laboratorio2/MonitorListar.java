package com.example.laboratorio2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
        Lista lista = (Lista) getIntent().getSerializableExtra("lista");

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

            }
        });
        alertDialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();

    }
}