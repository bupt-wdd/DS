package com.example.dsdemo0.controller;


import com.example.dsdemo0.entity.Course;
import com.example.dsdemo0.entity.ResultInfo;
import com.example.dsdemo0.entity.pojo.pagePack;
import com.example.dsdemo0.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    ICourseService service;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }


    @RequestMapping("/clock")
    public void clock(){

    }

    @PostMapping("/add")
    public ResponseEntity<ResultInfo<Course>> addCourse(@RequestParam(required = false) String name, @RequestParam(required = false) Date date , @RequestParam(required = false) String teacher){
        if (name == null || name == ""){
            return response_course(false, "字符串不能为空", null, HttpStatus.BAD_REQUEST);
        }
        Course course = new Course();
        course.setName(name);
        course.setDate(date);
        course.setTeacher(teacher);
        service.addCourse(course);
        return response_course(true, "", course, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResultInfo<Course>> deleteCourse(@RequestParam(value = "id", required = false) Integer id){
        if (id == null){
            return response_course(false, "id为空", null, HttpStatus.BAD_REQUEST);
        }else if (service.deleteCourse(id) == 0){
            return response_course(false, "无法找到数据", null, HttpStatus.NOT_FOUND);
        }else{
            return response_course(true, "删除成功", null, HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResultInfo<Course>> updateCourse(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) Date date , @RequestParam(required = false) String teacher){
        if (id == null || (name == null && date == null && teacher == null)){
            return response_course(false, "id或修改信息为空", null, HttpStatus.BAD_REQUEST);
        }
        Course course = new Course();
        course.setName(name);
        course.setCourseID(id);
        course.setDate(date);
        course.setTeacher(teacher);
        if(service.updateCourse(course) == 0){
            return response_course(false, "不存在此数据", null, HttpStatus.NOT_FOUND);
        }else {
            return response_course(true, "修改成功", course, HttpStatus.OK);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResultInfo<pagePack<Course>>> findAllCourse(@RequestParam(required = false) Integer pageNum ,@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer sort){
        List<Course> list1 = service.findAllCourse();
        if (pageNum == null || pageSize == null){
            pagePack<Course> pack = new pagePack<>(list1, list1.size());
            ResultInfo<pagePack<Course>> resultInfo = new ResultInfo<>(true, "查询成功", pack);
            return new ResponseEntity<>(resultInfo, HttpStatus.OK);
        }
        List<Course> list = service.findAllByPage(pageNum, pageSize, sort);
        pagePack<Course> pack = new pagePack(list, list1.size());
        ResultInfo<pagePack<Course>> resultInfo = new ResultInfo<>(true, "查询成功", pack);
        return new ResponseEntity<>(resultInfo, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<ResultInfo<List<Course>>> findCourse(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) String teacher){
        List<Course> list;
        if (id != null){
            list = service.findCourseById(id);
        }else if (name != null) {
            list = service.findCourseByName(name);
        }else if (teacher != null){
            list = service.findCourseByTeacher(teacher);
        }else {
            ResultInfo<List<Course>> resultInfo = new ResultInfo<>(false, "请输入查询字段", null);
            return new ResponseEntity<>(resultInfo, HttpStatus.BAD_REQUEST);
        }
        if (list == null){
            ResultInfo<List<Course>> resultInfo = new ResultInfo<>(false, "无法找到数据", null);
            return new ResponseEntity<>(resultInfo, HttpStatus.BAD_REQUEST);
        }
        ResultInfo<List<Course>> resultInfo = new ResultInfo<>(true, "查询成功", list);
        return new ResponseEntity<>(resultInfo, HttpStatus.OK);
    }

    private ResponseEntity<ResultInfo<Course>> response_course(boolean success, String message, Course entity, HttpStatus status){
        ResultInfo<Course> resultInfo = new ResultInfo<>(success, message, entity);
        return new ResponseEntity<>(resultInfo, status);
    }




}
