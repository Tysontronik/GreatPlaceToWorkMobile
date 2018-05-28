package com.learning.gptw.greatplacetowork_learning.Adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio.MainActivity;
import com.learning.gptw.greatplacetowork_learning.R;
import com.learning.gptw.greatplacetowork_learning.Splash.SplashGreatPlaceToWork;

public class IsOnLineNotFound extends AppCompatActivity {
    public Button btn_conected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_on_line_not_found);
        btn_conected =  findViewById(R.id.btn_conected);

    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }
}
