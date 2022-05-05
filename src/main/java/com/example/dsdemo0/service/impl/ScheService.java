package com.example.dsdemo0.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheService {

    @Scheduled(cron = "0 * * * * 0-7")//sec min hour day mon period
    public String hello(){
        ////业务逻辑




return "";
    }
}
