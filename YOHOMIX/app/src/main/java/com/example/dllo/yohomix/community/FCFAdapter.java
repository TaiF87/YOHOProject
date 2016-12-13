package com.example.dllo.yohomix.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.dalong.francyconverflow.FancyCoverFlowAdapter;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.Tolls;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/12/7.
 */
public class FCFAdapter extends FancyCoverFlowAdapter {
    private Context mContext;
    private BaseFCF mBean;

    public void setBean(BaseFCF bean) {
        mBean = bean;
        notifyDataSetChanged();
    }

    public FCFAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getCoverFlowItem(int position, View reusableView, ViewGroup parent) {
        FCFViewHolder holder;
        if (reusableView == null) {
            reusableView = LayoutInflater.from(mContext).inflate(R.layout.item_fcf, null);
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth() + 600;
            reusableView.setLayoutParams(new FancyCoverFlow.LayoutParams(width / 3, FancyCoverFlow.LayoutParams.WRAP_CONTENT));
            holder = new FCFViewHolder();
            holder.mLinearLayout = (LinearLayout) reusableView.findViewById(R.id.ll_fcf);
            holder.ivForumPic = (ImageView) reusableView.findViewById(R.id.iv_forumPic);
            holder.ivHeadIcon = (ImageView) reusableView.findViewById(R.id.iv_head_fcfIcon);
            holder.ivBgPic = (ImageView) reusableView.findViewById(R.id.iv_bgPic);
            holder.tvForumName = (TextView) reusableView.findViewById(R.id.tv_fcf_forumName);
            holder.tvPostsNum = (TextView) reusableView.findViewById(R.id.tv_postsNum);
            holder.tvCommentsNum = (TextView) reusableView.findViewById(R.id.tv_commentsNum);
            holder.tvPraiseNum = (TextView) reusableView.findViewById(R.id.tv_praiseNum);
            holder.tvPostsTitle = (TextView) reusableView.findViewById(R.id.tv_fcf_postsTitle);
            holder.tvNewPostsTitle = (TextView) reusableView.findViewById(R.id.tv_newPost_postsTitle);
            holder.tvOneDayAddNum = (TextView) reusableView.findViewById(R.id.oneDayAddNum);
            reusableView.setTag(holder);
        } else {
            holder = (FCFViewHolder) reusableView.getTag();
        }
        if (position == 0){
            holder.mLinearLayout.setBackgroundResource(R.mipmap.yohocommid1);
        } else if (position == mBean.getData().getForumInfo().size() + 1){
            holder.mLinearLayout.setBackgroundResource(R.mipmap.yohocommid1);
        } else {
            Glide.with(mContext).load(mBean.getData().getForumInfo().get(position - 1).getForumPic()).into(holder.ivForumPic);
            Glide.with(mContext).load(Tolls.cutStrings(mBean.getData().getForumInfo().get(position-1).getHotPost().getUser().
                    getHeadIcon())).bitmapTransform(new CropCircleTransformation(mContext)).into(holder.ivHeadIcon);
            Glide.with(mContext).load(Tolls.cutStrings(mBean.getData().getForumInfo().get(position-1).getNewPost().getUser().
                    getHeadIcon())).bitmapTransform(new CropCircleTransformation(mContext)).into(holder.ivBgPic);
            holder.tvForumName.setText(mBean.getData().getForumInfo().get(position-1).getForumName());
            holder.tvPostsNum.setText("帖子" + String.valueOf(mBean.getData().getForumInfo().get(position-1).getPostsNum()));
            holder.tvCommentsNum.setText("回复" + String.valueOf(mBean.getData().getForumInfo().get(position-1).getCommentsNum()));
            holder.tvPraiseNum.setText("赞" + String.valueOf(mBean.getData().getForumInfo().get(position-1).getPraiseNum()));
            holder.tvPostsTitle.setText(mBean.getData().getForumInfo().get(position-1).getHotPost().getPostsTitle());
            holder.tvNewPostsTitle.setText(mBean.getData().getForumInfo().get(position-1).getNewPost().getPostsTitle());
            holder.tvOneDayAddNum.setText(String.valueOf(mBean.getData().getForumInfo().get(position-1).getOneDayAddNum()) + "条更新");
        }
        return reusableView;
    }

    @Override
    public int getCount() {
        return mBean == null ? 0 : mBean.getData().getForumInfo().size() + 2;
    }

    @Override
    public Object getItem(int i) {
        return mBean.getData().getForumInfo().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class FCFViewHolder {
         ImageView ivForumPic, ivHeadIcon, ivBgPic;
         TextView tvForumName, tvPostsNum, tvCommentsNum, tvPraiseNum,
                tvPostsTitle, tvNewPostsTitle, tvOneDayAddNum;
         LinearLayout mLinearLayout;
    }
}
