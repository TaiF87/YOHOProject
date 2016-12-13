package com.example.dllo.yohomix;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.sqlgreendao.YoHoApp;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/13.
 */

public class WelcomeActivity extends BaseActivity {
    private Button btSkip;
    private ImageView WebPic;
    private CountDownTimer mTimer;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        btSkip = bindView(R.id.bt_skip);
        WebPic = bindView(R.id.iv_welcome);
    }

    @Override
    protected void initData() {
        initPic();
        initSkip();
        countDownTimer();
    }

    private void initPic() {
        NetHelper.MyRequest(URLValues.WELCOME_URL, BaseWelcome.class, new NetListener<BaseWelcome>() {
            @Override
            public void successListener(BaseWelcome response) {

                BaseWelcome base = response;
                Picasso.with(YoHoApp.getContext()).load(base.getData().getPic()).fit().into(WebPic);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void countDownTimer() {
        mTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                btSkip.setText("跳过" + l / 1000);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    private void initSkip() {
        btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                mTimer.cancel();//点击跳转取消定时功能

            }
        });
    }
}
