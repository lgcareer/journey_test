package com.journey.demo.data;

import com.alibaba.fastjson.JSONObject;
import com.journey.demo.data.domain.Course;
import com.journey.demo.data.domain.Sc;
import com.journey.demo.data.domain.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qiaozhanwei on 18-8-13.
 */
public class GenerateData {

    private static Student[] students = null;
    private static Course[] courses;
    private static Sc[] scs;
    static {
        students=new Student[]{
                new Student(1001,"牛一","男"),
                new Student(1002,"蔡二","女"),
                new Student(1003,"张三","男"),
                new Student(1004,"李四","女"),
                new Student(1005,"王五","男"),
                new Student(1006,"李志基","男"),
                new Student(1007,"陈李强","男"),
                new Student(1008,"王八","女"),
                new Student(1009,"张仲景","男")


        };

        courses = new Course[]{
                new Course(001,"高等数学",1001),
                new Course(002,"马克思",1001),
                new Course(003,"大学英语",1002),
                new Course(004,"数据库",1002),
                new Course(005,"数据结构",1003)
        };

        scs = new Sc[]{
                new Sc(1001,001,100),
                new Sc(1001,002,80),
                new Sc(1001,003,70),
                new Sc(1001,004,60),
                new Sc(1001,005,50),

                new Sc(1002,001,60),
                new Sc(1002,002,50),
                new Sc(1002,003,80),
                new Sc(1002,004,30),
                new Sc(1002,005,100),

                new Sc(1003,001,60),
                new Sc(1003,002,80),
                new Sc(1003,003,60),
                new Sc(1003,004,40),

                new Sc(1004,001,20),
                new Sc(1004,002,100),
                new Sc(1004,003,30),
                new Sc(1004,005,40),

                new Sc(1005,002,80),
                new Sc(1005,003,60),
                new Sc(1005,004,50),
                new Sc(1006,002,100),
                new Sc(1006,005,100),
                new Sc(1007,001,50),
                new Sc(1007,004,40),
                new Sc(1008,003,60)
        };
    }

    public static void main(String[] args) throws Exception{


        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH,-1);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(c.getTime());

        Writer studentWriter = new FileWriter(new File("/opt/data/student/"+dateStr));
        Writer scourseWriter = new FileWriter(new File("/opt/data/course/"+dateStr));
        Writer scWriter = new FileWriter(new File("/opt/data/sc/"+dateStr));
        for (Student stu : students){
            String str = JSONObject.toJSONString(stu);
            studentWriter.write(str +"\r\n");
        }

        for (Course course : courses){
            String str = JSONObject.toJSONString(course);
            scourseWriter.write(str +"\r\n");
        }

        for (Sc sc : scs){
            String str = JSONObject.toJSONString(sc);
            scWriter.write(str +"\r\n");
        }



        studentWriter.close();
        scourseWriter.close();
        scWriter.close();
    }
}