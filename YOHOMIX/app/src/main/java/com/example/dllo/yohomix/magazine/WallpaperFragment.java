package com.example.dllo.yohomix.magazine;

import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.URLValues;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.volley.NetHelper;
import com.example.dllo.yohomix.volley.NetListener;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/11/28.
 */
public class WallpaperFragment extends BaseFragment {
    private ListView lvWall;
    private BaseWallpaper beans;
    private WallAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.wallpaper_fragment;
    }

    @Override
    protected void initView(View view) {
        lvWall = bindView(R.id.lv_wall);
    }

    @Override
    protected void initData() {
        getInternet();

    }

    private void getInternet() {
        mAdapter = new WallAdapter(getContext());
        NetHelper.MyRequest(URLValues.WALLPAPER_URL, BaseWallpaper.class, new NetListener<BaseWallpaper>() {
            @Override
            public void successListener(BaseWallpaper response) {
                beans = response;
                mAdapter.setBeen(beans);
                lvWall.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
