package com.zhang.administrator.thermal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/10/25
 */
public class FocusView extends androidx.appcompat.widget.AppCompatTextView {
    public FocusView(@NonNull Context context) {
        super(context);
    }

    public FocusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        //始终返回true
        return true;
    }
}
