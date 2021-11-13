package com.zhang.administrator.thermal.ui.find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zhang.administrator.thermal.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class FindDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FindDetailActivity.class);
        context.startActivity(intent);
    }
}
