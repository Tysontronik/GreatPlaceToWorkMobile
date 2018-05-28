package com.learning.gptw.greatplacetowork_learning.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.learning.gptw.greatplacetowork_learning.Models.RespuestasModulo;
import com.learning.gptw.greatplacetowork_learning.R;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Dell on 09/03/2018.
 */

public class RespuestaAdapter  extends ArrayAdapter {

    protected Activity activity;

    private Context context;
    private int resource;
    protected ArrayList<RespuestasModulo> items;
    protected int semanaitem;
    protected List<RespuestasModulo> cursoInicioList;
    private LayoutInflater mInflater;

    private FragmentActivity myContext;

    public RespuestaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<RespuestasModulo> objects) {
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

    public void addAll(ArrayList<RespuestasModulo> category) {
        for (int i = 0; i < category.size(); i++) {
            cursoInicioList.add(category.get(i));
        }
    }

    @Override
    public RespuestasModulo getItem(int arg0) {
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
            v = inf.inflate(R.layout.respuestas_adapter, null);
        }

       // final RespuestasModulo dir = cursoInicioList.get(position);
         final RespuestasModulo dir = cursoInicioList.get(position);



        RadioGroup contenedor = (RadioGroup) v.findViewById(R.id.opciones);
        int checkedRadioButtonId = contenedor.getCheckedRadioButtonId();

      // RadioButton ch_response=v.findViewById(R.id.ch_response);
      //  ch_response.setText(dir.respuesta);
      //  int idx = contenedor.indexOfChild(ch_response);
      //  boolean estado = ch_response.isChecked();
        //String selectedtext = ch_response.getText().toString();
       // Log.i("indiceradio",selectedtext);
        int index = contenedor.indexOfChild(v.findViewById(contenedor.getCheckedRadioButtonId()));

        int count = contenedor.getChildCount();
        /*ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
        for (int i=0;i<count;i++) {
            View o = contenedor.getChildAt(i);
            if (o instanceof RadioButton) {
                listOfRadioButtons.add((RadioButton)o);
            }
        }*/

        contenedor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton ch_response=group.findViewById(R.id.ch_response);
                View radioButton = group.findViewById(checkedId);
             //   String variant_name = radioButton.getTag().toString();
               // Toast.makeText(getContext(), variant_name + "",
                 //       Toast.LENGTH_LONG).show();

            }
        });


        return v;
    }

}
