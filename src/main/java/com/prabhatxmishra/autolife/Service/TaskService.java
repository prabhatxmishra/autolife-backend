package com.prabhatxmishra.autolife.Service;

import com.prabhatxmishra.autolife.DTO.TaskRequestDTO;
import com.prabhatxmishra.autolife.DTO.TaskResponseDTO;
import com.prabhatxmishra.autolife.enums.TaskPriority;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO request);
    Page<TaskResponseDTO> getAllTasks(TaskStatus status, TaskPriority priority,LocalDateTime from,
                                      LocalDateTime to,
                                      String search,Pageable pageable);
    TaskResponseDTO updateTask(Long id, TaskRequestDTO request);
    void deleteTask(Long id);
}
