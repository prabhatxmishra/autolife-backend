package com.prabhatxmishra.autolife.Service.impl;

import com.prabhatxmishra.autolife.DTO.ReminderResponse;
import com.prabhatxmishra.autolife.ExceptionHandling.ResourceNotFoundException;
import com.prabhatxmishra.autolife.Service.ReminderService;
import com.prabhatxmishra.autolife.entity.Reminder;
import com.prabhatxmishra.autolife.entity.Task;
import com.prabhatxmishra.autolife.enums.ReminderStatus;
import com.prabhatxmishra.autolife.repository.ReminderRepository;
import com.prabhatxmishra.autolife.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.prabhatxmishra.autolife.DTO.CreateReminderRequest;

import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService {

    private static final Logger log = LoggerFactory.getLogger(ReminderServiceImpl.class);

    TaskRepository taskRepository;
    ReminderRepository reminderRepository;

    public ReminderServiceImpl(TaskRepository taskRepository, ReminderRepository reminderRepository) {
        this.taskRepository = taskRepository;
        this.reminderRepository = reminderRepository;
    }

    private Reminder toEntity(CreateReminderRequest req, Task task) {
        return Reminder.builder()
                .title(req.getTitle())
                .task(task)
                .nextTriggerTime(req.getNextTriggerTime())
                .build();
    }

    private ReminderResponse toResponse(Reminder reminder) {
        ReminderResponse res = new ReminderResponse();
        res.setId(reminder.getId());
        res.setTitle(reminder.getTitle());
        res.setStatus(reminder.getStatus());
        res.setNextTriggerTime(reminder.getNextTriggerTime());
        return res;
    }

    @Override
    @Transactional
    public void triggerReminder(Reminder reminder) {

        log.info("Reminder triggered id={} title={}",
                reminder.getId(), reminder.getTitle());

        reminder.setStatus(ReminderStatus.TRIGGERED);
    }

    @Override
    public ReminderResponse createReminder(CreateReminderRequest request) {
        Task task=null;

        if(request.getTaskId()!=null)
        {
            task=taskRepository.findById(request.getTaskId())
                    .orElseThrow(()-> new ResourceNotFoundException("Task not found"));
        }

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
