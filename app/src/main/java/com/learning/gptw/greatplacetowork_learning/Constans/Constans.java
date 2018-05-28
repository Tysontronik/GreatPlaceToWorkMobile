package com.learning.gptw.greatplacetowork_learning.Constans;

import android.content.Context;

import org.json.JSONObject;

/**
 * Francisco Javier Flores Morales
 * Constans.java
 * Created by Dell on 22/01/2018.
 */

public class Constans {


    public static final String EMPTY_STRING  = "";
    public static final String KEY_EMAIL = "correo";
    public static final String KEY_PASSWORD = "password";
    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";
    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";
    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "correo";
    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static Context context;
    public static JSONObject loggedUser;
    public static String servicesURL = "http://10.27.148.136:8181/gptw-web/api/";
    public static String actividadURL ="actividad/get?idActividad=";
    public static String materialURL="material/get?idMaterial=";
    public static  String cursosURL ="curso/get?idCurso=";
    public static  String evaluacionURL="evaluacion/get?idEvaluacion=";
    public  static  String prendizajeUrl="https://s3.amazonaws.com/repositorioaprendizaje/";

    public static final String OK_STATUS_RESPONSE = "OK";

    public static final String NOT_FOUND_STATUS_RESPONSE = "NOT_FOUND";


}
