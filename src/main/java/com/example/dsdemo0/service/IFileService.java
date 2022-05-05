package com.example.dsdemo0.service;

import com.example.dsdemo0.entity.File;

import java.util.List;

public interface IFileService {

    List<File> deFind();

    int addFile(File file);

    int deleteFile(Integer id);

    int updateFile(File file);

    File findByid(Integer id);

    List<File> findAll();
}
