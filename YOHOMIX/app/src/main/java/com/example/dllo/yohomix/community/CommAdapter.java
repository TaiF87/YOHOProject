package com.example.dllo.yohomix.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yohomix.R;

/**
 * Created by dllo on 16/11/29.
 */
public class CommAdapter extends BaseAdapter{
    private BaseCommunity beens;
    private Context mContext;

    public void setBeens(BaseCommunity beens) {
        this.beens = beens;
        notifyDataSetChanged();
    }

    public CommAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return beens == null ? 0 : beens.getData().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return beens.getData().getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_comm_list,viewGroup,false);
            holder = new CommViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CommViewHolder) view.getTag();
        }
        return view;
    }

    private class CommViewHolder {
        public CommViewHolder(View view) {
        }
    }
}
