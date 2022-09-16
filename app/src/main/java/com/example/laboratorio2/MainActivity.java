package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Se agrega una Lista de objetos
    Lista lista = new Lista();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listaMonitores(View view) {
        Intent intent = new Intent(this, MainActivity_MonitorListar.class);
        startActivity(intent);
    }
    //Redireccion al registro de Computadora
    public void registroComputadora(View view){
        Intent intent = new Intent(this,ComputadoraActivity.class);
        intent.putExtra("lista",lista);
        startActivity(intent);
    }
}