package com.zhang.administrator.thermal.ui.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.ui.MainActivity;

/**
 * Created by Administrator on 2016/4/6.
 */
public class MetalCategoryActivity extends Activity implements View.OnClickListener {
    private Button iron,aluminum,steel;
    private ImageView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_metal_category);
        initView();

    }

    private void initView() {
        iron= (Button) findViewById(R.id.iron);
        steel= (Button) findViewById(R.id.steel);
        aluminum= (Button) findViewById(R.id.aluminum);
        mBack= (ImageView) findViewById(R.id.Category_back);

        iron.setOnClickListener(this);
        steel.setOnClickListener(this);
        aluminum.setOnClickListener(this);
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Category_back:
                startActivity(new Intent(MetalCategoryActivity.this, MainActivity.class));
                break;
            case R.id.iron:
                startActivity(new Intent(this, IronActivity.class));
                break;
            case R.id.steel:
                startActivity(new Intent(this, SteelActivity.class));
                break;
            case R.id.aluminum:
                startActivity(new Intent(this, AluminumActivity.class));
                break;
        }
    }
}
