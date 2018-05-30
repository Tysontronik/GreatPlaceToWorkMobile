package com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.Actividad;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.Constans.UrlConstants;
import com.learning.gptw.greatplacetowork_learning.Models.ActividadModulo;
import com.learning.gptw.greatplacetowork_learning.R;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Franacisco Javier Flores Morales
 * ActividadFragment
 */
public class ActividadFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences prefs;
    private String urlIdMaterial;
    public static final String TAG = ActividadFragment.class.getSimpleName();
    FragmentManager fm;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private int idUserParam;
    private Integer urlActividad;

    private MediaController mediaController;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ActividadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActividadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActividadFragment newInstance(String param1, String param2) {
        ActividadFragment fragment = new ActividadFragment();
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


            idUserParam = getArguments().getInt("idUser");
            urlActividad = getArguments().getInt("idActividad");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_actividad, container, false);
        fm = getActivity().getSupportFragmentManager();
        mediaController = new MediaController(getActivity());
        prefs = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        ImageView img_back = rootView.findViewById(R.id.img_backAc);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    ActividadFragment.super.getActivity().onBackPressed();
                } else {
                    ActividadFragment.super.getActivity(). getSupportFragmentManager().popBackStack();
                }
            }
        });

        loadActividad
                ();

        return rootView;
    }

    private void loadActividad() {
        mRequestQueue = Volley.newRequestQueue(getContext());


        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, UrlConstants.servicesURL + UrlConstants.actividadURL + urlActividad, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                JSONObject event = null;
                try {
                    event = new JSONObject(response);

                    String status = event.getString("status");
                    if (status.equals("OK")) {

                        // SE OBTINE DATA
                        JSONObject data = event.getJSONObject("data");

                        final ActividadModulo actividadModulo = new ActividadModulo();

                        actividadModulo.idActividad = data.getInt("idActividad");
                        actividadModulo.titulo = data.getString("titulo");
                        TextView tv_titleActividad = getActivity().findViewById(R.id.tv_titleActividad);
                        tv_titleActividad.setText(actividadModulo.titulo = data.getString("titulo"));
                        actividadModulo.descripcion = data.getString("descripcion");
                        TextView tv_descActividad = getActivity().findViewById(R.id.tv_descActividad);
                        tv_descActividad.setText(Html.fromHtml(actividadModulo.titulo = data.getString("descripcion")));
                        actividadModulo.peso = data.getString("peso");
                        actividadModulo.duration = data.getString("duration");


                        Log.i("idActividad", String.valueOf(data.getInt("idActividad")));
                        Log.i("titulo", data.getString("titulo"));
                        Log.i("descripcion", data.getString("descripcion"));
                        Log.i("peso", data.getString("peso"));
                        Log.i("duration", data.getString("duration"));

                    }

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


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
