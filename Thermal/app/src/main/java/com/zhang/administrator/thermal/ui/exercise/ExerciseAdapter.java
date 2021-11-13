package com.zhang.administrator.thermal.ui.exercise;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zsf.common.BaseRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class ExerciseAdapter extends BaseRecyclerViewAdapter<ExerciseBean, ExerciseAdapter.ViewHolder> {


    @Override
    protected void bindDataToItemView(ViewHolder holder, ExerciseBean item) {
        holder.serialNum.setBackgroundResource(item.background);
        holder.serialNum.setText((holder.getAdapterPosition() + 1) + "");
        holder.title.setText(item.title);
        holder.subject.setText(item.content);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateItemView(parent, R.layout.item_exercise);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serialNum;
        TextView title;
        TextView subject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serialNum = itemView.findViewById(R.id.tv_serial_number);
            title = itemView.findViewById(R.id.tv_exercise_title);
            subject = itemView.findViewById(R.id.tv_subject_total);
        }
    }

}
