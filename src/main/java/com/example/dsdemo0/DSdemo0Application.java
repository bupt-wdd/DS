package com.example.dsdemo0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAsync
@EnableScheduling //定时功能
@SpringBootApplication
public class DSdemo0Application {

    public static void main(String[] args) {
        SpringApplication.run(DSdemo0Application.class, args);
    }
    @RequestMapping("/")


    @Scheduled(cron = "0 * * * * 0-7")
    public String test(){
        System.out.println("asdasdas");
        return "这是后端端口9090";
    }

}

