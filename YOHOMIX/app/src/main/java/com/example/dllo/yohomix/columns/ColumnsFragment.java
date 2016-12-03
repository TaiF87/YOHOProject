package com.example.dllo.yohomix.columns;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
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
import com.squareup.picasso.Picasso;

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
    private BaseColumnsHead beans;
    private ImageView ivBanner,ivHeadPic;
    private TextView tvDescription,tvAnswers,tvNick,tvQuestion,tvTitle,tvAnswer;


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
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.head_columns,null);
        lvColumns.addHeaderView(headView);

        ivBanner = (ImageView) headView.findViewById(R.id.iv_banner);
        ivHeadPic = (ImageView) headView.findViewById(R.id.iv_headPic);
        tvDescription = (TextView) headView.findViewById(R.id.tv_description);
        tvAnswers = (TextView) headView.findViewById(R.id.tv_answers);
        tvNick = (TextView) headView.findViewById(R.id.tv_nick);
        tvQuestion = (TextView) headView.findViewById(R.id.tv_question);
        tvTitle = (TextView) headView.findViewById(R.id.tv_title);
        tvAnswer = (TextView) headView.findViewById(R.id.tv_answer);

        headPic();
        listVolley();
    }

    private void headPic() {
        HashMap<String,String> map = new HashMap<>();
        map.put(URLValues.POST_KEY,URLValues.HEAD_COLUMNS_VALUES);
        NetHelper.MyRequest(URLValues.HEAD_COLUMNS_URL, BaseColumnsHead.class, new NetListener<BaseColumnsHead>() {
            @Override
            public void successListener(BaseColumnsHead response) {
                beans = response;
                tvDescription.setText(beans.getData().getDescription());
                tvAnswers.setText("已诊断了" + beans.getData().getAnswers() + "位症案");
                tvNick.setText(beans.getData().getData().get(0).getNick());
                tvQuestion.setText(beans.getData().getData().get(0).getQuestion());
                tvTitle.setText(beans.getData().getTitle());
                tvAnswer.setText(beans.getData().getData().get(0).getAnswer());
                Picasso.with(getContext()).load(beans.getData().getBanner()).fit().into(ivBanner);
                Picasso.with(getContext()).load(beans.getData().getData().get(0).getHeadpic()).fit().into(ivHeadPic);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);

//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLValues.HEAD_COLUMNS_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                beans = gson.fromJson(response,BaseColumnsHead.class);
//                tvDescription.setText(beans.getData().getDescription());
//                tvAnswers.setText("已诊断了" + beans.getData().getAnswers() + "位症案");
//                tvNick.setText(beans.getData().getData().get(0).getNick());
//                tvQuestion.setText(beans.getData().getData().get(0).getQuestion());
//                tvTitle.setText(beans.getData().getTitle());
//                tvAnswer.setText(beans.getData().getData().get(0).getAnswer());
//                Picasso.with(getContext()).load(beans.getData().getBanner()).fit().into(ivBanner);
//                Picasso.with(getContext()).load(beans.getData().getData().get(0).getHeadpic()).fit().into(ivHeadPic);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map = new HashMap<>();
//                map.put(URLValues.POST_KEY,URLValues.HEAD_COLUMNS_VALUES);
//                return map;
//            }
//        };
//        requestQueue.add(stringRequest);
    }

    private void listVolley() {
        HashMap<String,String> map = new HashMap<>();
        map.put(URLValues.POST_KEY,URLValues.COLUMNS_VALUES);
        NetHelper.MyRequest(URLValues.COLUMNS_URL, BaseColumns.class, new NetListener<BaseColumns>() {
            @Override
            public void successListener(BaseColumns response) {
                mBean = response;
                mAdapter.setBean(mBean);
                lvColumns.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);

//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(URLValues.COLUMNS_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                mBean = gson.fromJson(response,BaseColumns.class);
//                mAdapter.setBean(mBean);
//                lvColumns.setAdapter(mAdapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map = new HashMap<>();
//                map.put(URLValues.POST_KEY,URLValues.CYCLE_VALUES);
//                return map;
//            }
//        };
//        requestQueue.add(stringRequest);
    }
}
