package com.learning.gptw.greatplacetowork_learning.Utils;

import com.learning.gptw.greatplacetowork_learning.Constans.Constants;

import java.util.Formatter;

/**
 * @author rodrigo.cruz
 * @since 24/05/2018
 * Manage the strings taking care of the device performance
 */

public class StringUtils {

    private static final StringBuilder stringBulider = new StringBuilder();
    private static final Formatter formatter = new Formatter(stringBulider);


    /**
     * Concatenate Strings with StringBuilder
     *
     * @param stringsToConcatenate
     * @return new string
     */
    public static String concatenateStrings(String... stringsToConcatenate) {

        //Clear the String Builder to do one concatenation in every execution
        stringBulider.setLength(0);

        for (String stringInArray : stringsToConcatenate) {
            stringBulider.append(stringInArray);
        }

        return stringBulider.toString();
    }

    /**
     * @param stringWithPattern
     * @param values
     * @return
     */
    public static String formatStringBuilder(String stringWithPattern, Object... values) {

        stringBulider.setLength(0);
        return formatter.format(stringWithPattern, values).toString();

    }

    /**
     * @param stringToEvaluate string to evaluate
     * @return True if the string is not empty
     */
    public static Boolean isNotEmptyOrNull(String stringToEvaluate) {

        Boolean notEmptyFlag = Boolean.TRUE;

        if (stringToEvaluate == null || Constants.EMPTY_STRING.equals(stringToEvaluate)) {
            notEmptyFlag = Boolean.FALSE;
        }
        return notEmptyFlag;
    }

    /**
     * Evaluate Many strings , if one are null or empty return false
     *
     * @param stringsToEvaluate strings array to iterate and evaluate
     * @return True if  any string is null or empty
     */
    public static Boolean isAnyEmptyOrNull(String... stringsToEvaluate) {
        Boolean notEmptyFlag = Boolean.TRUE;

        for (String stringToEvaluate : stringsToEvaluate) {
            notEmptyFlag =isNotEmptyOrNull(stringToEvaluate);
                if(!notEmptyFlag){ break; }
        }

        return !notEmptyFlag;
    }

}
