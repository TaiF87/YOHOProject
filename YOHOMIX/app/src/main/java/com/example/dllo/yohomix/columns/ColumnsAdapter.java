package com.example.dllo.yohomix.columns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.recommend.BeanCarousel;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/26.
 */
public class ColumnsAdapter extends BaseAdapter{
    private BaseColumns mBean;
    private Context mContext;

    public ColumnsAdapter(Context context) {
        mContext = context;
    }

    public void setBean(BaseColumns bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBean == null ? 0 : mBean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return mBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ColumnViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_columns,viewGroup,false);
            holder = new ColumnViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ColumnViewHolder) view.getTag();
        }
        holder.tvPic.setText(mBean.getData().get(i).getTitle());
        holder.tvData.setText(mBean.getData().get(i).getSummary());
        holder.tvPrompt.setText("更新至" + mBean.getData().get(i).getTotal() + "篇");
        Picasso.with(mContext).load(mBean.getData().get(i).getCover()).fit().into(holder.ivColumns);
        return view;
    }
    public void addMore(BaseColumns data){
        mBean.getData().addAll(data.getData());
        notifyDataSetChanged();
    }
    private class ColumnViewHolder {
        private ImageView ivColumns;
        private TextView tvPic,tvData,tvPrompt;
        public ColumnViewHolder(View view) {
            ivColumns = (ImageView) view.findViewById(R.id.iv_columns);
            tvPic = (TextView) view.findViewById(R.id.tv_data_pic);
            tvData = (TextView) view.findViewById(R.id.tv_columns_data);
            tvPrompt = (TextView) view.findViewById(R.id.tv_columns);
        }
    }
}
