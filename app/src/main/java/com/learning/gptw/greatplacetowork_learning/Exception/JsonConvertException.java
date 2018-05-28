package com.learning.gptw.greatplacetowork_learning.Exception;

import android.util.Log;

import com.learning.gptw.greatplacetowork_learning.Utils.StringUtils;

/**
 * Exception for Json parse errors
 */
public class JsonConvertException extends Exception {

    /**
     * Logger tag
     */
    private static final String LOGGER_TAG = JsonConvertException.class.getSimpleName();

    /**
     * JSON status from GPTW API
     */
    private String status;
    /**
     * JSON String who fails
     */
    private String jsonFailed;

    /**
     * Throw Exception and store the status of the response an set a message to the super exception
     * @param status
     * @param message
     */
    public JsonConvertException(String status, String message){
        super(message);
        this.status = status;

    }

    /**
     * Throw Exception, store the status of the response, set a message to the super exception and log the Json Fail
     * @param status
     * @param message
     * @param jsonFailed
     */
    public JsonConvertException(String status, String message, String jsonFailed){

        super(StringUtils.concatenateStrings(message,jsonFailed));
        this.status = status;
        this.jsonFailed = jsonFailed;
        Log.e(LOGGER_TAG,jsonFailed);
    }

    /**
     * Throw the exception an nest the root esception cause , store the failed Json
     * @param jsonFailed
     */
    public JsonConvertException(String jsonFailed,Exception ex) {
        super(jsonFailed,ex);
        this.jsonFailed = jsonFailed;
    }

        /**
         * Avoid instances without status
         */
    private JsonConvertException(){
        // Avoid instances without status
    }

    /**
     * get the status
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * get the Json fail
     * @return
     */
    public String getJsonFailed() {
        return jsonFailed;
    }


}
