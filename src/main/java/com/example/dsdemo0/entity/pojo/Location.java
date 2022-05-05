package com.example.dsdemo0.entity.pojo;

import lombok.Data;

@Data
public class Location {
    private int loc_ID;//主键key value
    private String name;
    private int type;//0-路口 1-建筑物
}
