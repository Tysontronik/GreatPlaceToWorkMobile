package com.learning.gptw.greatplacetowork_learning.Listener;

import android.util.Log;

import com.android.volley.Response;
import com.learning.gptw.greatplacetowork_learning.Constans.Constants;
import com.learning.gptw.greatplacetowork_learning.Converters.GenericJsonToObjectConverter;
import com.learning.gptw.greatplacetowork_learning.Exception.JsonConvertException;

/**
 * Get the Json response in the Volley Request and parse it
 *
 * @param <T>
 */
public class GenericResponseListener<T> implements Response.Listener<T>{

    private static final String LOGGER_TAG = GenericResponseListener.class.getSimpleName();
    /**
     * Parsed Object
     */
    private T convertedObjectInstance;

    /**
     * Class of generic type
     */
    private Class<T> typeParameterClass;

    /**
     * The status of the call
     */
    private String jsonCallStatus;
    /**
     * Converter Json to Login Object
     */
    private GenericJsonToObjectConverter<T> converter = new GenericJsonToObjectConverter<>();

    /**
     * Private for no intances
     */
    @SuppressWarnings("unused")
    private GenericResponseListener() {
        //No Instance without parameters
    }

    /**
     * Constructor whit typeParameterClass
     *
     * @param typeParameterClass type of response
     */
    public GenericResponseListener(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public synchronized void onResponse(T response) {

        Log.i(LOGGER_TAG, "Volley Service Response: " + String.valueOf(response));
        try {

            this.convertedObjectInstance = converter.jsonToObject(String.valueOf(response), typeParameterClass);
            this.jsonCallStatus = Constants.OK_STATUS_RESPONSE;

        } catch (JsonConvertException jex) {
            Log.e(LOGGER_TAG, jex.getJsonFailed(), jex);
            this.jsonCallStatus = jex.getStatus();
        }

    }

    /**
     * Get th extracted object
     *
     * @return Generic Type
     */
    public T getConvertedObjectInstance() {
        return convertedObjectInstance;
    }

    /**
     * get status of call
     *
     * @return
     */
    public String getJsonCallStatus() {
        return jsonCallStatus;
    }
}
