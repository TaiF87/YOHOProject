package com.example.dllo.yohomix.columns;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.sqlgreendao.DBColumns;
import com.example.dllo.yohomix.sqlgreendao.DBTool;
import com.example.dllo.yohomix.sqlgreendao.YoHoColumns;
import com.example.dllo.yohomix.sqlgreendao.YoHoNew;
import com.example.dllo.yohomix.sqlgreendao.YoHoNewDao;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/12/12.
 */
public class ColumnsThreeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack, ivShare;
    private WebView mWebView;
    private ImageView ivCollect;
    private YoHoColumns mYoHoColumns;
    private Intent mIntent;
    private boolean love = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_columnsthree;
    }

    @Override
    protected void initView() {
        ivBack = bindView(R.id.columns_three_back);
        ivShare = bindView(R.id.columns_share);
        mWebView = bindView(R.id.wv_columns);
        ivCollect = bindView(R.id.columns_collect);
        setClick(this,ivBack,ivShare,ivCollect);

    }

    @Override
    protected void initData() {
        mIntent = getIntent();
        if (DBColumns.getInstance().querySingle(mIntent.getStringExtra("data"))) {
            ivCollect.setImageResource(R.mipmap.love_b_s);
        } else {
            ivCollect.setImageResource(R.mipmap.love_b_new);
        }
        ShareSDK.initSDK(this);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mIntent.getStringExtra("webview"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.columns_three_back:
                finish();
                break;
            case R.id.columns_share:
                showShare();
                break;
            case R.id.columns_collect:
                love = !love;
                if (love) {
                    ivCollect.setImageResource(R.mipmap.love_b_s);
                    mYoHoColumns = new YoHoColumns();
                    mYoHoColumns.setImgUrl(mIntent.getStringExtra("img"));
                    mYoHoColumns.setTitle(mIntent.getStringExtra("data"));
                    mYoHoColumns.setTagName(mIntent.getStringExtra("tagname"));
                    mYoHoColumns.setTime(mIntent.getStringExtra("time"));
                    mYoHoColumns.setWebUrl(mIntent.getStringExtra("webview"));
                    DBColumns.getInstance().insertYoHoColumns(mYoHoColumns);
                } else {
                    if (DBColumns.getInstance().querySingle(mIntent.getStringExtra("data"))) {
                        ivCollect.setImageResource(R.mipmap.love_b_new);
                        DBColumns.getInstance().deleteByTitle(mIntent.getStringExtra("data"));
                    }
                }
//                initCollect();
                break;
        }
    }

    private void initCollect() {
        YoHoColumns yoHoColumns = new YoHoColumns();
        yoHoColumns.setImgUrl(mIntent.getStringExtra("img"));
        yoHoColumns.setTitle(mIntent.getStringExtra("data"));
        yoHoColumns.setTagName(mIntent.getStringExtra("tagname"));
        yoHoColumns.setTime(mIntent.getStringExtra("time"));
        yoHoColumns.setWebUrl(mIntent.getStringExtra("webview"));
        DBColumns.getInstance().insertYoHoColumns(yoHoColumns);
    }

    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


}
