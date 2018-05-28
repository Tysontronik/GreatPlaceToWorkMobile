package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 01/03/2018.
 */

public class AsignacionesModulo implements Serializable {
    public int idAsignacion;
    public int tipoAsignacion;
    public int order;
    public String material;
    public String evaluacion;
    public String actividad;
    public String extraMaterial;
    public String presencial;
    public String autoevaluacion;
    public ExtraMaterial extraMateriales;
    public String infografia;
    public PresencialModulo presenciales;
    public MaterialModulo materialModulo;
    public AutoEvaluacionModulo autoEvaluacionModulo;
    public EvaluacionModulo evaluacionModulo;
    public ActividadModulo actividadModulo;

    public InfografiaModulo infografiaModulo;


    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public int getTipoAsignacion() {
        return tipoAsignacion;
    }

    public void setTipoAsignacion(int tipoAsignacion) {
        this.tipoAsignacion = tipoAsignacion;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getAutoevaluacion() {
        return autoevaluacion;
    }

    public void setAutoevaluacion(String autoevaluacion) {
        this.autoevaluacion = autoevaluacion;
    }


    public String getInfografia() {
        return infografia;
    }

    public void setInfografia(String infografia) {
        this.infografia = infografia;
    }


}
