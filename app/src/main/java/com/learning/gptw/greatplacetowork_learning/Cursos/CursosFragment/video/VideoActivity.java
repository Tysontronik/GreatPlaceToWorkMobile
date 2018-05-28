package com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.video;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.Cursos.CursosFragment;
import com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio.InicioFragmentGreatPlaceToWork;
import com.learning.gptw.greatplacetowork_learning.Login.LoginGreatPlaceToWork;
import com.learning.gptw.greatplacetowork_learning.R;

public class VideoActivity extends AppCompatActivity  implements View.OnClickListener {
    FragmentManager fm = getSupportFragmentManager();
    private FABToolbarLayout layout;
    private View one, two, three, four;
    private View fab;
    private SharedPreferences prefs;
    private int idMaterials,idExtraMateriales,idEvaluaciones;
    private  int idUserParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//recibir valores de un fragment

        idMaterials = getIntent().getExtras().getInt("idMaterial");
        idExtraMateriales = getIntent().getExtras().getInt("idExtraMaterial");
        idUserParam = getIntent().getExtras().getInt("idUser");
        idEvaluaciones = getIntent().getExtras().getInt("idEvaluacion");
        Log.i("idMateriales", String.valueOf(idMaterials));

        // Log.i("valores",name);


        VideoFragment fragment1 = new VideoFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragment1).commit();



        Bundle data = new Bundle();
        data.putSerializable("idMaterial", (idMaterials));
        fragment1.setArguments(data);


        layout = findViewById(R.id.fabtoolbarv);
        one = findViewById(R.id.onev);
        two = findViewById(R.id.twov);
        three = findViewById(R.id.threev);
        four = findViewById(R.id.fourv);


        fab = findViewById(R.id.fabtoolbar_fabv);

        //SET DATA TO TEXTVIEWS


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



    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Element clicked", Toast.LENGTH_SHORT).show();
        layout.hide();


        switch (v.getId()) {
            case R.id.one:
                fm.beginTransaction().replace(R.id.content_frame, new InicioFragmentGreatPlaceToWork()).commit();

                break;

            case R.id.two:
                fm.beginTransaction().replace(R.id.content_frame, new CursosFragment()).commit();


                break;
            case R.id.three:
                fm.beginTransaction().replace(R.id.content_frame, new VideoFragment()).commit();


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
        Intent a = new Intent(VideoActivity.this, LoginGreatPlaceToWork.class);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(a);

    }


    public void removeSharedPreferences() {

        prefs.edit().clear().apply();

    }
}
