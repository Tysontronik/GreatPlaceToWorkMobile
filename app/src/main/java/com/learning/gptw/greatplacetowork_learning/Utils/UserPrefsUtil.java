package com.learning.gptw.greatplacetowork_learning.Utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.learning.gptw.greatplacetowork_learning.Constans.Constans;

/**
 * @author rodrigo.cruz
 * @since  24/05/2018
 */

public class UserPrefsUtil {
    /**
     * Logger TAG
     */
    private static final String LOGGER_TAG =  UserPrefsUtil.class.getSimpleName();


    /**
     * Constants to extract values from Shared Preferences
     */
    public  static final String USER_KEY = "Usuario";
    public static final String PASSWORD_KEY = "Password";


    /**
     * Get any attribute in shared preferences
     * @param preferences
     * @param attributeName
     * @return the preference value
     */
    public static String getUserSharedPreferencesAttribute(SharedPreferences preferences, String attributeName){

        String stringResult = preferences.getString(attributeName,Constans.EMPTY_STRING);
        Log.d(LOGGER_TAG,"Extract attribute["+attributeName+"] extractedValue["+stringResult+"]");
        return stringResult;
    }

    /**
     * Check if the user credentials already are in shared preferences into device
     * @param preferences
     * @return true if  the device contains user and password information
     */
    public static Boolean isUserCredentialsSharedPreferencesData(SharedPreferences preferences){

        Boolean evaluationResult = Boolean.TRUE;

        if(getUserSharedPreferencesAttribute(preferences,USER_KEY) == Constans.EMPTY_STRING ||
                getUserSharedPreferencesAttribute(preferences,PASSWORD_KEY) == Constans.EMPTY_STRING){
            evaluationResult = Boolean.FALSE;
        }
        Log.d(LOGGER_TAG,"Checking if shared preferences contains the user credentials ... ["+evaluationResult+"]" );

        return evaluationResult;
    }
}
