package com.prabhatxmishra.autolife.Service;

import com.prabhatxmishra.autolife.DTO.TaskRequestDTO;
import com.prabhatxmishra.autolife.DTO.TaskResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO request);
    Page<TaskResponseDTO> getAllTasks(Pageable pageable);
    TaskResponseDTO updateTasks(Long id, TaskRequestDTO request);
}
