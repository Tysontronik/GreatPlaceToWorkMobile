package com.learning.gptw.greatplacetowork_learning.Models;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

public class Login extends RestRequestDTO implements Serializable {

    private int idUsuario;
    private int idRol;
    private Boolean active;
    private String correo;
    private String contrasenia;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rfc;
    private String genero;
    private Date fechaNacimiento;
    private String fechaNacimientoDatePicker;
    private String calle;
    private String numero;
    private String colonia;
    private String municipio;
    private String estado;
    private String codigoPostal;
    private String posicion;
    private String nivelEmpresarial;
    private String supervisor;
    private String mentor;
    private String correoMentor;
    private String nacionalidad;
    private String numeroEmpleado;
    private String nombreReportar;
    private Integer idGrupo;
    private String lastSessionDate;
    private Time lastSessionHour;

    public Login() {
    }

    public Login(int idUsuario, int idRol, Boolean active, String correo, String contrasenia, String nombres,
                      String apellidoPaterno, String apellidoMaterno, String rfc, String genero, Date fechaNacimiento,
                      String calle, String numero, String colonia, String municipio, String estado, String codigoPostal,
                      String posicion, String nivelEmpresarial, String supervisor, String mentor, String correoMentor,
                      String nacionalidad, String numeroEmpleado, String nombreReportar, Rol rol, Integer idGrupo,
                      String lastSessionDate, Time lastSessionHour) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.active = active;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rfc = rfc;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.municipio = municipio;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.posicion = posicion;
        this.nivelEmpresarial = nivelEmpresarial;
        this.supervisor = supervisor;
        this.mentor = mentor;
        this.correoMentor = correoMentor;
        this.nacionalidad = nacionalidad;
        this.numeroEmpleado = numeroEmpleado;
        this.nombreReportar = nombreReportar;
        this.idGrupo = idGrupo;
        this.lastSessionDate = lastSessionDate;
        this.lastSessionHour = lastSessionHour;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getCorreoMentor() {
        return correoMentor;
    }

    public void setCorreoMentor(String correoMentor) {
        this.correoMentor = correoMentor;
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

    public String getLastSessionDate() {
        return lastSessionDate;
    }

    public void setLastSessionDate(String lastSessionDate) {
        this.lastSessionDate = lastSessionDate;
    }

    public Time getLastSessionHour() {
        return lastSessionHour;
    }

    public void setLastSessionHour(Time lastSessionHour) {
        this.lastSessionHour = lastSessionHour;
    }

    public String getFechaNacimientoDatePicker() {
        return fechaNacimientoDatePicker;
    }

    public void setFechaNacimientoDatePicker(String fechaNacimientoDatePicker) {
        this.fechaNacimientoDatePicker = fechaNacimientoDatePicker;
    }

}
