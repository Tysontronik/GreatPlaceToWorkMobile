package com.learning.gptw.greatplacetowork_learning.Login;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.learning.gptw.greatplacetowork_learning.R;

public class OlvidoContrasenaActivity extends AppCompatActivity {


    private Button btnAcptarOlvido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_olvido_contrasena);

        btnAcptarOlvido = findViewById(R.id.btn_olvido);
        btnAcptarOlvido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OlvidoContrasenaActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.pop_up_olvido, null);

                // Specify alert dialog is not cancelable/not ignorable
                builder.setCancelable(false);

                // Set the custom layout as alert dialog view
                builder.setView(dialogView);

                // Get the custom alert dialog view widgets reference
                Button btn_positive = dialogView.findViewById(R.id.dialog_positive_btn);


                // Create the alert dialog
                final AlertDialog dialog = builder.create();

                // Set positive/yes button click listener
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss the alert dialog
                        dialog.cancel();
                        // Set the main layout background color red

                        Toast.makeText(getApplication(),
                                "Background color changed", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });


    }

}
