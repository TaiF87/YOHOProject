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
public class VideoAdapter extends BaseAdapter {
    private BaseVideo mBeen;
    private Context mContext;

    public void setBeen(BaseVideo been) {
        mBeen = been;
    }

    public VideoAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mBeen == null ? 0 : mBeen.getData().getContent().size();
    }

    @Override
    public Object getItem(int i) {
        return mBeen.getData().getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VideoViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_video, viewGroup, false);
            holder = new VideoViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (VideoViewHolder) view.getTag();
        }
        holder.tvData.setText(mBeen.getData().getContent().get(i).getTitle());
        holder.tvKinds.setText("# " + mBeen.getData().getContent().get(i).getTag().get(0).getTag_name());
        Date date = new Date(Long.valueOf(mBeen.getData().getContent().get(i).getCreate_time()));
        String time = new SimpleDateFormat("MM.dd.yyy").format(date);
        holder.tvTime.setText(String.valueOf(time));
        Picasso.with(mContext).load(mBeen.getData().getContent().get(i).getImage()).fit().into(holder.ivPic);
        return view;
    }

    private class VideoViewHolder {
        private ImageView ivPic;
        private TextView tvData,tvKinds,tvTime;
        public VideoViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_video_pic);
            tvData = (TextView) view.findViewById(R.id.tv_video_data);
            tvKinds = (TextView) view.findViewById(R.id.tv_video_kinds);
            tvTime = (TextView) view.findViewById(R.id.tv_video_time);
        }
    }
}
