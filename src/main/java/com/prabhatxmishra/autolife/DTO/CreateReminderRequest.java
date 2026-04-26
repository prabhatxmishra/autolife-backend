package com.prabhatxmishra.autolife.DTO;

import java.time.LocalDateTime;

public class CreateReminderRequest {
    private String title;
    private Long taskId; // optional
    private LocalDateTime nextTriggerTime;

    public CreateReminderRequest(String title, Long taskId, LocalDateTime nextTriggerTime) {
        this.title = title;
        this.taskId = taskId;
        this.nextTriggerTime = nextTriggerTime;
    }

    public CreateReminderRequest()
    {

    }

    public String getTitle() {
        return title;
    }

    public Long getTaskId() {
        return taskId;
    }

    public LocalDateTime getNextTriggerTime() {
        return nextTriggerTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setNextTriggerTime(LocalDateTime nextTriggerTime) {
        this.nextTriggerTime = nextTriggerTime;
    }


}
