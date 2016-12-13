package com.example.dllo.yohomix.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.base.BaseActivity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/12/13.
 */
public class VideoTwoActivity  extends BaseActivity implements View.OnClickListener{
    private ImageView ivBack,ivShare,ivCollect;
    private VideoView mVideo;
    private WebView mWebView;
    private Intent mIntent;

    @Override
    protected int setLayout() {
        return R.layout.activity_videotwo;
    }

    @Override
    protected void initView() {
        ivBack = bindView(R.id.video_two_back);
        ivShare = bindView(R.id.video_share);
        ivCollect = bindView(R.id.video_collect);
        mVideo = bindView(R.id.vv_video);
        mWebView = bindView(R.id.wv_video);

        setClick(this,ivBack,ivShare,ivCollect,mVideo,mWebView);
    }



    @Override
    protected void initData() {
        ShareSDK.initSDK(this);
        mIntent = getIntent();

        videoPlay();
        webView();
    }

    private void shareShow() {
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

    private void videoPlay() {
        final String video = mIntent.getStringExtra("videoUrl");
        Uri uri = Uri.parse(video);
        mVideo.setVideoURI(uri);
        mVideo.setMediaController(new MediaController(this));
        mVideo.start();
        /**
         * 准备播放监听
         */
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        /**
         * 播放结束的监听
         */
        mVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVideo.setVideoPath(video);
                mVideo.start();
            }
        });
    }

    private void webView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mIntent.getStringExtra("publishURL"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_two_back:
                finish();
                break;
            case R.id.video_share:
                shareShow();
                break;
        }
    }
}
