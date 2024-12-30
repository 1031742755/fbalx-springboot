package com.hehe.fbalx.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TaskStatusService {

    private boolean scheduledTaskActive = false;  // 默认值

    public boolean isScheduledTaskActive() {
        return scheduledTaskActive;
    }

    public void setScheduledTaskActive(boolean scheduledTaskActive) {
        this.scheduledTaskActive = scheduledTaskActive;
    }
}
