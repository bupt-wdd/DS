package com.example.dsdemo0.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Course{
    @TableId(value = "course_id", type = IdType.AUTO)
    private int courseID;
    private String name;
    @TableField(value = "location_id")
    private int locationID;//地点
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //返回时间类型
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date date;
    private String teacher;
    //private String file;//课程资料
//    private Assignment homework;
//    private Exam exam;
    public int compareTo(Course course, int sort){
        if (sort == 1) {
            if (this.date.before(course.date)) {
                return -1;
            } else if (this.date.after(course.date)) {
                return 1;
            } else {
                return 0;
            }
        }else if (sort == 2){
            if (this.name.compareTo(course.name) == 1){
                return 1;
            }else if (this.name.compareTo(course.name) == -1){
                return -1;
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }



}


