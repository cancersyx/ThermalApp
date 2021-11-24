package com.zhang.administrator.thermal.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/11/24
 */
public class ChangeUserInfoActivity extends BaseActivity {
    private TextView mBack, mTitleTv, mSave;
    private EditText mEditText;
    private ImageView mDelete;

    private int activityFlag;//1-修改昵称;2-修改签名
    private String mTitle;
    private String mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        mTitle = getIntent().getStringExtra("title");
        mContent = getIntent().getStringExtra("content");
        int flag = getIntent().getIntExtra("flag", 0);

        initView();
        initEvent();
    }

    private void initView() {
        mBack = findViewById(R.id.tv_title_back);
        mTitleTv = findViewById(R.id.tv_title);
        mSave = findViewById(R.id.tv_title_save);
        mEditText = findViewById(R.id.et_content);
        mDelete = findViewById(R.id.iv_delete);

        mSave.setVisibility(View.VISIBLE);
        mTitleTv.setText(mTitle);
        if (!TextUtils.isEmpty(mContent)){
            mEditText.setText(mContent);
            mEditText.setSelection(mContent.length());
        }
    }

    private void initEvent() {
        mBack.setOnClickListener(v -> finish());
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2021/11/24  

            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ChangeUserInfoActivity.class);
        context.startActivity(intent);
    }
}
