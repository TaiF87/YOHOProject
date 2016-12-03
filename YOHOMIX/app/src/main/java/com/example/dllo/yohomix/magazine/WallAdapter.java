package com.example.dllo.yohomix.magazine;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.yohomix.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/1.
 */
public class WallAdapter extends BaseAdapter {
    private BaseWallpaper mBeen;
    private Context mContext;

    public void setBeen(BaseWallpaper been) {
        mBeen = been;
    }

    public WallAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mBeen == null ? 0 : mBeen.getData().getWallpaperList().size();
    }

    @Override
    public Object getItem(int i) {
        return mBeen.getData().getWallpaperList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        WallViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_wall, viewGroup, false);
            holder = new WallViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (WallViewHolder) view.getTag();
        }
        holder.tvJournal.setText(mBeen.getData().getWallpaperList().get(i).getJournal());
        holder.tvMonth.setText(mBeen.getData().getWallpaperList().get(i).getMonth());
        RecyclerAdapter adapter = new RecyclerAdapter(mContext);
        List<BaseWallpaper.DataBean.WallpaperListBean.ImagesBean> been = mBeen.getData().getWallpaperList().get(i).getImages();
//        adapter.setBeans(mBeen);
        adapter.setBeen(been);
        holder.mRecyclerView.setAdapter(adapter);
//        adapter.setPos(i);
        return view;

    }

    private class WallViewHolder {
        private RecyclerView mRecyclerView;
        private TextView tvJournal, tvMonth;

        public WallViewHolder(View view) {
            tvJournal = (TextView) view.findViewById(R.id.tv_journal);
            tvMonth = (TextView) view.findViewById(R.id.tv_month);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_wall);
            GridLayoutManager manager = new GridLayoutManager(mContext,2);
            mRecyclerView.setLayoutManager(manager);
        }

    }
}
