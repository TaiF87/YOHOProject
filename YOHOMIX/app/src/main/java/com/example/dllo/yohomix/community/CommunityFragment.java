package com.example.dllo.yohomix.community;

import android.view.View;
import android.widget.ListView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseFragment;

/**
 * Created by dllo on 16/11/29.
 */
public class CommunityFragment extends BaseFragment {
    private ListView mListView;
    private BaseCommunity beans;
    @Override
    protected int setLayout() {
        return R.layout.community_fragment;
    }

    @Override
    protected void initView(View view) {
        mListView = bindView(R.id.lv_community);
    }

    @Override
    protected void initData() {
        beans = new BaseCommunity();
        CommAdapter adapter = new CommAdapter();
    }
}
