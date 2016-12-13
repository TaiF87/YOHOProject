package com.example.dllo.yohomix.login;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/5.
 */
public class LoginActivity extends BaseActivity  {
    private ViewPager vpLogin;
    private TextView tvCollection;
    private ArrayList<Fragment> mFragments;
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        vpLogin = bindView(R.id.vp_login);
        tvCollection = bindView(R.id.tv_collection);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new LoginFragment());
        mFragments.add(new RegisteredFragment());
        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager());
        adapter.setFragments(mFragments);
        vpLogin.setAdapter(adapter);
//        collectionClick();
    }

    private void collectionClick() {
        tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
