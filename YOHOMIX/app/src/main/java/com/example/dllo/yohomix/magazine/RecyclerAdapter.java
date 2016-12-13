package com.example.dllo.yohomix.magazine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.yohomix.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/12/1.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.WallViewHolder>{
    private Context mContext;

    List<BaseWallpaper.DataBean.WallpaperListBean.ImagesBean> mBeen;

    public void setBeen(List<BaseWallpaper.DataBean.WallpaperListBean.ImagesBean> been) {
        this.mBeen = been;
        notifyDataSetChanged();
    }

    public RecyclerAdapter(Context context) {
        mContext = context;
    }


    @Override
    public WallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler,parent,false);
        WallViewHolder holder = new WallViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WallViewHolder holder, int position) {
        Picasso.with(mContext).load(mBeen.get(position).
                getThumbImage()).fit().into(holder.wallPic);
    }

    @Override
    public int getItemCount() {
        return mBeen== null? 0 : mBeen.size();
    }

    public class WallViewHolder extends RecyclerView.ViewHolder {
        private ImageView wallPic;
        public WallViewHolder(View itemView) {
            super(itemView);
            wallPic = (ImageView) itemView.findViewById(R.id.recycler_pic);
        }
    }
}
