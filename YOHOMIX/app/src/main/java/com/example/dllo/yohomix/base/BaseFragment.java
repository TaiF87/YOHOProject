package com.example.dllo.yohomix.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/11/23.
 */

public abstract class BaseFragment extends Fragment {
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    protected abstract int setLayout();
    protected abstract void initView(View view);
    protected abstract void initData();

    public <T extends View> T bindView(int id){
        return (T)getView().findViewById(id);
    }

    public void setClick (View.OnClickListener listener,View...views){
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }
}
