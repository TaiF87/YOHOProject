package com.example.dllo.yohomix.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.dllo.yohomix.R;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by dllo on 16/11/24.
 */
public class ListViewAdapter extends BaseAdapter {
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 2;
    private static final int Type_four = 4;
    private static final int TYPE_COUNT = 10;
    private Context mContext;
    private BeanList mListBean;

    public void setListBean(BeanList listBean) {
        mListBean = listBean;
        notifyDataSetChanged();
    }

    public ListViewAdapter(Context context) {
        mContext = context;
    }



    @Override
    public int getCount() {
        return mListBean.getData() == null ? 0 : mListBean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return mListBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return mListBean.getData().get(position).getType();

    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        ViewHolderOne holderOne = null;
        ViewHolderTwo holderTwo = null;
        if (view == null) {
            switch (type) {
                case TYPE_ONE:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup, false);
                    holderOne = new ViewHolderOne(view);
                    view.setTag(holderOne);
                    break;
                case TYPE_TWO:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_pic, viewGroup, false);
                    holderTwo = new ViewHolderTwo(view);
                    view.setTag(holderTwo);
                    break;
                default:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_other,viewGroup,false);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_ONE:
                    holderOne = (ViewHolderOne) view.getTag();
                    break;
                case TYPE_TWO:
                    holderTwo = (ViewHolderTwo) view.getTag();
                    break;

            }
        }
        switch (type) {
            case TYPE_ONE:
                holderOne.oneData.setText(mListBean.getData().get(i).getParams().get(0).getTitle());
                Date date = new Date(Long.valueOf(mListBean.getData().get(i).getParams().get(0).getCreate_time()));
                String time = new SimpleDateFormat("MM.dd.yyy").format(date);
                holderOne.oneTime.setText(String.valueOf(time));
                if (!mListBean.getData().get(i).getParams().get(0).getImage().isEmpty()) {
                    Picasso.with(mContext).load(mListBean.getData().get(i).getParams().get(0).getImage()).fit().into(holderOne.onePic);
                }
                holderOne.twoData.setText(mListBean.getData().get(i).getParams().get(1).getTitle());
                Date date1 = new Date(Long.valueOf(mListBean.getData().get(i).getParams().get(1).getCreate_time()));
                String time1 = new SimpleDateFormat("MM.dd.yyy").format(date1);
                holderOne.twoTime.setText(String.valueOf(time1));
                Picasso.with(mContext).load(mListBean.getData().get(i).getParams().get(1).getImage()).fit().into(holderOne.twoPic);

                holderOne.threeData.setText(mListBean.getData().get(i).getParams().get(2).getTitle());
                Date date2 = new Date(Long.valueOf(mListBean.getData().get(i).getParams().get(2).getCreate_time()));
                String time2 = new SimpleDateFormat("MM.dd.yyy").format(date2);
                holderOne.threeTime.setText(String.valueOf(time2));
                if (!mListBean.getData().get(i).getParams().get(2).getImage().isEmpty()) {
                    Picasso.with(mContext).load(mListBean.getData().get(i).getParams().get(2).getImage()).fit().into(holderOne.threePic);
                }
                    break;
            case TYPE_TWO:
                holderTwo.recomData.setText(mListBean.getData().get(i).getParams().get(0).getTitle());
                Date date3 = new Date(Long.valueOf(mListBean.getData().get(i).getParams().get(0).getCreate_time()));
                String time3 = new SimpleDateFormat("MM.dd.yyy").format(date3);
                holderTwo.recomTime.setText(String.valueOf(time3));
                Picasso.with(mContext).load(mListBean.getData().get(i).getParams().get(0).getImage()).fit().into(holderTwo.RecomPic);
                break;
        }
        return view;

    }

    public void addMore(BeanList data){
        mListBean.getData().addAll(data.getData());
        notifyDataSetChanged();
    }

    class ViewHolderOne {
        ImageView onePic,twoPic,threePic;
        TextView oneData, oneGoods, oneTime,twoData, twoGoods, twoTime,threeData, threeGoods, threeTime;

        public ViewHolderOne(View view) {
            onePic = (ImageView) view.findViewById(R.id.one_pic);
            oneData = (TextView) view.findViewById(R.id.one_data);
            oneGoods = (TextView) view.findViewById(R.id.one_goods);
            oneTime = (TextView) view.findViewById(R.id.one_time);

            twoPic = (ImageView) view.findViewById(R.id.two_pic);
            twoData = (TextView) view.findViewById(R.id.two_data);
            twoGoods = (TextView) view.findViewById(R.id.two_goods);
            twoTime = (TextView) view.findViewById(R.id.two_time);

            threePic = (ImageView) view.findViewById(R.id.three_pic);
            threeData = (TextView) view.findViewById(R.id.three_data);
            threeGoods = (TextView) view.findViewById(R.id.three_goods);
            threeTime = (TextView) view.findViewById(R.id.three_time);
        }
    }

    class ViewHolderTwo {
        ImageView RecomPic;
        TextView recomData, recomFoot, recomTime;

        public ViewHolderTwo(View view) {
            RecomPic = (ImageView) view.findViewById(R.id.recom_pic);
            recomData = (TextView) view.findViewById(R.id.recom_data);
            recomFoot = (TextView) view.findViewById(R.id.recom_foot);
            recomTime = (TextView) view.findViewById(R.id.recom_time);
        }


    }
}
