package com.example.dllo.yohomix.mymagazine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.sqlgreendao.YoHoNew;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/12/9.
 */
public class GVAdapter extends BaseAdapter {

    private Context mContext;
    private List<YoHoNew> mList;

    public void setList(List<YoHoNew> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public GVAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GVViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_grid,viewGroup,false);
            holder = new GVViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (GVViewHolder) view.getTag();
        }
        holder.tvData.setText(mList.get(i).getData());
        Picasso.with(mContext).load(mList.get(i).getUrl()).into(holder.ivPic);
        return view;
    }

    private class GVViewHolder {
        private ImageView ivPic;
        private TextView tvData;
        public GVViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_gv_pic);
            tvData = (TextView) view.findViewById(R.id.tv_gv_data);
        }
    }
}
