package com.learning.gptw.greatplacetowork_learning.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import com.learning.gptw.greatplacetowork_learning.Models.CursoInicio;
import com.learning.gptw.greatplacetowork_learning.Quiz.QuizViewCardFragment.CardFragment;
import com.learning.gptw.greatplacetowork_learning.Quiz.QuizViewCardFragment.QuizCardctivity;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<CardFragment> mFragments;
    private float mBaseElevation;

    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation) {
        super(fm);

        //recibo parametro de fragment

        QuizCardctivity quizCardctivity = new QuizCardctivity();
       int num= quizCardctivity.numero();

        mFragments = new ArrayList<>();
        mBaseElevation = baseElevation;

        for(int i = 0; i< num; i++){
            addCardFragment(new CardFragment());
        }
    }


    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(CardFragment fragment) {
        mFragments.add(fragment);
    }

}
