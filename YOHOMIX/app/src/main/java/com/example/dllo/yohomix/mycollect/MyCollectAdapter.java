package com.example.dllo.yohomix.mycollect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.sqlgreendao.YoHoColumns;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/12/12.
 */
public class MyCollectAdapter extends BaseAdapter {
    private List<YoHoColumns> bean;
    private Context mContext;

    public void setBean(List<YoHoColumns> bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public MyCollectAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.size();
    }

    @Override
    public Object getItem(int i) {
        return bean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CollectViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mycollect, viewGroup, false);
            holder = new CollectViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CollectViewHolder) view.getTag();
        }
        holder.tvTitle.setText(bean.get(i).getTitle());
        holder.tvTagName.setText(bean.get(i).getTagName());
        holder.tvTime.setText(bean.get(i).getTime());
        Picasso.with(mContext).load(bean.get(i).getImgUrl()).fit().into(holder.ivPic);
        return view;
    }

    private class CollectViewHolder {
        private ImageView ivPic;
        private TextView tvTitle, tvTagName, tvTime;

        public CollectViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_mycollect);
            tvTitle = (TextView) view.findViewById(R.id.tv_collect_title);
            tvTagName = (TextView) view.findViewById(R.id.tv_collect_tagName);
            tvTime = (TextView) view.findViewById(R.id.tv_collect_time);
        }
    }
}
