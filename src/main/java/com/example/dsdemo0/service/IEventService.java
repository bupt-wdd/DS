package com.example.dsdemo0.service;

import com.example.dsdemo0.entity.Event;

import java.util.List;

public interface IEventService {
    int addEvent(Event event);

    int deleteEvent(Integer id);

    int updateCourse(Event event);

    Event findById(Integer id);

    List<Event> findByName(String name);

    List<Event> findByType(Integer type);

    List<Event> findAll(Integer sort, Integer type);
}
