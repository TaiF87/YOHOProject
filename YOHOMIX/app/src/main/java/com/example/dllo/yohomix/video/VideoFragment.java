package com.example.dllo.yohomix.video;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseFragment;
import java.util.ArrayList;
/**
 * Created by dllo on 16/11/28.
 */

public class VideoFragment extends BaseFragment {
    private TabLayout tlVideo;
    private ViewPager vpVideo;
    private ArrayList<Fragment> beans;
    @Override
    protected int setLayout() {
        return R.layout.video_fragment;
    }

    @Override
    protected void initView(View view) {
        tlVideo = bindView(R.id.tl_video);
        vpVideo = bindView(R.id.vp_video);
    }
    @Override
    protected void initData() {
        ViewPagerAdapter mVPAdapter = new ViewPagerAdapter(getChildFragmentManager());
        beans = new ArrayList<>();
        beans.add(new VideoVideoFragment());
        beans.add(new LiveFragment());
        mVPAdapter.setFragments(beans);
        vpVideo.setAdapter(mVPAdapter);
        tlVideo.setupWithViewPager(vpVideo);
        tlVideo.setSelectedTabIndicatorColor(Color.WHITE);
        tlVideo.setTabTextColors(Color.RED,Color.BLACK);
    }

}
