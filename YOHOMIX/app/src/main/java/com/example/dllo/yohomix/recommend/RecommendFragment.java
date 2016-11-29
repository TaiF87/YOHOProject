package com.example.dllo.yohomix.recommend;

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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 16/11/23.
 */
public class RecommendFragment extends BaseFragment {
    private ViewPager vpCycle;
    private ListView lvRecommend;
    private LinearLayout llCircle;
    private ListViewAdapter mAdapter;
    private CarouselAdapter mCarouselAdapter;
    private Handler mHandler;
    private BeanCarousel mBean;
    private ArrayList<MyPoint> mMyPoints;
    private DrawerLayout mDrawer;
    private ImageView ivDrawer;
    private boolean flag = true;
    @Override
    protected int setLayout() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initView(View view) {
        lvRecommend = bindView(R.id.lv_recommend);
        ivDrawer = bindView(R.id.iv_drawer);
        mDrawer = (DrawerLayout) getActivity().findViewById(R.id.activity_main);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
    @Override
    protected void initData() {

        ivDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });
        mAdapter = new ListViewAdapter(getContext());
        mCarouselAdapter = new CarouselAdapter(getContext());
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.head_recommend,null);
        vpCycle = (ViewPager) headView.findViewById(R.id.vp_cycle);
        llCircle = (LinearLayout) headView.findViewById(R.id.ll_circle);
        lvRecommend.addHeaderView(headView);
        mMyPoints = new ArrayList<>();
        CarouselPic();
        initVolley();
        DrawRound();


    }

    private void DrawRound() {

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

    private void CarouselPic() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLValues.CYCLE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                mBean = gson.fromJson(response,BeanCarousel.class);
                mCarouselAdapter.setBean(mBean);
                mCarouselAdapter.setViewPager(vpCycle);
                vpCycle.setAdapter(mCarouselAdapter);
                MyPoint();
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLValues.RECOM_LIST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                BeanList bean = gson.fromJson(response,BeanList.class);
                mAdapter.setListBean(bean);
                lvRecommend.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> map = new HashMap<>();
                map.put(URLValues.POST_KEY,URLValues.RECOM_LIST_VALUES);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
