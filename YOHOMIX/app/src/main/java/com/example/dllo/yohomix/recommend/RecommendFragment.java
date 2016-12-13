package com.example.dllo.yohomix.recommend;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.video.BaseLive;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 16/11/23.
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener,OnRefreshListener,OnLoadMoreListener {
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
    private HashMap<String,String> mapNew;
    private HashMap<String,String> map;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private boolean flag = true;
    private int num = 0;
    @Override
    protected int setLayout() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initView(View view) {
        lvRecommend = bindView(R.id.swipe_target);
        ivDrawer = bindView(R.id.iv_drawer);
        ivSearch = bindView(R.id.iv_search);
        mSwipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipe_to_load_layout);
        mDrawer = (DrawerLayout) getActivity().findViewById(R.id.activity_main);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
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
        myMap();
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

    private void myMap(){
        map = new HashMap<>();
        map.put("newsEndtime", "0");
        map.put("otherEndTime", "0");
        map.put("magazineType", "3");
        map.put("WallpaperType", "3");
        map.put("scale", "2");
        map.put("num", String.valueOf(num));
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> mm = new HashMap<>();
        mm.put("udid", "00000000000000063aa461b71c4cfcf");
        String a = new Gson().toJson(mm).toString();
        map.put("authInfo", a);
        Gson gson = new Gson();
        String value = gson.toJson(map).toString();
        mapNew = new HashMap<>();
        mapNew.put("parameters", value);
    }
    @Override   //上拉加载
    public void onLoadMore() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setLoadingMore(false);
                NetHelper.MyRequest(URLValues.RECOM_LIST_URL, BeanList.class, new NetListener<BeanList>() {
                    @Override
                    public void successListener(BeanList response) {
                        mAdapter.addMore(response);
                        map.put("num",String.valueOf(num));
                        num += 16;
                        Gson gson = new Gson();
                        String value = gson.toJson(map).toString();
                        mapNew.put(URLValues.POST_KEY,value);
                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                },mapNew);
            }
        },100);
    }

    @Override   //下拉刷新
    public void onRefresh() {
        Log.d("RecommendFragment", "1111111");
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
                NetHelper.MyRequest(URLValues.RECOM_LIST_URL, BeanList.class, new NetListener<BeanList>() {
                    @Override
                    public void successListener(BeanList response) {
                        mAdapter.setListBean(response);

                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                },mapNew);
            }
        },2000);
    }
}
