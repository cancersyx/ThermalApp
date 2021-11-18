package com.zhang.administrator.thermal.ui.exercise;

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
 * 2021/11/16
 */
public class ExercisesDetailAdapter extends BaseRecyclerViewAdapter<ExerciseBean, ExercisesDetailAdapter.ViewHolder> {

    public ExercisesDetailAdapter(OnClickOptionListener optionListener) {
        mOptionListener = optionListener;
    }

    @Override
    protected void bindDataToItemView(ViewHolder holder, ExerciseBean item) {
        holder.question.setText(item.questionSubject);
        holder.optionA.setText(item.a);
        holder.optionB.setText(item.b);
        holder.optionC.setText(item.c);
        holder.optionD.setText(item.d);

        holder.optionA.setOnClickListener(v -> {
            mOptionListener.onSelectA(holder.getAdapterPosition(), holder.ivA, holder.ivB, holder.ivC, holder.ivD);

        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateItemView(parent, R.layout.item_exercise_detail);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        TextView optionA;
        TextView optionB;
        TextView optionC;
        TextView optionD;
        ImageView ivA;
        ImageView ivB;
        ImageView ivC;
        ImageView ivD;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.tv_question_subject);
            optionA = itemView.findViewById(R.id.tv_a);
            optionB = itemView.findViewById(R.id.tv_b);
            optionC = itemView.findViewById(R.id.tv_c);
            optionD = itemView.findViewById(R.id.tv_d);
            ivA = itemView.findViewById(R.id.iv_a);
            ivB = itemView.findViewById(R.id.iv_b);
            ivC = itemView.findViewById(R.id.iv_c);
            ivD = itemView.findViewById(R.id.iv_d);
        }
    }

    //----------------------------------------------------------------------
    private OnClickOptionListener mOptionListener;

    interface OnClickOptionListener {
        void onSelectA(int position, ImageView viewA, ImageView viewB, ImageView viewC, ImageView viewD);

        void onSelectB(int position, ImageView viewA, ImageView viewB, ImageView viewC, ImageView viewD);

        void onSelectC(int position, ImageView viewA, ImageView viewB, ImageView viewC, ImageView viewD);

        void onSelectD(int position, ImageView viewA, ImageView viewB, ImageView viewC, ImageView viewD);
    }
}
