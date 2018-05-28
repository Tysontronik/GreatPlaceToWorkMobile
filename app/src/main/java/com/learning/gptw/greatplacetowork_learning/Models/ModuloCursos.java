package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dell on 01/03/2018.
 */

public class ModuloCursos implements Serializable {
    public	int	idModuloCurso	;
    public String fechaInicioDatePicker	;

    public int getIdModuloCurso() {
        return idModuloCurso;
    }

    public void setIdModuloCurso(int idModuloCurso) {
        this.idModuloCurso = idModuloCurso;
    }

    public String getFechaInicioDatePicker() {
        return fechaInicioDatePicker;
    }

    public void setFechaInicioDatePicker(String fechaInicioDatePicker) {
        this.fechaInicioDatePicker = fechaInicioDatePicker;
    }

    public String getFechaFinDatePicker() {
        return fechaFinDatePicker;
    }

    public void setFechaFinDatePicker(String fechaFinDatePicker) {
        this.fechaFinDatePicker = fechaFinDatePicker;
    }

    public int getPonderation() {
        return ponderation;
    }

    public void setPonderation(int ponderation) {
        this.ponderation = ponderation;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public	String	fechaFinDatePicker	;
    public	int	ponderation	;
    public	int	position	;



    public CursoModulo curso;

}
