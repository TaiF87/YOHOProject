package com.example.dllo.yohomix.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.yohomix.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/11/29.
 */
public class


ListAdapter extends BaseAdapter {
    private BaseCommunity beens;
    private Context mContext;
    private ArrayList<String> urls;

    public void setBeens(BaseCommunity beens) {
        this.beens = beens;
        notifyDataSetChanged();
    }

    public ListAdapter(Context context) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_comm_list, viewGroup, false);
            holder = new CommViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CommViewHolder) view.getTag();
        }
        urls = new ArrayList<>();
        int type = beens.getData().getList().get(i).getBlocks().size();
        for (int j = 0; j < type; j++) {
            if (beens.getData().getList().get(i).getBlocks().get(j).getTemplateKey().equals("text")) {
                if (beens.getData().getList().get(i).getBlocks().get(j).getContentData() != null) {
                    holder.tvContentData.setText(beens.getData().getList().get(i).getBlocks().get(j).getContentData());
                } else {
                    holder.tvContentData.setVisibility(View.VISIBLE);
                }
            } else {
                String pics = StaticMethod.getSubstring(beens.getData().getList().get(i).getBlocks().get(j).getContentData());
                urls.add(pics);
            }
        }
        if (urls.size() == 1) {
            Picasso.with(mContext).load(urls.get(0)).fit().into(holder.ivContentPicOne);
            holder.ivContentPicTwo.setVisibility(View.INVISIBLE);
            holder.ivContentPicThree.setVisibility(View.INVISIBLE);
            holder.picNum.setVisibility(View.INVISIBLE);

        } else if (urls.size() == 2) {
            holder.ivContentPicTwo.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(urls.get(0)).fit().into(holder.ivContentPicOne);
            Picasso.with(mContext).load(urls.get(1)).fit().into(holder.ivContentPicTwo);
            holder.ivContentPicThree.setVisibility(View.INVISIBLE);
            holder.picNum.setVisibility(View.INVISIBLE);
        } else {
            holder.picNum.setVisibility(View.INVISIBLE);
            holder.ivContentPicTwo.setVisibility(View.VISIBLE);
            holder.ivContentPicThree.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(urls.get(0)).fit().into(holder.ivContentPicOne);
            Picasso.with(mContext).load(urls.get(1)).fit().into(holder.ivContentPicTwo);
            Picasso.with(mContext).load(urls.get(2)).fit().into(holder.ivContentPicThree);
            if (urls.size() > 3) {
                holder.picNum.setVisibility(View.VISIBLE);
                holder.tvNUM.setText(String.valueOf(urls.size()));
            } else {
                holder.picNum.setVisibility(View.INVISIBLE);
            }
        }

        holder.tvNickName.setText(beens.getData().getList().get(i).getAuthorInfo().getNickName());
        holder.tvCreateTime.setText(String.valueOf(beens.getData().getList().get(i).getCreateTime()));
        holder.tvPostsTitle.setText(beens.getData().getList().get(i).getPostsTitle());
        holder.tvForumName.setText(beens.getData().getList().get(i).getForumName());
        holder.tvComment.setText(String.valueOf(beens.getData().getList().get(i).getComment()));
        holder.tvPraise.setText(String.valueOf(beens.getData().getList().get(i).getPraise()));
        String headIcon = StaticMethod.getSubstring(beens.getData().getList().get(i).getAuthorInfo().getHeadIcon());
        Glide.with(mContext).load(headIcon).bitmapTransform(new CropCircleTransformation(mContext)).into(holder.ivHeadIcon);
        return view;
    }

    public class CommViewHolder {
        private LinearLayout picNum;
        private TextView tvNickName, tvCreateTime, tvPostsTitle, tvContentData, tvForumName, tvComment, tvPraise, tvNUM;
        private ImageView ivHeadIcon, ivContentPicOne, ivContentPicTwo, ivContentPicThree;

        public CommViewHolder(View view) {
            tvNUM = (TextView) view.findViewById(R.id.tv_num);
            picNum = (LinearLayout) view.findViewById(R.id.pic_num);
            tvNickName = (TextView) view.findViewById(R.id.tv_nickName);
            tvCreateTime = (TextView) view.findViewById(R.id.tv_createTime);
            tvPostsTitle = (TextView) view.findViewById(R.id.tv_postsTitle);
            tvContentData = (TextView) view.findViewById(R.id.tv_contentData);
            tvForumName = (TextView) view.findViewById(R.id.tv_forumName);
            tvComment = (TextView) view.findViewById(R.id.tv_comment);
            tvPraise = (TextView) view.findViewById(R.id.tv_praise);
            ivHeadIcon = (ImageView) view.findViewById(R.id.iv_headIcon);
            ivContentPicOne = (ImageView) view.findViewById(R.id.iv_contentPicOne);
            ivContentPicTwo = (ImageView) view.findViewById(R.id.iv_contentPicTwo);
            ivContentPicThree = (ImageView) view.findViewById(R.id.iv_contentPicThree);
        }
    }
}
