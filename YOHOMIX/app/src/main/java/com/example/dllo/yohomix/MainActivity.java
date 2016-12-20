package com.example.dllo.yohomix;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.columns.ColumnsFragment;
import com.example.dllo.yohomix.community.CommunityFragment;
import com.example.dllo.yohomix.login.LoginActivity;
import com.example.dllo.yohomix.magazine.MagazineFragment;
import com.example.dllo.yohomix.mycollect.MyCollectActivity;
import com.example.dllo.yohomix.mymagazine.MyMagazineActivity;
import com.example.dllo.yohomix.recommend.RecommendFragment;
import com.example.dllo.yohomix.video.VideoFragment;
import java.util.HashMap;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private DrawerLayout mDrawer;
    private LinearLayout mLogin;
    private RadioButton rbRecommended, rbColumns, rbCommunity, rbVideo, rbMagazine;
    private FragmentManager manager;
    private ImageView ivIcon;
    private TextView tvName,myMagazine, myCollect,myQuestions;
    private PlatformActionListener platformActionListener;
    private String mName;
    private String mIcon;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        isLogin();
        ivIcon = bindView(R.id.iv_icon);
        tvName = bindView(R.id.tv_name);
        mDrawer = bindView(R.id.activity_main);
        rbRecommended = bindView(R.id.rb_recommended);
        rbColumns = bindView(R.id.rb_columns);
        rbCommunity = bindView(R.id.rb_community);
        rbVideo = bindView(R.id.rb_video);
        rbMagazine = bindView(R.id.rb_magazine);
        mLogin = bindView(R.id.login_click);
        myMagazine = bindView(R.id.tv_magazine);
        myCollect = bindView(R.id.tv_collection);
        myQuestions = bindView(R.id.tv_questions);
    }

    private void isLogin() {
            Platform qq = ShareSDK.getPlatform(QQ.NAME);
            //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
            try {
                PlatformDb platformDb = qq.getDb();
                mName = platformDb.getUserName();
                mIcon = platformDb.getUserIcon();

                if (!TextUtils.isEmpty(mName)) {
                    tvName.setText(mName);
                    Glide.with(this).load(mIcon).bitmapTransform(new CropCircleTransformation(this)).into(ivIcon);
                }
            } catch (NullPointerException e) {

            }

            platformActionListener = new PlatformActionListener() {

                @Override
                public void onError(Platform arg0, int arg1, Throwable arg2) {
                    arg2.printStackTrace();
                }

                @Override
                public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                    //输出所有授权信息
                    arg0.getDb().exportData();
                }

                @Override
                public void onCancel(Platform arg0, int arg1) {
                }
            };

    }

    @Override
    protected void initData() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, new RecommendFragment());
        transaction.commit();
        setClick(this, rbRecommended, rbColumns, rbCommunity, rbVideo,
                rbMagazine, mLogin, myCollect, myMagazine,myQuestions);

    }

    @Override
    public void onClick(View view) {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.rb_recommended:
                transaction.replace(R.id.fl_main, new RecommendFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case R.id.rb_columns:
                transaction.replace(R.id.fl_main, new ColumnsFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.rb_community:
                transaction.replace(R.id.fl_main, new CommunityFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.rb_video:
                transaction.replace(R.id.fl_main, new VideoFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.login_click:
                if (!TextUtils.isEmpty(mName)){
                    Intent intent = new Intent(this, BackActivity.class);
                    startActivityForResult(intent, 0);
                }else  {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, 0);
                }
                break;
            case R.id.tv_collection:
                Intent intent1 = new Intent(this, MyCollectActivity.class);
                startActivity(intent1);
                break;
            case R.id.rb_magazine:
                transaction.replace(R.id.fl_main, new MagazineFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;

            case R.id.tv_magazine:
                Intent intent2 = new Intent(this, MyMagazineActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_questions:
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    // isValid和removeAccount不开启线程，会直接返回。
                    platform.removeAccount(true);// 移除授权
                    tvName.setText("登录");
                    ivIcon.setImageResource(R.mipmap.default_head);
                    mName = null;
                } else {
                    Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                }
                // 实现接口回调(login中的)
                platform.setPlatformActionListener(platformActionListener);
                // authorize与showUser单独调用一个即可
//                platform.authorize();//单独授权，OnComplete返回的hashmap是空的
//                platform.showUser(null);//授权并获取用户信息
                setResult(-1);
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            Toast.makeText(this, "已经退出", Toast.LENGTH_SHORT).show();
            tvName.setText("登录");
            ivIcon.setImageResource(R.mipmap.default_head);
            mName = null;
            mIcon = null;
        }
        if (data != null && requestCode == 0 && resultCode == 1) {
            mName = data.getStringExtra("name");
            mIcon = data.getStringExtra("icon");
            Glide.with(this).load(mIcon).into(ivIcon);
            tvName.setText(mName);
        }

    }


}
