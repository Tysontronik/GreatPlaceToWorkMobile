package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;

/**
 * Created by Dell on 23/01/2018.
 */

public class Login implements Serializable {

    private String user;
    private String password;
    private int idUsuario;
    private int idRol;
    private Boolean active;
    private String correo;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rfc;
    private String genero;
    private String fechaNacimiento;
    private String calle;
    private String numero;
    private String colonia;
    private String municipio;
    private String estado;
    private String codigoPostal;
    private String posicion;
    private String nivelEmpresarial;
    private String mentor;
    private String nacionalidad;
    private String numeroEmpleado;
    private String nombreReportar;
    private Integer idGrupo;
    private String lastSessionDate;
    private String lastSessionHour;

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return this.idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNivelEmpresarial() {
        return nivelEmpresarial;
    }

    public void setNivelEmpresarial(String nivelEmpresarial) {
        this.nivelEmpresarial = nivelEmpresarial;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombreReportar() {
        return nombreReportar;
    }

    public void setNombreReportar(String nombreReportar) {
        this.nombreReportar = nombreReportar;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getLastSessionDate() {
        return lastSessionDate;
    }

    public void setLastSessionDate(String lastSessionDate) {
        this.lastSessionDate = lastSessionDate;
    }

    public String getLastSessionHour() {
        return lastSessionHour;
    }

    public void setLastSessionHour(String lastSessionHour) {
        this.lastSessionHour = lastSessionHour;
    }

}
