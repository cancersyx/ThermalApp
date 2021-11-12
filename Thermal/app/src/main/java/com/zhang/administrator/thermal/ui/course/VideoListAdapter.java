package com.zhang.administrator.thermal.ui.course;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zsf.common.BaseRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/11
 */
public class VideoListAdapter extends BaseRecyclerViewAdapter<VideoBean, VideoListAdapter.ViewHolder> {



    @Override
    protected void bindDataToItemView(ViewHolder holder, VideoBean item) {
        holder.icon.setBackgroundResource(R.drawable.icon_course_bar);
        holder.title.setText(item.secondTitle);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateItemView(parent, R.layout.item_video);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
            title = itemView.findViewById(R.id.tv_video_title);
        }
    }
}
