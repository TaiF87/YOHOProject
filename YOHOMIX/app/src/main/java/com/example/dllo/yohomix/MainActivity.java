package com.example.dllo.yohomix;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.dllo.yohomix.base.BaseActivity;
import com.example.dllo.yohomix.columns.ColumnsFragment;
import com.example.dllo.yohomix.community.CommunityFragment;
import com.example.dllo.yohomix.magazine.MagazineFragment;
import com.example.dllo.yohomix.recommend.RecommendFragment;
import com.example.dllo.yohomix.video.VideoFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FrameLayout flMain;
    private DrawerLayout mDrawer;
    private RadioButton rbRecommended, rbColumns, rbCommunity, rbVideo, rbMagazine;
    private FragmentManager manager;
    //哈哈哈哈哈哈哈哈哈AHZv
    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mDrawer = bindView(R.id.activity_main);
        flMain = bindView(R.id.fl_main);
        rbRecommended = bindView(R.id.rb_recommended);
        rbColumns = bindView(R.id.rb_columns);
        rbCommunity = bindView(R.id.rb_community);
        rbVideo = bindView(R.id.rb_video);
        rbMagazine = bindView(R.id.rb_magazine);
    }

    @Override
    protected void initData() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, new RecommendFragment());
        transaction.commit();
        setClick(this, rbRecommended, rbColumns, rbCommunity, rbVideo, rbMagazine);

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
                transaction.replace(R.id.fl_main,new ColumnsFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                break;
            case R.id.rb_community:
                transaction.replace(R.id.fl_main,new CommunityFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                break;
            case R.id.rb_video:
                transaction.replace(R.id.fl_main,new VideoFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                break;
            case R.id.rb_magazine:
                transaction.replace(R.id.fl_main,new MagazineFragment());
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                break;

        }
        transaction.commit();
    }
}
