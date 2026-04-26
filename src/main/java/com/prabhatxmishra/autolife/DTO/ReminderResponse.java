package com.prabhatxmishra.autolife.DTO;

import com.prabhatxmishra.autolife.enums.ReminderStatus;

import java.time.LocalDateTime;

public class ReminderResponse {
    private Long id;
    private String title;
    private ReminderStatus status;
    private LocalDateTime nextTriggerTime;

    public ReminderResponse() {
    }

    public ReminderResponse(Long id, String title, ReminderStatus status, LocalDateTime nextTriggerTime) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.nextTriggerTime = nextTriggerTime;
    }

    // --- Builder ---
    public static class Builder {
        private Long id;
        private String title;
        private ReminderStatus status;
        private LocalDateTime nextTriggerTime;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(ReminderStatus status) {
            this.status = status;
            return this;
        }

        public Builder nextTriggerTime(LocalDateTime nextTriggerTime) {
            this.nextTriggerTime = nextTriggerTime;
            return this;
        }

        public ReminderResponse build() {
            return new ReminderResponse(id, title, status, nextTriggerTime);
        }
    }

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public void setStatus(ReminderStatus status) {
        this.status = status;
    }

    public LocalDateTime getNextTriggerTime() {
        return nextTriggerTime;
    }

    public void setNextTriggerTime(LocalDateTime nextTriggerTime) {
        this.nextTriggerTime = nextTriggerTime;
    }
}