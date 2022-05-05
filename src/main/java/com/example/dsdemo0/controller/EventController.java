package com.example.dsdemo0.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.dsdemo0.entity.Event;
import com.example.dsdemo0.entity.ResultInfo;
import com.example.dsdemo0.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    IEventService service;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    @PostMapping("/add")
    public ResponseEntity<ResultInfo<Event>> addEvent(@RequestParam(required = false) String name,@RequestParam(required = false) Date date, @RequestParam(required = false) int type){
        if (name == null || name == ""){
            ResultInfo<Event> info = new ResultInfo<>(false, "字符串为空", null);
            return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
        }
        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        service.addEvent(event);
        ResultInfo<Event> info = new ResultInfo<>(true, "添加成功", event);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResultInfo<Event>> deleteEvent(@RequestParam(required = false) Integer id){
        if (id == null){
            ResultInfo<Event> info = new ResultInfo<>(false, "id为空", null);
            return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
        }else if (service.deleteEvent(id) == 0){
            ResultInfo<Event> info = new ResultInfo<>(false, "不存在此数据", null);
            return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
        }else {
            ResultInfo<Event> info = new ResultInfo<>(true, "删除成功", null);
            return new ResponseEntity<>(info, HttpStatus.OK);
        }
    }

    @PutMapping("update")
    public ResponseEntity<ResultInfo<Event>> updateEvent(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) Date date, @RequestParam(required = false) int type){
        if (name == null && date == null){
            ResultInfo<Event> info = new ResultInfo<>(false, "更新字段为空", null);
            return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
        }
        Event event = new Event();
        event.setEventID(id);
        event.setName(name);
        event.setDate(date);
        if (service.updateCourse(event) == 0){
            ResultInfo<Event> info = new ResultInfo<>(false, "不存在此数据", null);
            return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
        }else{
            ResultInfo<Event> info = new ResultInfo<>(true, "更新成功", null);
            return new ResponseEntity<>(info, HttpStatus.OK);
        }
    }

    @GetMapping("find")
    public ResponseEntity<ResultInfo<List<Event>>> findEvent(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name){
        List<Event> list = new ArrayList<>();
        if (id != null){
            list.add(service.findById(id));
        }else if (name != null){
            list = service.findByName(name);
        }else {
            ResultInfo<List<Event>> info = new ResultInfo<>(false, "查询字段为空", null);
            return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
        }
        if (list == null){
            ResultInfo<List<Event>> info = new ResultInfo<>(false, "无法找到数据", null);
            return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
        }
        ResultInfo<List<Event>> info = new ResultInfo<>(true, "", list);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResultInfo<List<Event>>> findAll( @RequestParam(required = false) Integer sort, @RequestParam(required = false) Integer type){
        List<Event> list = service.findAll(sort, type);
        ResultInfo<List<Event>> info = new ResultInfo<>(true, "查询成功", list);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }






}
