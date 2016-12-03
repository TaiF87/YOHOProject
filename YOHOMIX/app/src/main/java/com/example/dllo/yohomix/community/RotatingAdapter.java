package com.example.dllo.yohomix.community;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.yohomix.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/2.
 */
public class RotatingAdapter extends PagerAdapter{
    private int[] pic;
    private Context mContext;

    public void setPic(int[] pic) {
        this.pic = pic;
        notifyDataSetChanged();
    }

    public RotatingAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return pic == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView ivPic = new ImageView(mContext);
        ivPic.setImageResource(pic[position % pic.length]);
        container.addView(ivPic,ViewPager.LayoutParams.MATCH_PARENT,ViewPager.LayoutParams.WRAP_CONTENT);
        return ivPic;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object){
            container.removeViewAt(position);
        }
    }
}
