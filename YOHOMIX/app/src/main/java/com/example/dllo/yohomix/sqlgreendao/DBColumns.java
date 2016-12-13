package com.example.dllo.yohomix.sqlgreendao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/13.
 */

public class DBColumns {
    private static DBColumns ourInstance = new DBColumns();
    private static YoHoColumnsDao mYoHoColumnsDao;

    public static DBColumns getInstance(){
        if (ourInstance == null){
            synchronized (DBColumns.class){
                if (ourInstance == null){
                    ourInstance = new DBColumns();
                }
            }
        }
        mYoHoColumnsDao = YoHoApp.getDaoSession().getYoHoColumnsDao();
        return  ourInstance;
    }

    public DBColumns() {
    }

    public void insertYoHoColumns(YoHoColumns yoHoColumns){
        QueryBuilder<YoHoColumns> queryBuilder = mYoHoColumnsDao.queryBuilder()
                .where(YoHoColumnsDao.Properties.Title.eq(yoHoColumns.getTitle()));
        Long size = queryBuilder.buildCount().count();
        if (size > 0) {
            return;
        }
        mYoHoColumnsDao.insert(yoHoColumns);
    }
    public void deleteYoHoColumns(YoHoColumns yoHoColumns){mYoHoColumnsDao.delete(yoHoColumns);}

    public List<YoHoColumns> queryAll(){
        List<YoHoColumns> list = mYoHoColumnsDao.loadAll();
        return list;
    }

}
