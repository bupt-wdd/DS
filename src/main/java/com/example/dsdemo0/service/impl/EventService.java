package com.example.dsdemo0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.dsdemo0.entity.Event;
import com.example.dsdemo0.mapper.EventMapper;
import com.example.dsdemo0.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements IEventService {
    @Autowired
    EventMapper mapper;

    @Override
    public int addEvent(Event event){
        return mapper.insert(event);
    }

    @Override
    public int deleteEvent(Integer id){
        return mapper.deleteById(id);
    }

    @Override
    public int updateCourse(Event event){
        UpdateWrapper wrapper = new UpdateWrapper<>();
        wrapper.eq("event_id", event.getEventID());
        return mapper.update(event, wrapper);
    }

    @Override
    public Event findById(Integer id){
        return mapper.selectById(id);
    }

    @Override
    public List<Event> findByName(String name){
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Event> findByType(Integer type){
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        return mapper.selectList(wrapper);
    }
    @Override
    public List<Event> findAll(Integer sort, Integer type){
        if (sort == null && type == null){
            return mapper.selectList(null);
        }
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        if (sort == 1){
            wrapper.orderByAsc("date");
        }else if (sort == 2){
            wrapper.orderByAsc("name");
        }


        return mapper.selectList(wrapper);
    }


}
