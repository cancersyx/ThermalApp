package com.zhang.administrator.thermal.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by EWorld
 * 2021/11/21
 */
public class ApkUtil {

    /**
     * 获取程序包信息
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        String versionName = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
