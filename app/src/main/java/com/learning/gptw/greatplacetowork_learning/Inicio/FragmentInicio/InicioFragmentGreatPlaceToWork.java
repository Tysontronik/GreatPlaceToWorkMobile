package com.learning.gptw.greatplacetowork_learning.Inicio.FragmentInicio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.learning.gptw.greatplacetowork_learning.Adapter.IsOnLineNotFound;
import com.learning.gptw.greatplacetowork_learning.Adapter.SemanaAdapter;
import com.learning.gptw.greatplacetowork_learning.Constans.Constans;
import com.learning.gptw.greatplacetowork_learning.Models.ActividadModulo;
import com.learning.gptw.greatplacetowork_learning.Models.AsignacionesModulo;
import com.learning.gptw.greatplacetowork_learning.Models.AutoEvaluacionModulo;
import com.learning.gptw.greatplacetowork_learning.Models.BloquesModulo;
import com.learning.gptw.greatplacetowork_learning.Models.CursoModulo;
import com.learning.gptw.greatplacetowork_learning.Models.EvaluacionModulo;
import com.learning.gptw.greatplacetowork_learning.Models.InfografiaModulo;
import com.learning.gptw.greatplacetowork_learning.Models.MaterialModulo;
import com.learning.gptw.greatplacetowork_learning.Models.Modulo;
import com.learning.gptw.greatplacetowork_learning.Models.ModuloCursos;
import com.learning.gptw.greatplacetowork_learning.Models.ModuloInicio;
import com.learning.gptw.greatplacetowork_learning.Models.RecursosModulo;
import com.learning.gptw.greatplacetowork_learning.R;
import com.learning.gptw.greatplacetowork_learning.Utils.NetworkUtil;
import com.learning.gptw.greatplacetowork_learning.Utils.UserPrefsUtil;
import com.library.NavigationBar;
import com.library.NvTab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

/**
 * * Franscico Javier Flores Morales
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p>
 * to handle interaction events.
 * Use the {@link InicioFragmentGreatPlaceToWork#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragmentGreatPlaceToWork extends Fragment implements NavigationBar.OnTabSelected, NavigationBar.OnTabClick {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int MIN_STATE_NUMBER = 1;
    private static final int MAX_STATE_NUMBER = 4;
    public static String urlInicio = Constans.servicesURL + "modulo/get?idModulo=" + 1;
    public TextView tvtil;
    FragmentManager fm;
    private TextView tv_textInicio;
    private int tamanio;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private NavigationBar bar;
    private int position;
    private ListView lv;
    private SharedPreferences prefs;
    private FragmentActivity myContext;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private List<ModuloCursos> cursosIniArrayList = new ArrayList<ModuloCursos>();
    private Integer idUset;
    private  String idUserParam;

    private String cursoId;
    public InicioFragmentGreatPlaceToWork() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragmentGreatPlaceToWork.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragmentGreatPlaceToWork newInstance(String param1, String param2) {
        InicioFragmentGreatPlaceToWork fragment = new InicioFragmentGreatPlaceToWork();
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


            idUserParam =getArguments().getString("idUser");
         // cursoId=getArguments().getString("idCurso");

          Log.i("IdUserParam",idUserParam);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_inicio_fragment_great_place_to_work, container, false);
        fm = getActivity().getSupportFragmentManager();
        prefs = getContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        tvtil = rootView.findViewById(R.id.tvtil);
        bar = rootView.findViewById(R.id.navBar);
        bar.setOnTabSelected(this);
        bar.setOnTabClick(this);


        idUserParam =getArguments().getString("idUser");

        tv_textInicio = rootView.findViewById(R.id.tv_textInicios);



   loadInicio();


        lv = rootView.findViewById(R.id.ListView_listado);


        return rootView;


    }


    private void loadInicio() {


        mRequestQueue = Volley.newRequestQueue(getContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, Constans.servicesURL + "/modulo/getForUsuario?idUsuario="+idUserParam, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                JSONObject event = null;
                try {
                    event = new JSONObject(response);

                    String status = event.getString("status");
                    if (status.equals("OK")) {

                        // SE OBTINE DATA
                        JSONArray data = event.getJSONArray("data");
                        Log.i("data1", String.valueOf(data));


                        for (int i = 0; i < data.length(); i++) {

                            final JSONObject cursoObjectArray = data.getJSONObject(i);

                            ModuloInicio moduloInicio = new ModuloInicio();
                            moduloInicio.idGrupoModulo = cursoObjectArray.getInt("idGrupoModulo");
                            moduloInicio.modulo = cursoObjectArray.getString("modulo");


                            Log.i("idGrupoModulo:", String.valueOf(moduloInicio.idGrupoModulo = cursoObjectArray.getInt("idGrupoModulo")));
                            Log.i("modulo:", moduloInicio.modulo = cursoObjectArray.getString("modulo"));


                            JSONObject modulos = cursoObjectArray.getJSONObject("modulo");
                            Modulo modulo = new Modulo();
                            modulo.idModulo = modulos.getInt("idModulo");
                            modulo.titulo = modulos.getString("titulo");
                            modulo.descripcion = modulos.getString("descripcion");
                            modulo.moduloCursos = modulos.getString("moduloCursos");
                            tvtil.setText(modulos.getString("titulo"));
                            tv_textInicio.setText(modulos.getString("descripcion"));

                            Log.i("idModulo:", modulo.titulo = String.valueOf(modulos.getInt("idModulo")));
                            Log.i("titulo:", modulo.titulo = modulos.getString("titulo"));
                            Log.i("descripcion:", modulo.titulo = modulos.getString("descripcion"));
                            Log.i("moduloCursos:", modulo.titulo = modulos.getString("moduloCursos"));

                            JSONArray modulosCursos = modulos.getJSONArray("moduloCursos");


                            for (int a = 0; a < modulosCursos.length(); a++) {

                                final JSONObject moduloCursosObjectArray = modulosCursos.getJSONObject(a);

                                ModuloCursos moduloCursos = new ModuloCursos();

                                moduloCursos.idModuloCurso = moduloCursosObjectArray.getInt(("idModuloCurso"));
                                moduloCursos.fechaInicioDatePicker = moduloCursosObjectArray.getString("fechaInicioDatePicker");
                                moduloCursos.fechaFinDatePicker = moduloCursosObjectArray.getString("fechaFinDatePicker");
                                moduloCursos.ponderation = moduloCursosObjectArray.getInt("ponderation");
                                moduloCursos.position = moduloCursosObjectArray.getInt("position");

                                Log.i("idModuloCurso:", String.valueOf(moduloCursos.idModuloCurso = moduloCursosObjectArray.getInt(("idModuloCurso"))));
                                Log.i("fechaInicioDatePicker:", moduloCursos.fechaInicioDatePicker = moduloCursosObjectArray.getString(("fechaInicioDatePicker")));
                                Log.i("fechaFinDatePicker:", moduloCursos.fechaFinDatePicker = moduloCursosObjectArray.getString(("fechaFinDatePicker")));
                                Log.i("ponderation:", String.valueOf(moduloCursos.idModuloCurso = moduloCursosObjectArray.getInt(("ponderation"))));
                                Log.i("position:", String.valueOf(moduloCursos.idModuloCurso = moduloCursosObjectArray.getInt(("position"))));
                             //   Log.i("curso:", moduloCursos.curso = moduloCursosObjectArray.getString(("curso")));


                                JSONObject CursoObject = moduloCursosObjectArray.getJSONObject("curso");


                                CursoModulo cursoModulo = new CursoModulo();

                                cursoModulo.idCurso = CursoObject.getInt("idCurso");
                                cursoModulo.titulo = CursoObject.getString("titulo");
                                cursoModulo.subtitulo = CursoObject.getString("subtitulo");
                                cursoModulo.description = CursoObject.getString("description");
                                cursoModulo.isEditable = CursoObject.getBoolean("isEditable");
                                cursoModulo.bloques = CursoObject.getString("bloques");

                                moduloCursos.curso = cursoModulo;

                                Log.i("idCurso", String.valueOf(CursoObject.getInt("idCurso")));
                                Log.i("titulo", CursoObject.getString("titulo"));
                                Log.i("subtitulo", CursoObject.getString("subtitulo"));
                                Log.i("description", CursoObject.getString("description"));
                                Log.i("isEditable", String.valueOf(cursoModulo.isEditable = CursoObject.getBoolean("isEditable")));
                                Log.i("bloques", cursoModulo.bloques = CursoObject.getString("bloques"));


                                JSONArray bloquesArray = CursoObject.getJSONArray("bloques");


                                for (int b = 0; b < bloquesArray.length(); b++) {

                                    final JSONObject bloquesObjectArray = bloquesArray.getJSONObject(b);
                                    BloquesModulo bloquesModulo = new BloquesModulo();
                                    bloquesModulo.idBloqueCurso = bloquesObjectArray.getInt("idBloqueCurso");
                                    bloquesModulo.titulo = bloquesObjectArray.getString("titulo");
                                    bloquesModulo.ordered = bloquesObjectArray.getInt("ordered");
                                    //bloquesModulo.asignaciones = bloquesObjectArray.getString("asignaciones");

                                    Log.i("idBloqueCurso", String.valueOf(bloquesModulo.idBloqueCurso = bloquesObjectArray.getInt("idBloqueCurso")));
                                    Log.i("titulo", bloquesModulo.titulo = bloquesObjectArray.getString("titulo"));
                                    Log.i("ordered", String.valueOf(bloquesModulo.ordered = bloquesObjectArray.getInt("ordered")));

                                    JSONArray asignacionsArray = bloquesObjectArray.getJSONArray("asignaciones");


                                    for (int c = 0; c < asignacionsArray.length(); c++) {
                                        final JSONObject asignacionesObjectArray = asignacionsArray.getJSONObject(c);

                                        AsignacionesModulo asignacionesModulo = new AsignacionesModulo();

                                        asignacionesModulo.idAsignacion = asignacionesObjectArray.getInt("idAsignacion");
                                        asignacionesModulo.tipoAsignacion = asignacionesObjectArray.getInt("tipoAsignacion");
                                        asignacionesModulo.order = asignacionesObjectArray.getInt("order");
                                        asignacionesModulo.material = asignacionesObjectArray.getString("material");
                                        asignacionesModulo.evaluacion = asignacionesObjectArray.getString("evaluacion");
                                        asignacionesModulo.actividad = asignacionesObjectArray.getString("actividad");
                                        asignacionesModulo.autoevaluacion = asignacionesObjectArray.getString("autoevaluacion");
                                        asignacionesModulo.extraMaterial = asignacionesObjectArray.getString("extraMaterial");
                                        asignacionesModulo.infografia = asignacionesObjectArray.getString("infografia");
                                        asignacionesModulo.presencial = asignacionesObjectArray.getString("presencial");
                                         bloquesModulo.asignaciones = asignacionesModulo;
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
                                            JSONObject materialObject = asignacionesObjectArray.getJSONObject("material");
                                            MaterialModulo materialModulo = new MaterialModulo();
                                            materialModulo.idMaterial = materialObject.getInt("idMaterial");
                                            materialModulo.titulo = materialObject.getString("titulo");
                                            materialModulo.descripcion = materialObject.getString("descripcion");
                                            materialModulo.ruta = materialObject.getString("ruta");
                                            materialModulo.rutaTranscript = materialObject.getString("rutaTranscript");
                                            materialModulo.recursos = materialObject.getString("recursos");
                                            materialModulo.isEditable = materialObject.getBoolean("isEditable");
                                            materialModulo.duration = materialObject.getString("duration");

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
                                            //evaluacionModulo.preguntas = autoEvaluacionObject.getString("preguntas");
                                            evaluacionModulo.isEditable = autoEvaluacionObject.getBoolean("isEditable");
                                            evaluacionModulo.peso = autoEvaluacionObject.getString("peso");
                                            evaluacionModulo.duration = autoEvaluacionObject.getString("duration");


                                            Log.i("idEvaluacion", String.valueOf(autoEvaluacionObject.getInt("idEvaluacion")));
                                            Log.i("titulo", autoEvaluacionObject.getString("titulo"));
                                            Log.i("preguntas", String.valueOf(autoEvaluacionObject.getString("preguntas")));
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


                                            Log.i("idActividad", String.valueOf(actividadObject.getInt("idActividad")));
                                            Log.i("titulo", actividadObject.getString("titulo"));
                                            Log.i("descripcion", actividadObject.getString("descripcion"));
                                            Log.i("peso", actividadObject.getString("peso"));
                                            Log.i("duration", actividadObject.getString("duration"));
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

                                            Log.i("idAutoEvaluacion", String.valueOf(autoEvaluacionObject.getInt("idAutoEvaluacion")));
                                            Log.i("titulo", autoEvaluacionObject.getString("titulo"));
                                            Log.i("duration", autoEvaluacionObject.getString("duration"));
                                            Log.i("preguntas", autoEvaluacionObject.getString("preguntas"));


                                        }
                                        if (asignacionesObjectArray.getString("infografia").toString().equals("null")) {

                                            Log.i("sin dato", "sin infografia");
                                        } else {
                                            JSONObject infografiaObject = asignacionesObjectArray.getJSONObject("infografia");

                                            InfografiaModulo infografiaModulo = new InfografiaModulo();

                                            infografiaModulo.idInfografia = infografiaObject.getInt("idInfografia");
                                            infografiaModulo.titulo = infografiaObject.getString("titulo");
                                            infografiaModulo.duration = infografiaObject.getString("duration");

                                            Log.i("idInfografia", String.valueOf(infografiaObject.getInt("idInfografia")));
                                            Log.i("titulo", infografiaObject.getString("titulo"));
                                            Log.i("duration", infografiaObject.getString("duration"));
cursosIniArrayList.clear();
                                        }


                                    }


                                }

                                cursosIniArrayList.add(moduloCursos);

                            }


                        }


                    } else {
                        Toast.makeText(getActivity(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen


                    }
                    tamanio = cursosIniArrayList.size();
                    setup(false, tamanio);
                    Log.i("tamaño", String.valueOf(tamanio));
                    //  Log.i("dataCursosInicio", String.valueOf(cursoUno));

                    SemanaAdapter adapterSemanas = new SemanaAdapter(getContext(), R.layout.itempruebasemanas, cursosIniArrayList);
                    lv.setAdapter(adapterSemanas);


                    setListViewHeight(lv, R.layout.itempruebasemanas, cursosIniArrayList.size());


                } catch (JSONException e) {
                    e.printStackTrace();

                    if (!NetworkUtil.isConnectedWifi(getActivity().getApplicationContext()) == true) {
                        Intent mainIntent = new Intent().setClass(getActivity(), IsOnLineNotFound.class);
                        startActivity(mainIntent);
                    }

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


    private void setup(boolean reset, int count) {
        if (reset)
            bar.resetItems();
        bar.setTabCount(count);
        bar.animateView(3000);
        bar.setCurrentPosition(position <= 0 ? 0 : position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        myContext = (FragmentActivity) context;

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

    @Override
    public void onTabSelected(int touchPosition, NvTab prev, NvTab nvTab) {
        Toast.makeText(getActivity(), "Selected position: " + touchPosition, Toast.LENGTH_LONG).show();
    }

    public void onClickView(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onTabClick(int touchPosition, NvTab prev, NvTab nvTab) {
        Toast.makeText(getActivity(), "You clicked on: " + touchPosition, Toast.LENGTH_LONG).show();
    }


}
