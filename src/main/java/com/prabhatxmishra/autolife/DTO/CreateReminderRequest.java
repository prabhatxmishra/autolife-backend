package com.prabhatxmishra.autolife.DTO;

import com.prabhatxmishra.autolife.enums.RecurrenceType;

import java.time.LocalDateTime;

public class CreateReminderRequest {

    private String title;
    private Long taskId; // optional
    private LocalDateTime nextTriggerTime;

    // NEW
    private RecurrenceType recurrenceType;
    private Integer recurrenceInterval;

    public CreateReminderRequest() {}

    public CreateReminderRequest(String title, Long taskId,
                                 LocalDateTime nextTriggerTime,
                                 RecurrenceType recurrenceType,
                                 Integer recurrenceInterval) {
        this.title = title;
        this.taskId = taskId;
        this.nextTriggerTime = nextTriggerTime;
        this.recurrenceType = recurrenceType;
        this.recurrenceInterval = recurrenceInterval;
    }

    // getters & setters...

    public String getTitle() { return title; }
    public Long getTaskId() { return taskId; }
    public LocalDateTime getNextTriggerTime() { return nextTriggerTime; }
    public RecurrenceType getRecurrenceType() { return recurrenceType; }
    public Integer getRecurrenceInterval() { return recurrenceInterval; }

    public void setTitle(String title) { this.title = title; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public void setNextTriggerTime(LocalDateTime nextTriggerTime) { this.nextTriggerTime = nextTriggerTime; }
    public void setRecurrenceType(RecurrenceType recurrenceType) { this.recurrenceType = recurrenceType; }
    public void setRecurrenceInterval(Integer recurrenceInterval) { this.recurrenceInterval = recurrenceInterval; }
}