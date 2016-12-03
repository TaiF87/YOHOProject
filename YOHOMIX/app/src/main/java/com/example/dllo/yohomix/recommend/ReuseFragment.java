package com.example.dllo.yohomix.recommend;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.video.BaseLive;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;

import java.util.HashMap;

/**
 * Created by dllo on 16/12/3.
 */

public class ReuseFragment extends BaseFragment {
    private ListView lvSearch;
    private BaseTablayout beans;
    @Override
    protected int setLayout() {
        return R.layout.search_fragment;
    }

    @Override
    protected void initView(View view) {
        lvSearch = bindView(R.id.lv_search);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String msg = bundle.get("key").toString();
        HashMap<String,String> map = new HashMap<>();
        PostBody post = new PostBody();
        post.setChannelId(msg);
        map.put(URLValues.POST_KEY,post.m(post));
        NetHelper.MyRequest(URLValues.LIST_URL, BaseSearchList.class, new NetListener<BaseSearchList>() {
            @Override
            public void successListener(BaseSearchList response) {
                SearchListAdapter adapter = new SearchListAdapter(getContext());
                adapter.setBean(response);
                lvSearch.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        },map);
    }

    public static ReuseFragment newInstance(int pos) {
        Bundle bundle = new Bundle();
        String message = SearchAdapter.getMessage(pos);
        bundle.putString("key",message);
        ReuseFragment fragment = new ReuseFragment();
        fragment.setArguments(bundle);
        return fragment;

    }
}
