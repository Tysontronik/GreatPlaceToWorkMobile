package com.learning.gptw.greatplacetowork_learning.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.learning.gptw.greatplacetowork_learning.Models.QuizResponse;
import com.learning.gptw.greatplacetowork_learning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fflores on 2/21/18.
 */

public class ResponseQuizAdapter extends ArrayAdapter<QuizResponse
        > {

    protected Activity activity;
    protected Context context;
    protected ArrayList<QuizResponse> items;
    protected int semanaitem;
    protected List<QuizResponse> quizResponseList;
    private LayoutInflater mInflater;

    private FragmentActivity myContext;

    public ResponseQuizAdapter(Context context, int semanaitem, List<QuizResponse> quizResponseArrayList) {
        super(context, semanaitem, quizResponseArrayList);
        this.context = context;
        this.semanaitem = semanaitem;
        this.quizResponseList = quizResponseArrayList;


    }

    @Override
    public int getCount() {
        return quizResponseList.size();
    }

    public void clear() {
        quizResponseList.clear();
    }

    public void addAll(ArrayList<QuizResponse> category) {
        for (int i = 5; i < category.size(); i++) {
            quizResponseList.add(category.get(i));
        }
    }

    @Override
    public QuizResponse getItem(int arg0) {
        return quizResponseList.get(arg0);
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
            v = inf.inflate(R.layout.questionresponseitem, null);
        }

        final QuizResponse dir = quizResponseList.get(position);

        CheckBox ch_response= v.findViewById(R.id.ch_response);
        ch_response.setText(dir.responseQuiz);


        return v;
    }
}