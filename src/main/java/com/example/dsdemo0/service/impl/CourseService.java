package com.example.dsdemo0.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dsdemo0.entity.Course;
import com.example.dsdemo0.mapper.CourseMapper;
import com.example.dsdemo0.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    CourseMapper mapper;

    @Override
    public int addCourse(Course course){
        return mapper.insert(course);
    }


    @Override
    public int deleteCourse(Integer id){
        return mapper.deleteById(id);
    }

    @Override
    public int updateCourse(Course course){
        UpdateWrapper<Course> wrapper = new UpdateWrapper<>();
        wrapper.eq("course_id", course.getCourseID());
        return mapper.update(course, wrapper);
    }

    @Override
    public List<Course> findCourseById(Integer id){ //id排序
        List<Course> list = new ArrayList<>();
        list.add(mapper.selectById(id));
        return list;
    }

    @Override
    public List<Course> findCourseByName(String name){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Course> findCourseByTeacher(String teacher){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher", teacher);
        return mapper.selectList(wrapper);
    }

    @Override
    public List<Course> findAllCourse() {
        return mapper.selectList(null);
    }

    @Override
    public List<Course> findAllByPage(int pageNum, int pageSize, Integer sort) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
//        wrapper.select("course_id", "name", "location_id", "date", "teacher", "file");
        if (sort == null || sort == 0){
            wrapper = null;
        }else if (sort == 1){
            wrapper.orderByAsc("date");
        }else if(sort == 2){
            wrapper.orderByAsc("name");
        }
        Page<Course> page = new Page<>(pageNum, pageSize);
        IPage<Course> ipage = mapper.selectPage(page, wrapper);
        return ipage.getRecords();
    }

    @Override
    public List<Course> sort(List<Course> list, int sort){
        Course[] course = new Course[list.size()];
        for (int i = 0; i < course.length; i++){
            course[i] = list.get(i);
        }
        quciksort(course, sort);
        list = new ArrayList(Arrays.asList(course)) ;
        return list;
    }

    private void quciksort(Course[] course, int sort) {
        if (course.length <= 2 || course == null){
            return;
        }
        process(course, 0, course.length - 1, sort);
    }

    private void process(Course[] course, int L, int R, int sort) {
        if (L < R){
            int[] equal_session = partition(course, L, R, sort);
            process(course, L, equal_session[0] - 1, sort);
            process(course, equal_session[1] + 1, R, sort);
        }
    }

    private void swap(Course[] course, int i, int j) {
        Course temp = course[i];
        course[i] = course[j];
        course[j] = temp;
    }

    private int[] partition(Course[] course, int L, int R, int sort) {
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        int random = (int)(Math.random()*course.length);
        swap(course, R, random);
        while (index < moreL){
            if (course[index].compareTo(course[moreL], sort) == -1){
                swap(course, ++lessR, index++);
            }else if (course[index].compareTo(course[moreL], sort) == 1){
                swap(course, --moreL, index);
            }else{
                index++;
            }
        }
        swap(course, R, lessR);
        return new int[] {lessR + 1, moreL};
    }
}
