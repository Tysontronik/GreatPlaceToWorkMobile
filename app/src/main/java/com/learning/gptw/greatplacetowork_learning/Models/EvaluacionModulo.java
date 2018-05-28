package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 05/03/2018.
 */

public class EvaluacionModulo  implements  Serializable{
    public  int idEvaluacion;

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Boolean getEditable() {
        return isEditable;
    }

    public void setEditable(Boolean editable) {
        isEditable = editable;
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
    public PreguntasModulo preguntas;
    public  Boolean isEditable;
    public  String peso;

    public  String duration;






}
