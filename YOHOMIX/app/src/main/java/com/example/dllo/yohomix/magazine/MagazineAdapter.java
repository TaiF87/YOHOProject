package com.example.dllo.yohomix.magazine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


/**
 * Created by dllo on 16/11/28.
 */
public class MagazineAdapter extends FragmentPagerAdapter{
    private String[] title = {"杂志","壁纸"};
    private ArrayList<Fragment> beas;

    public void setBeas(ArrayList<Fragment> beas) {
        this.beas = beas;
        notifyDataSetChanged();
    }

    public MagazineAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return beas.get(position);
    }

    @Override
    public int getCount() {
        return beas == null ? 0 : beas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
