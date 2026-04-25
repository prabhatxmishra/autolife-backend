package com.prabhatxmishra.autolife.Scheduler;

import com.prabhatxmishra.autolife.enums.ReminderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
import com.prabhatxmishra.autolife.repository.ReminderRepository;
import com.prabhatxmishra.autolife.Service.impl.ReminderServiceImpl;

import com.prabhatxmishra.autolife.entity.Reminder;
import org.springframework.stereotype.Component;

@Component
public class ReminderScheduler {

    @Autowired
    ReminderRepository reminderRepository;

    @Autowired
    ReminderServiceImpl reminderService;

    @Scheduled(fixedDelay = 60000)
    public void processReminders()
    {
        LocalDateTime now=LocalDateTime.now();
        List<Reminder> dueReminders= reminderRepository
                .findByNextTriggerTimeLessThanEqualAndStatus(now, ReminderStatus.PENDING);

        for(Reminder reminder: dueReminders)
        {
            reminderService.triggerReminder(reminder);
        }
    }

}
