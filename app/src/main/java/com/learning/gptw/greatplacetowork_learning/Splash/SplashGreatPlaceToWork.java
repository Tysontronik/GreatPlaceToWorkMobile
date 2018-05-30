package com.learning.gptw.greatplacetowork_learning.Splash;

/*
 * Francisco Javier Flores Morales
 * francisco.flores@code-ing.com
 * splash
 * */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Window;

import com.learning.gptw.greatplacetowork_learning.Constans.IntentExtraKeyParamsConstants;
import com.learning.gptw.greatplacetowork_learning.Converters.GenericJsonToObjectConverter;
import com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio.MainActivity;
import com.learning.gptw.greatplacetowork_learning.Login.LoginGreatPlaceToWork;
import com.learning.gptw.greatplacetowork_learning.Models.Login;
import com.learning.gptw.greatplacetowork_learning.R;
import com.learning.gptw.greatplacetowork_learning.Services.LoginService;
import com.learning.gptw.greatplacetowork_learning.Utils.NetworkUtil;
import com.learning.gptw.greatplacetowork_learning.Utils.SharedPreferencesUtil;
import com.learning.gptw.greatplacetowork_learning.Utils.StringUtils;
import com.learning.gptw.greatplacetowork_learning.Utils.ValidatorUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Screen splash  whit Great place to work Logo
 * in the creation this must charge the user data and check the connectivity with Internet
 */
public class SplashGreatPlaceToWork extends Activity {

    /**
     * Set the duration of the splash screen
     **/
    private static final long SPLASH_SCREEN_DELAY = 3000;
    /**
     * Tag for logger
     **/
    private static final String LOGGER_TAG = SplashGreatPlaceToWork.class.getSimpleName();
    /**
     * The current context of the App
     */
    private Context context;
    /**
     * Shared Preferences attributes
     **/
    private SharedPreferences sharedPreferences;
    /**
     * This object stores  all the login data
     */
    private Login sessionUserData;
    /**
     * username recover from login object or the shared preferences
     */
    private String username;
    /**
     * password recover from login object or the shared preferences
     */
    private String password;
    /**
     * Login service for login in the app, this invoke a remote Rest login service from GPTW api
     */
    private LoginService _loginService;


    /**
     * Userdata auto load and set of application Look
     *
     * @param savedInstanceState Framework parameter required
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.i(LOGGER_TAG, "Screen Splash Init...");

        this.context = this;
        this.sharedPreferences = SharedPreferencesUtil.getAppSharedPreferences(context);

        // Set portrait orientation and hide the top title bar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Check  the network status and the access to the GWTP web API
        NetworkUtil.isConnectedMobile(context);
        NetworkUtil.isConnectedWifi(context);

        loginAndRedirect();
    }

    /**
     * Define the app behavior on resume
     */
    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    /**
     * Define the app behavior on pause
     */
    @Override
    public void onPause() {
        unregisterReceiver(networkStateReceiver);
        super.onPause();
    }

    /**
     * Login in the app and redirect to main content or to Login screen if is not logged
     */
    private void loginAndRedirect() {

        Timer timer = new Timer();

        setContentView(R.layout.activity_splash_great_place_to_work);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                Intent intentRedirect;

                if (isUserLoggedIn(sharedPreferences)) {
                    intentRedirect = new Intent(context, MainActivity.class);
                    intentRedirect.putExtra(IntentExtraKeyParamsConstants.ID_USER, sessionUserData.getIdUsuario());
                    intentRedirect.putExtra(IntentExtraKeyParamsConstants.USERNAME, username);
                    intentRedirect.putExtra(IntentExtraKeyParamsConstants.PASSWORD, password);
                } else {

                    intentRedirect = new Intent(context, LoginGreatPlaceToWork.class);
                }
                startActivity(intentRedirect);
                finish();
            }
        };

        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }


    /**
     * BroadcastReceiver to moninate the network connection
     */
    private BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = manager.getActiveNetworkInfo();
            onNetworkChange(ni);
        }
    };

    /**
     * Check the User login status
     *
     * @return True if the user is logged
     */
    private Boolean isUserLoggedIn(SharedPreferences sharedPreferences) {

        Boolean activeUserFlag;
        String loggedFlag = SharedPreferencesUtil.getSharedPreferencesAttribute(sharedPreferences, SharedPreferencesUtil.IS_LOGGED_KEY);

        if (Boolean.valueOf(loggedFlag)) {
            activeUserFlag = Boolean.valueOf(loggedFlag);
            this.sessionUserData = new GenericJsonToObjectConverter<Login>()
                    .jsonToObject(SharedPreferencesUtil.getSharedPreferencesAttribute(sharedPreferences,
                            SharedPreferencesUtil.USER_DATA), Login.class);
        } else {

            username = SharedPreferencesUtil.getSharedPreferencesAttribute(sharedPreferences, SharedPreferencesUtil.USER_KEY);
            password = SharedPreferencesUtil.getSharedPreferencesAttribute(sharedPreferences, SharedPreferencesUtil.PASSWORD_KEY);

            if (!StringUtils.isAnyEmptyOrNull(username, password)) {
                _loginService = new LoginService(this.context);
                this.sessionUserData = _loginService.login(username, password);
            }
            activeUserFlag = ValidatorUtil.isNotNull(this.sessionUserData);
        }

        return activeUserFlag;
    }

    /**
     * Log if the device is connected or disconnected
     *
     * @param networkInfo from device
     */
    private void onNetworkChange(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                Log.d(LOGGER_TAG, "CONNECTED");
            } else {
                Log.d(LOGGER_TAG, "DISCONNECTED");
            }
        }
    }

}