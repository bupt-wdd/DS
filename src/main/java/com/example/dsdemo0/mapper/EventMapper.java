package com.example.dsdemo0.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dsdemo0.entity.Event;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper extends BaseMapper<Event> {
}
