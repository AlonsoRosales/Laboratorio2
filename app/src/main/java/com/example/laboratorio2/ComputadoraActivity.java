package com.example.laboratorio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ComputadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora);

        Intent intent2 = getIntent();



        FloatingActionButton botonAgregar = findViewById(R.id.botonagregar);
        botonAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(ComputadoraActivity.this,ComputadoraAgregarActivity.class);

            startActivity(intent);

        });


    }



}