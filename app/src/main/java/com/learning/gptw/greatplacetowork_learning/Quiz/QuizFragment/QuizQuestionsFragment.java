package com.learning.gptw.greatplacetowork_learning.Quiz.QuizFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.RelativeDateTimeFormatter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.Adapter.RespuestaAdapter;
import com.learning.gptw.greatplacetowork_learning.Constans.Constans;
import com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio.MainActivity;
import com.learning.gptw.greatplacetowork_learning.Models.EvaluacionModulo;
import com.learning.gptw.greatplacetowork_learning.Models.PreguntasModulo;
import com.learning.gptw.greatplacetowork_learning.Models.RespuestasModulo;
import com.learning.gptw.greatplacetowork_learning.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link QuizQuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizQuestionsFragment extends Fragment {
    public static final String TAGG = QuizQuestionsFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int idPreguntas;
    private TextView tv_questions;
    private SharedPreferences prefs;
    private boolean loggedIn = false;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private LinearLayout lt_login;
    private int idPreguntasEvaluacion;
    private int idPreguntasEvaluaciones;
    private String urlLoadQuiztitle;
    private TextView tv_textInicios, tv_munpreg1, tv_preg_num;
    private ImageView img_NextQuestion, img_backQuestion;
    private List<PreguntasModulo> evaluacionModuloArrayList = new ArrayList<PreguntasModulo>();
    private List<EvaluacionModulo> evaluacionModuloArrayListes = new ArrayList<EvaluacionModulo>();
    private List<RespuestasModulo> respuestasModuloArrayList = new ArrayList<RespuestasModulo>();
    private int tamanio2, tamanio1, idEvaluaciones,tamanio;
    private  String resp;
    private RadioButton checkedRadioButton,rb;
    private List<RespuestaAdapter> stList;
    private      RadioGroup ll;
    //   private OnFragmentInteractionListener mListener;
    private TextView tv_num_preg;

   private Boolean respuestacheck;

    private ListView ListView_listadoQuiz;
    private int d = 0;
    private int m = 1;

    public QuizQuestionsFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizQuestionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizQuestionsFragment newInstance(String param1, String param2) {
        QuizQuestionsFragment fragment = new QuizQuestionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


            Bundle bundle = getArguments();
            idPreguntas = bundle.getInt("idPregunta");
            tamanio1 = bundle.getInt("numpre");
            idEvaluaciones = bundle.getInt("idEvaluacion");
            Log.i("idpreguntasEva", String.valueOf(idPreguntas));
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_quiz_questions, container, false);
        loadQuiztitle();

        prefs = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.apply();

        tv_munpreg1 = rootView.findViewById(R.id.tv_munpreg1);
        tv_preg_num = rootView.findViewById(R.id.tv_preg_num);
        img_NextQuestion = rootView.findViewById(R.id.img_NextQuestions);
        img_backQuestion = rootView.findViewById(R.id.img_backQuestion);
        ll =  rootView.findViewById(R.id.radiogroup);
        ImageView img_back = rootView.findViewById(R.id.img_backFq);

        ll.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        int selectedId = ll .getCheckedRadioButtonId();



        //Student contact=(Student)group.getTag();

        //contact.setSelectedRadioButtonId(radioButtonID);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    QuizQuestionsFragment.super.getActivity().onBackPressed();
                } else {
                    QuizQuestionsFragment.super.getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
        return rootView;


    }


    private void loadQuiztitle() {

        urlLoadQuiztitle = Constans.servicesURL + Constans.evaluacionURL + idEvaluaciones;


        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, urlLoadQuiztitle, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                JSONObject event = null;
                try {
                    event = new JSONObject(response);
                    JSONObject data = event.getJSONObject("data");
//                    JSONObject rols = data.getJSONObject("rol");

                    prefs = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
                    final EvaluacionModulo evaluacionModulo = new EvaluacionModulo();


                    evaluacionModulo.idEvaluacion = data.getInt("idEvaluacion");
                    evaluacionModulo.titulo = data.getString("titulo");
                    //  evaluacionModulo.preguntas = data.getString("preguntas");
                    evaluacionModulo.isEditable = data.getBoolean("isEditable");
                    evaluacionModulo.peso = data.getString("peso");
                    evaluacionModulo.duration = data.getString("duration");


                    Log.i("idEvaluacion", String.valueOf(data.getInt("idEvaluacion")));
                    Log.i("titulo", data.getString("titulo"));
                    Log.i("preguntas", String.valueOf(data.getString("preguntas")));
                    Log.i("isEditable", String.valueOf(data.getBoolean("isEditable")));
                    Log.i("peso", String.valueOf(data.getString("peso")));
                    Log.i("duration", String.valueOf(data.getString("duration")));


                    Log.i("Evaluaciondata", String.valueOf(data));

                    evaluacionModuloArrayList.clear();


                    final JSONArray preguuntasarray = data.getJSONArray("preguntas");

                    leerPregunta(preguuntasarray, 0, m);

                    img_NextQuestion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            d++;




                            boolean estado = checkedRadioButton.isChecked();
                            if (m <= tamanio1) {

                                m++;
                                tv_preg_num.setText(String.valueOf(d + 1));

                                img_backQuestion.setVisibility(View.VISIBLE);


                            }

                            if (m == tamanio1) {

                                Log.i("fin", "fin");
                                img_NextQuestion.setVisibility(View.INVISIBLE);


                            }
                            try {

                                leerPregunta(preguuntasarray, d, m);
                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }
                    });

                    img_backQuestion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            d--;


                            boolean estado = checkedRadioButton.isChecked();
                            if (m <= tamanio1) {

                                m--;

                                img_NextQuestion.setVisibility(View.VISIBLE);
                                tv_preg_num.setText(String.valueOf(d + 1));


                            }
                            if (m == 1) {
                                Log.i("fin2", "fin2");
                                img_backQuestion.setVisibility(View.INVISIBLE);

                            }
                            try {

                                leerPregunta(preguuntasarray, d, m);
                            } catch (JSONException e) {

                                e.printStackTrace();

                            }
                        }
                    });

                    tv_munpreg1.setText(String.valueOf(tamanio1));

                    Log.i("tamañopreguntasTa", String.valueOf(tamanio));
                    //     ListView_listadoQuiz = getActivity().findViewById(R.id.ListView_listadoQuizPr);
                    //  RespuestaAdapter respuestaAdapter = new RespuestaAdapter(getContext(), R.layout.respuestas_adapter, respuestasModuloArrayList);
                    //    ListView_listadoQuiz.setAdapter(respuestaAdapter);


            //        setListViewHeight(ListView_listadoQuiz, R.layout.respuestas_adapter, respuestasModuloArrayList.size());


                } catch (JSONException e) {
                    e.printStackTrace   ();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAGG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }

    private void leerPregunta(JSONArray preguuntasarray, int index, int m) throws JSONException {
        JSONObject recursosObjectArray = preguuntasarray.getJSONObject(index);


        PreguntasModulo preguntasModulo1 = new PreguntasModulo();

        preguntasModulo1.idPregunta = recursosObjectArray.getInt("idPregunta");

        preguntasModulo1.pregunta = recursosObjectArray.getString("pregunta");
        tv_textInicios = getActivity().findViewById(R.id.tv_textInicios);
        tv_textInicios.setText(preguntasModulo1.pregunta = recursosObjectArray.getString("pregunta"));

        Log.i("idPregunta", String.valueOf(preguntasModulo1.idPregunta = recursosObjectArray.getInt("idPregunta")));


        //valuacionModulo.preguntas.respuestas = respuestasModulo;
        evaluacionModuloArrayList.add(preguntasModulo1);

        respuestasModuloArrayList.clear();


        JSONArray respuestasarray = recursosObjectArray.getJSONArray("respuestas");
        ll.removeAllViews();

        for (int e = 0; e < respuestasarray.length(); e++) {
            final JSONObject respuestasamodulos = respuestasarray.getJSONObject(e);

            RespuestasModulo respuestasModulo = new RespuestasModulo();

            respuestasModulo.idResponder = respuestasamodulos.getInt("idResponder");
            resp = respuestasModulo.respuesta = respuestasamodulos.getString("respuesta");
            respuestasModulo.correcto = respuestasamodulos.getBoolean("correcto");

            Log.i("respuestasbien", respuestasModulo.respuesta = respuestasamodulos.getString("respuesta"));

            respuestasModuloArrayList.add(respuestasModulo);
            tamanio2 = respuestasarray.length();
            Log.i("leng", String.valueOf(tamanio2));

            checkedRadioButton = new RadioButton(getContext());
            checkedRadioButton.setText(resp);
            ll.addView(checkedRadioButton);
        }


        //checkedRadioButton.setId(e);
        ll.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                // find the radio button by returned id
     checkedRadioButton = getActivity().findViewById(checkedId);
                String text = checkedRadioButton.getText().toString();
checkedRadioButton.isChecked();
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();



            }
        });



        /*    ListView_listadoQuiz = getActivity().findViewById(R.id.ListView_listadoQuizPr);
            RespuestaAdapter respuestaAdapter = new RespuestaAdapter(getContext(), R.layout.respuestas_adapter, respuestasModuloArrayList);
            ListView_listadoQuiz.setAdapter(respuestaAdapter);*/
        //   respuestasModuloArrayList.clear();

    }


}

    // TODO: Rename method, update argument and hook method into UI event




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


