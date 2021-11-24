package com.zhang.administrator.thermal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhang.administrator.thermal.bean.MyUser;

/**
 * Created by EWorld
 * 2021/11/24
 */
public class DBUtils {
    private static DBUtils instance = null;
    private SQLiteDatabase mDatabase;
    private SQLiteHelper mHelper;

    public DBUtils(Context context) {
        mHelper = new SQLiteHelper(context);
        mDatabase = mHelper.getWritableDatabase();

    }

    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    /**
     * 保存个人资料信息
     *
     * @param user
     */
    public void saveUserInfo(MyUser user) {
        ContentValues cv = new ContentValues();
        cv.put("userName", user.getUserName());
        cv.put("nickName", user.getNickName());
        cv.put("sex", user.getSex());
        cv.put("signature", user.getSignature());
        cv.put("address", user.getAddress());
        cv.put("age", user.getAge());
        mDatabase.insert(SQLiteHelper.TABLE_USER_INFO, null, cv);
    }

    /**
     * 获取个人资料
     *
     * @param userName
     * @return
     */
    public MyUser getUserInfo(String userName) {
        String sql = "select * from " + SQLiteHelper.TABLE_USER_INFO + " where userName=?";
        Cursor cursor = mDatabase.rawQuery(sql, new String[]{userName});
        MyUser myUser = null;
        while (cursor.moveToNext()) {
            myUser = new MyUser();
            myUser.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            myUser.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
            myUser.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            myUser.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
            myUser.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            myUser.setAge(cursor.getInt(cursor.getColumnIndex("age")));
        }
        cursor.close();
        return myUser;
    }

    /**
     * 修改个人资料
     * @param key
     * @param value
     * @param userName
     */
    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        mDatabase.update(SQLiteHelper.TABLE_USER_INFO, cv, "userName=?", new String[]{userName});
    }
}
