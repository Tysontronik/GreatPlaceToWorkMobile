package com.learning.gptw.greatplacetowork_learning.Models;

import org.json.JSONObject;

/**
 * Created by Dell on 01/03/2018.
 */

public class BloquesModulo extends AsignacionesModulo {
    public int getIdBloqueCurso() {
        return idBloqueCurso;
    }

    public void setIdBloqueCurso(int idBloqueCurso) {
        this.idBloqueCurso = idBloqueCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public	int	idBloqueCurso	;
    public	String	titulo	;
    public	int	ordered	;


    public AsignacionesModulo asignaciones;


}
