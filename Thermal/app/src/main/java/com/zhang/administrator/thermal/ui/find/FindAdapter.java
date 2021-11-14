package com.zhang.administrator.thermal.ui.find;

import android.app.Activity;
import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zsf.common.BaseRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class FindAdapter extends BaseRecyclerViewAdapter<FindBean, FindAdapter.ViewHolder> {
    private Context mContext;

    public FindAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    protected void bindDataToItemView(ViewHolder holder, FindBean item) {
        holder.thumb.setImageResource(item.thumb);
        holder.title.setText(item.title);
        for (int i = 0; i < item.topics.length; i++) {
            holder.topicContainer.addView(createTopicView(item.topics[i]));
        }
    }

    private TextView createTopicView(String topic) {
        TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.rightMargin = 20;
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(10);
        textView.setBackgroundResource(R.drawable.bg_topic);
        textView.setText(topic);
        return textView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateItemView(parent, R.layout.item_find);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumb;
        TextView title;
        LinearLayout topicContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.iv_thumb);
            title = itemView.findViewById(R.id.tv_title);
            topicContainer = itemView.findViewById(R.id.ll_topic_container);
        }
    }
}
