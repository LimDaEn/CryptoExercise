package com.example.demo.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class PriceScheduler {

    @Scheduled(fixedDelay = 1000)
    public void schedulePriceRetrieval(){

    }
}
