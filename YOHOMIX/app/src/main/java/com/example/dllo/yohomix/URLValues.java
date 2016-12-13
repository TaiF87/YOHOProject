package com.example.dllo.yohomix;

/**
 * Created by dllo on 16/11/24.
 */

public class URLValues {
    //欢迎页
    public static final String WELCOME_URL = "http://new.yohoboys.com/yohoboyins/v5/common/getSplashScreen";
    //轮播图
    public static final String POST_KEY = "parameters";
    public static final String CYCLE_URL = "http://new.yohoboys.com/yohoboyins/v5/channel/newBanner";
    public static final String CYCLE_VALUES = "%7B%22platform%22%3A%224%22%2C%22udid%22%3A%22000000000000000b667280949982902%22%2C%22language%22%3A%22zh-Hans%22%2C%22curVersion%22%3A%225.0.5%22%2C%22authInfo%22%3A%7B%22udid%22%3A%22000000000000000b667280949982902%22%7D%2C%22locale%22%3A%22zh-Hans%22%7D&";
    //推荐ListView
    public static final String RECOM_LIST_URL = "http://new.yohoboys.com/yohoboyins/v5/channel/recommendList";
    public static final String RECOM_LIST_VALUES = "%7B%22platform%22%3A%224%22%2C%22scale%22%3A2%2C%22num%22%3A0%2C%22curVersion%22%3A%225.0.5%22%2C%22newsEndtime%22%3A0%2C%22authInfo%22%3A%7B%22udid%22%3A%22000000000000000b667280949982902%22%7D%2C%22locale%22%3A%22zh-Hans%22%2C%22magazineType%22%3A3%2C%22udid%22%3A%22000000000000000b667280949982902%22%2C%22language%22%3A%22zh-Hans%22%2C%22WallpaperType%22%3A3%2C%22otherEndTime%22%3A0%7D&";
    //栏目
    //Head
    public static final String HEAD_COLUMNS_URL = "http://new.yohoboys.com/yohoboyins/v5/comment/shoseList";
    public static final String HEAD_COLUMNS_VALUES = "%7B%22limit%22%3A12%2C%22uid%22%3A%22%22%2C%22platform%22%3A%224%22%2C%22curVersion%22%3A%225.0.5%22%2C%22authInfo%22%3A%7B%22udid%22%3A%220000000000000005158d5733dbef8e1%22%7D%2C%22locale%22%3A%22zh-Hans%22%2C%22lastTime%22%3A%220%22%2C%22udid%22%3A%220000000000000005158d5733dbef8e1%22%2C%22language%22%3A%22zh-Hans%22%7D&";
    //ListView
    public static final String COLUMNS_URL = "http://new.yohoboys.com/yohoboyins/v5/channel/aggregationIndex";
    public static final String COLUMNS_VALUES = "%7B%22startTime%22%3A%220%22%2C%22limit%22%3A%2212%22%2C%22platform%22%3A%224%22%2C%22curVersion%22%3A%225.0.5%22%2C%22authInfo%22%3A%7B%22udid%22%3A%220000000000000005158d5733dbef8e1%22%7D%2C%22locale%22%3A%22zh-Hans%22%2C%22lastTime%22%3A%220%22%2C%22udid%22%3A%220000000000000005158d5733dbef8e1%22%2C%22language%22%3A%22zh-Hans%22%7D&";
    //二级List
    public static final String COLUMNS_TWOURL = "http://new.yohoboys.com/yohoboyins/v5/channel/lookbook";
    //社区
    public static final String COMMUNITY_LIST_URL = "http://social.yoho.cn/social?app_version=5.0.4&client_secret=2e515454baad66272556819822cb0bbe&client_type=android&lastedTime=0&limit=10&method=app.social.getHomePagePostList&os_version=android5.1%3AGoogle_Nexus_5_-_5.1.0_-_API_22_-_1080x1920&screen_size=1080x1776&v=7";
    //花哨的ViewPager
    public static final String FCF_URL = "http://social.yoho.cn/social?appType=2&app_version=5.0.4&client_secret=c93f2c29298e27c3abbab38628fab244&client_type=android&method=app.social.getForumCarouselInfo&os_version=android5.1%3AGoogle_Nexus_5_-_5.1.0_-_API_22_-_1080x1920&screen_size=1080x1776&v=7";
    //视频
    //VIDEO
    public static final String VIDEO_URL = "http://new.yohoboys.com/yohoboyins/v5/media/videoList";
    public static final String VIDEO_VALUES = "String values 2 = %7B%22startTime%22%3A%220%22%2C%22limit%22%3A%2230%22%2C%22platform%22%3A%224%22%2C%22curVersion%22%3A%225.0.5%22%2C%22authInfo%22%3A%7B%22udid%22%3A%220000000000000005158d5733dbef8e1%22%7D%2C%22locale%22%3A%22zh-Hans%22%2C%22udid%22%3A%220000000000000005158d5733dbef8e1%22%2C%22language%22%3A%22zh-Hans%22%7D&";
    //直播
    public static final String LIVE_URL = "http://new.yohoboys.com/yohoboyins/v5/media/qcloudList";
    //杂志1
    public static final String MAGAZINE_ONEURL = " http://h5api.myoho.net/index.php?r=Apiemag/magList&startTime=0&lastTime=1479786199&magCount=3&magType=1&width=1080&height=1776&ppi=480&num=3";
    public static final String MAGAZINE_ONEVALUES = "{platform:4,locale:zh-Hans,app:efashion,language:zh-Hans,udid:00000000000000063aa461b71c4cfcf,curVersion:5.0.4,authInfo:{udid:00000000000000063aa461b71c4cfcf}}&";
    //杂志2
    public static final String MAGAZINE_TWOURL = " http://h5api.myoho.net/index.php?r=Apiemag/magList&startTime=0&lastTime=1479786199&magCount=3&magType=3&width=1080&height=1776&ppi=480&num=3";
    public static final String MAGAZINE_TWOVALUES = "{“platform:4,locale:zh-Hans,app:efashion,language:zh-Hans,udid:00000000000000063aa461b71c4cfcf,curVersion:5.0.4,authInfo:{udid:00000000000000063aa461b71c4cfcf}}&";
    //杂志3
    public static final String MAGAZINE_THREEURL = " http://h5api.myoho.net/index.php?r=Apiemag/magList&startTime=0&lastTime=1479784306&magCount=3&magType=5&width=1080&height=1776&ppi=480&num=3";
    public static final String MAGAZINE_THREEVALUES = "{“platform”:4,locale:zh-Hans,app:efashion,language:zh-Hans,udid:00000000000000063aa461b71c4cfcf,curVersion:5.0.4,authInfo:{udid:00000000000000063aa461b71c4cfcf}}&";
    //壁纸
    public static final String WALLPAPER_URL = "http://h5api.myoho.net/index.php?r=Apiemag/getWallpaperListV4&start=0&end=200&device=3&scale=2body:";
    public static final String WALLPAPER_VALUES = "4,udid:000000000000000b667280949982902,language:zh-Hans,curVersion:5.0.5,authInfo:{udid:000000000000000b667280949982902},locale:zh-Hans}" +
            "getData().getWallpaperList().get(position).getImages().get(position).";
    //搜索
    public  static final String TABL_URL = "http://new.yohoboys.com/yohoboyins/v5/channel/navigation";
    public static final String LIST_URL = "http://new.yohoboys.com/yohoboyins/v5/channel/contentList";
}

