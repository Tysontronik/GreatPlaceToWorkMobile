package com.learning.gptw.greatplacetowork_learning.Converters;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.gptw.greatplacetowork_learning.Constans.Constants;
import com.learning.gptw.greatplacetowork_learning.Exception.JsonConvertException;
import com.learning.gptw.greatplacetowork_learning.Utils.StringUtils;
import com.learning.gptw.greatplacetowork_learning.Utils.ValidatorUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GenericJsonToObjectConverter<T> {

    /**
     * Logger tag
     */
    private static final String LOGGER_TAG = GenericJsonToObjectConverter.class.getSimpleName();
    /**
     * Status of execution
     */
    private String status;

    /**
     * Convert a JSON string to Object un generic Type
     *
     * @param jsonString JSON string to parse in object
     * @return T generic type
     */
    public T jsonToObject(String jsonString, Class<T> objectClass) throws JsonConvertException {

        JSONObject event;
        JSONObject data;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T object;

        try {
            event = new JSONObject(jsonString);
            status = event.getString("status");
            if (Constants.OK_STATUS_RESPONSE.equals(status)) {
                data = event.getJSONObject("data");
                Log.i(LOGGER_TAG, String.valueOf(data));
                object = objectMapper.readValue(String.valueOf(data), objectClass);
            } else {
                throw new JsonConvertException(StringUtils.isNotEmptyOrNull(status) ? status : Constants.NOT_FOUND_STATUS_RESPONSE, "GPTW API response an error status");
            }
        } catch (IOException | JSONException e) {
            status = Constants.EXCEPTION_STATUS_RESPONSE;
            throw new JsonConvertException(Constants.EXCEPTION_STATUS_RESPONSE, jsonString, e);
        }
        return object;
    }

    /**
     * Convert a JSON string to Object un generic Type
     *
     * @param jsonString JSON string to parse in object
     * @return T generic type
     */
    public T jsonNoStandartToObject(final String jsonString, final Class<T> objectClass) throws JsonConvertException {

        ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try {

            Log.i(LOGGER_TAG, StringUtils.concatenateStrings("JSON to parse:", String.valueOf(jsonString)));
            object = objectMapper.readValue(String.valueOf(jsonString), objectClass);
        } catch (IOException ioe) {
            throw new JsonConvertException(Constants.EXCEPTION_STATUS_RESPONSE, jsonString, ioe);
        }
        return object;
    }


    /**
     * get the status of execution
     *
     * @return status of execution
     */
    public String getStatus() {
        return status;
    }
}
