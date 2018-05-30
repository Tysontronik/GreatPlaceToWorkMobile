package com.learning.gptw.greatplacetowork_learning.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.learning.gptw.greatplacetowork_learning.Constans.Constants;

/**
 * @author rodrigo.cruz
 * @since 24/05/2018
 */

public class SharedPreferencesUtil {
    /**
     * Logger TAG
     */
    private static final String LOGGER_TAG = SharedPreferencesUtil.class.getSimpleName();

    /**
     * Constants to extract values from Shared Preferences
     */
    public static final String USER_KEY = "USER";
    public static final String PASSWORD_KEY = "PASSWORD";
    public static final String IS_LOGGED_KEY = "IS_LOGGED";
    public static  final String USER_DATA = "USER_DATA";

    private static final String PREFERENCES_NAME = "preferences";

    /**
     * Shared preferences
     */
    private static SharedPreferences sharedPreferences;

    /**
     * Get any attribute in shared preferences
     *
     * @param preferences
     * @param attributeName
     * @return the preference value
     */
    public static String getSharedPreferencesAttribute(SharedPreferences preferences, String attributeName) {

        String stringResult = preferences.getString(attributeName, Constants.EMPTY_STRING);
        Log.d(LOGGER_TAG, "Extract attribute[" + attributeName + "] extractedValue[" + stringResult + "]");
        return stringResult;
    }

    /**
     * Check if the user credentials already are in shared preferences into device
     *
     * @param preferences
     * @return true if  the device contains user and password information
     */
    public static Boolean isUserCredentialsSharedPreferencesData(SharedPreferences preferences) {

        Boolean evaluationResult = Boolean.TRUE;

        if (getSharedPreferencesAttribute(preferences, USER_KEY) == Constants.EMPTY_STRING ||
                getSharedPreferencesAttribute(preferences, PASSWORD_KEY) == Constants.EMPTY_STRING) {
            evaluationResult = Boolean.FALSE;
        }
        Log.d(LOGGER_TAG, "Checking if shared preferences contains the user credentials ... [" + evaluationResult + "]");

        return evaluationResult;
    }

    /**
     * Persiste a value in shared preferences
     *
     * @param sharedPreferences
     * @param preferenceKey
     * @param preferenceValue
     */
    public static void persistAttribute(SharedPreferences sharedPreferences, String preferenceKey, String preferenceValue) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceKey, preferenceValue);
        editor.apply();

    }

    /**
     * get the shared preferences from context
     *
     * @param context context
     * @return SharedPreferences object
     */
    public static SharedPreferences getAppSharedPreferences(Context context) {

        if (ValidatorUtil.isNull(sharedPreferences)) {
            sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;

    }

}
