package com.example.dsdemo0.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dsdemo0.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper extends BaseMapper<File> {
    @Select("SELECT min(file_id)file_id, min(course_id)course_Id, min(title)title,min(content)content from file \n" +
            "GROUP BY title")
    public List<File> deFind();
}
