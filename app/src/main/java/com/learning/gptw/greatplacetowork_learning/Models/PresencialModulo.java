package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 06/03/2018.
 */

public class PresencialModulo implements Serializable {
    public  int idPresencial;
            public  String titulo;

    public int getIdPresencial() {
        return idPresencial;
    }

    public void setIdPresencial(int idPresencial) {
        this.idPresencial = idPresencial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public  String ruta;
            public String peso;
    public  String duration;
}
