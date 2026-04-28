package com.prabhatxmishra.autolife.DTO;

import com.prabhatxmishra.autolife.enums.RecurrenceType;
import com.prabhatxmishra.autolife.enums.ReminderStatus;

import java.time.LocalDateTime;

public class ReminderResponse {

    private Long id;
    private String title;
    private ReminderStatus status;
    private LocalDateTime nextTriggerTime;

    // NEW
    private RecurrenceType recurrenceType;
    private Integer recurrenceInterval;

    // constructors
    public ReminderResponse() {}

    public ReminderResponse(Long id, String title, ReminderStatus status,
                            LocalDateTime nextTriggerTime,
                            RecurrenceType recurrenceType,
                            Integer recurrenceInterval) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.nextTriggerTime = nextTriggerTime;
        this.recurrenceType = recurrenceType;
        this.recurrenceInterval = recurrenceInterval;
    }

    // getters & setters

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public ReminderStatus getStatus() { return status; }
    public LocalDateTime getNextTriggerTime() { return nextTriggerTime; }
    public RecurrenceType getRecurrenceType() { return recurrenceType; }
    public Integer getRecurrenceInterval() { return recurrenceInterval; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setStatus(ReminderStatus status) { this.status = status; }
    public void setNextTriggerTime(LocalDateTime nextTriggerTime) { this.nextTriggerTime = nextTriggerTime; }
    public void setRecurrenceType(RecurrenceType recurrenceType) { this.recurrenceType = recurrenceType; }
    public void setRecurrenceInterval(Integer recurrenceInterval) { this.recurrenceInterval = recurrenceInterval; }
}