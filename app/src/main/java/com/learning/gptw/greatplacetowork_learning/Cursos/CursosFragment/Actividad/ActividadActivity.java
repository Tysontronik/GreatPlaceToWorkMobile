
/**
 * Franacisco Javier Flores Morales
 * ActividadActivity
 */


package com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.Actividad;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.learning.gptw.greatplacetowork_learning.R;

public class ActividadActivity extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();
    private FABToolbarLayout layout;
    private View one, two, three, four;
    private View fab;
    private SharedPreferences prefs;
    private int idactividad,idUserParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_actividad);
        prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        idactividad = getIntent().getExtras().getInt("idActividad");
        idUserParam = getIntent().getExtras().getInt("idUser");
        ActividadFragment fragment = new ActividadFragment();
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.add(R.id.content_frame, fragment).commit();

        Bundle data1 = new Bundle();
        data1.putSerializable("idActividad", (idactividad));
        fragment.setArguments(data1);



    }



}
