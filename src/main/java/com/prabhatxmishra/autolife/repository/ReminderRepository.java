package com.prabhatxmishra.autolife.repository;

import com.prabhatxmishra.autolife.entity.Reminder;
import com.prabhatxmishra.autolife.enums.ReminderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByNextTriggerTimeLessThanEqualAndStatus(
            LocalDateTime time,
            ReminderStatus status
    );
}
