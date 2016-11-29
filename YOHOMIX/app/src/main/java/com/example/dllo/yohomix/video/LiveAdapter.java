package com.example.dllo.yohomix.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/11/28.
 */
public class LiveAdapter extends BaseAdapter {
    private BaseLive mLive;
    private Context mContext;

    public void setLive(BaseLive live) {
        mLive = live;
        notifyDataSetChanged();
    }

    public LiveAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mLive == null ? 0 : mLive.getData().getContent().size();
    }

    @Override
    public Object getItem(int i) {
        return mLive.getData().getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LiveViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_live, viewGroup, false);
            holder = new LiveViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (LiveViewHolder) view.getTag();
        }
        holder.tvNumber.setText(mLive.getData().getContent().get(i).getOnlineNum()+ "");
        holder.tvData.setText(mLive.getData().getContent().get(i).getTitle());
        holder.tvType.setText("# " + mLive.getData().getContent().get(i).getTag().get(0).getTag_name());
        Date date = new Date(Long.valueOf(mLive.getData().getContent().get(i).getCreate_time()));
        String time = new SimpleDateFormat("MM.dd.yyy").format(date);
        holder.tvTime.setText(String.valueOf(time));
        Picasso.with(mContext).load(mLive.getData().getContent().get(i).getImage()).fit().into(holder.ivPic);

        return view;
    }

    private class LiveViewHolder {
        private ImageView ivPic;
        private TextView tvData, tvNumber, tvType, tvTime;

        public LiveViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_live_pic);
            tvData = (TextView) view.findViewById(R.id.tv_live_data);
            tvNumber = (TextView) view.findViewById(R.id.tv_live_number);
            tvType = (TextView) view.findViewById(R.id.tv_live_type);
            tvTime = (TextView) view.findViewById(R.id.tv_live_time);
        }
    }
}
