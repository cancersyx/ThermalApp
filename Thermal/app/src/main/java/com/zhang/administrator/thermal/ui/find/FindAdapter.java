package com.zhang.administrator.thermal.ui.find;

import android.content.ReceiverCallNotAllowedException;
import android.view.View;
import android.view.ViewGroup;

import com.zsf.common.BaseRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class FindAdapter extends BaseRecyclerViewAdapter<FindBean, FindAdapter.ViewHolder> {


    @Override
    protected void bindDataToItemView(ViewHolder viewHolder, FindBean item) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
