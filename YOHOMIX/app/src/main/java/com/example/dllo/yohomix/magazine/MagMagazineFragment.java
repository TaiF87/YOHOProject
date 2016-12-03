package com.example.dllo.yohomix.magazine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

/**
 * Created by dllo on 16/11/28.
 */
public class MagMagazineFragment extends BaseFragment {
    private ImageView ivLineOOne, ivLineOTwo,ivLineOThree,
                      ivLineTOne, ivLineTTwo,ivLineTThree,
                      ivLineSOne, ivLineSTwo,ivLineSThree;
    private TextView tvLineOOne,tvLineOTwo,tvLineOThree,
                     tvLineTOne,tvLineTTwo,tvLineTThree,
                     tvLineSOne,tvLineSTwo,tvLineSThree;
    private BaseMagazineOne OneBeans;
    private BaseMagazineTwo twoBeans;
    private BaseMagazineThree threeBeans;
    @Override
    protected int setLayout() {
        return R.layout.magmagazine_fragment;
    }
    @Override
    protected void initView(View view) {
        tvLineOOne = bindView(R.id.tv_line1_two);
        tvLineOTwo = bindView(R.id.tv_line1_three);
        tvLineOThree = bindView(R.id.tv_line1_four);
        ivLineOOne = bindView(R.id.iv_line1_two);
        ivLineOTwo = bindView(R.id.iv_line1_three);
        ivLineOThree = bindView(R.id.iv_line1_four);

        tvLineTOne = bindView(R.id.tv_line2_two);
        tvLineTTwo = bindView(R.id.tv_line2_three);
        tvLineTThree = bindView(R.id.tv_line2_four);
        ivLineTOne = bindView(R.id.iv_line2_two);
        ivLineTTwo = bindView(R.id.iv_line2_three);
        ivLineTThree = bindView(R.id.iv_line2_four);

        tvLineSOne = bindView(R.id.tv_line3_two);
        tvLineSTwo = bindView(R.id.tv_line3_three);
        tvLineSThree = bindView(R.id.tv_line3_four);
        ivLineSOne = bindView(R.id.iv_line3_two);
        ivLineSTwo = bindView(R.id.iv_line3_three);
        ivLineSThree = bindView(R.id.iv_line3_four);
    }

    @Override
    protected void initData() {
        OneVolley();
        TwoVolley();
        ThreeVolley();
    }

    private void OneVolley() {
        HashMap<String,String> map = new HashMap<>();
        map.put(URLValues.POST_KEY,URLValues.MAGAZINE_ONEVALUES);
        NetHelper.MyRequest(URLValues.MAGAZINE_ONEURL, BaseMagazineOne.class, new NetListener<BaseMagazineOne>() {
            @Override
            public void successListener(BaseMagazineOne response) {
                OneBeans = response;
                tvLineOOne.setText(OneBeans.getData().get(0).getJournal());
                tvLineOTwo.setText(OneBeans.getData().get(1).getJournal());
                tvLineOThree.setText(OneBeans.getData().get(2).getJournal());
                Picasso.with(getContext()).load(OneBeans.getData().get(0).getCover()).fit().into(ivLineOOne);
                Picasso.with(getContext()).load(OneBeans.getData().get(1).getCover()).fit().into(ivLineOTwo);
                Picasso.with(getContext()).load(OneBeans.getData().get(2).getCover()).fit().into(ivLineOThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);

//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(URLValues.MAGAZINE_ONEURL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                OneBeans = gson.fromJson(response,BaseMagazineOne.class);
//                tvLineOOne.setText(OneBeans.getData().get(0).getJournal());
//                tvLineOTwo.setText(OneBeans.getData().get(1).getJournal());
//                tvLineOThree.setText(OneBeans.getData().get(2).getJournal());
//                Picasso.with(getContext()).load(OneBeans.getData().get(0).getCover()).fit().into(ivLineOOne);
//                Picasso.with(getContext()).load(OneBeans.getData().get(1).getCover()).fit().into(ivLineOTwo);
//                Picasso.with(getContext()).load(OneBeans.getData().get(2).getCover()).fit().into(ivLineOThree);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);
    }

    private void TwoVolley() {
        HashMap<String,String> map = new HashMap<>();
        map.put(URLValues.POST_KEY,URLValues.MAGAZINE_TWOVALUES);
        NetHelper.MyRequest(URLValues.MAGAZINE_TWOURL, BaseMagazineTwo.class, new NetListener<BaseMagazineTwo>() {
            @Override
            public void successListener(BaseMagazineTwo response) {
                twoBeans = response;
                tvLineTOne.setText(twoBeans.getData().get(0).getJournal());
                tvLineTTwo.setText(twoBeans.getData().get(1).getJournal());
                tvLineTThree.setText(twoBeans.getData().get(2).getJournal());
                Picasso.with(getContext()).load(twoBeans.getData().get(0).getCover()).fit().into(ivLineTOne);
                Picasso.with(getContext()).load(twoBeans.getData().get(1).getCover()).fit().into(ivLineTTwo);
                Picasso.with(getContext()).load(twoBeans.getData().get(2).getCover()).fit().into(ivLineTThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);

//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(URLValues.MAGAZINE_TWOURL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                twoBeans = gson.fromJson(response,BaseMagazineTwo.class);
//                tvLineTOne.setText(twoBeans.getData().get(0).getJournal());
//                tvLineTTwo.setText(twoBeans.getData().get(1).getJournal());
//                tvLineTThree.setText(twoBeans.getData().get(2).getJournal());
//                Picasso.with(getContext()).load(twoBeans.getData().get(0).getCover()).fit().into(ivLineTOne);
//                Picasso.with(getContext()).load(twoBeans.getData().get(1).getCover()).fit().into(ivLineTTwo);
//                Picasso.with(getContext()).load(twoBeans.getData().get(2).getCover()).fit().into(ivLineTThree);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);
    }

    private void ThreeVolley() {
        HashMap<String,String> map = new HashMap<>();
        map.put(URLValues.POST_KEY,URLValues.MAGAZINE_THREEVALUES);
        NetHelper.MyRequest(URLValues.MAGAZINE_THREEURL, BaseMagazineThree.class, new NetListener<BaseMagazineThree>() {
            @Override
            public void successListener(BaseMagazineThree response) {
                threeBeans = response;
                tvLineSOne.setText(threeBeans.getData().get(0).getJournal());
                tvLineSTwo.setText(threeBeans.getData().get(1).getJournal());
                tvLineSThree.setText(threeBeans.getData().get(2).getJournal());
                Picasso.with(getContext()).load(threeBeans.getData().get(0).getCover()).fit().into(ivLineSOne);
                Picasso.with(getContext()).load(threeBeans.getData().get(1).getCover()).fit().into(ivLineSTwo);
                Picasso.with(getContext()).load(threeBeans.getData().get(2).getCover()).fit().into(ivLineSThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);

//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(URLValues.MAGAZINE_THREEURL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                threeBeans = gson.fromJson(response,BaseMagazineThree.class);
//                tvLineSOne.setText(threeBeans.getData().get(0).getJournal());
//                tvLineSTwo.setText(threeBeans.getData().get(1).getJournal());
//                tvLineSThree.setText(threeBeans.getData().get(2).getJournal());
//                Picasso.with(getContext()).load(threeBeans.getData().get(0).getCover()).fit().into(ivLineSOne);
//                Picasso.with(getContext()).load(threeBeans.getData().get(1).getCover()).fit().into(ivLineSTwo);
//                Picasso.with(getContext()).load(threeBeans.getData().get(2).getCover()).fit().into(ivLineSThree);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);
    }
}
