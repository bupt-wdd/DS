package com.example.dsdemo0.service;

import com.example.dsdemo0.entity.Course;
import com.example.dsdemo0.entity.File;

import java.util.List;

public interface ICourseService {
    int addCourse(Course course);

    int deleteCourse(Integer id);

    int updateCourse(Course course);

    List<Course> findCourseById(Integer id);

    List<Course> findCourseByName(String name);

    List<Course> findCourseByTeacher(String teacher);

    List<Course> findAllCourse();

    List<Course> findAllByPage(int pageNum, int pageSize, Integer sort);

    List<Course> sort(List<Course> list, int sort);
}
