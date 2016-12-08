package com.zhang.administrator.thermal.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.Acitivity.LiXueXingNeng_Activity;
import com.zhang.administrator.thermal.Class.ListItem;
import com.zhang.administrator.thermal.Class.ListItemView;
import com.zhang.administrator.thermal.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ListViewAdapter extends BaseAdapter {
    // 声明数组链表，其装载的类型是ListItem(封装了一个Drawable和一个String的类)
    private ArrayList<ListItem> mList;
    private Context mContext;
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
            convertView = LayoutInflater.from(mContext).inflate(
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



