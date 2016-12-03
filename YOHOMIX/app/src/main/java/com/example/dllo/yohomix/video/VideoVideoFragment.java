package com.example.dllo.yohomix.video;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 16/11/28.
 */
public class VideoVideoFragment extends BaseFragment{
    private ListView lvVideo;
    private VideoAdapter mAdapter;
    private BaseVideo mBean;
    @Override
    protected int setLayout() {
        return R.layout.video_video_fragment;
    }

    @Override
    protected void initView(View view) {
        lvVideo = bindView(R.id.lv_video_fragment);
    }

    @Override
    protected void initData() {
        mAdapter = new VideoAdapter(getContext());
        initVolley();
    }
    private void initVolley() {
        HashMap<String,String> map = new HashMap<>();
        map.put(URLValues.POST_KEY,URLValues.VIDEO_VALUES);
        NetHelper.MyRequest(URLValues.VIDEO_URL, BaseVideo.class, new NetListener<BaseVideo>() {
            @Override
            public void successListener(BaseVideo response) {
                mBean = response;
                mAdapter.setBeen(mBean);
                lvVideo.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);

//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(URLValues.VIDEO_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                mBean = gson.fromJson(response,BaseVideo.class);
//                mAdapter.setBeen(mBean);
//                lvVideo.setAdapter(mAdapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> mMap = new HashMap<>();
//                mMap.put(URLValues.POST_KEY,URLValues.VIDEO_VALUES);
//                return mMap;
//            }
//        };
//        requestQueue.add(stringRequest);
    }

}
