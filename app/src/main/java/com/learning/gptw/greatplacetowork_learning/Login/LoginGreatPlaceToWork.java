package com.learning.gptw.greatplacetowork_learning.Login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.learning.gptw.greatplacetowork_learning.Constans.Constans;
import com.learning.gptw.greatplacetowork_learning.DBHelper.DBHelper;
import com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio.MainActivity;
import com.learning.gptw.greatplacetowork_learning.Models.Login;
import com.learning.gptw.greatplacetowork_learning.R;
import com.learning.gptw.greatplacetowork_learning.Services.LoginService;
import com.learning.gptw.greatplacetowork_learning.Utils.NetworkUtil;
import com.learning.gptw.greatplacetowork_learning.Utils.UserPrefsUtil;
import com.learning.gptw.greatplacetowork_learning.Utils.ValidatorUtil;

public class LoginGreatPlaceToWork extends AppCompatActivity implements View.OnClickListener {

    /**
     * Logger tag
     */
    public static final String TAG = LoginGreatPlaceToWork.class.getSimpleName();

    /**
     * Login View components
     */
    public Button btn_login;
    public TextView tv_forgotten_password;
    public TextInputEditText et_user;
    public TextInputEditText et_password;

    private LinearLayout lt_login;
    private SharedPreferences sharedPreferences;

    /**
     * Database manage
     */
    private DBHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabaseObj;
    private Cursor cursor;

    /**
     * Services
     */
    private LoginService _loLoginService = new LoginService();
    private Login successLogin;

    /**
     * Is logged flag
     */
    private boolean loggedIn = false;
    /**
     * Current Context
     */
    private Context context;


    /**
     * On create recover user and password data from shared preferences, check the network status,
     * attach events to view controls and set the device screen position an look and feel
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        context = this;

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_great_place_to_work);

        NetworkUtil.isConnectedMobile(this);
        NetworkUtil.isConnectedWifi(this);
        NetworkUtil.isOnline(this);

        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        setControlsActionListeners();
        setCredentialIsExist();
    }

    /**
     * Set the credentials to login form from sharedPreferences
     */
    private void setCredentialIsExist() {

        String user = UserPrefsUtil.getUserSharedPreferencesAttribute(sharedPreferences, UserPrefsUtil.USER_KEY);
        String password = UserPrefsUtil.getUserSharedPreferencesAttribute(sharedPreferences, UserPrefsUtil.PASSWORD_KEY);

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
            et_user.setText(user);
            et_password.setText(password);
        }
    }

    /**
     * Set action events  to view controls
     */
    private void setControlsActionListeners() {

        lt_login = findViewById(R.id.lt_login);
        lt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_password.setError(null);
                et_user.setError(null);
            }
        });


        et_user = findViewById(R.id.et_user);
        et_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_user.setError(null);
            }
        });


        et_password = findViewById(R.id.et_password);
        et_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_password.setError(null);
            }
        });


        tv_forgotten_password = findViewById(R.id.tv_forgotten_password);
        tv_forgotten_password.setOnClickListener(this);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

    }


    /**
     * In the app resume
     */
    @Override
    protected void onResume() {
        super.onResume();

        if (loggedIn) {
            sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
            Intent intent = new Intent(LoginGreatPlaceToWork.this, MainActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Login
     */
    private void logins() {

        final String email = et_user.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        String status = _loLoginService.login(email, password, context, successLogin);

        et_user.setError(null);
        et_password.setError(null);


        if (status.equals(Constans.OK_STATUS_RESPONSE)) {
            goToMain();
        }

        if (status.equals(Constans.NOT_FOUND_STATUS_RESPONSE)) {
            et_user.setError("Usuario Incorrecto");
        }

        if (status.equals("WRONG_PASSWORD")) {
            et_password.setError("Contraseña Incorrecta");
        }
    }


    /**
     * Save the authentication data in shared preferences
     *
     * @param user     user who login
     * @param Password password of the user
     */
    private void saveOnPreferences(String user, String Password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user);
        editor.putString("Password", Password);
        editor.apply();

    }


    private Boolean login(String user, String password) {
        Boolean responseFlag = Boolean.TRUE;
        et_user.setError(null);
        et_password.setError(null);

        if (!ValidatorUtil.isValidUser(user)) {
            et_user.setError(getText(R.string.campo_vacio));
            responseFlag = Boolean.FALSE;
        } else if (!ValidatorUtil.isValidPassword(password)) {
            et_password.setError(getText(R.string.campo_vacio));
            responseFlag = Boolean.FALSE;
        }

        return responseFlag;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String user = et_user.getText().toString();
                String Password = et_password.getText().toString();

                if (login(user, Password)) {
                    // goToMain();
                    logins();
                    //dataUser();
                    saveOnPreferences(user, Password);
                }
                break;

            case R.id.tv_forgotten_password:
                Intent a = new Intent(LoginGreatPlaceToWork.this, OlvidoContrasenaActivity.class);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(a);
                break;
        }
    }


    public void goToMain() {


        DBHelper admin = new DBHelper(this,

                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String user = et_user.getText().toString();
        String Password = et_password.getText().toString();


        ContentValues registro = new ContentValues();

        registro.put("dni", 0);
        registro.put("nombre", user);
        registro.put("ciudad", Password);


        // los inserto en la base de datos
        bd.insert("user", null, registro);

        bd.close();

        // ponemos los campos a vacío para insertar el siguiente user

        et_user.setText("");
        et_password.setText("");

        Toast.makeText(this, "Datos del user cargados", Toast.LENGTH_SHORT).show();
        Intent a = new Intent(LoginGreatPlaceToWork.this, MainActivity.class);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        a.putExtra("idUser", this.successLogin.getIdUsuario());

        startActivity(a);
    }


}
