package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 01/03/2018.
 */

public class CursoModulo implements Serializable {
    public	int	idCurso	;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getBloques() {
        return bloques;
    }

    public void setBloques(String bloques) {
        this.bloques = bloques;
    }

    public	String	titulo	;
    public	String	subtitulo	;
    public	String	description	;
    public	Boolean	isEditable	;
    public  String bloques;


}
