package com.example.dsdemo0.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo<T> {
    private boolean success;
    private String message;
    private T entity;
}
