package com.prabhatxmishra.autolife.Service;

import com.prabhatxmishra.autolife.DTO.CreateReminderRequest;
import com.prabhatxmishra.autolife.DTO.ReminderResponse;
import com.prabhatxmishra.autolife.entity.Reminder;

import java.util.List;

public interface ReminderService {
    public void triggerReminder(Reminder reminder);

    ReminderResponse createReminder(CreateReminderRequest request);
    List<ReminderResponse> getAllReminders();
}
