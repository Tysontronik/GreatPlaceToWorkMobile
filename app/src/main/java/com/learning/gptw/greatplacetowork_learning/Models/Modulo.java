package com.learning.gptw.greatplacetowork_learning.Models;

/**
 * Created by Dell on 05/03/2018.
 */

public class Modulo {
    public	int idModulo	;

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
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

    public	String titulo	;
    public	 String descripcion	;

    public String getModuloCursos() {
        return moduloCursos;
    }

    public void setModuloCursos(String moduloCursos) {
        this.moduloCursos = moduloCursos;
    }

    public String moduloCursos;

}
