package com.hehe.fbalx.service;

import com.hehe.fbalx.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

    @Autowired
    private TaskStatusService taskStatusService;

    @Autowired
    private UpdateTrackingListService updateTrackingListService;
    // 控制定时任务是否启用
    @Scheduled(fixedRate = 1000*60*60) // 每60min执行一次
    public void executeTask() {
        if (taskStatusService.isScheduledTaskActive()) {
            LogUtil.initLog();
            updateTrackingListService.updateTrackingList();
        } /*else {
            System.out.println("Scheduled task is inactive.");
        }*/
    }

}