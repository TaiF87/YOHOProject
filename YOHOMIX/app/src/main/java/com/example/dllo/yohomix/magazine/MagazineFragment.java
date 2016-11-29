package com.example.dllo.yohomix.magazine;

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
public class MagazineFragment extends BaseFragment {
    private TabLayout tlMagazine;
    private ViewPager vpMagazine;
    private ArrayList<Fragment> mFragments;
    private MagazineAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.magazine_fragment;
    }

    @Override
    protected void initView(View view) {
        tlMagazine = bindView(R.id.tl_magazine);
        vpMagazine = bindView(R.id.vp_magazine);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mAdapter = new MagazineAdapter(getChildFragmentManager());
        mFragments.add(new MagMagazineFragment());
        mFragments.add(new WallpaperFragment());
        mAdapter.setBeas(mFragments);
        vpMagazine.setAdapter(mAdapter);
        tlMagazine.setupWithViewPager(vpMagazine);
        tlMagazine.setTabTextColors(Color.BLACK,Color.RED);
        tlMagazine.setSelectedTabIndicatorColor(Color.WHITE);
    }
}
