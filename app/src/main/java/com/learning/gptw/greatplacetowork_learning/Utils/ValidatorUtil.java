package com.learning.gptw.greatplacetowork_learning.Utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Util designed to contain validations
 * @author rodrigo.cruz
 * @since 25/05/2018
 */
public class ValidatorUtil {

    /**
     * Validate user format
     * @param user
     * @return
     */
    public static boolean isValidUser(String user) {
        return !TextUtils.isEmpty(user) && Patterns.EMAIL_ADDRESS.matcher(user).matches();
    }

    /**
     * Validate Password format
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        return password.length() >= 4;

    }

    /**
     * Validate if the object is not null
     * @param objectToValidate
     * @return Boolean , True if the object is not null
     */
    public static Boolean isNotNull(Object objectToValidate){
        return objectToValidate != null?  Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * Validate if the object is null
     * @param objectToValidate
     * @return Boolean , True if the object is null
     */
    public static Boolean isNull(Object objectToValidate){
        return objectToValidate == null?  Boolean.TRUE : Boolean.FALSE;
    }
}
