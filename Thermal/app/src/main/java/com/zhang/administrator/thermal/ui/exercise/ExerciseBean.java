package com.zhang.administrator.thermal.ui.exercise;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class ExerciseBean {
    public int id;//每章习题id
    public String title;//每章习题标题
    public String content;//每章习题的数目
    public int background;//习题序号的背景
    public int subjectId;//每道习题的id
    public String subject;//每道习题的题干
    public String a;//A选项
    public String b;
    public String c;
    public String d;
    public int answer;//每道题的正确答案
    public int select;//select=0,代表选择正确;select=1,代表选中的A选项是错误的;select=2,代表选中的B选项是错误的...
}
