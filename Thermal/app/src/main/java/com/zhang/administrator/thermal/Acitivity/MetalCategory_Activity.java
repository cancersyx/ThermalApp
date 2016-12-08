package com.zhang.administrator.thermal.Acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhang.administrator.thermal.R;

/**
 * Created by Administrator on 2016/4/6.
 */
public class MetalCategory_Activity extends Activity implements View.OnClickListener {
    private Button iron,aluminum,steel;
    private ImageView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_metalcategory);
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
                startActivity(new Intent(MetalCategory_Activity.this,MainActivity.class));
                break;
            case R.id.iron:
                startActivity(new Intent(this,Iron_Activity.class));
                break;
            case R.id.steel:
                startActivity(new Intent(this,Steel_Activity.class));
                break;
            case R.id.aluminum:
                startActivity(new Intent(this,Aluminum_Activity.class));
                break;
        }
    }
}
