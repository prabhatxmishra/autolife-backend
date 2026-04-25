package com.prabhatxmishra.autolife.entity;

import com.prabhatxmishra.autolife.enums.ReminderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "reminders",
        indexes = {
                @Index(name = "idx_trigger_time_status", columnList = "nextTriggerTime,status")
        }
)
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "task_id")
    private Task task;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReminderStatus status;

    @Column(nullable = false)
    private LocalDateTime nextTriggerTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ===== Constructors =====

    public Reminder() {
    }

    public Reminder(Long id, String title, Task task, ReminderStatus status,
                    LocalDateTime nextTriggerTime, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.task = task;
        this.status = status;
        this.nextTriggerTime = nextTriggerTime;
        this.createdAt = createdAt;
    }

    // ===== Builder =====

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String title;
        private Task task;
        private ReminderStatus status;
        private LocalDateTime nextTriggerTime;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder task(Task task) {
            this.task = task;
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

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Reminder build() {
            return new Reminder(id, title, task, status, nextTriggerTime, createdAt);
        }
    }

    // ===== Lifecycle =====

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = ReminderStatus.PENDING;
        }
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Task getTask() {
        return task;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public LocalDateTime getNextTriggerTime() {
        return nextTriggerTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setStatus(ReminderStatus status) {
        this.status = status;
    }

    public void setNextTriggerTime(LocalDateTime nextTriggerTime) {
        this.nextTriggerTime = nextTriggerTime;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}