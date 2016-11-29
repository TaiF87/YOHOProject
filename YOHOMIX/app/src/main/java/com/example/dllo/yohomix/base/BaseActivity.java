package com.example.dllo.yohomix.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by dllo on 16/11/23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();

    }
    protected abstract int setLayout();
    protected abstract void initView();
    protected abstract void initData();

    public <T extends View> T bindView(int id){
        return (T)findViewById(id);
    }

    public void setClick(View.OnClickListener listener,View ... views){
        for (View view : views){
            view.setOnClickListener(listener);
        }
    }
}
