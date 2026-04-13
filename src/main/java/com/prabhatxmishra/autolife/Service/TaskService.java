package com.prabhatxmishra.autolife.Service;

import com.prabhatxmishra.autolife.DTO.TaskRequestDTO;
import com.prabhatxmishra.autolife.DTO.TaskResponseDTO;
import com.prabhatxmishra.autolife.enums.TaskPriority;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO request);
    Page<TaskResponseDTO> getAllTasks(Pageable pageable, TaskStatus status, TaskPriority priority);
    TaskResponseDTO updateTask(Long id, TaskRequestDTO request);
    void deleteTask(Long id);
}
