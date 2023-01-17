package com.zhang.administrator.thermal;

import android.app.Application;

/**
 * Created by Administrator on 2016/5/24.
 */
public class ThermalApplication extends Application {
    /**
     * SDK初始化也可以放到Application中
     */
    public static String APPID = "612c964de8567f521833d1182ceb8c2e";

    @Override
    public void onCreate() {
        super.onCreate();
        //Bmob.initialize(this, APPID);


    }
}
