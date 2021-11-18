package com.zhang.administrator.thermal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;
import android.widget.ImageView;


import com.zhang.administrator.thermal.ui.course.CourseBean;
import com.zhang.administrator.thermal.ui.exercise.ExerciseBean;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EWorld
 * 2021/10/14
 */
public class AnalysisUtils {

    /**
     * SharedPreferences中读取用户名
     *
     * @param context
     * @return
     */
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");
        return userName;
    }

    public static List<ExerciseBean> getExercisesInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<ExerciseBean> exercisesInfos = null;
        ExerciseBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        exercisesInfos = new ArrayList<>();
                    } else if ("exercises".equals(parser.getName())) {
                        exercisesInfo = new ExerciseBean();
                        String ids = parser.getAttributeValue(0);
                        exercisesInfo.subjectId = Integer.parseInt(ids);
                    } else if ("subject".equals(parser.getName())) {
                        String subject = parser.nextText();
                        exercisesInfo.questionSubject = subject;
                    } else if ("a".equals(parser.getName())) {
                        String a = parser.nextText();
                        exercisesInfo.a = a;
                    } else if ("b".equals(parser.getName())) {
                        String b = parser.nextText();
                        exercisesInfo.b = b;
                    } else if ("c".equals(parser.getName())) {
                        String c = parser.nextText();
                        exercisesInfo.c = c;
                    } else if ("d".equals(parser.getName())) {
                        String d = parser.nextText();
                        exercisesInfo.d = d;
                    } else if ("answer".equals(parser.getName())) {
                        String answer = parser.nextText();
                        exercisesInfo.answer = Integer.parseInt(answer);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("exercises".equals(parser.getName())) {
                        exercisesInfos.add(exercisesInfo);
                        exercisesInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return exercisesInfos;
    }

    public static List<CourseBean> getCourseList(InputStream is) throws Exception {
        XmlPullParser parse = Xml.newPullParser();
        parse.setInput(is, "utf-8");
        List<CourseBean> courseList = null;
        CourseBean courseInfo = null;
        int type = parse.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parse.getName())) {
                        courseList = new ArrayList<>();
                    } else if ("course".equals(parse.getName())) {
                        courseInfo = new CourseBean();
                        String ids = parse.getAttributeValue(0);
                        courseInfo.chapterId = Integer.parseInt(ids);
                    } else if ("imgtitle".equals(parse.getName())) {
                        String imgTitle = parse.nextText();
                        courseInfo.courseTitle = imgTitle;
                    } else if ("title".equals(parse.getName())) {
                        String title = parse.nextText();
                        courseInfo.chapterTitle = title;
                    } else if ("intro".equals(parse.getName())) {
                        String intro = parse.nextText();
                        courseInfo.intro = intro;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("course".equals(parse.getName())) {
                        courseList.add(courseInfo);
                        courseInfo = null;
                    }
                    break;
            }
            type = parse.next();
        }
        return courseList;
    }
}
