package com.prabhatxmishra.autolife.entity;

import com.prabhatxmishra.autolife.enums.RecurrenceType;
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

    private Integer recurrenceInterval;

    @Enumerated(EnumType.STRING)
    private RecurrenceType recurrenceType;

    @Column(nullable = false)
    private LocalDateTime nextTriggerTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ===== Constructors =====

    public Reminder() {
    }

    public Reminder(Long id, String title, Task task, ReminderStatus status,
                    LocalDateTime nextTriggerTime, LocalDateTime createdAt,
                    Integer recurrenceInterval, RecurrenceType recurrenceType) {
        this.id = id;
        this.title = title;
        this.task = task;
        this.status = status;
        this.nextTriggerTime = nextTriggerTime;
        this.createdAt = createdAt;
        this.recurrenceInterval = recurrenceInterval;
        this.recurrenceType = recurrenceType;
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
        private Integer recurrenceInterval;
        private RecurrenceType recurrenceType;

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

        public Builder recurrenceInterval(Integer recurrenceInterval) {
            this.recurrenceInterval = recurrenceInterval;
            return this;
        }

        public Builder recurrenceType(RecurrenceType recurrenceType) {
            this.recurrenceType = recurrenceType;
            return this;
        }

        public Reminder build() {
            return new Reminder(
                    id,
                    title,
                    task,
                    status,
                    nextTriggerTime,
                    createdAt,
                    recurrenceInterval,
                    recurrenceType
            );
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

    // ===== Getters =====

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

    public Integer getRecurrenceInterval() {
        return recurrenceInterval;
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public LocalDateTime getNextTriggerTime() {
        return nextTriggerTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // ===== Setters =====

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

    public void setRecurrenceInterval(Integer recurrenceInterval) {
        this.recurrenceInterval = recurrenceInterval;
    }

    public void setRecurrenceType(RecurrenceType recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public void setNextTriggerTime(LocalDateTime nextTriggerTime) {
        this.nextTriggerTime = nextTriggerTime;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}