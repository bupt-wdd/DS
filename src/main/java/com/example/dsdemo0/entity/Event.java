package com.example.dsdemo0.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Event {
    @TableId(value = "event_id")
    private int eventID;
    private int type;//0---集体    1---个人
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")  // 返回给前端的时候进行格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    //private Date end_date;

}
