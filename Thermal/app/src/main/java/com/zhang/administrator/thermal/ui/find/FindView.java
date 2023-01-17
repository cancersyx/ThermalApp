package com.zhang.administrator.thermal.ui.find;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhang.administrator.thermal.R;
import com.zsf.common.LUtils;
import com.zsf.common.OnItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class FindView {
    private static final String TAG = "FindView";
    private Activity mActivity;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private TextView mBackTv;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private FindAdapter mFindAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    public FindView(Activity activity) {
        this.mActivity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestFindData();

            }
        });
    }

    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.view_layout_find, null);
        mRecyclerView = mCurrentView.findViewById(R.id.find_recycler);
        mBackTv = mCurrentView.findViewById(R.id.tv_title_back);
        mTitle = mCurrentView.findViewById(R.id.tv_title);
        mBackTv.setVisibility(View.INVISIBLE);
        mTitle.setText("发现");
        mRefreshLayout = mCurrentView.findViewById(R.id.swipe_refresh);
        mRefreshLayout.setColorSchemeColors(mActivity.getResources().getColor(R.color.colorPrimary));
        mRefreshLayout.setRefreshing(true);

        mFindAdapter = new FindAdapter(mActivity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mFindAdapter);
        initData();
    }


    private void initData() {
        requestFindData();
    }

    private void requestFindData() {
        List<FindBean.DataDTO> findBeanList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.sfwind.cn/AppPrivacy/thermal_app/find_data.json")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                FindBean findBean = new Gson().fromJson(response.body().string(), FindBean.class);
                if (findBean == null) return;
                List<FindBean.DataDTO> dataDTO = findBean.getData();
                for (int i = 0; i < dataDTO.size(); i++) {
                    LUtils.d(TAG, ">>>>>title = " + dataDTO.get(i).getTitle());
                    LUtils.d(TAG, ">>>>>pageUrl = " + dataDTO.get(i).getPage_url());
                    LUtils.d(TAG, ">>>>>thumbnail = " + dataDTO.get(i).getThumbnail_img_url());
                    LUtils.d(TAG, ">>>>>keywords = " + dataDTO.get(i).getKeywords());
                    LUtils.d(TAG, "*************************************************************** END ");
                    findBeanList.add(dataDTO.get(i));
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                        mFindAdapter.clear();
                        mFindAdapter.addItems(findBeanList);
                        mFindAdapter.setItemClickListener(new OnItemClickListener<FindBean.DataDTO>() {
                            @Override
                            public void onClick(FindBean.DataDTO item) {
                                FindDetailActivity.startActivity(mActivity, item);
                            }
                        });
                    }
                });
            }
        });

    }

    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }

    public void showView() {
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
