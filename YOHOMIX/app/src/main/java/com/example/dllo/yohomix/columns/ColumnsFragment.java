package com.example.dllo.yohomix.columns;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/11/26.
 */

public class ColumnsFragment extends BaseFragment {
    private ListView lvColumns;
    private BaseColumns mBean;
    private ColumnsAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_columns;
    }

    @Override
    protected void initView(View view) {
        lvColumns = bindView(R.id.lv_columns);
    }

    @Override
    protected void initData() {
        mAdapter = new ColumnsAdapter(getContext());
//        View headView = LayoutInflater.from(getContext()).inflate(R.layout.head_columns,null);
//        ImageView headPic = (ImageView) headView.findViewById(R.id.iv_pic_head);
//        lvColumns.addHeaderView(headPic);
        initVolley();
    }

    private void initVolley() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(URLValues.COLUMNS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                mBean = gson.fromJson(response,BaseColumns.class);
                mAdapter.setBean(mBean);
                lvColumns.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put(URLValues.POST_KEY,URLValues.CYCLE_VALUES);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
