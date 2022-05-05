package com.example.dsdemo0.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class pagePack<T> {
    private List<T> list;
    private int total;
}
