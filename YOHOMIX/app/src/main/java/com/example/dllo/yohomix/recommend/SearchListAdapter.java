package com.example.dllo.yohomix.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/3.
 */
public class SearchListAdapter extends BaseAdapter {
    private BaseSearchList bean;
    private Context mContext;

    public SearchListAdapter(Context context) {
        mContext = context;
    }

    public void setBean(BaseSearchList bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getData().getContent().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getData().getContent().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SearchViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_search, viewGroup, false);
            holder = new SearchViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (SearchViewHolder) view.getTag();
        }
        if (bean.getData().getContent().get(i).getImgNum() != 0 ){
            holder.llPicNum.setVisibility(View.VISIBLE);
            holder.tvNum.setText(String.valueOf(bean.getData().getContent().get(i).getImgNum()));
        } else {
            holder.llPicNum.setVisibility(View.INVISIBLE);
        }
        holder.tvData.setText(bean.getData().getContent().get(i).getTitle());
        holder.tvTagName.setText("# " + bean.getData().getContent().get(i).getTag().get(0).getTag_name());
        holder.tvCreateTime.setText(String.valueOf(bean.getData().getContent().get(i).getCreate_time()));
        Picasso.with(mContext).load(bean.getData().getContent().get(i).getImage()).fit().into(holder.ivPic);
        return view;

    }

    private class SearchViewHolder {
        private ImageView ivPic;
        private LinearLayout llPicNum;
        private TextView tvData,tvNum,tvTagName,tvCreateTime;
        public SearchViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_searchPic);
            tvNum = (TextView)view.findViewById(R.id.search_pic_num);
            tvData = (TextView) view.findViewById(R.id.search_title);
            tvTagName = (TextView) view.findViewById(R.id.search_tag_name);
            tvCreateTime = (TextView) view.findViewById(R.id.search_create_time);
            llPicNum = (LinearLayout) view.findViewById(R.id.ll_pic_num);
        }
    }
}
