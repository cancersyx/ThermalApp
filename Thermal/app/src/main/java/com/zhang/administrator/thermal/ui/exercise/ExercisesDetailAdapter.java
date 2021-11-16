package com.zhang.administrator.thermal.ui.exercise;

import android.view.View;
import android.view.ViewGroup;

import com.zhang.administrator.thermal.R;
import com.zsf.common.BaseRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/16
 */
public class ExercisesDetailAdapter extends BaseRecyclerViewAdapter<ExerciseBean, ExercisesDetailAdapter.ViewHolder> {


    @Override
    protected void bindDataToItemView(ViewHolder viewHolder, ExerciseBean item) {
        // TODO: 2021/11/16  

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateItemView(parent, R.layout.item_exercise_detail);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
