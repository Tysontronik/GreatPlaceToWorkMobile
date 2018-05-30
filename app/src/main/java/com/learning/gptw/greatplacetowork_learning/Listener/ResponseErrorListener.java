package com.learning.gptw.greatplacetowork_learning.Listener;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

/**
 * Error listener
 */
public class ResponseErrorListener implements Response.ErrorListener {

    /**
     * Logger tag
     *
     * @param error
     */
    private static final String LOGGER_TAG = ResponseErrorListener.class.getSimpleName();

    /**
     * Implementation to handle the request errors
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(LOGGER_TAG, "Error in the Volley request  :", error);
    }
}
