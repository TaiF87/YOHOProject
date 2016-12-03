package com.example.dllo.yohomix.community;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/29.
 */
public class CommunityFragment extends BaseFragment {
    private ListView mListView;
    private BaseCommunity beans;
    private ListAdapter listAdapter;
    private ViewPager vpRotating;
    private RotatingAdapter mRotatingAdapter;
    private Handler mHandler;
    private int[] pics = {R.mipmap.comtop_one,R.mipmap.comtop};
    @Override
    protected int setLayout() {
        return R.layout.community_fragment;
    }

    @Override
    protected void initView(View view) {
        mListView = bindView(R.id.lv_community);

    }

    @Override
    protected void initData() {
        mRotatingAdapter = new RotatingAdapter(getContext());
        View headCommunity = LayoutInflater.from(getContext()).inflate(R.layout.head_community,null);
        mListView.addHeaderView(headCommunity);
        vpRotating = (ViewPager) headCommunity.findViewById(R.id.vp_community);
        initRotating();
        listVolley();

    }

    private void initRotating() {

        mRotatingAdapter.setPic(pics);
         vpRotating.setAdapter(mRotatingAdapter);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (vpRotating != null && msg.what == 1){
                    vpRotating.setCurrentItem(vpRotating.getCurrentItem() + 1);
                }
                mHandler.sendEmptyMessageDelayed(1,3000);
            }
        };
        vpRotating.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mHandler.sendEmptyMessageDelayed(1,3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeMessages(1);
        mHandler.sendEmptyMessageDelayed(1,3000);
    }

    private void listVolley() {
        listAdapter = new ListAdapter(getContext());
        NetHelper.MyRequest(URLValues.COMMUNITY_LIST_URL, BaseCommunity.class, new NetListener<BaseCommunity>() {
            @Override
            public void successListener(BaseCommunity response) {
                beans = response;
                listAdapter.setBeens(beans);
                mListView.setAdapter(listAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
