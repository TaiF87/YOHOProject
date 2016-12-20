package com.example.dllo.yohomix.mycollect;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.sqlgreendao.DBColumns;
import com.example.dllo.yohomix.sqlgreendao.YoHoColumns;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/12/14.
 */
public class CollectDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack, ivShare;
    private WebView mWebView;
    private ImageView ivCollect;
    private YoHoColumns mYoHoColumns;
    private boolean heart = false;
    private Intent mIntent;

    @Override
    protected int setLayout() {
        return R.layout.activity_collectdetails;
    }

    @Override
    protected void initView() {
        ivBack = bindView(R.id.columns_details_back);
        ivShare = bindView(R.id.columns_details_share);
        mWebView = bindView(R.id.wb_columns_dateils);
        ivCollect = bindView(R.id.iv_columns_details);
    }

    @Override
    protected void initData() {
        mIntent = getIntent();
        mWebView.loadUrl(mIntent.getStringExtra("webdetails"));
//        if (DBColumns.getInstance().querySingle(mIntent.getStringExtra("data"))) {
//            ivCollect.setImageResource(R.mipmap.love_b_s);
//        } else {
//            ivCollect.setImageResource(R.mipmap.love_b_new);
//        }
        ivShare.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ShareSDK.initSDK(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.columns_details_back:
                finish();
                break;
            case R.id.columns_details_share:
                showShare();
                break;
            case R.id.iv_columns_details:
//                heart = !heart;
//                if (heart) {
//                    ivCollect.setImageResource(R.mipmap.love_b_s);
//                    mYoHoColumns = new YoHoColumns();
//                    mYoHoColumns.setImgUrl(mIntent.getStringExtra("img"));
//                    mYoHoColumns.setTitle(mIntent.getStringExtra("data"));
//                    mYoHoColumns.setTagName(mIntent.getStringExtra("tagname"));
//                    mYoHoColumns.setTime(mIntent.getStringExtra("time"));
//                    mYoHoColumns.setWebUrl(mIntent.getStringExtra("webview"));
//                    DBColumns.getInstance().insertYoHoColumns(mYoHoColumns);
//                } else {
//                    if (DBColumns.getInstance().querySingle(mIntent.getStringExtra("data"))) {
//                        ivCollect.setImageResource(R.mipmap.love_b_new);
//                        DBColumns.getInstance().deleteByTitle(mIntent.getStringExtra("data"));
//                    }
//                }
        }
    }
}
