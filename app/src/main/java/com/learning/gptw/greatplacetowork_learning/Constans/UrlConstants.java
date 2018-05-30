package com.learning.gptw.greatplacetowork_learning.Constans;

/**
 * @author rodrigo.cruz
 * @since 25/05/2018
 */
public class UrlConstants {

    /**
     * URL form login service
     */
    public static final String LOGIN_URL = "login/check?usuario=%s&password=%s";
    /**
     * Services
     */
    public static String servicesURL = "http://10.27.148.138:8181/gptw-web/api/";

    public static String actividadURL ="actividad/get?idActividad=";
    public static String materialURL="material/get?idMaterial=";
    public static  String cursosURL ="curso/get?idCurso=";
    public static  String evaluacionURL="evaluacion/get?idEvaluacion=";
    public  static  String prendizajeUrl="https://s3.amazonaws.com/repositorioaprendizaje/";

}
