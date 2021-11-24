package com.zhang.administrator.thermal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by EWorld
 * 2021/11/24
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "thermal_personal.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_USER_INFO = "userInfo";
    private String CREATE_TABLE_USER_INFO = "create table if not exists " + TABLE_USER_INFO + " ("
            + "_id integer primary key autoincrement,"
            + "userName varchar,"
            + "nickName varchar,"
            + "sex varchar,"
            + "signature varchar,"
            + "address varchar,"
            + "age integer"
            + ")";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER_INFO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USER_INFO);
        onCreate(db);
    }
}
