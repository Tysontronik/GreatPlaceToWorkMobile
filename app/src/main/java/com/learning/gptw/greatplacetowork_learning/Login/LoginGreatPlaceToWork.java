package com.learning.gptw.greatplacetowork_learning.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.learning.gptw.greatplacetowork_learning.Callback.VolleyCallback;
import com.learning.gptw.greatplacetowork_learning.Constans.Constants;
import com.learning.gptw.greatplacetowork_learning.Constans.IntentExtraKeyParamsConstants;
import com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio.MainActivity;
import com.learning.gptw.greatplacetowork_learning.Models.Login;
import com.learning.gptw.greatplacetowork_learning.Models.RestResponseDTO;
import com.learning.gptw.greatplacetowork_learning.R;
import com.learning.gptw.greatplacetowork_learning.Services.LoginService;
import com.learning.gptw.greatplacetowork_learning.Utils.NetworkUtil;
import com.learning.gptw.greatplacetowork_learning.Utils.SharedPreferencesUtil;


public class LoginGreatPlaceToWork extends AppCompatActivity {

    /**
     * Logger tag
     */
    public static final String LOGGER_TAG = LoginGreatPlaceToWork.class.getSimpleName();

    /**
     * Shared Preferences
     */
    private SharedPreferences sharedPreferences;
    /**
     * Context reference
     */
    private Context context;

    /**
     * Username in the aplication , set as instance attribute because
     * is used in many methods in this class
     */
    private String username;
    /**
     * Username in the aplication , set as instance attribute because
     * is used in many methods in this class
     */
    private String password;
    /**
     * Login data
     */
    private Login sessionUserData;


    /**
     * Controls on screen reference
     */
    private TextInputEditText textInputEditTextUser;
    private TextInputEditText textInputEditTextPassword;


    /**
     * On Create actions
     *
     * @param savedInstanceState Android parameter needed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.i(LOGGER_TAG, "Login Init ...");

        this.context = this;
        this.sharedPreferences = SharedPreferencesUtil.getAppSharedPreferences(context);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_great_place_to_work);

        NetworkUtil.isConnectedMobile(this.context);
        NetworkUtil.isConnectedWifi(this.context);

        setCredentialIsExist();

    }


    /**
     * Set the credentials if exist in shared preferences
     */
    private void setCredentialIsExist() {

        textInputEditTextUser = findViewById(R.id.et_user);
        textInputEditTextPassword = findViewById(R.id.et_password);

        if (SharedPreferencesUtil.isUserCredentialsSharedPreferencesData(sharedPreferences)) {
            textInputEditTextUser.setText(SharedPreferencesUtil.getSharedPreferencesAttribute(sharedPreferences, SharedPreferencesUtil.USER_KEY));
            textInputEditTextPassword.setText(SharedPreferencesUtil.getSharedPreferencesAttribute(sharedPreferences, SharedPreferencesUtil.PASSWORD_KEY));
        }
    }

    /**
     * Do the login into the application
     *
     * @param view
     */
    public void loginEvent(View view) {

      final LoginService _loginService =  new LoginService(context);

        this.username = String.valueOf(textInputEditTextUser.getText());
        this.password = String.valueOf(textInputEditTextPassword.getText());
        _loginService.login(username, password, new VolleyCallback() {
            @Override
            public void onSuccess(RestResponseDTO result) {
                sessionUserData = _loginService.getSessionLoginData();
                validateLoginMessages(sessionUserData.getStatus());
            }
        });

    }

    /**
     * Validate the Login service response Status
     *
     * @param loginResponse the response of the volley request inside the service
     */
    private void validateLoginMessages(String loginResponse) {

        Log.d(LOGGER_TAG,"Login response...[" + String.valueOf(loginResponse) + "]");
        switch (loginResponse) {
            case Constants.OK_STATUS_RESPONSE:
                clearTextFields();
                redirectMain();
                break;
            case Constants.NOT_FOUND_STATUS_RESPONSE:
                textInputEditTextUser.setError("Usuario Incorrecto");
                break;
            case Constants.WRONG_PASSWORD_RESPONSE:
                textInputEditTextPassword.setError("Password Incorrecto");
                break;
            default:
                clearTextFields();
        }

    }

    /**
     * Redirecto to main page
     */
    private void redirectMain() {

        Intent intentRedirect = new Intent(context, MainActivity.class);
        intentRedirect.putExtra(IntentExtraKeyParamsConstants.ID_USER, sessionUserData.getIdUsuario());
        intentRedirect.putExtra(IntentExtraKeyParamsConstants.USERNAME, username);
        intentRedirect.putExtra(IntentExtraKeyParamsConstants.PASSWORD, password);
        startActivity(intentRedirect);

    }

    /**
     * Clear text fields on the screen
     */
    private void clearTextFields() {
        textInputEditTextUser.setError(null);
        textInputEditTextPassword.setError(null);
    }
}
