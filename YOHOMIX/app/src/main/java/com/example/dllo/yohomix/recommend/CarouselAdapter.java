package com.example.dllo.yohomix.recommend;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.yohomix.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/26.
 */
public class CarouselAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private BeanCarousel mBean;
    private ViewPager mViewPager;
    private Context mContext;
    private ArrayList<MyPoint> mPoints;

    public void setPoints(ArrayList<MyPoint> points) {
        mPoints = points;
    }

    public void setBean(BeanCarousel bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        notifyDataSetChanged();
    }

    public CarouselAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mBean == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_carousel, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.iv_carousel);
//      //轮播图,数组越界 取余 才能实现循环轮播
        Picasso.with(mContext).load(mBean.getData().get(position % mBean.getData().size()).getImage()).fit().into(img);
        container.addView(view);
        mViewPager.addOnPageChangeListener(this);
        return view;
    }

    /**
     * 当页面在滑动的时候会调用此方法，在滑动被停止之前，
     * 此方法会一直得到调用。其中三个参数的含义分别为：
     * arg0 :当前页面，及你点击滑动的页面
     * arg1:当前页面偏移的百分比
     * arg2:当前页面偏移的像素位置
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //此方法是页面跳转完后得到调用，arg0是你当前选中的页面的Position（位置编号）。
    @Override
    public void onPageSelected(int position) {
        int a = position % mBean.getData().size();
        for (MyPoint point : mPoints) {
            point.setSelected(false);
        }
        if (a == position % mPoints.size()){
            mPoints.get(a).setSelected(true);
        }
    }

    @Override   //状态改变时调用,有三种状态（0，1，2）,三种状态的变化顺序为（1，2，0）;
    // state==1的时辰默示正在滑动，state==2时滑动完毕，state==0时什么都没做。
    public void onPageScrollStateChanged(int state) {

    }
}
