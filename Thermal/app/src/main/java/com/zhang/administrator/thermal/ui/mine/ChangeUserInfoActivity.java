package com.zhang.administrator.thermal.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/11/24
 */
public class ChangeUserInfoActivity extends BaseActivity {
    public static final int FLAG_CHANGE_NICK_NAME = 1;
    public static final int FLAG_CHANGE_SIGNATURE = 2;
    private TextView mBack, mTitleTv, mSave;
    private EditText mEditText;
    private ImageView mDelete;

    private int activityFlag;//1-修改昵称;2-修改签名
    private String mTitle;
    private String mContent;
    private int mFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        mTitle = getIntent().getStringExtra("title");
        mContent = getIntent().getStringExtra("content");
        mFlag = getIntent().getIntExtra("flag", 0);

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
        if (!TextUtils.isEmpty(mContent)) {
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
                Intent intent = new Intent();
                String content = mEditText.getText().toString().trim();
                switch (mFlag) {
                    case FLAG_CHANGE_NICK_NAME:
                        if (!TextUtils.isEmpty(content)) {
                            intent.putExtra("nickName", content);
                            setResult(RESULT_OK, intent);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case FLAG_CHANGE_SIGNATURE:
                        if (!TextUtils.isEmpty(content)){
                            intent.putExtra("signature",content);
                            setResult(RESULT_OK,intent);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this, "签名不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = mEditText.getText();
                int len = editable.length();
                if (len > 0) {
                    mDelete.setVisibility(View.VISIBLE);
                } else {
                    mDelete.setVisibility(View.GONE);
                }
                switch (mFlag) {
                    case FLAG_CHANGE_NICK_NAME:
                        //修改昵称，昵称最多8个字符
                        if (len > 8) {
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //截取新字符串
                            String newStr = str.substring(0, 8);
                            mEditText.setText(newStr);
                            editable = mEditText.getText();
                            int newLen = editable.length();
                            if (selEndIndex > newLen) {
                                selEndIndex = editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable, selEndIndex);
                        }
                        break;
                    case FLAG_CHANGE_SIGNATURE:
                        //修改签名,签名最多24个字符
                        if (len > 16) {
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            String newStr = str.substring(0, 16);
                            mEditText.setText(newStr);
                            editable = mEditText.getText();
                            //新字符串的长度
                            int newLen = editable.length();
                            if (selEndIndex > newLen) {
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable, selEndIndex);
                        }


                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ChangeUserInfoActivity.class);
        context.startActivity(intent);
    }
}
