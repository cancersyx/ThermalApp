package com.zhang.administrator.thermal.ui.course;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhang.administrator.thermal.R;
import com.zhang.administrator.thermal.bean.Concept;
import com.zhang.administrator.thermal.util.AnalysisUtils;
import com.zhang.administrator.thermal.util.LUtils;
import com.zsf.common.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/10/25
 */
public class CourseView {
    private static final String TAG = "CourseView";
    private Activity activity;
    private LayoutInflater mInflater;
    private View mView;
    private TextView mMarqueeView;
    private RecyclerView mRecyclerView;

    private List<Concept> conceptList;//名词、概念
    private List<CourseBean> mCourseList;
    private CourseAdapter mCourseAdapter;


    public CourseView(Activity activity) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    private void createView() {
        initView();
        initData();
    }

    private void initView() {
        mView = mInflater.inflate(R.layout.view_layout_course, null);
        mMarqueeView = mView.findViewById(R.id.tv_marquee);
        mRecyclerView = mView.findViewById(R.id.course_recycler);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mCourseAdapter = new CourseAdapter();
        mCourseList = new ArrayList<>();
        mRecyclerView.setAdapter(mCourseAdapter);
        mCourseAdapter.setItemClickListener(item -> VideoListActivity.startActivity(activity, item.chapterId, item.chapterTitle, item.intro));
    }

    private void initData() {
        getConcept();
        getCourseData();

    }

    private void getConcept() {
        JSONArray jsonArray;
        try {
            InputStream is = activity.getResources().getAssets().open("banner_txt/concept.json");
            jsonArray = new JSONArray(read(is));
            conceptList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Concept concept = new Concept();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                concept.conceptId = jsonObject.getInt("conceptId");
                concept.title = jsonObject.getString("title");
                concept.content = jsonObject.getString("content");
                conceptList.add(concept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LUtils.d(TAG, ">>>>>> conceptList = " + conceptList);
        if (conceptList != null) {
            int index = new Random().nextInt(conceptList.size());
            mMarqueeView.setText(conceptList.get(index).content + "");
        }
    }

    private void getCourseData() {
        try {
            InputStream is = activity.getResources().getAssets().open("chaptertitle.xml");
            mCourseList = AnalysisUtils.getCourseList(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mCourseAdapter.addItems(mCourseList);
        }
    }

    public View getView() {
        if (mView == null) {
            createView();
        }
        return mView;
    }

    public void showView() {
        if (mView == null) {
            createView();
        }
        mView.setVisibility(View.VISIBLE);
    }


    private String read(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line = null;
        try {
            sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
