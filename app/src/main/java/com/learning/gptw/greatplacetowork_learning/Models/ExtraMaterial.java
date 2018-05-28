package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 06/03/2018.
 */

public class ExtraMaterial  implements Serializable {
    public int idExtraMaterial;

    public int getIdExtraMaterial() {
        return idExtraMaterial;
    }

    public void setIdExtraMaterial(int idExtraMaterial) {
        this.idExtraMaterial = idExtraMaterial;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public  String ruta;
            public String titulo;
            public  int duration;
}
