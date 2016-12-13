package com.example.dllo.yohomix.community;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.android.volley.VolleyError;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.login.LoginActivity;
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
    private FancyCoverFlow mFancyCoverFlow;
    private Handler mHandler;
    private ImageView ivLogin;
    private int[] pics = {R.mipmap.comtop_one,R.mipmap.comtop};
    @Override
    protected int setLayout() {
        return R.layout.community_fragment;
    }

    @Override
    protected void initView(View view) {
        mListView = bindView(R.id.lv_community);
        ivLogin = bindView(R.id.community_login);
    }

    @Override
    protected void initData() {
        jumpLogin();
        mRotatingAdapter = new RotatingAdapter(getContext());
        View headCommunity = LayoutInflater.from(getContext()).inflate(R.layout.head_community,null);
        mListView.addHeaderView(headCommunity);
        vpRotating = (ViewPager) headCommunity.findViewById(R.id.vp_community);
        mFancyCoverFlow = (FancyCoverFlow) headCommunity.findViewById(R.id.fancyCoverFlow);
        fancyCoverFlow();
        initRotating();
        listVolley();
    }

    private void fancyCoverFlow() {
        NetHelper.MyRequest(URLValues.FCF_URL, BaseFCF.class, new NetListener<BaseFCF>() {
            @Override
            public void successListener(BaseFCF response) {
                FCFAdapter fcfAdapter = new FCFAdapter(getContext());
                fcfAdapter.setBean(response);
                mFancyCoverFlow.setAdapter(fcfAdapter);
                fcfAdapter.notifyDataSetChanged();

                mFancyCoverFlow.setUnselectedAlpha(1);
                mFancyCoverFlow.setUnselectedSaturation(0.6f);
                mFancyCoverFlow.setUnselectedScale(0.3f);
                mFancyCoverFlow.setSpacing(-20);
                mFancyCoverFlow.setMaxRotation(10);
                mFancyCoverFlow.setScaleDownGravity(0.5f);
                mFancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });


    }

    private void jumpLogin() {
        ivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
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
