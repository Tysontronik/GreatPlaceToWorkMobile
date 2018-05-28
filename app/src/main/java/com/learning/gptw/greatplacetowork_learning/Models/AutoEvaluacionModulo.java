package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 05/03/2018.
 */

public class AutoEvaluacionModulo implements Serializable {
    public  int idAutoEvaluacion;
            public String titulo;

    public int getIdAutoEvaluacion() {
        return idAutoEvaluacion;
    }

    public void setIdAutoEvaluacion(int idAutoEvaluacion) {
        this.idAutoEvaluacion = idAutoEvaluacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(String preguntas) {
        this.preguntas = preguntas;
    }

    public String            duration;
            public String preguntas;
}
