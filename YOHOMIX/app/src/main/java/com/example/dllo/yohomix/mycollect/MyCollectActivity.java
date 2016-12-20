package com.example.dllo.yohomix.mycollect;

import android.content.Intent;
import android.opengl.Visibility;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.columns.ColumnsThreeActivity;
import com.example.dllo.yohomix.sqlgreendao.DBColumns;
import com.example.dllo.yohomix.sqlgreendao.YoHoColumns;

import java.util.List;

/**
 * Created by dllo on 16/12/12.
 */
public class MyCollectActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView myCollect;
    private ImageView ivBack;
    private TextView tvEditor;
    private List<YoHoColumns> mList;
    private MyCollectAdapter mAdapter;
    private boolean chose = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initView() {
        myCollect = bindView(R.id.lv_mycollect);
        ivBack = bindView(R.id.back_mycolumns);
        tvEditor = bindView(R.id.collection_editor);
        ivBack.setOnClickListener(this);
        tvEditor.setOnClickListener(this);
        myCollect.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        mAdapter = new MyCollectAdapter(this);
        mList = DBColumns.getInstance().queryAll();
        mAdapter.setBean(mList);
        myCollect.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_mycolumns:
                finish();
                break;
            case R.id.collection_editor:
                chose = !chose;
                if (chose) {
                    tvEditor.setText("取消");
                    mAdapter.isChose(chose);
                } else {
                    tvEditor.setText("编辑");
                    mAdapter.isChose(chose);
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, CollectDetailsActivity.class);
        intent.putExtra("webdetails", mList.get(i).getWebUrl());
        startActivity(intent);
    }
}
