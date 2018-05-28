package com.learning.gptw.greatplacetowork_learning.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.Actividad.ActividadActivity;
import com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.video.VideoActivity;
import com.learning.gptw.greatplacetowork_learning.Models.AsignacionesModulo;
import com.learning.gptw.greatplacetowork_learning.Models.BloquesModulo;
import com.learning.gptw.greatplacetowork_learning.Quiz.Quiz;
import com.learning.gptw.greatplacetowork_learning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fflores on 2/16/18.
 * Franacisco Javier Flores Morales
 * Cursos Adapter
 */

public class CursosAdapter extends ArrayAdapter {

    protected Activity activity;
    protected Context context;
    private int resource;
    protected ArrayList<BloquesModulo> items;
    protected int semanaitem;
    protected List<AsignacionesModulo> cursoInicioListContent;
    private LayoutInflater mInflater;

    private FragmentActivity myContext;

    public CursosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<AsignacionesModulo> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.cursoInicioListContent = objects;
    }

    @Override
    public int getCount() {
        return cursoInicioListContent.size();
    }

    public void clear() {
        cursoInicioListContent.clear();
    }

    public void addAll(ArrayList<BloquesModulo> category) {
        for (int i = 5; i < category.size(); i++) {
            cursoInicioListContent.add(category.get(i));
        }
    }

    @Override
    public AsignacionesModulo getItem(int arg0) {
        return cursoInicioListContent.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View v = convertView;


        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.cursositem, null);
        }


        final AsignacionesModulo dir = cursoInicioListContent.get(position);


        TextView tv_video = v.findViewById(R.id.tv_video);
        ImageView id_imgvideo = v.findViewById(R.id.id_imgvideo);
        ImageView id_imgvideo1 = v.findViewById(R.id.id_imgvideo1);
        ImageView id_imgvideo2 = v.findViewById(R.id.id_imgvideo2);
        ImageView id_imgvideo3 = v.findViewById(R.id.id_imgvideo3);
        ImageView id_imgvideo4 = v.findViewById(R.id.id_imgvideo4);
        ImageView id_imgvideo5 = v.findViewById(R.id.id_imgvideo5);
        ImageView id_imgvideo6 = v.findViewById(R.id.id_imgvideo6);


        id_imgvideo.setImageResource(R.drawable.ic_video);
        if (dir.material.toString().equals("null")) {

        } else {
            tv_video.setText(dir.materialModulo.titulo);

            id_imgvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getContext(),
                            VideoActivity.class);


                    i.putExtra("idMaterial", dir.materialModulo.idMaterial);


                    getContext().startActivity(i);


                }
            });


        }

        if (dir.evaluacion.toString().equals("null")) {


        } else {
            tv_video.setText(dir.evaluacionModulo.titulo);
id_imgvideo.setVisibility(View.GONE);
id_imgvideo1.setVisibility(View.VISIBLE);
            id_imgvideo1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getContext(),
                            Quiz.class);




                    i.putExtra("idEvaluacion", dir.evaluacionModulo.idEvaluacion);



                    getContext().startActivity(i);


                }
            });
        }


        if (dir.actividad.toString().equals("null")) {

        } else {
            tv_video.setText(dir.actividadModulo.titulo);
            id_imgvideo.setVisibility(View.GONE);
            id_imgvideo1.setVisibility(View.GONE);
            id_imgvideo2.setVisibility(View.VISIBLE);

            id_imgvideo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getContext(),
                            ActividadActivity.class);




                    i.putExtra("idActividad", dir.actividadModulo.idActividad);

                    getContext().startActivity(i);


                }
            });
        }

        if (dir.autoevaluacion.toString().equals("null")) {

        } else {
            tv_video.setText(dir.autoEvaluacionModulo.titulo);
            id_imgvideo.setVisibility(View.GONE);
            id_imgvideo2.setVisibility(View.GONE);
            id_imgvideo3.setVisibility(View.VISIBLE);
            id_imgvideo3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getContext(),
                            ActividadActivity.class);




                    i.putExtra("idActividad", dir.actividadModulo.idActividad);

                    getContext().startActivity(i);


                }
            });

        }

        if (dir.extraMaterial.toString().equals("null")) {

        } else {
            tv_video.setText(dir.extraMateriales.titulo);
            id_imgvideo.setVisibility(View.GONE);
            id_imgvideo3.setVisibility(View.GONE);
            id_imgvideo4.setVisibility(View.VISIBLE);
            id_imgvideo4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getContext(),
                            ActividadActivity.class);




                    i.putExtra("idActividad", dir.actividadModulo.idActividad);

                    getContext().startActivity(i);


                }
            });

        }

        if (dir.infografia.toString().equals("null")) {

        } else {
            tv_video.setText(dir.infografiaModulo.titulo);
            id_imgvideo.setVisibility(View.GONE);
            id_imgvideo4.setVisibility(View.GONE);
            id_imgvideo5.setVisibility(View.VISIBLE);
            id_imgvideo5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getContext(),
                            ActividadActivity.class);




                    i.putExtra("idActividad", dir.actividadModulo.idActividad);

                    getContext().startActivity(i);


                }
            });
        }
        if (dir.presencial.toString().equals("null")) {

        } else {
            tv_video.setText(dir.presenciales.titulo);
            id_imgvideo.setVisibility(View.GONE);
            id_imgvideo5.setVisibility(View.GONE);
            id_imgvideo6.setVisibility(View.VISIBLE);
            id_imgvideo6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(getContext(),
                            ActividadActivity.class);




                    i.putExtra("idActividad", dir.actividadModulo.idActividad);

                    getContext().startActivity(i);


                }
            });
        }


        ImageView id_imgdownloadvideo = v.findViewById(R.id.id_imgdownloadvideo);
        id_imgdownloadvideo.setImageResource(R.drawable.ic_download);





        return v;
    }
}