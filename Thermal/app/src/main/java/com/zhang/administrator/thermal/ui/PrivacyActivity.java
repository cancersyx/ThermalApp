package com.zhang.administrator.thermal.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zhang.administrator.thermal.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by EWorld
 * 2022/3/17
 */
public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context,PrivacyActivity.class);
        context.startActivity(intent);
    }
}
