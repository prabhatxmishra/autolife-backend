package com.prabhatxmishra.autolife.Service.impl;

import com.prabhatxmishra.autolife.Service.ReminderService;
import com.prabhatxmishra.autolife.entity.Reminder;
import com.prabhatxmishra.autolife.enums.ReminderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReminderServiceImpl implements ReminderService {

    private static final Logger log = LoggerFactory.getLogger(ReminderService.class);

    @Override
    @Transactional
    public void triggerReminder(Reminder reminder) {

        log.info("Reminder triggered id={} title={}",
                reminder.getId(), reminder.getTitle());

        reminder.setStatus(ReminderStatus.TRIGGERED);
    }
}
