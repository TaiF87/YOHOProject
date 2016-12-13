package com.example.dllo.yohomix.mymagazine;

import android.widget.GridView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.sqlgreendao.DBTool;
import com.example.dllo.yohomix.sqlgreendao.YoHoNew;

import java.util.List;

/**
 * Created by dllo on 16/12/9.
 */
public class MyMagazineActivity extends BaseActivity {
    private GridView gvPic;
    private List<YoHoNew> base;

    @Override
    protected int setLayout() {
        return R.layout.activity_mymagazine;
    }

    @Override
    protected void initView() {
        gvPic = bindView(R.id.gv_pic);
    }

    @Override
    protected void initData() {
        GVAdapter adapter = new GVAdapter(this);
        base = DBTool.getInstance().queryAll();
        adapter.setList(base);
        gvPic.setAdapter(adapter);
    }
}

