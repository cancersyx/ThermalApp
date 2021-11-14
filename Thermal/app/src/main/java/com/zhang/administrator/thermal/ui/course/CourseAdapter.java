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
public class CourseAdapter extends BaseRecyclerViewAdapter<CourseBean, CourseAdapter.ViewHolder> {

    @Override
    protected void bindDataToItemView(ViewHolder holder, CourseBean item) {
        holder.courseTitle.setText(item.courseTitle);
        holder.chapterTitle.setText(item.chapterTitle);
        switch (item.chapterId) {
            case 0:
                holder.cover.setImageResource(R.drawable.img_chapter_00);
                break;
            case 1:
                holder.cover.setImageResource(R.drawable.img_chapter_01);
                break;
            case 2:
                holder.cover.setImageResource(R.drawable.img_chapter_02);
                break;
            case 3:
                holder.cover.setImageResource(R.drawable.img_chapter_03);
                break;
            case 4:
                holder.cover.setImageResource(R.drawable.img_chapter_04);
                break;
            case 5:
                holder.cover.setImageResource(R.drawable.img_chapter_05);
                break;
            case 6:
                holder.cover.setImageResource(R.drawable.img_chapter_06);
                break;
            case 7:
                holder.cover.setImageResource(R.drawable.img_chapter_07);
                break;
            case 8:
                holder.cover.setImageResource(R.drawable.img_chapter_08);
                break;
            case 9:
                holder.cover.setImageResource(R.drawable.img_chapter_09);
                break;
            default:
                holder.cover.setImageResource(R.drawable.img_chapter_0);
                break;

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateItemView(parent, R.layout.item_course);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView courseTitle;
        TextView chapterTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.iv_course_cover);
            courseTitle = itemView.findViewById(R.id.tv_course_title);
            chapterTitle = itemView.findViewById(R.id.tv_chapter_title);
        }
    }
}
