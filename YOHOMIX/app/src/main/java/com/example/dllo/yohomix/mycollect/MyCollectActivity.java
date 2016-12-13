package com.example.dllo.yohomix.mycollect;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.sqlgreendao.DBColumns;
import com.example.dllo.yohomix.sqlgreendao.YoHoColumns;

import java.util.List;

/**
 * Created by dllo on 16/12/12.
 */
public class MyCollectActivity extends BaseActivity implements View.OnClickListener {
    private ListView myCollect;
    private ImageView ivBack;
    private List<YoHoColumns> mList;
    @Override
    protected int setLayout() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initView() {
        myCollect = bindView(R.id.lv_mycollect);
        ivBack = bindView(R.id.back_mycolumns);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        MyCollectAdapter adapter = new MyCollectAdapter(this);
        mList = DBColumns.getInstance().queryAll();

        adapter.setBean(mList);
        myCollect.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_mycolumns:
                finish();
                break;
        }
    }
}
