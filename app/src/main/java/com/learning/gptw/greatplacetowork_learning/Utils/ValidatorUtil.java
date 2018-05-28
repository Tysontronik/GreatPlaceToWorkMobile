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
}
