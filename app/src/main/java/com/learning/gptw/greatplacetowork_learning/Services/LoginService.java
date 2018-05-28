package com.learning.gptw.greatplacetowork_learning.Services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.Constans.Constans;
import com.learning.gptw.greatplacetowork_learning.Constans.UrlConstants;
import com.learning.gptw.greatplacetowork_learning.Listener.GenericResponseListener;
import com.learning.gptw.greatplacetowork_learning.Listener.ResponseErrorListener;
import com.learning.gptw.greatplacetowork_learning.Models.Login;
import com.learning.gptw.greatplacetowork_learning.Utils.StringUtils;

/**
 * Login service
 */
public class LoginService {

    /**
     * Volley Request Queue
     */
    private RequestQueue mRequestQueue;
    /**
     * String Request
     */
    private StringRequest mStringRequest;

    /**
     * @param username username
     * @param password password
     * @param context  used to set the context to Volley service
     * @return Login instance with user instance
     */
    public String login(String username, String password, Context context , Login requiredInstance) {

        String loginUrl;

        GenericResponseListener genericResponseListener = new GenericResponseListener(Login.class);

        mRequestQueue = Volley.newRequestQueue(context);

        loginUrl =
                StringUtils.concatenateStrings(
                        Constans.servicesURL,
                        StringUtils.formatStringBuilder(UrlConstants.LOGIN_URL, username, password));

        mStringRequest = new StringRequest(Request.Method.GET, loginUrl, genericResponseListener, new ResponseErrorListener());
        mRequestQueue.add(mStringRequest);

        requiredInstance = (Login) genericResponseListener.getConvertedObjectInstance();

        return genericResponseListener.getJsonCallStatus();
    }

}
