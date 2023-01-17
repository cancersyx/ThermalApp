package com.zsf.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/1
 */
public abstract class BaseRecyclerViewAdapter<D, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    private static final String TAG = "BaseRecyclerViewAdapter";
    protected final List<D> mDataSet = new ArrayList<>();
    private OnItemClickListener<D> mItemClickListener;

    public void setItemClickListener(OnItemClickListener<D> itemClickListener) {
        mItemClickListener = itemClickListener;
    }



    protected D getItem(int position) {
        return mDataSet.get(position);
    }

    public synchronized void addItems(List<D> items) {
        LUtils.d(TAG,">>>>> items.size = " + items.size());
        items.removeAll(mDataSet);
        mDataSet.clear();
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        final D item = getItem(position);
        bindDataToItemView(holder, item);
        initItemViewClickListener(holder, item);
    }

    protected abstract void bindDataToItemView(V viewHolder, D item);

    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
    }


    private void initItemViewClickListener(V holder, D item) {
        holder.itemView.setOnClickListener(v -> {
            if (mItemClickListener != null) {
                mItemClickListener.onClick(item);
            }
        });
    }


}
