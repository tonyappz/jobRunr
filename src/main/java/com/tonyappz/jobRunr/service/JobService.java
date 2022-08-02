package com.tonyappz.jobRunr.service;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class JobService {
    @Job(name = "This is job processing %0", retries = 2)
    public void executeJob(String name) {

        int wait = (int) (Math.random()*100);
        long threadId = Thread.currentThread().getId();
        System.out.println(getDateTimeLine() + " entered " + name + " " + threadId + " " + wait*100 + " milisec wait");
        try {
            Thread.sleep(wait * 100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(getDateTimeLine() + " executed " + name + " complete " + threadId + " " + wait*100 + " milisec wait");
        }
    }

    public static String getDateTimeLine() {
        return Instant.now().atZone(ZoneId.of("+9")).format(formatter);
    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ");
}
