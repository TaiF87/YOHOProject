package com.example.dllo.yohomix.columns;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.login.LoginActivity;

/**
 * Created by dllo on 16/12/10.
 */
public class HeadTwoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack,ivLogin;
    @Override
    protected int setLayout() {
        return R.layout.activity_twohead;
    }

    @Override
    protected void initView() {
        ivBack = bindView(R.id.two_head_back);
        ivLogin = bindView(R.id.two_head_login);
    }

    @Override
    protected void initData() {
        ivBack.setOnClickListener(this);
        ivLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.two_head_back:
                finish();
                break;
            case R.id.two_head_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
