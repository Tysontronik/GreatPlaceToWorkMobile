package com.learning.gptw.greatplacetowork_learning.Quiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.Constans.UrlConstants;
import com.learning.gptw.greatplacetowork_learning.Models.EvaluacionModulo;
import com.learning.gptw.greatplacetowork_learning.Models.PreguntasModulo;
import com.learning.gptw.greatplacetowork_learning.Quiz.QuizFragment.QuizFragment;
import com.learning.gptw.greatplacetowork_learning.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {
    public static final String TAGQ = Quiz.class.getSimpleName();
    private SharedPreferences prefs;
    private boolean loggedIn = false;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private LinearLayout lt_login;
    private int idPreguntasEvaluacion;
    private String urlLoadQuiztitle;
    private TextView tv_textInicios;
    private List<EvaluacionModulo> evaluacionModuloArrayList = new ArrayList<EvaluacionModulo>();
    private int tamanio;
    private QuizFragment.OnFragmentInteractionListener mListener;
    private int tam, idPreg, idEvaluaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        idEvaluaciones = getIntent().getExtras().getInt("idEvaluacion");
        idPreg = getIntent().getExtras().getInt("idPregunta");
        QuizFragment fragment1 = new QuizFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragment1).commit();

        Bundle data = new Bundle();

        //  int id=idPreg;
        data.putInt("idPregunta", (idPreg));
        data.putInt("idEvaluacion", idEvaluaciones);
        data.putInt("tam", tamanio);

        Log.i("idid", String.valueOf(idPreg));
        fragment1.setArguments(data);

        loadQuiztitle();
    }

    private void loadQuiztitle() {

        urlLoadQuiztitle = UrlConstants.servicesURL + UrlConstants.evaluacionURL + idEvaluaciones;


        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, urlLoadQuiztitle, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                JSONObject event = null;
                try {
                    event = new JSONObject(response);
                    JSONObject data = event.getJSONObject("data");


                    final EvaluacionModulo evaluacionModulo = new EvaluacionModulo();
                    PreguntasModulo preguntasModulos = new PreguntasModulo();

                    evaluacionModulo.idEvaluacion = data.getInt("idEvaluacion");
                    evaluacionModulo.titulo = data.getString("titulo");
                    //  evaluacionModulo.preguntas = data.getString("preguntas");
                    evaluacionModulo.isEditable = data.getBoolean("isEditable");
                    evaluacionModulo.peso = data.getString("peso");
                    evaluacionModulo.duration = data.getString("duration");

                    evaluacionModulo.preguntas = preguntasModulos;


                    Log.i("idEvaluacion", String.valueOf(data.getInt("idEvaluacion")));
                    Log.i("titulo", data.getString("titulo"));
                    Log.i("preguntas", String.valueOf(data.getString("preguntas")));
                    Log.i("isEditable", String.valueOf(data.getBoolean("isEditable")));
                    Log.i("peso", String.valueOf(data.getString("peso")));
                    Log.i("duration", String.valueOf(data.getString("duration")));


                    Log.i("Evaluaciondata", String.valueOf(data));

                    JSONArray preguuntasarray = data.getJSONArray("preguntas");
                    for (int d = 0; d < preguuntasarray.length(); d++) {
                        final JSONObject recursosObjectArray = preguuntasarray.getJSONObject(d);

                        idPreguntasEvaluacion = evaluacionModulo.preguntas.idPregunta = recursosObjectArray.getInt("idPregunta");


                        Log.i("idPreguntasEvaluacion", String.valueOf(evaluacionModulo.preguntas.idPregunta = recursosObjectArray.getInt("idPregunta")));

                        evaluacionModuloArrayList.add(evaluacionModulo);

                    }


                    tamanio = evaluacionModuloArrayList.size();


                    Log.i("tamañopreguntasQuizEva", String.valueOf(tamanio));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAGQ, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
