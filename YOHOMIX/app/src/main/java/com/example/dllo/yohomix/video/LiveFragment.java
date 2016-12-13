package com.example.dllo.yohomix.video;

import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/11/28.
 */
public class LiveFragment extends BaseFragment{
    private ListView lvLive;
    private BaseLive beans;
    private LiveAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.live_fragment;
    }

    @Override
    protected void initView(View view) {
        lvLive = bindView(R.id.lv_live);
    }

    @Override
    protected void initData() {
        mAdapter = new LiveAdapter(getContext());
        initVolley();
    }

    private void initVolley() {
        NetHelper.MyRequest(URLValues.LIVE_URL, BaseLive.class, new NetListener<BaseLive>() {
            @Override
            public void successListener(BaseLive response) {
                beans = response;
                mAdapter.setLive(beans);
                lvLive.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
