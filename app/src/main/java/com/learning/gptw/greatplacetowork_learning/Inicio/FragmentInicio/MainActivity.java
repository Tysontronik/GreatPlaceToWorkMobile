package com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio;

/*
* Francisco Javier Flores Morales
* francisco.flores@code-ing.com
* Main
* */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.video.VideoActivity;
import com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.video.VideoFragment;
import com.learning.gptw.greatplacetowork_learning.Login.LoginGreatPlaceToWork;
import com.learning.gptw.greatplacetowork_learning.Quiz.Quiz;
import com.learning.gptw.greatplacetowork_learning.Quiz.QuizViewCardFragment.QuizCardctivity;
import com.learning.gptw.greatplacetowork_learning.R;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    FragmentManager fm = getSupportFragmentManager();
    private FABToolbarLayout layout;
    private View one, two, three, four;
    private View fab;
    private SharedPreferences prefs;
    private int idUsers;
    public static final String TAG2 = MainActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);


        envioParam();


        layout = findViewById(R.id.fabtoolbar);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);


        fab = findViewById(R.id.fabtoolbar_fab);


        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.show();
            }
        });


    }

    private void envioParam() {
        idUsers = getIntent().getExtras().getInt("idUser");

        Log.i("idUserMain", String.valueOf(idUsers));

//Traigo el fragment
        InicioFragmentGreatPlaceToWork fragment1 = new InicioFragmentGreatPlaceToWork();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragment1).commit();

        Bundle data = new Bundle();
        data.putString("idUser", String.valueOf(idUsers));
        fragment1.setArguments(data);
    }

    @Override
    public void onBackPressed() {
        // do nothing.
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Element clicked", Toast.LENGTH_SHORT).show();
        layout.hide();


        switch (v.getId()) {
            case R.id.one:
                //  fm.beginTransaction().replace(R.id.content_frame, new QuizFragment()).commit();
                Intent q = new Intent(MainActivity.this, Quiz.class);
                startActivity(q);

                break;

            case R.id.two:
                fm.beginTransaction().replace(R.id.content_frame, new VideoFragment()).commit();
                break;
            case R.id.three:

                // fm.beginTransaction().replace(R.id.content_frame, new VideoFragment()).commit();
                //fm.beginTransaction().replace(R.id.content_frame, new QuizFragment()).commit();
                Intent c = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(c);


                break;

            case R.id.four:

                // fm.beginTransaction().replace(R.id.content_frame, new VideoFragment()).commit();
                //fm.beginTransaction().replace(R.id.content_frame, new QuizFragment()).commit();
                Intent b = new Intent(MainActivity.this, QuizCardctivity.class);
                startActivity(b);


                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Forget_Logout:
                item.setChecked(true);
                removeSharedPreferences();
                logOut();
                return true;
            case R.id.logout:
                item.setChecked(true);
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void logOut() {
        Intent a = new Intent(MainActivity.this, LoginGreatPlaceToWork.class);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(a);

    }


    public void removeSharedPreferences() {

        prefs.edit().clear().apply();

    }
}
