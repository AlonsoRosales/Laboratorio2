package com.example.laboratorio2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lista implements Serializable {
    List<Object> listaEquipos = new ArrayList<>();

    public List<Object> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(List<Object> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }



}
