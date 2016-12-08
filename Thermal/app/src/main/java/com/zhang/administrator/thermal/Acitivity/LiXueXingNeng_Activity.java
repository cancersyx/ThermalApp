package com.zhang.administrator.thermal.Acitivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhang.administrator.thermal.Class.ListItem;
import com.zhang.administrator.thermal.ItemActivity.First_Item_Activity;
import com.zhang.administrator.thermal.ItemActivity.Five_Item_Activity;
import com.zhang.administrator.thermal.ItemActivity.Fourth_Item_Activity;
import com.zhang.administrator.thermal.ItemActivity.SecondActivity;
import com.zhang.administrator.thermal.ItemActivity.Six_Item_Activity;
import com.zhang.administrator.thermal.ItemActivity.Third_Item_Activity;
import com.zhang.administrator.thermal.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/6.
 */
public  class LiXueXingNeng_Activity extends Activity implements View.OnClickListener {
    private ImageView mBack;
    // 声明ListView控件
    private ListView mListView;
    // 声明数组链表，其装载的类型是ListItem(封装了一个Drawable和一个String的类)
    private ArrayList<ListItem> mList;

    private int mCurrentItem =0;
    public void setCurrentItem(int currentItem) {
        this.mCurrentItem = currentItem;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定Activity的布局使用activity_main.xml
        setContentView(R.layout.layout_lixuexingneng);


        initView();

        // 获取Resources对象
        Resources res = this.getResources();

        mList = new ArrayList<ListItem>();
        // 初始化data，装载八组数据到数组链表mList中
        ListItem item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.ll));
        item.setTitle("力学性能");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.hx));
        item.setTitle("物理性能");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.wl));
        item.setTitle("化学性能");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.hx));
        item.setTitle("强度");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.yingdu));
        item.setTitle("硬度");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.su));
        item.setTitle("塑性");
        mList.add(item);



        final ListViewAdapter adapter = new ListViewAdapter();
        // 将MainListAdapter对象传递给ListView视图
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
              switch (position){
                  case 0:
                      Intent i1=new Intent(LiXueXingNeng_Activity.this,First_Item_Activity.class);
                      startActivity(i1);
                      finish();
                      break;
                  case 1:
                      Intent i2=new Intent(LiXueXingNeng_Activity.this,SecondActivity.class);
                      startActivity(i2);
                      finish();
                      break;
                  case 2:
                      Intent i3=new Intent(LiXueXingNeng_Activity.this,Third_Item_Activity.class);
                      startActivity(i3);
                      finish();
                      break;
                  case 3:
                      Intent i4=new Intent(LiXueXingNeng_Activity.this,Fourth_Item_Activity.class);
                      startActivity(i4);
                      finish();
                      break;
                  case 4:
                      Intent i5=new Intent(LiXueXingNeng_Activity.this,Five_Item_Activity.class);
                      startActivity(i5);
                      finish();
                      break;
                  case 5:
                      Intent i6=new Intent(LiXueXingNeng_Activity.this,Six_Item_Activity.class);
                      startActivity(i6);
                      finish();
                      break;

              }
            }
        });
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.XingNeng_back);
        mListView = (ListView) findViewById(R.id.listView1);

        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.XingNeng_back:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }

    class ListViewAdapter extends BaseAdapter {

        /**
         * 返回item的个数
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mList.size();
        }

        /**
         * 返回item的内容
         */
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mList.get(position);
        }

        /**
         * 返回item的id
         */
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView listItemView;

            // 初始化item view
            if (convertView == null) {
                // 通过LayoutInflater将xml中定义的视图实例化到一个View中
                convertView = LayoutInflater.from(LiXueXingNeng_Activity.this).inflate(
                        R.layout.item, null);

                // 实例化一个封装类ListItemView，并实例化它的两个域
                listItemView = new ListItemView();
                listItemView.imageView = (ImageView) convertView
                        .findViewById(R.id.image);
                listItemView.textView = (TextView) convertView
                        .findViewById(R.id.title);

                // 将ListItemView对象传递给convertView
                convertView.setTag(listItemView);
            } else {
                // 从converView中获取ListItemView对象
                listItemView = (ListItemView) convertView.getTag();
            }

            // 获取到mList中指定索引位置的资源
            Drawable img = mList.get(position).getImage();
            String title = mList.get(position).getTitle();

            // 将资源传递给ListItemView的两个域对象
            listItemView.imageView.setImageDrawable(img);
            listItemView.textView.setText(title);

            // 返回convertView对象
            return convertView;
        }

        /**
         * 封装两个视图组件的类
         */

        class ListItemView {
            ImageView imageView;
            TextView textView;
        }

        class ListItem {

            private Drawable image;
            private String title;

            public Drawable getImage() {
                return image;
            }

            public void setImage(Drawable image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }


        }

    }
}
