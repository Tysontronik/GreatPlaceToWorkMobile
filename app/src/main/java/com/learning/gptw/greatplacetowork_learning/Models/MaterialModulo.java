package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 01/03/2018.
 */

public class MaterialModulo implements Serializable {
    public	int	idMaterial	;
    public	String	titulo	;
    public	String	descripcion	;

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRutaTranscript() {
        return rutaTranscript;
    }

    public void setRutaTranscript(String rutaTranscript) {
        this.rutaTranscript = rutaTranscript;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public Boolean getEditable() {
        return isEditable;
    }

    public void setEditable(Boolean editable) {
        isEditable = editable;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public	String	ruta	;
    public	String rutaTranscript	;
     public	String	recursos	;
    public	Boolean	isEditable	;
    public	String	duration	;

}
