package com.example.dllo.yohomix.magazine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.magazine.BaseMagazine;
import com.example.dllo.yohomix.sqlgreendao.DBTool;
import com.example.dllo.yohomix.sqlgreendao.YoHoNew;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by dllo on 16/11/28.
 */
public class MagMagazineFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout llOne, llTwo, llThree, llFour, llFive,
            llSix, llSeven, llEight, llNine;
    private ImageView ivLineOOne, ivLineOTwo, ivLineOThree,
            ivLineTOne, ivLineTTwo, ivLineTThree,
            ivLineSOne, ivLineSTwo, ivLineSThree;
    private TextView tvLineOOne, tvLineOTwo, tvLineOThree,
            tvLineTOne, tvLineTTwo, tvLineTThree,
            tvLineSOne, tvLineSTwo, tvLineSThree;
    private RelativeLayout mDown;
    private BaseMagazine OneBeans,twoBeans, threeBeans;
    private PopupWindow mPop;
    private String url;
    private String body;
    private YoHoNew mYoHoNew;

    @Override
    protected int setLayout() {
        return R.layout.magmagazine_fragment;
    }

    @Override
    protected void initView(View view) {
        tvLineOOne = bindView(R.id.tv_line1_two);
        tvLineOTwo = bindView(R.id.tv_line1_three);
        tvLineOThree = bindView(R.id.tv_line1_four);
        ivLineOOne = bindView(R.id.iv_line1_two);
        ivLineOTwo = bindView(R.id.iv_line1_three);
        ivLineOThree = bindView(R.id.iv_line1_four);

        tvLineTOne = bindView(R.id.tv_line2_two);
        tvLineTTwo = bindView(R.id.tv_line2_three);
        tvLineTThree = bindView(R.id.tv_line2_four);
        ivLineTOne = bindView(R.id.iv_line2_two);
        ivLineTTwo = bindView(R.id.iv_line2_three);
        ivLineTThree = bindView(R.id.iv_line2_four);

        tvLineSOne = bindView(R.id.tv_line3_two);
        tvLineSTwo = bindView(R.id.tv_line3_three);
        tvLineSThree = bindView(R.id.tv_line3_four);
        ivLineSOne = bindView(R.id.iv_line3_two);
        ivLineSTwo = bindView(R.id.iv_line3_three);
        ivLineSThree = bindView(R.id.iv_line3_four);

        llOne = bindView(R.id.ll_line1_two);
        llTwo = bindView(R.id.ll_line1_three);
        llThree = bindView(R.id.ll_line1_four);
        llFour = bindView(R.id.ll_line2_two);
        llFive = bindView(R.id.ll_line2_three);
        llSix = bindView(R.id.ll_line2_four);
        llSeven = bindView(R.id.ll_line3_two);
        llEight = bindView(R.id.ll_line3_three);
        llNine = bindView(R.id.ll_line3_four);

        setClick(this, llOne, llTwo, llThree, llFour, llFive, llSix, llSeven, llEight, llNine);
    }

    @Override
    protected void initData() {
        OneVolley();
        TwoVolley();
        ThreeVolley();
    }

    private void OneVolley() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.MAGAZINE_ONEVALUES);
        NetHelper.MyRequest(URLValues.MAGAZINE_ONEURL, BaseMagazine.class, new NetListener<BaseMagazine>() {
            @Override
            public void successListener(BaseMagazine response) {
                OneBeans = response;
                tvLineOOne.setText(OneBeans.getData().get(0).getJournal());
                tvLineOTwo.setText(OneBeans.getData().get(1).getJournal());
                tvLineOThree.setText(OneBeans.getData().get(2).getJournal());
                Picasso.with(getContext()).load(OneBeans.getData().get(0).getCover()).fit().into(ivLineOOne);
                Picasso.with(getContext()).load(OneBeans.getData().get(1).getCover()).fit().into(ivLineOTwo);
                Picasso.with(getContext()).load(OneBeans.getData().get(2).getCover()).fit().into(ivLineOThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);

    }

    private void TwoVolley() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.MAGAZINE_TWOVALUES);
        NetHelper.MyRequest(URLValues.MAGAZINE_TWOURL, BaseMagazine.class, new NetListener<BaseMagazine>() {
            @Override
            public void successListener(BaseMagazine response) {
                twoBeans = response;
                tvLineTOne.setText(twoBeans.getData().get(0).getJournal());
                tvLineTTwo.setText(twoBeans.getData().get(1).getJournal());
                tvLineTThree.setText(twoBeans.getData().get(2).getJournal());
                Picasso.with(getContext()).load(twoBeans.getData().get(0).getCover()).fit().into(ivLineTOne);
                Picasso.with(getContext()).load(twoBeans.getData().get(1).getCover()).fit().into(ivLineTTwo);
                Picasso.with(getContext()).load(twoBeans.getData().get(2).getCover()).fit().into(ivLineTThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    private void ThreeVolley() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.MAGAZINE_THREEVALUES);
        NetHelper.MyRequest(URLValues.MAGAZINE_THREEURL, BaseMagazine.class, new NetListener<BaseMagazine>() {
            @Override
            public void successListener(BaseMagazine response) {
                threeBeans = response;
                tvLineSOne.setText(threeBeans.getData().get(0).getJournal());
                tvLineSTwo.setText(threeBeans.getData().get(1).getJournal());
                tvLineSThree.setText(threeBeans.getData().get(2).getJournal());
                Picasso.with(getContext()).load(threeBeans.getData().get(0).getCover()).fit().into(ivLineSOne);
                Picasso.with(getContext()).load(threeBeans.getData().get(1).getCover()).fit().into(ivLineSTwo);
                Picasso.with(getContext()).load(threeBeans.getData().get(2).getCover()).fit().into(ivLineSThree);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_line1_two:
                body = OneBeans.getData().get(0).getJournal();
                url = OneBeans.getData().get(0).getCover();
                initPop(body, url);
                break;
            case R.id.ll_line1_three:
                body = OneBeans.getData().get(1).getJournal();
                url = OneBeans.getData().get(1).getCover();
                initPop(body, url);
                break;
            case R.id.ll_line1_four:
                body = OneBeans.getData().get(2).getJournal();
                url = OneBeans.getData().get(2).getCover();
                initPop(body, url);
                break;
            case R.id.ll_line2_two:
                body = twoBeans.getData().get(0).getJournal();
                url = twoBeans.getData().get(0).getCover();
                initPop(body, url);
                break;
            case R.id.ll_line2_three:
                body = twoBeans.getData().get(1).getJournal();
                url = twoBeans.getData().get(1).getCover();
                initPop(body, url);
                break;
            case R.id.ll_line2_four:
                body = twoBeans.getData().get(2).getJournal();
                url = twoBeans.getData().get(2).getCover();
                initPop(body, url);
                break;
            case R.id.ll_line3_two:
                body = threeBeans.getData().get(0).getJournal();
                url = threeBeans.getData().get(0).getCover();
                initPop(body, url);
                break;
            case R.id.ll_line3_three:
                body = threeBeans.getData().get(1).getJournal();
                url = threeBeans.getData().get(1).getCover();
                initPop(body, url);
                break;
            case R.id.ll_line3_four:
                body = threeBeans.getData().get(2).getJournal();
                url = threeBeans.getData().get(2).getCover();
                initPop(body, url);
                break;
            case R.id.close_download:
                mPop.dismiss();
                break;
            case R.id.down_pic:
                DBTool.getInstance().insertYoHoNew(mYoHoNew);
                break;
        }
    }

    private void initPop(String body, String url) {
        if (mPop == null || !mPop.isShowing()) {
            mPop = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_pop, null);
            ImageView pic = (ImageView) view.findViewById(R.id.iv_pop_pic);
            TextView mData = (TextView) view.findViewById(R.id.tv_title_pic);
            mDown = (RelativeLayout) view.findViewById(R.id.down_pic);
            Picasso.with(getContext()).load(url).into(pic);
            mData.setText(body);
            mPop.setContentView(view);
            ImageView back = (ImageView) view.findViewById(R.id.close_download);
            back.setOnClickListener(this);
            mDown.setOnClickListener(this);
            mYoHoNew = new YoHoNew();
            mYoHoNew.setUrl(url);
            mYoHoNew.setData(body);
            mPop.showAsDropDown(view, 0, 0);
        }
    }
}
