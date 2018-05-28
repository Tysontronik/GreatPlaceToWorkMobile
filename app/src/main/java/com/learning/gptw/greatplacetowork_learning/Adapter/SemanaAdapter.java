package com.learning.gptw.greatplacetowork_learning.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.learning.gptw.greatplacetowork_learning.Cursos.CursosFragment.Cursos.CursosFragment;
import com.learning.gptw.greatplacetowork_learning.Models.ModuloCursos;
import com.learning.gptw.greatplacetowork_learning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 26/01/2018.
 */

public class SemanaAdapter extends ArrayAdapter {

    protected Activity activity;

    private Context context;
    private int resource;
    protected ArrayList<ModuloCursos> items;
    protected int semanaitem;
    protected List<ModuloCursos> cursoInicioList;
    private LayoutInflater mInflater;

    private FragmentActivity myContext;

    public SemanaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ModuloCursos> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.cursoInicioList = objects;
    }


    @Override
    public int getCount() {
        return cursoInicioList.size();
    }

    public void clear() {
        cursoInicioList.clear();
    }

    public void addAll(ArrayList<ModuloCursos> category) {
        for (int i = 0; i < category.size(); i++) {
            cursoInicioList.add(category.get(i));
        }
    }

    @Override
    public ModuloCursos getItem(int arg0) {
        return cursoInicioList.get(arg0);
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
            v = inf.inflate(R.layout.itempruebasemanas, null);
        }

        final ModuloCursos dir = cursoInicioList.get(position);


        TextView title = v.findViewById(R.id.tv_semana1);
        title.setText("Semana 1");


        TextView date = v.findViewById(R.id.tv_semana1Date1);
        date.setText(dir.fechaFinDatePicker);

        TextView desc = v.findViewById(R.id.descsem1);
        desc.setText(dir.curso.titulo);


        ImageView imagensem = v.findViewById(R.id.img_imgsem1);
        imagensem.setImageResource(R.drawable.ic_circlegray);

        ImageView imgdownload = v.findViewById(R.id.imgsem1download);
        imgdownload.setImageResource(R.drawable.ic_download);

        ImageView imgNext = v.findViewById(R.id.img_imgsem1Next1);
        imgNext.setImageResource(R.drawable.ic_next);


        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CursosFragment cursosFragment = new CursosFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("idCurso", cursoInicioList.get(position));
                bundle.putSerializable("idPregunta", cursoInicioList.get(position));


                cursosFragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, cursosFragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return v;
    }
}