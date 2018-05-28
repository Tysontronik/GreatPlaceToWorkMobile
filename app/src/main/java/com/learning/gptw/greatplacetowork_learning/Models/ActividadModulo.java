package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 05/03/2018.
 */

public class ActividadModulo implements Serializable {

    public  int idActividad;

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public  String titulo;
            public  String descripcion;
            public  String peso;
            public  String duration;
}
