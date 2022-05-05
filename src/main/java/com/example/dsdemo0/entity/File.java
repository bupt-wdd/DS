package com.example.dsdemo0.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NonNull;

@Data
public class File {
    @TableId(value = "file_id")
    private int fileID;
    @TableField(value = "course_id")
    private int courseID;
    private String title;
    private String content;


}
