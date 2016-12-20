package com.example.dllo.yohomix;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.sqlgreendao.YoHoApp;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/12/14.
 */
public class BackActivity extends BaseActivity{
    private Button btBack,btExit;
//    private PlatformActionListener platformActionListener;
    @Override
    protected int setLayout() {
        return R.layout.activity_back;
    }

    @Override
    protected void initView() {
        btBack = bindView(R.id.back_login);
        btExit = bindView(R.id.back_main);
    }

    @Override
    protected void initData() {
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    // isValid和removeAccount不开启线程，会直接返回。
                    platform.removeAccount(true);// 移除授权

                } else {
                    Toast.makeText(YoHoApp.getContext(), "退出登录", Toast.LENGTH_SHORT).show();
                }
                // 实现接口回调(login中的)
//                platform.setPlatformActionListener(platformActionListener);
                // authorize与showUser单独调用一个即可
//                platform.authorize();//单独授权，OnComplete返回的hashmap是空的
//                platform.showUser(null);//授权并获取用户信息
                setResult(-1);
                finish();
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YoHoApp.getContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
