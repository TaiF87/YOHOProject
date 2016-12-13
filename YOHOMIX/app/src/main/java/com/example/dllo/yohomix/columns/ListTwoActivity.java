package com.example.dllo.yohomix.columns;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by dllo on 16/12/10.
 */
public class ListTwoActivity extends BaseActivity{
    private TextView tvData,tvUpDate,tvTitle;
    private ImageView ivBack,ivHeadPic;
    private BaseTwoList mBaseTwoList;
    private ListView lvTwo;
    private HashMap<String,String> mMapSure;
    private Intent mIntent;
    private TwoListAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_twolist;
    }

    @Override
    protected void initView() {
        ivBack = bindView(R.id.two_list_back);
        mIntent = getIntent();
        lvTwo = bindView(R.id.lv_community_two);
        tvTitle = bindView(R.id.two_title);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        initHead();
        mAdapter = new TwoListAdapter(this);
        newMap(mIntent.getStringExtra("id"));
        initVolley();
        initThree();
    }

    private void initVolley() {
        NetHelper.MyRequest(URLValues.COLUMNS_TWOURL, BaseTwoList.class, new NetListener<BaseTwoList>() {
            @Override
            public void successListener(BaseTwoList response) {
                mBaseTwoList = response;
                mAdapter.setBaseTwoList(mBaseTwoList);
                lvTwo.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },mMapSure);
    }

    private void initHead() {

        mIntent.getStringExtra("id");
        mIntent.getStringExtra("title");
        mIntent.getStringExtra("cover");
        mIntent.getStringExtra("summary");
        mIntent.getStringExtra("total");
        tvTitle.setText(mIntent.getStringExtra("title"));
        View headView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.head_columns_two,null);
        lvTwo.addHeaderView(headView);
        tvData = (TextView) headView.findViewById(R.id.two_list_title_head);
        tvUpDate = (TextView) headView.findViewById(R.id.two_list_title_update);
        ivHeadPic = (ImageView) headView.findViewById(R.id.columms_two_list);
        tvData.setText(mIntent.getStringExtra("summary"));
        tvUpDate.setText("更新至" + mIntent.getStringExtra("total") + "篇");
        Picasso.with(this).load(mIntent.getStringExtra("cover")).fit().into(ivHeadPic);
    }
    private void newMap(String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("page", "1");
        map.put("limit", "20");
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> mapnew = new HashMap<>();
        mapnew.put("udid", "00000000000000063aa461b71c4cfcf");
        Gson gson = new Gson();
        String a = gson.toJson(mapnew).toString();
        map.put("authInfo", a);
        String value = gson.toJson(map).toString();

        mMapSure = new HashMap<>();
        mMapSure.put("parameters", value);
    }

    private void initThree() {
        lvTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListTwoActivity.this,ColumnsThreeActivity.class);
                intent.putExtra("img",mBaseTwoList.getData().getContent().get(i).getImage());
                intent.putExtra("data",mBaseTwoList.getData().getContent().get(i).getTitle());
                intent.putExtra("tagname",mBaseTwoList.getData().getContent().get(i).getTag().get(0).getTag_name());
                intent.putExtra("time",mBaseTwoList.getData().getContent().get(i).getCreate_time());
                intent.putExtra("webview",mBaseTwoList.getData().getContent().get(i - 1).getPublishURL());
                startActivity(intent);
            }
        });
    }
}
