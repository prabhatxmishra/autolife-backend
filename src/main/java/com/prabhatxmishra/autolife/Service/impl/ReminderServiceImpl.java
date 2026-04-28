package com.prabhatxmishra.autolife.Service.impl;

import com.prabhatxmishra.autolife.DTO.CreateReminderRequest;
import com.prabhatxmishra.autolife.DTO.ReminderResponse;
import com.prabhatxmishra.autolife.ExceptionHandling.ResourceNotFoundException;
import com.prabhatxmishra.autolife.Service.ReminderService;
import com.prabhatxmishra.autolife.entity.Reminder;
import com.prabhatxmishra.autolife.entity.Task;
import com.prabhatxmishra.autolife.enums.RecurrenceType;
import com.prabhatxmishra.autolife.enums.ReminderStatus;
import com.prabhatxmishra.autolife.repository.ReminderRepository;
import com.prabhatxmishra.autolife.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService {

    private static final Logger log = LoggerFactory.getLogger(ReminderServiceImpl.class);

    private final TaskRepository taskRepository;
    private final ReminderRepository reminderRepository;

    public ReminderServiceImpl(TaskRepository taskRepository,
                               ReminderRepository reminderRepository) {
        this.taskRepository = taskRepository;
        this.reminderRepository = reminderRepository;
    }

    // ===================== MAPPERS =====================

    private Reminder toEntity(CreateReminderRequest req, Task task) {

        RecurrenceType type = req.getRecurrenceType() != null
                ? req.getRecurrenceType()
                : RecurrenceType.NONE;

        int interval = req.getRecurrenceInterval() != null
                ? req.getRecurrenceInterval()
                : 1;

        return Reminder.builder()
                .title(req.getTitle())
                .task(task)
                .nextTriggerTime(req.getNextTriggerTime())
                .recurrenceType(type)
                .recurrenceInterval(interval)
                .build();
    }

    private ReminderResponse toResponse(Reminder reminder) {
        ReminderResponse res = new ReminderResponse();
        res.setId(reminder.getId());
        res.setTitle(reminder.getTitle());
        res.setStatus(reminder.getStatus());
        res.setNextTriggerTime(reminder.getNextTriggerTime());
        res.setRecurrenceType(reminder.getRecurrenceType());
        res.setRecurrenceInterval(reminder.getRecurrenceInterval());
        return res;
    }

    // ===================== VALIDATIONS =====================

    private void validateRecurrence(Integer interval, RecurrenceType type) {

        // both null → treated as NONE later → valid
        if (type == null && interval == null) {
            return;
        }

        // one null → invalid
        if (type == null || interval == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Both recurrenceType and recurrenceInterval must be provided together"
            );
        }

        if (interval <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "recurrenceInterval must be greater than 0"
            );
        }
    }

    private void validateTriggerTime(LocalDateTime nextTriggerTime) {
        if (nextTriggerTime == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "nextTriggerTime is required"
            );
        }

        // Optional: allow past time (recommended)
        // If you want strict validation, uncomment:
        /*
        if (nextTriggerTime.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "nextTriggerTime cannot be in the past"
            );
        }
        */
    }

    // ===================== CORE LOGIC =====================

    private LocalDateTime calculateNextTrigger(Reminder reminder) {

        LocalDateTime current = reminder.getNextTriggerTime();
        int interval = reminder.getRecurrenceInterval() != null
                ? reminder.getRecurrenceInterval()
                : 1;

        return switch (reminder.getRecurrenceType()) {
            case DAILY -> current.plusDays(interval);
            case WEEKLY -> current.plusWeeks(interval);
            case NONE -> current; // won't be used
        };
    }

    @Override
    @Transactional
    public void triggerReminder(Reminder reminder) {

        log.info("Reminder triggered id={} title={}",
                reminder.getId(), reminder.getTitle());

        if (reminder.getRecurrenceType() == RecurrenceType.NONE) {
            reminder.setStatus(ReminderStatus.TRIGGERED);
        } else {
            LocalDateTime next = calculateNextTrigger(reminder);
            reminder.setNextTriggerTime(next);
        }
    }

    // ===================== APIs =====================

    @Override
    public ReminderResponse createReminder(CreateReminderRequest request) {

        Task task = null;

        if (request.getTaskId() != null) {
            task = taskRepository.findById(request.getTaskId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Task not found"));
        }

        validateRecurrence(request.getRecurrenceInterval(),
                request.getRecurrenceType());

        validateTriggerTime(request.getNextTriggerTime());

        Reminder reminder = toEntity(request, task);

        Reminder saved = reminderRepository.save(reminder);

        return toResponse(saved);
    }

    @Override
    public List<ReminderResponse> getAllReminders() {
        return reminderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
}