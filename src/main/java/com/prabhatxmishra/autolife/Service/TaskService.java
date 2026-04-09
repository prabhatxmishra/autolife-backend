package com.prabhatxmishra.autolife.Service;

import com.prabhatxmishra.autolife.DTO.TaskRequestDTO;
import com.prabhatxmishra.autolife.DTO.TaskResponseDTO;
import com.prabhatxmishra.autolife.entity.Task;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO request);
}
