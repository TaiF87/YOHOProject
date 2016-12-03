package com.example.dllo.yohomix.recommend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/3.
 */
public class SearchAdapter extends FragmentPagerAdapter{
    private static BaseTablayout bean;

    public void setBean(BaseTablayout bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public SearchAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ReuseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getData().get(0).getSubNav().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return bean.getData().get(0).getSubNav().get(position).getChannel_name_cn();
    }

    public static String getMessage(int pos){
        return bean.getData().get(0).getSubNav().get(pos).getId();
    }
}
