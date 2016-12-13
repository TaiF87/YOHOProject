package com.example.dllo.yohomix.columns;

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
 * Created by dllo on 16/12/10.
 */
public class TwoListAdapter extends BaseAdapter{
    private BaseTwoList mBaseTwoList;
    private Context mContext;

    public void setBaseTwoList(BaseTwoList baseTwoList) {
        mBaseTwoList = baseTwoList;
        notifyDataSetChanged();
    }

    public TwoListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mBaseTwoList == null ? 0 : mBaseTwoList.getData().getContent().size();
    }

    @Override
    public Object getItem(int i) {
        return mBaseTwoList.getData().getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TwoListViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_two_list,viewGroup,false);
            holder = new TwoListViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (TwoListViewHolder) view.getTag();
        }
        Picasso.with(mContext).load(mBaseTwoList.getData().getContent().get(i).getImage()).fit().into(holder.ivPic);
        holder.tvTitle.setText(mBaseTwoList.getData().getContent().get(i).getTitle());
        holder.tvTagName.setText("#" + mBaseTwoList.getData().getContent().get(i).getTag().get(0).getTag_name());
        Date date = new Date(Long.valueOf(mBaseTwoList.getData().getContent().get(i).getCreate_time()));
        String time = new SimpleDateFormat("MM.dd.yyy").format(date);
        holder.tvTime.setText(String.valueOf(time));
        return view;
    }

    private class TwoListViewHolder {
        private TextView tvTitle,tvTagName,tvTime;
        private ImageView ivPic;
        public TwoListViewHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.two_list_title);
            tvTagName = (TextView) view.findViewById(R.id.two_list_tag_name);
            tvTime = (TextView) view.findViewById(R.id.two_list_create_time);
            ivPic = (ImageView) view.findViewById(R.id.two_list_image);
        }
    }
}
