package com.learning.gptw.greatplacetowork_learning.Converters;

import android.util.Log;

import com.learning.gptw.greatplacetowork_learning.Constans.Constans;
import com.learning.gptw.greatplacetowork_learning.Exception.JsonConvertException;

import org.json.JSONObject;

public class GenericJsonToObjectConverter<T> {

    /**
     * Logger tag
     */
    private static final String LOGGER_TAG = GenericJsonToObjectConverter.class.getSimpleName();

    /**
     * Class reference
     */
    Class<T> typedClassReference;

    /**
     * Convert a JSON string to Object un generic Type
     *
     * @param jsonString
     * @return T generic type
     */
    public T jsonToObject(String jsonString, Class<T> objectClass) throws JsonConvertException {

        typedClassReference = objectClass;

        JSONObject event;
        JSONObject data;
        String status;

        T object = null;
        Log.i(LOGGER_TAG, jsonString);

        try {
            event = new JSONObject(jsonString);
            status = event.getString("status");

            if(Constans.OK_STATUS_RESPONSE.equals(status)){

                data = event.getJSONObject("data");
                object = objectClass.newInstance();


            }else{
                throw new JsonConvertException(status,"GPTW API response an error status");
            }

        } catch (Exception e) {

            throw new JsonConvertException(jsonString,e);
        }

        return object;
    }

}
