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
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.Constans.Constans;
import com.learning.gptw.greatplacetowork_learning.Constans.UrlConstants;
import com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio.MainActivity;
import com.learning.gptw.greatplacetowork_learning.Listener.GenericResponseListener;
import com.learning.gptw.greatplacetowork_learning.Listener.ResponseErrorListener;
import com.learning.gptw.greatplacetowork_learning.Login.LoginGreatPlaceToWork;
import com.learning.gptw.greatplacetowork_learning.Models.Login;
import com.learning.gptw.greatplacetowork_learning.R;
import com.learning.gptw.greatplacetowork_learning.Services.LoginService;
import com.learning.gptw.greatplacetowork_learning.Utils.NetworkUtil;
import com.learning.gptw.greatplacetowork_learning.Utils.StringUtils;
import com.learning.gptw.greatplacetowork_learning.Utils.UserPrefsUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
     * Intance attributes
     **/
    private SharedPreferences prefs;

    private LoginService _LoginService = new LoginService();

    private Login successLogin;


    /**
     * Userdata autoload and set of application Look
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation and hide the top title bar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Recover the User preference from app cache
        this.prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //Check  the network status and the accesibility to the GWTP web API
        NetworkUtil.isConnectedMobile(this);
        NetworkUtil.isConnectedWifi(this);

        //Load the user Data if exist
        loadDataUser();
        timerTask();

    }


    public void loadDataUser() {

        if (UserPrefsUtil.isUserCredentialsSharedPreferencesData(this.prefs)) {

            _LoginService.login(
                    UserPrefsUtil.getUserSharedPreferencesAttribute(prefs, UserPrefsUtil.USER_KEY),
                    UserPrefsUtil.getUserSharedPreferencesAttribute(prefs, UserPrefsUtil.PASSWORD_KEY),
                    this,
                    successLogin
            );

        } else {


        }
    }

    private void timerTask() {

        setContentView(R.layout.activity_splash_great_place_to_work);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Intent intentLogin = new Intent(SplashGreatPlaceToWork.this, LoginGreatPlaceToWork.class);
                Intent intentMain = new Intent(SplashGreatPlaceToWork.this, MainActivity.class);

                if (!TextUtils.isEmpty(
                        UserPrefsUtil.getUserSharedPreferencesAttribute(prefs, UserPrefsUtil.USER_KEY)) &&
                        !TextUtils.isEmpty(UserPrefsUtil.getUserSharedPreferencesAttribute(prefs, UserPrefsUtil.PASSWORD_KEY))) {
                    intentMain.putExtra("idUser", successLogin.getIdUsuario());
                    startActivity(intentMain);
                } else {
                    intentLogin.putExtra("idUser", successLogin.getIdUsuario());
                    startActivity(intentLogin);

                }
                finish();
            }
        };


        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }


    /**
     *
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
     *
     */
    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        unregisterReceiver(networkStateReceiver);
        super.onPause();
    }

    private void onNetworkChange(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                Log.d("MenuActivity", "CONNECTED");
            } else {
                Log.d("MenuActivity", "DISCONNECTED");

            }
        }
    }

}