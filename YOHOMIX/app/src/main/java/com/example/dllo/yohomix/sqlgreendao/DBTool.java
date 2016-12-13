package com.example.dllo.yohomix.sqlgreendao;

import org.greenrobot.greendao.query.QueryBuilder;
import java.util.List;

/**
 * Created by dllo on 16/12/9.
 */

public class DBTool {
    private static DBTool ourInstance = new DBTool();
    private static YoHoNewDao mYoHoNewDao;

    public static DBTool getInstance(){
        if (ourInstance == null){
            synchronized (DBTool.class){
                if (ourInstance == null){
                    ourInstance = new DBTool();
                }
            }
        }
        mYoHoNewDao = YoHoApp.getDaoSession().getYoHoNewDao();
        return  ourInstance;
    }

    public DBTool() {
    }

    public void insertYoHoNew(YoHoNew yoHoNew){
        QueryBuilder<YoHoNew> queryBuilder = mYoHoNewDao.queryBuilder()
                .where(YoHoNewDao.Properties.Url.eq(yoHoNew.getUrl()));
        Long size = queryBuilder.buildCount().count();
        if (size > 0) {
           return;
        }
        mYoHoNewDao.insert(yoHoNew);
    }
    public void deleteYoHoNew(YoHoNew yoHoNew){mYoHoNewDao.delete(yoHoNew);}

    public List<YoHoNew> queryAll(){
        List<YoHoNew> list = mYoHoNewDao.loadAll();
        return list;
    }

}
