package com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.video;

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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.learning.gptw.greatplacetowork_learning.Constans.UrlConstants;
import com.learning.gptw.greatplacetowork_learning.Models.MaterialModulo;
import com.learning.gptw.greatplacetowork_learning.R;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {

    FragmentManager fm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = VideoFragment.class.getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPreferences prefs;
    private  String urlIdMaterial;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private  int idUserParam,idEvaluaciones;
  private Integer urlMaterial;

    private MediaController mediaController;
    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
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


            idUserParam =getArguments().getInt("idUser");
            urlMaterial =getArguments().getInt("idMaterial");
            idEvaluaciones= getArguments().getInt("idEvaluacion");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        fm = getActivity().getSupportFragmentManager();
  mediaController = new MediaController(getActivity());
        prefs = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);



                ImageView img_back = rootView.findViewById(R.id.img_backv);
                img_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0) {
                            VideoFragment.super.getActivity().onBackPressed();
                        } else {
                            VideoFragment.super.getActivity(). getSupportFragmentManager().popBackStack();
                        }






                    }
                });





loadMaterial();

        return rootView;
    }

    private void loadMaterial() {



        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(getContext());


        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, UrlConstants.servicesURL+UrlConstants.materialURL+urlMaterial, new Response.Listener<String>() {


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
                        Viewvideo.setVideoURI(Uri.parse(UrlConstants.prendizajeUrl+VideoMaterial));
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
    }


}
