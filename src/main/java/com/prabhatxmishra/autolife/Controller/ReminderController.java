package com.prabhatxmishra.autolife.Controller;

import com.prabhatxmishra.autolife.DTO.CreateReminderRequest;
import com.prabhatxmishra.autolife.DTO.ReminderResponse;
import com.prabhatxmishra.autolife.Service.ReminderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public ReminderResponse createReminder(@RequestBody CreateReminderRequest request) {
        return reminderService.createReminder(request);
    }

    @GetMapping
    public List<ReminderResponse> getAllReminders() {
        return reminderService.getAllReminders();
    }
}
