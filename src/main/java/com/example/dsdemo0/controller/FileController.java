package com.example.dsdemo0.controller;

import com.example.dsdemo0.entity.File;
import com.example.dsdemo0.entity.ResultInfo;
import com.example.dsdemo0.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class FileController {
    @Autowired
    IFileService service;

    @PostMapping("/upload")
    public ResponseEntity<ResultInfo<File>> addFile(@RequestParam(required = false) Integer id, @RequestParam(required = false) String title, @RequestParam(required = false) String content){
        if (id == null || title == null || title == ""){
            ResultInfo<File> info = new ResultInfo<>(false, "id或title为空", null);
            return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
        }
        File file = new File();
        file.setCourseID(id);
        file.setTitle(title);
        file.setContent(content);
        service.addFile(file);
        ResultInfo<File> info = new ResultInfo<>(true, "", file);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("/defind")
    public ResponseEntity<ResultInfo<List<File>>> defindAll(){
        ResultInfo<List<File>> info = new ResultInfo<>(true, "查询成功", service.deFind());
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

}
