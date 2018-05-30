package com.learning.gptw.greatplacetowork_learning.Quiz.QuizFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.learning.gptw.greatplacetowork_learning.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {
    public static final String TAGF = QuizFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public Button btn_iniciarQuestions;
    FragmentManager fm;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPreferences prefs;
    private boolean loggedIn = false;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private LinearLayout lt_login;
    private int idPreguntasEvaluacion;
    private int idPreguntasEvaluaciones, tamanios, tam;
    private String urlLoadQuiztitle;
    private TextView tv_textInicios;

    private List<EvaluacionModulo> evaluacionModuloArrayList = new ArrayList<EvaluacionModulo>();
    private int tamanio;
    private OnFragmentInteractionListener mListener;
    private TextView tv_num_preg;

    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            idPreguntasEvaluacion = getArguments().getInt("idPregunta");
            idPreguntasEvaluaciones = getArguments().getInt("idEvaluacion");
            tam = getArguments().getInt("tam");
            Log.i("ididi", String.valueOf(idPreguntasEvaluacion));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        fm = getActivity().getSupportFragmentManager();
        prefs = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        tv_textInicios = rootView.findViewById(R.id.tv_textInicios);
        tv_num_preg = rootView.findViewById(R.id.tv_num_preg);

        loadQuiztitle();
        ImageView img_back = rootView.findViewById(R.id.img_backQuiz);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    QuizFragment.super.getActivity().onBackPressed();
                } else {
                    QuizFragment.super.getActivity(). getSupportFragmentManager().popBackStack();
                }






            }
        });

        btn_iniciarQuestions = rootView.findViewById(R.id.btn_iniciarQuestions);
        btn_iniciarQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // fm.beginTransaction().replace(R.id.content_frame, new QuizQuestionsFragment()).commit();
                QuizQuestionsFragment cursosFragment = new QuizQuestionsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idPregunta", idPreguntasEvaluacion);
                bundle.putInt("numpre", tamanio);
                bundle.putInt("idEvaluacion", idPreguntasEvaluaciones);


                cursosFragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, cursosFragment).addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return rootView;


    }


    private void loadQuiztitle() {

        urlLoadQuiztitle = UrlConstants.servicesURL + UrlConstants.evaluacionURL + idPreguntasEvaluaciones;


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


                    final EvaluacionModulo evaluacionModulo = new EvaluacionModulo();
                    PreguntasModulo preguntasModulo = new PreguntasModulo();

                    evaluacionModulo.idEvaluacion = data.getInt("idEvaluacion");
                    evaluacionModulo.titulo = data.getString("titulo");
                    //  evaluacionModulo.preguntas = data.getString("preguntas");
                    evaluacionModulo.isEditable = data.getBoolean("isEditable");
                    evaluacionModulo.peso = data.getString("peso");
                    evaluacionModulo.duration = data.getString("duration");

                    evaluacionModulo.preguntas = preguntasModulo;

                    tv_textInicios.setText(evaluacionModulo.titulo = data.getString("titulo"));


                    Log.i("idEvaluacion", String.valueOf(data.getInt("idEvaluacion")));
                    Log.i("titulo", data.getString("titulo"));
                    Log.i("preguntas", String.valueOf(data.getString("preguntas")));
                    Log.i("isEditable", String.valueOf(data.getBoolean("isEditable")));
                    Log.i("peso", String.valueOf(data.getString("peso")));
                    Log.i("duration", String.valueOf(data.getString("duration")));


                    Log.i("Evaluaciondata", String.valueOf(data));

                    evaluacionModuloArrayList.clear();
                    JSONArray preguuntasarray = data.getJSONArray("preguntas");
                    for (int d = 0; d < preguuntasarray.length(); d++) {
                        final JSONObject recursosObjectArray = preguuntasarray.getJSONObject(d);

                        evaluacionModulo.preguntas.idPregunta = recursosObjectArray.getInt("idPregunta");


                        Log.i("idPregunta", String.valueOf(evaluacionModulo.preguntas.idPregunta = recursosObjectArray.getInt("idPregunta")));

                        evaluacionModuloArrayList.add(evaluacionModulo);

                    }


                    tamanio = evaluacionModuloArrayList.size();
                    tv_num_preg.setText(String.valueOf(tamanio));

                    Log.i("tamañopreguntasPruebas", String.valueOf(tamanio));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAGF, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
