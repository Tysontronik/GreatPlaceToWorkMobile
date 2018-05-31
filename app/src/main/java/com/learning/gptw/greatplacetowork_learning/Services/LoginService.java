package com.learning.gptw.greatplacetowork_learning.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.gptw.greatplacetowork_learning.Callback.VolleyCallback;
import com.learning.gptw.greatplacetowork_learning.Constans.Constants;
import com.learning.gptw.greatplacetowork_learning.Constans.UrlConstants;
import com.learning.gptw.greatplacetowork_learning.Models.Login;
import com.learning.gptw.greatplacetowork_learning.Models.RestResponseDTO;
import com.learning.gptw.greatplacetowork_learning.Utils.SharedPreferencesUtil;
import com.learning.gptw.greatplacetowork_learning.Utils.StringUtils;


/**
 * Login service
 */
public class LoginService {
    /**
    *Shared preferences
    */
   private SharedPreferences sharedPreferences;
    /**
     *
     */
private Login sessionLoginData;
    /**
     * No instance wihout context
     */
    private  LoginService(){
        //No instance
    }

    /**
     * Recive the context
     * @param context
     */
    public LoginService(Context context){
        this.loginRestApiInvokerService  = RestApiInvokerService.getInstance(context);
        this.sharedPreferences =  SharedPreferencesUtil.getAppSharedPreferences(context);
    }

    /**
     * Logger Tag
     */
    private static final String LOGGER_TAG = LoginService.class.getSimpleName();
    /**
     *Rest api invoker
     */
    private RestApiInvokerService<Login> loginRestApiInvokerService;
    /**
     * @param username username
     * @param password password
     */
    public void login(final String username,final  String password, final VolleyCallback serviceCallback ) {


        String loginUrl =
                StringUtils.concatenateStrings(
                        UrlConstants.servicesURL,
                        StringUtils.formatStringBuilder(UrlConstants.LOGIN_URL, username, password));

        loginRestApiInvokerService.getObjectFromRequest(loginUrl, Login.class, new VolleyCallback() {
            @Override
            public void onSuccess(RestResponseDTO result) {
                sessionLoginData =  (Login) result;
                try {
                    if(Constants.OK_STATUS_RESPONSE.equals(sessionLoginData.getStatus())){

                        ObjectMapper objectMapper =  new ObjectMapper();
                        SharedPreferencesUtil.persistAttribute(sharedPreferences, SharedPreferencesUtil.IS_LOGGED_KEY, Boolean.TRUE.toString());
                        SharedPreferencesUtil.persistAttribute(sharedPreferences, SharedPreferencesUtil.PASSWORD_KEY, password);
                        SharedPreferencesUtil.persistAttribute(sharedPreferences, SharedPreferencesUtil.USER_KEY, username);
                        SharedPreferencesUtil.persistAttribute(sharedPreferences, SharedPreferencesUtil.USER_DATA, objectMapper.writeValueAsString(sessionLoginData));

                    }
                }catch (JsonProcessingException jpex){
                    Log.e(LOGGER_TAG,"Error in Login serialization", jpex);
                }

                serviceCallback.onSuccess(sessionLoginData);
            }
        });
    }

    /**
     * Data from callback
     * @return
     */
    public Login getSessionLoginData() {
        return sessionLoginData;
    }

}
