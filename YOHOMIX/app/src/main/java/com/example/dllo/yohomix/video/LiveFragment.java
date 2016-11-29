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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(URLValues.LIVE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                beans = gson.fromJson(response,BaseLive.class);
                mAdapter.setLive(beans);
                lvLive.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
