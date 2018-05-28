package com.learning.gptw.greatplacetowork_learning.Listener;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Error listener
 */
public class ResponseErrorListener implements Response.ErrorListener {

    /**
     *
     * @param error
     */
    private static final String LOGGER_TAG =  ResponseErrorListener.class.getSimpleName();

    /**
     * Implementation to handle the request errors
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i(LOGGER_TAG, "Error :" + error.toString());
    }
}
