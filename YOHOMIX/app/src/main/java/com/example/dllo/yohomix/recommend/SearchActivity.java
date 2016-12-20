package com.example.dllo.yohomix.recommend;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/12/3.
 */
public class SearchActivity extends BaseActivity{
    private TabLayout tlSearch;
    private ViewPager vpSearch;
    private BaseTablayout bean;
    private SearchAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        tlSearch = bindView(R.id.tbl_search);
        vpSearch = bindView(R.id.vp_search);
    }

    @Override
    protected void initData() {
        initTab();
        mAdapter = new SearchAdapter(getSupportFragmentManager());
    }

    private void initTab() {
        NetHelper.MyRequest(URLValues.TABL_URL, BaseTablayout.class, new NetListener<BaseTablayout>() {
            @Override
            public void successListener(BaseTablayout response) {
                bean = response;
                mAdapter.setBean(bean);

                vpSearch.setAdapter(mAdapter);
                tlSearch.setupWithViewPager(vpSearch);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
