package com.example.dllo.yohomix.sqlgreendao;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/12/9.
 */

public class YoHoApp extends Application {
    private static Context sContext;
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }

    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "YoHoNew.db");
        sDaoMaster = new DaoMaster(helper.getWritableDb());
        return sDaoMaster;
    }

    public static DaoSession getDaoSession() {
        if (sDaoSession == null) {
            if (sDaoMaster == null) {
                sDaoMaster = getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }
}
