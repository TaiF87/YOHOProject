package com.example.dllo.yohomix.columns;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/11/26.
 */

public class ColumnsFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener, View.OnClickListener {
    private ListView lvColumns;
    private BaseColumns mBean;
    private ColumnsAdapter mAdapter;
    private BaseColumnsHead beans;
    private ImageView ivBanner, ivHeadPic;
    private TextView tvDescription, tvAnswers, tvNick, tvQuestion, tvTitle, tvAnswer;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private LinearLayout twoHead,twoList;
    private HashMap<String, String> map;
    private HashMap<String, String> mapNew;
    private String num = "0";
    private String me = "";
    private Gson mGson;
    private int j = 1;
    private String mValues;

    @Override
    protected int setLayout() {
        return R.layout.fragment_columns;
    }

    @Override
    protected void initView(View view) {
        lvColumns = bindView(R.id.swipe_target);
        mSwipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipe_to_load_layout);
        twoList = (LinearLayout) lvColumns.findViewById(R.id.ll_two_list);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData() {
        mAdapter = new ColumnsAdapter(getContext());
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.head_columns, null);
        lvColumns.addHeaderView(headView);

        ivBanner = (ImageView) headView.findViewById(R.id.iv_banner);
        ivHeadPic = (ImageView) headView.findViewById(R.id.iv_headPic);
        tvDescription = (TextView) headView.findViewById(R.id.tv_description);
        tvAnswers = (TextView) headView.findViewById(R.id.tv_answers);
        tvNick = (TextView) headView.findViewById(R.id.tv_nick);
        tvQuestion = (TextView) headView.findViewById(R.id.tv_question);
        tvTitle = (TextView) headView.findViewById(R.id.tv_title);
        tvAnswer = (TextView) headView.findViewById(R.id.tv_answer);
        twoHead = (LinearLayout) headView.findViewById(R.id.columms_two_head);
        headPic();
        listVolley();
        myMap();
        initNext();

        lvColumns.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getActivity(),ListTwoActivity.class);
                if ((i - 1) >= 0) {
                    intent1.putExtra("id", mBean.getData().get(i - 1).getId());
                    intent1.putExtra("title", mBean.getData().get(i - 1).getTitle());
                    intent1.putExtra("cover",mBean.getData().get(i - 1).getCover());
                    intent1.putExtra("summary", mBean.getData().get(i - 1).getSummary());
                    intent1.putExtra("total", mBean.getData().get(i - 1).getTotal());
                    startActivity(intent1);
                }
            }
        });
    }

    private void headPic() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.HEAD_COLUMNS_VALUES);
        NetHelper.MyRequest(URLValues.HEAD_COLUMNS_URL, BaseColumnsHead.class, new NetListener<BaseColumnsHead>() {
            @Override
            public void successListener(BaseColumnsHead response) {
                beans = response;
                tvDescription.setText(beans.getData().getDescription());
                tvAnswers.setText("已诊断了" + beans.getData().getAnswers() + "位症案");
                tvNick.setText(beans.getData().getData().get(0).getNick());
                tvQuestion.setText(beans.getData().getData().get(0).getQuestion());
                tvTitle.setText(beans.getData().getTitle());
                tvAnswer.setText(beans.getData().getData().get(0).getAnswer());
                Picasso.with(getContext()).load(beans.getData().getBanner()).fit().into(ivBanner);
                Glide.with(getContext()).load(beans.getData().getData().get(0).getHeadpic()).
                        bitmapTransform(new CropCircleTransformation(getContext())).into(ivHeadPic);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);


    }

    private void listVolley() {
        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.COLUMNS_VALUES);
        NetHelper.MyRequest(URLValues.COLUMNS_URL, BaseColumns.class, new NetListener<BaseColumns>() {
            @Override
            public void successListener(BaseColumns response) {
                mBean = response;
                mAdapter.setBean(mBean);
                lvColumns.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    private void myMap() {
        map = new HashMap<>();
        map.put("limit", "12");
        map.put("lastTime", num);
        map.put("startTime", "0");
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");
        HashMap<String, String> mm = new HashMap<>();
        mm.put("udid", "00000000000000063aa461b71c4cfcf");
        String a = new Gson().toJson(mm).toString();
        map.put("authInfo", a);
        mGson = new Gson();
        mValues = mGson.toJson(map).toString();
        mapNew = new HashMap<>();
        mapNew.put("parameters", mValues);
    }

    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
                NetHelper.MyRequest(URLValues.COLUMNS_URL, BaseColumns.class, new NetListener<BaseColumns>() {
                    @Override
                    public void successListener(BaseColumns response) {
                        mAdapter.setBean(response);
                        mBean = response;
                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                }, mapNew);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        if (j == 1) {
            num = me;
            map.put("lastTime", num);
            mValues = mGson.toJson(map).toString();
            mapNew.put(URLValues.POST_KEY, mValues);
            j = -1;
        }
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setLoadingMore(false);
                NetHelper.MyRequest(URLValues.COLUMNS_URL, BaseColumns.class, new NetListener<BaseColumns>() {
                    @Override
                    public void successListener(BaseColumns response) {
                        mAdapter.addMore(response);
                        mBean.getData().addAll(response.getData());
                        //接口为空时让其重复加载
                        if (response.getData().isEmpty()) {
                            num = "0";
                        } else {
                            num = response.getData().get(response.getData().size() - 1).getCreate_time();
                        }
                        //更新map
                        map.put("lastTime", num);
                        mValues = mGson.toJson(map).toString();
                        mapNew.put(URLValues.POST_KEY, mValues);
                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                }, mapNew);
            }
        }, 2000);
    }

    private void initNext() {
        twoHead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.columms_two_head:
                Intent intent = new Intent(getActivity(),HeadTwoActivity.class);
                startActivity(intent);
                break;

        }
    }
}
