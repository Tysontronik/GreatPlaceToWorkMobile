package com.learning.gptw.greatplacetowork_learning.Models;

/**
 * Created by Dell on 30/01/2018.
 */

import java.io.Serializable;

public class CursoInicio implements Serializable {


    public int idCurso;
    public String titulo;
    public String subtitulo;
    public String description;
    public Boolean isEditable;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEditable() {
        return isEditable;
    }

    public void setEditable(Boolean editable) {
        isEditable = editable;
    }


}
