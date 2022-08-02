package com.tonyappz.jobRunr.controller;

import com.tonyappz.jobRunr.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JobController {

    @Autowired
    JobScheduler jobScheduler;
    JobService jobService;

    @GetMapping("job/{name}")
    public String JobProcessing(
            @PathVariable("name") String name
    ) {
        jobScheduler.enqueue(() -> jobService.executeJob(name));
        return "job enqueued";
    }
}
