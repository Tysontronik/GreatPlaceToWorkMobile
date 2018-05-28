package com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.Extramaterial;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExtraMaterialfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExtraMaterialfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExtraMaterialfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences prefs;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private  int idUserParam;
    private Integer urlExtraMaterial;

    FragmentManager fm;
    private MediaController mediaController;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ExtraMaterialfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExtraMaterialfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExtraMaterialfragment newInstance(String param1, String param2) {
        ExtraMaterialfragment fragment = new ExtraMaterialfragment();
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

            urlExtraMaterial =getArguments().getInt("idExtraMaterial");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_extra_materialfragment, container, false);
        fm = getActivity().getSupportFragmentManager();
        mediaController = new MediaController(getActivity());
        prefs = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

//loadExtraMaterial();
    return  rootView;
    }

  /*  private void loadExtraMaterial() {

        // urlMaterial ="3";


        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getContext());

        Bundle bundle = getArguments();



        //String Request initialized
    mStringRequest = new StringRequest(Request.Method.GET,Constans.servicesURL+"material/get?idMaterial="+urlMaterial, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                JSONObject event = null;
                try {
                    event = new JSONObject(response);

                    String status = event.getString("status");
                    if (status.equals("OK")) {

                        // SE OBTINE DATA
                        JSONObject data = event.getJSONObject("data");

                        final MaterialModulo materialModulo = new MaterialModulo();

                        materialModulo.idMaterial = data.getInt("idMaterial");
                        materialModulo.titulo = data.getString("titulo");
                        TextView tvtil= getActivity().findViewById(R.id.tvtil);
                        tvtil.setText(data.getString("titulo"));

                        materialModulo.descripcion = data.getString("descripcion");
                        TextView tv_textIniciovideo = getActivity().findViewById(R.id.tv_textIniciovideo);
                        tv_textIniciovideo.setText(data.getString("descripcion"));
                        materialModulo.ruta = data.getString("ruta");
                        VideoView Viewvideo = getActivity().findViewById(R.id.Viewvideo);
                        Uri VideoMaterial = Uri.parse(data.getString("ruta"));
                        Viewvideo.setVideoURI(Uri.parse(Constans.prendizajeUrl+VideoMaterial));
                        Viewvideo.start();
                        Viewvideo.setMediaController(mediaController);
                        materialModulo.rutaTranscript = data.getString("rutaTranscript");
                        materialModulo.recursos = data.getString("recursos");

                        materialModulo.isEditable = data.getBoolean("isEditable");
                        materialModulo.duration = data.getString("duration");


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
    }*/

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
