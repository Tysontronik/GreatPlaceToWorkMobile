package com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.Cursos;

/**
 * Francisco Javier Flores Morales
 * CursosFragment
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.Adapter.CursosAdapter;
import com.learning.gptw.greatplacetowork_learning.Constans.Constans;
import com.learning.gptw.greatplacetowork_learning.Models.ActividadModulo;
import com.learning.gptw.greatplacetowork_learning.Models.AsignacionesModulo;
import com.learning.gptw.greatplacetowork_learning.Models.AutoEvaluacionModulo;
import com.learning.gptw.greatplacetowork_learning.Models.BloquesModulo;
import com.learning.gptw.greatplacetowork_learning.Models.CursoModulo;
import com.learning.gptw.greatplacetowork_learning.Models.EvaluacionModulo;
import com.learning.gptw.greatplacetowork_learning.Models.ExtraMaterial;
import com.learning.gptw.greatplacetowork_learning.Models.InfografiaModulo;
import com.learning.gptw.greatplacetowork_learning.Models.MaterialModulo;
import com.learning.gptw.greatplacetowork_learning.Models.ModuloCursos;
import com.learning.gptw.greatplacetowork_learning.Models.PresencialModulo;
import com.learning.gptw.greatplacetowork_learning.Models.RecursosModulo;
import com.learning.gptw.greatplacetowork_learning.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.learning.gptw.greatplacetowork_learning.Login.LoginGreatPlaceToWork.TAG;

/**
 * * Franscico Javier Flores Morales
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CursosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CursosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CursosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentManager fm;
    // TODO: Rename and change types of parameters

    private Integer cursoId;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private SharedPreferences prefs;
    private ImageView img_back;
    private String idUserParam;
    int idMateriales;
    private JSONObject materialObject;

    private MaterialModulo materialModulo;

    private List<AsignacionesModulo> bloquesIniArrayList = new ArrayList<AsignacionesModulo>();
    private ListView lv;

    public CursosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CursosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CursosFragment newInstance(String param1, String param2) {
        CursosFragment fragment = new CursosFragment();
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


            idUserParam = getArguments().getString("idUser");


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_cursos, container, false);
        fm = getActivity().getSupportFragmentManager();
        img_back = rootView.findViewById(R.id.img_back);

        lv = rootView.findViewById(R.id.ListView_listadoCursos);
        prefs = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }


            }
        });
        loadId();


        return rootView;
    }


    public void loadId() {


        mRequestQueue = Volley.newRequestQueue(getContext());

        Bundle bundle = getArguments();




        ModuloCursos cursoInicio = (ModuloCursos) bundle.getSerializable("idCurso");
        cursoId = cursoInicio.curso.idCurso;
        Log.i("idcursosos", String.valueOf(cursoId));

        mStringRequest = new StringRequest(Request.Method.GET, Constans.servicesURL+Constans.cursosURL + cursoId, new Response.Listener<String>() {
            //  mStringRequest = new StringRequest(Request.Method.GET, "http://10.1.2.71:9191/api/curso/get?idCurso="+cursoId, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONObject event = null;
                try {
                    event = new JSONObject(response);

                    String status = event.getString("status");
                    if (status.equals("OK")) {

                        // SE OBTINE DATA
                        JSONObject data = event.getJSONObject("data");
                        CursoModulo cursoModulo = new CursoModulo();

                        cursoModulo.idCurso = data.getInt("idCurso");
                        cursoModulo.titulo = data.getString("titulo");
                        cursoModulo.subtitulo = data.getString("subtitulo");
                        cursoModulo.description = data.getString("description");
                        cursoModulo.isEditable = data.getBoolean("isEditable");
                        cursoModulo.bloques = data.getString("bloques");

                        Log.i("data1", String.valueOf(data));
                        Log.i("idCurso", String.valueOf(data.getInt("idCurso")));
                        Log.i("titulo", data.getString("titulo"));
                        Log.i("subtitulo", data.getString("subtitulo"));
                        Log.i("description", data.getString("description"));
                        Log.i("isEditable", String.valueOf(cursoModulo.isEditable = data.getBoolean("isEditable")));
                        Log.i("bloques", cursoModulo.bloques = data.getString("bloques"));


                        TextView tv_titles = getActivity().findViewById(R.id.tv_titles);
                        tv_titles.setText(data.getString("titulo"));
                        TextView tv_textInicio = getActivity().findViewById(R.id.tv_textInicio);
                        tv_textInicio.setText(data.getString("description"));




                        JSONArray bloquesArray = data.getJSONArray("bloques");


                        for (int b = 0; b < bloquesArray.length(); b++) {

                            final JSONObject bloquesObjectArray = bloquesArray.getJSONObject(b);
                            final BloquesModulo bloquesModulo = new BloquesModulo();
                            bloquesModulo.idBloqueCurso = bloquesObjectArray.getInt("idBloqueCurso");
                            bloquesModulo.titulo = bloquesObjectArray.getString("titulo");
                            bloquesModulo.ordered = bloquesObjectArray.getInt("ordered");
                            //.asignaciones = bloquesObjectArray.getString("asignaciones");
                            TextView tv_subtitleCur = getActivity().findViewById(R.id.tv_subtitleCur);
                            tv_subtitleCur.setText(data.getString("titulo"));
                            Log.i("idBloqueCurso", String.valueOf(bloquesModulo.idBloqueCurso = bloquesObjectArray.getInt("idBloqueCurso")));
                            Log.i("titulo", bloquesModulo.titulo = bloquesObjectArray.getString("titulo"));
                            Log.i("ordered", String.valueOf(bloquesModulo.ordered = bloquesObjectArray.getInt("ordered")));

                            JSONArray asignacionsArray = bloquesObjectArray.getJSONArray("asignaciones");


                            for (int c = 0; c < asignacionsArray.length(); c++) {
                                final JSONObject asignacionesObjectArray = asignacionsArray.getJSONObject(c);

                                final AsignacionesModulo asignacionesModulo = new AsignacionesModulo();

                                asignacionesModulo.idAsignacion = asignacionesObjectArray.getInt("idAsignacion");
                                asignacionesModulo.tipoAsignacion = asignacionesObjectArray.getInt("tipoAsignacion");
                                asignacionesModulo.order = asignacionesObjectArray.getInt("order");


                                bloquesModulo.asignaciones = asignacionesModulo;


                                switch (asignacionesModulo.order = asignacionesObjectArray.getInt("order")) {
                                    case 0:

                                        asignacionesModulo.material = asignacionesObjectArray.getString("material");

                                        break;
                                    case 1:

                                        asignacionesModulo.evaluacion = asignacionesObjectArray.getString("evaluacion");

                                        break;
                                    case 2:

                                        asignacionesModulo.actividad = asignacionesObjectArray.getString("actividad");

                                        break;
                                    case 3:

                                        asignacionesModulo.autoevaluacion = asignacionesObjectArray.getString("autoevaluacion");

                                        break;
                                    case 4:
                                        asignacionesModulo.extraMaterial = asignacionesObjectArray.getString("extraMaterial");

                                        break;
                                    case 5:

                                        asignacionesModulo.infografia = asignacionesObjectArray.getString("infografia");

                                        break;
                                    case 6:

                                        asignacionesModulo.presencial = asignacionesObjectArray.getString("presencial");

                                        break;

                                }


                                Log.i("idAsignacion", String.valueOf(asignacionesModulo.idAsignacion = asignacionesObjectArray.getInt("idAsignacion")));
                                Log.i("tipoAsignacion", String.valueOf(asignacionesModulo.tipoAsignacion = asignacionesObjectArray.getInt("tipoAsignacion")));
                                Log.i("order", String.valueOf(asignacionesModulo.order = asignacionesObjectArray.getInt("order")));
                                Log.i("material", asignacionesModulo.material = asignacionesObjectArray.getString("material"));
                                Log.i("evaluacion", asignacionesModulo.evaluacion = asignacionesObjectArray.getString("evaluacion"));
                                Log.i("actividad", asignacionesModulo.actividad = asignacionesObjectArray.getString("actividad"));
                                Log.i("autoevaluacion", asignacionesModulo.autoevaluacion = asignacionesObjectArray.getString("autoevaluacion"));
                                Log.i("extraMaterial", asignacionesModulo.extraMaterial = asignacionesObjectArray.getString("extraMaterial"));
                                Log.i("infografia", asignacionesModulo.infografia = asignacionesObjectArray.getString("infografia"));
                                Log.i("presencial", asignacionesModulo.presencial = asignacionesObjectArray.getString("presencial"));

                                if (asignacionesObjectArray.getString("material").toString().equals("null")) {

                                    Log.i("sin dato", "sin material");
                                } else {
                                    materialObject = asignacionesObjectArray.getJSONObject("material");
                                    materialModulo = new MaterialModulo();

                                    idMateriales = materialModulo.idMaterial = materialObject.getInt("idMaterial");
                                    Log.i("mmmmmm", String.valueOf(idMateriales));
                                    materialModulo.titulo = materialObject.getString("titulo");
                                    materialModulo.descripcion = materialObject.getString("descripcion");
                                    materialModulo.ruta = materialObject.getString("ruta");
                                    materialModulo.rutaTranscript = materialObject.getString("rutaTranscript");
                                    materialModulo.recursos = materialObject.getString("recursos");
                                    materialModulo.isEditable = materialObject.getBoolean("isEditable");
                                    materialModulo.duration = materialObject.getString("duration");


                                    asignacionesModulo.materialModulo = materialModulo;
                                    Log.i("idMaterial", String.valueOf(materialModulo.idMaterial = materialObject.getInt("idMaterial")));
                                    Log.i("titulo", materialModulo.titulo = materialObject.getString("titulo"));
                                    Log.i("descripcion", materialModulo.descripcion = materialObject.getString("descripcion"));
                                    Log.i("ruta", materialModulo.ruta = materialObject.getString("ruta"));
                                    Log.i("rutaTranscript", materialModulo.rutaTranscript = materialObject.getString("rutaTranscript"));
                                    Log.i("recursos", materialModulo.recursos = materialObject.getString("recursos"));
                                    Log.i("isEditable", String.valueOf(materialModulo.isEditable = materialObject.getBoolean("isEditable")));
                                    Log.i("duration", String.valueOf(materialModulo.duration = materialObject.getString("duration")));


                                    JSONArray recursosArray = materialObject.getJSONArray("recursos");

                                    for (int d = 0; d < recursosArray.length(); d++) {
                                        final JSONObject recursosObjectArray = recursosArray.getJSONObject(d);
                                        RecursosModulo recursosModulo = new RecursosModulo();
                                        recursosModulo.idRecurso = recursosObjectArray.getInt("idRecurso");
                                        recursosModulo.titulo = recursosObjectArray.getString("titulo");
                                        recursosModulo.ruta = recursosObjectArray.getString("ruta");

                                        Log.i("idRecurso", String.valueOf(recursosModulo.idRecurso = recursosObjectArray.getInt("idRecurso")));
                                        Log.i("titulo", recursosModulo.titulo = recursosObjectArray.getString("titulo"));
                                        Log.i("ruta", recursosModulo.ruta = recursosObjectArray.getString("ruta"));


                                    }
                                }

                                if (asignacionesObjectArray.getString("evaluacion").toString().equals("null")) {

                                    Log.i("sin dato", "sin dato evaluacion");
                                } else {
                                    JSONObject autoEvaluacionObject = asignacionesObjectArray.getJSONObject("evaluacion");

                                    EvaluacionModulo evaluacionModulo = new EvaluacionModulo();
                                    evaluacionModulo.idEvaluacion = autoEvaluacionObject.getInt("idEvaluacion");
                                    evaluacionModulo.titulo = autoEvaluacionObject.getString("titulo");
                                    // evaluacionModulo.preguntas = autoEvaluacionObject.getString("preguntas");
                                    evaluacionModulo.isEditable = autoEvaluacionObject.getBoolean("isEditable");
                                    evaluacionModulo.peso = autoEvaluacionObject.getString("peso");
                                    evaluacionModulo.duration = autoEvaluacionObject.getString("duration");

                                    asignacionesModulo.evaluacionModulo = evaluacionModulo;


                                    Log.i("idEvaluacion", String.valueOf(autoEvaluacionObject.getInt("idEvaluacion")));
                                    Log.i("titulo", autoEvaluacionObject.getString("titulo"));
                                    // Log.i("preguntas", String.valueOf(autoEvaluacionObject.getString("preguntas")));
                                    Log.i("isEditable", String.valueOf(autoEvaluacionObject.getBoolean("isEditable")));
                                    Log.i("peso", String.valueOf(autoEvaluacionObject.getString("peso")));
                                    Log.i("duration", String.valueOf(autoEvaluacionObject.getString("duration")));


                                }


                                if (asignacionesObjectArray.getString("actividad").toString().equals("null")) {

                                    Log.i("sin dato", "sin dato Actividad");
                                } else {
                                    JSONObject actividadObject = asignacionesObjectArray.getJSONObject("actividad");
                                    ActividadModulo actividadModulo = new ActividadModulo();
                                    actividadModulo.idActividad = actividadObject.getInt("idActividad");
                                    actividadModulo.titulo = actividadObject.getString("titulo");
                                    actividadModulo.descripcion = actividadObject.getString("descripcion");
                                    actividadModulo.peso = actividadObject.getString("peso");
                                    actividadModulo.duration = actividadObject.getString("duration");
                                    asignacionesModulo.actividadModulo = actividadModulo;

                                    Log.i("idActividad", String.valueOf(actividadObject.getInt("idActividad")));
                                    Log.i("titulo", actividadObject.getString("titulo"));
                                    Log.i("descripcion", actividadObject.getString("descripcion"));
                                    Log.i("peso", actividadObject.getString("peso"));
                                    Log.i("duration", actividadObject.getString("duration"));
                                    asignacionesModulo.actividadModulo = actividadModulo;

                                }
                                if (asignacionesObjectArray.getString("autoevaluacion").toString().equals("null")) {

                                    Log.i("sin dato", "sin autoevaluacion");
                                } else {
                                    JSONObject autoEvaluacionObject = asignacionesObjectArray.getJSONObject("autoevaluacion");
                                    AutoEvaluacionModulo autoEvaluacionModulo = new AutoEvaluacionModulo();

                                    autoEvaluacionModulo.idAutoEvaluacion = autoEvaluacionObject.getInt("idAutoEvaluacion");
                                    autoEvaluacionModulo.titulo = autoEvaluacionObject.getString("titulo");
                                    autoEvaluacionModulo.duration = autoEvaluacionObject.getString("duration");
                                    autoEvaluacionModulo.preguntas = autoEvaluacionObject.getString("preguntas");
                                    asignacionesModulo.autoEvaluacionModulo = autoEvaluacionModulo;
                                    Log.i("idAutoEvaluacion", String.valueOf(autoEvaluacionObject.getInt("idAutoEvaluacion")));
                                    Log.i("titulo", autoEvaluacionObject.getString("titulo"));
                                    Log.i("duration", autoEvaluacionObject.getString("duration"));
                                    Log.i("preguntas", autoEvaluacionObject.getString("preguntas"));


                                }


                                if (asignacionesObjectArray.getString("extraMaterial").toString().equals("null")) {

                                    Log.i("sin dato", "sin extraMaterial");
                                } else {
                                    JSONObject extramaterial = asignacionesObjectArray.getJSONObject("extraMaterial");
                                    ExtraMaterial extraMaterial = new ExtraMaterial();
                                    extraMaterial.idExtraMaterial = extramaterial.getInt("idExtraMaterial");
                                    extraMaterial.ruta = extramaterial.getString("ruta");
                                    extraMaterial.titulo = extramaterial.getString("titulo");
                                    Log.i("tituloExtramaterial", extraMaterial.titulo = extramaterial.getString("titulo"));
                                    extraMaterial.duration = extramaterial.getInt("duration");

                                    asignacionesModulo.extraMateriales = extraMaterial;

                                }

                                if (asignacionesObjectArray.getString("infografia").toString().equals("null")) {

                                    Log.i("sin dato", "sin infografia");
                                } else {
                                    JSONObject infografiaObject = asignacionesObjectArray.getJSONObject("infografia");

                                    InfografiaModulo infografiaModulo = new InfografiaModulo();

                                    infografiaModulo.idInfografia = infografiaObject.getInt("idInfografia");
                                    infografiaModulo.titulo = infografiaObject.getString("titulo");
                                    infografiaModulo.duration = infografiaObject.getString("duration");
                                    asignacionesModulo.infografiaModulo = infografiaModulo;
                                    Log.i("idInfografia", String.valueOf(infografiaObject.getInt("idInfografia")));
                                    Log.i("titulo", infografiaObject.getString("titulo"));
                                    Log.i("duration", infografiaObject.getString("duration"));


                                }

                                if (asignacionesObjectArray.getString("presencial").toString().equals("null")) {

                                    Log.i("sin dato", "presencial");
                                } else {
                                    JSONObject presencialObject = asignacionesObjectArray.getJSONObject("presencial");

                                    PresencialModulo presencialModulo = new PresencialModulo();
                                    presencialModulo.idPresencial = presencialObject.getInt("idPresencial");
                                    presencialModulo.titulo = presencialObject.getString("titulo");
                                    presencialModulo.ruta = presencialObject.getString("ruta");
                                    presencialModulo.peso = presencialObject.getString("peso");
                                    presencialModulo.duration = presencialObject.getString("duration");
                                    asignacionesModulo.presenciales = presencialModulo;
                                }
                                bloquesIniArrayList.add(asignacionesModulo);

                            }


                        }

                        Log.i("data", String.valueOf(data));
                    } else {
                        Toast.makeText(getActivity(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen


                    }
                    CursosAdapter adapterCursos = new CursosAdapter(getContext(), R.layout.cursositem, bloquesIniArrayList);
                    lv.setAdapter(adapterCursos);


                    setListViewHeight(lv, R.layout.cursositem, bloquesIniArrayList.size());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);

    }


    public void setListViewHeight(ListView listView, int resource, int itemsCount) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(resource, null);
        layoutView(linearLayout);

        int contentHeight = linearLayout.getHeight();

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = contentHeight * itemsCount;
        listView.setLayoutParams(layoutParams);
    }

    public void layoutView(View view) {

        view.setDrawingCacheEnabled(true);
        int wrapContentSpec =
                View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.measure(wrapContentSpec, wrapContentSpec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
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
