package com.learning.gptw.greatplacetowork_learning.Services;

import android.content.Context;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.Callback.VolleyCallback;
import com.learning.gptw.greatplacetowork_learning.Converters.GenericJsonToObjectConverter;
import com.learning.gptw.greatplacetowork_learning.Exception.JsonConvertException;
import com.learning.gptw.greatplacetowork_learning.Listener.ResponseErrorListener;
import com.learning.gptw.greatplacetowork_learning.Models.RestResponseDTO;
import com.learning.gptw.greatplacetowork_learning.Utils.ValidatorUtil;

/***
 * Invoke rest api
 */
public class RestApiInvokerService<T extends RestResponseDTO> {

    private static RestApiInvokerService restApiInvokerService;

    private static final String LOGGER_TAG = RestApiInvokerService.class.getSimpleName();

    private GenericJsonToObjectConverter genericJsonToObjectConverter;

    private Class<T> objectClass;

    private T responseObjectInstance;

    private static Context context;

    /**
     * Avoid instances
     */
    private RestApiInvokerService() {
        genericJsonToObjectConverter = new GenericJsonToObjectConverter();
    }

    /**
     * Singleton instance
     *
     * @return
     */
    protected static RestApiInvokerService getInstance(Context recivedContext) {

        if (ValidatorUtil.isNull(restApiInvokerService)) {
            restApiInvokerService = new RestApiInvokerService();
            context = recivedContext;
        }
        return restApiInvokerService;
    }

    /**
     * get object type instance from URL request
     *
     * @param url
     * @param newObjectClass
     * @return T type parametrizade
     */
    public T getObjectFromRequest(final String url,final  Class<T> newObjectClass, final VolleyCallback callback) {

        objectClass = newObjectClass;

        Log.i(LOGGER_TAG,"Volley Request :" + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public synchronized void onResponse(String volleyResponse) {

                Log.i(LOGGER_TAG,"Volley Response :" + volleyResponse);

                try {
                    responseObjectInstance = (T) genericJsonToObjectConverter.jsonToObject(volleyResponse, objectClass);
                    responseObjectInstance.setStatus(genericJsonToObjectConverter.getStatus());

                } catch (JsonConvertException jex) {
                    Log.e(LOGGER_TAG, "Error in JSON [" + jex.getJsonFailed() + "] converse error code [" + jex.getStatus() + "]", jex);
                    try{
                        responseObjectInstance = objectClass.newInstance();
                        responseObjectInstance.setStatus(jex.getStatus());
                    }catch(InstantiationException| IllegalAccessException ex){
                        Log.e(LOGGER_TAG, "Error in object instance", ex);
                    }
                }

                callback.onSuccess(responseObjectInstance);
            }
        }, new ResponseErrorListener());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        requestQueue.start();

        return responseObjectInstance;
    }

}
