package com.example.laboratorio2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laboratorio2.Entity.Monitor;

import java.text.ParseException;
import java.util.ArrayList;

public class MonitorAnadir extends AppCompatActivity {

    int accionGlobal;
    Monitor monitor = new Monitor();
    Monitor monitorAActualizar = new Monitor();
    ArrayList<Monitor> listaMonitorActividad = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_anadir);

        accionGlobal = getIntent().getIntExtra("accion",1);
        monitorAActualizar = (Monitor) getIntent().getSerializableExtra("monitorAActualizar");
        listaMonitorActividad = (ArrayList<Monitor>) getIntent().getSerializableExtra("listaMonitores");

        if (accionGlobal == 1) {
            getSupportActionBar().setTitle("Nuevo");
        } else {
            getSupportActionBar().setTitle("Actualizar");
        }

        //Spinners
        Spinner spinner_pc = findViewById(R.id.spinner_pc);
        Spinner spinner_marca = findViewById(R.id.spinner_marca);
        Spinner spinner_pulgadas = findViewById(R.id.spinner_pulgadas);

        spinner_pc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String opcionSpinner_pc = spinner_pc.getSelectedItem().toString();
                if (opcionSpinner_pc.equals("PC Activo")) {
                    monitor.setPc("");
                } else {
                    monitor.setPc(opcionSpinner_pc);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                monitor.setPc("");
            }
        });

        spinner_marca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String opcionSpinner_marca = spinner_marca.getSelectedItem().toString();
                if (opcionSpinner_marca.equals("Marca")) {
                    monitor.setMarca("");
                } else {
                    monitor.setMarca(opcionSpinner_marca);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                monitor.setMarca("");
            }
        });

        spinner_pulgadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String opcionSpinner_pulgadas = spinner_pulgadas.getSelectedItem().toString();
                if (opcionSpinner_pulgadas.equals("Pulgadas")) {
                    monitor.setPulgadas("");
                } else {
                    monitor.setPulgadas(opcionSpinner_pulgadas);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                monitor.setPulgadas("");
            }
        });

        String[] valores_marcas = getResources().getStringArray(R.array.monitor_marcas);
        String[] monitor_pulgadas = getResources().getStringArray(R.array.monitor_pulgadas);
        if (monitorAActualizar != null) {
            for (int i=0; i<valores_marcas.length; i++) {
                if (valores_marcas[i].equals(monitorAActualizar.getMarca())) {
                    spinner_marca.setSelection(i);
                }
            }
            for (int i=0; i<monitor_pulgadas.length; i++) {
                if (monitor_pulgadas[i].equals(monitorAActualizar.getPulgadas())) {
                    spinner_pulgadas.setSelection(i);
                }
            }
            EditText activo_edit = findViewById(R.id.input_activo_anadir);
            activo_edit.setText(monitorAActualizar.getActivo());

            //Revisar si hace falta
            activo_edit.setFocusable(false);
            activo_edit.setEnabled(false);
            activo_edit.setCursorVisible(false);
            activo_edit.setKeyListener(null);

            EditText anio_edit = findViewById(R.id.input_anio_anadir);
            anio_edit.setText(monitorAActualizar.getAnio());
            EditText modelo_edit = findViewById(R.id.input_modelo_anadir);
            modelo_edit.setText(monitorAActualizar.getModelo());
        }

    }

    public void guardarMonitor(MenuItem menuItem) {
        EditText activo_edit = findViewById(R.id.input_activo_anadir);
        EditText anio_edit = findViewById(R.id.input_anio_anadir);
        EditText modelo_edit = findViewById(R.id.input_modelo_anadir);

        boolean guardar = true;
        if (activo_edit.getText().toString().equals("") || activo_edit.getText().toString() == null) {
            activo_edit.setError("Ingrese un activo");
            guardar = false;
        } else {
            Log.d("msg", "entre al else");
            for (Monitor m : listaMonitorActividad) {
                Log.d("msg", m.getActivo());
                Log.d("msg", String.valueOf(m.getActivo().equals(monitor.getActivo())));
                if ((monitorAActualizar == null && m.getActivo().equals(activo_edit.getText().toString())) || (monitorAActualizar != null && !activo_edit.getText().toString().equals(monitorAActualizar.getActivo()))) {
                    activo_edit.setError("No pueden repetirse los activos");
                    guardar = false;
                }
            }
        }
        if (anio_edit.getText().toString().equals("") || anio_edit.getText().toString() == null) {
            anio_edit.setError("Ingrese un año");
            guardar = false;
        } else {
            try {
                int anioInt = Integer.parseInt(anio_edit.getText().toString());
            } catch (NumberFormatException e) {
                anio_edit.setError("Debe ingresar un número entero");
                guardar = false;
            }
        }
        if (modelo_edit.getText().toString().equals("") || modelo_edit.getText().toString() == null) {
            modelo_edit.setError("Ingrese un modelo");
            guardar = false;
        }

        if (guardar) {
            monitor.setActivo(activo_edit.getText().toString());
            monitor.setAnio(anio_edit.getText().toString());
            monitor.setModelo(modelo_edit.getText().toString());

            Intent intent = new Intent(this,MonitorListar.class);

            if (accionGlobal == 1) {
                listaMonitorActividad.add(monitor);
                intent.putExtra("exito", "Se ha agregado un nuevo monitor de activo " + monitor.getActivo());
            } else {
                intent.putExtra("exito", "Se ha actualizado el monitor");
                for (Monitor m : listaMonitorActividad) {
                    if (m.getActivo().equals(monitorAActualizar.getActivo())) {
                        m.setActivo(monitor.getActivo());
                        m.setPc(monitor.getPc());
                        m.setMarca(monitor.getMarca());
                        m.setPulgadas(monitor.getPulgadas());
                        m.setAnio(monitor.getAnio());
                        m.setModelo(monitor.getModelo());
                    }
                }
            }

            Lista lista = new Lista();
            for (Monitor m : listaMonitorActividad) {
                lista.getListaEquipos().add(m);
            }
            intent.putExtra("lista", lista);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (accionGlobal == 1) {
            getMenuInflater().inflate(R.menu.menu_monitor_agregar,menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_monitor_actualizar,menu);
        }
        return true;
    }

    public void alertaEliminar(MenuItem menuItem) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Seguro que desea borrar?");
        alertDialog.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MonitorAnadir.this,MonitorListar.class);
                int j = 0;
                for (Monitor m : listaMonitorActividad) {
                    if (m.getActivo().equals(monitorAActualizar.getActivo())) {
                        j++;
                        intent.putExtra("exito", "Se ha eliminado el monitor de actividad " + m.getActivo());
                    }
                }
                listaMonitorActividad.remove(j);

                Lista lista = new Lista();
                for (Monitor m : listaMonitorActividad) {
                    lista.getListaEquipos().add(m);
                }
                intent.putExtra("lista", lista);
                startActivity(intent);
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