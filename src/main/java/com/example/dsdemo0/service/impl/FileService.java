package com.example.dsdemo0.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.dsdemo0.entity.File;
import com.example.dsdemo0.mapper.FileMapper;
import com.example.dsdemo0.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService implements IFileService {
    @Autowired
    FileMapper mapper;


    @Override
    public List<File> deFind(){//去重查找文件
        return mapper.deFind();
    }
    @Override
    public int addFile(File file){
        return mapper.insert(file);
    }

    @Override
    public int deleteFile(Integer id){
        return mapper.deleteById(id);
    }

    @Override
    public int updateFile(File file){
        UpdateWrapper<File> wrapper = new UpdateWrapper<>();
        wrapper.eq("file_id", file.getFileID());
        return mapper.update(file, wrapper);
    }

    @Override
    public File findByid(Integer id){
        return mapper.selectById(id);
    }

    @Override
    public List<File> findAll(){
        return mapper.selectList(null);
    }
}

