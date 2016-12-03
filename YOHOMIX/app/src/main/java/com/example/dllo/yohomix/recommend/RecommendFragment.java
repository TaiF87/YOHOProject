package com.example.dllo.yohomix.recommend;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 16/11/23.
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener {
    private ViewPager vpCycle;
    private ListView lvRecommend;
    private LinearLayout llCircle;
    private ListViewAdapter mAdapter;
    private CarouselAdapter mCarouselAdapter;
    private Handler mHandler;
    private BeanCarousel mBean;
    private ArrayList<MyPoint> mMyPoints;
    private DrawerLayout mDrawer;
    private ImageView ivDrawer,ivSearch;
    private boolean flag = true;
    @Override
    protected int setLayout() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initView(View view) {
        lvRecommend = bindView(R.id.lv_recommend);
        ivDrawer = bindView(R.id.iv_drawer);
        ivSearch = bindView(R.id.iv_search);
        mDrawer = (DrawerLayout) getActivity().findViewById(R.id.activity_main);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
    @Override
    protected void initData() {
        mAdapter = new ListViewAdapter(getContext());
        mCarouselAdapter = new CarouselAdapter(getContext());
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.head_recommend,null);
        vpCycle = (ViewPager) headView.findViewById(R.id.vp_cycle);
        llCircle = (LinearLayout) headView.findViewById(R.id.ll_circle);
        lvRecommend.addHeaderView(headView);
        mMyPoints = new ArrayList<>();
        carouselPic();
        initVolley();
        drawRound();
        jumpClick();
    }
    private void jumpClick() {
        ivSearch.setOnClickListener(this);
        ivDrawer.setOnClickListener(this);
    }

    private void drawRound() {

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                vpCycle.setCurrentItem(vpCycle.getCurrentItem() + 1);
                return false;
            }
        });

        if (flag){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(3000);
                            mHandler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            flag = false;
        }
        mCarouselAdapter.setViewPager(vpCycle);
    }

    private void carouselPic() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLValues.CYCLE_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                mBean = gson.fromJson(response,BeanCarousel.class);
//                mCarouselAdapter.setBean(mBean);
//                mCarouselAdapter.setViewPager(vpCycle);
//                vpCycle.setAdapter(mCarouselAdapter);
//                MyPoint();
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
        HashMap<String,String> map = new HashMap<>();
                map.put(URLValues.POST_KEY,URLValues.CYCLE_VALUES);
        NetHelper.MyRequest(URLValues.CYCLE_URL, BeanCarousel.class, new NetListener<BeanCarousel>() {
            @Override
            public void successListener(BeanCarousel response) {
                mCarouselAdapter.setBean(response);
                mCarouselAdapter.setViewPager(vpCycle);
                vpCycle.setAdapter(mCarouselAdapter);
                mBean = response;
                MyPoint();
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);
    }

    public void MyPoint() {
        for (int i = 0; i < mBean.getData().size(); i++) {
            MyPoint point = new MyPoint(getContext());
            if (i == 0) {
                point.setSelected(true);
            }
            mMyPoints.add(point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30,30);
            params.leftMargin = 10;
            params.rightMargin = 10;
            llCircle.addView(point,params);
        }
        mCarouselAdapter.setPoints(mMyPoints);
    }

    private void initVolley() {
        HashMap<String,String> map = new HashMap<>();
        map.put(URLValues.POST_KEY,URLValues.RECOM_LIST_VALUES);
        NetHelper.MyRequest(URLValues.RECOM_LIST_URL, BeanList.class, new NetListener<BeanList>() {
            @Override
            public void successListener(BeanList response) {
                BeanList bean = response;
                mAdapter.setListBean(bean);
                lvRecommend.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);

//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLValues.RECOM_LIST_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                BeanList bean = gson.fromJson(response,BeanList.class);
//                mAdapter.setListBean(bean);
//                lvRecommend.setAdapter(mAdapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String ,String> map = new HashMap<>();
//                map.put(URLValues.POST_KEY,URLValues.RECOM_LIST_VALUES);
//                return map;
//            }
//        };
//        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_drawer:
                mDrawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.iv_search:
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
