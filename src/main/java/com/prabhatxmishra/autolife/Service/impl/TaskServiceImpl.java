package com.prabhatxmishra.autolife.Service.impl;

import com.prabhatxmishra.autolife.DTO.TaskRequestDTO;
import com.prabhatxmishra.autolife.DTO.TaskResponseDTO;
import com.prabhatxmishra.autolife.Service.TaskService;
import com.prabhatxmishra.autolife.entity.Task;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import com.prabhatxmishra.autolife.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task mapToEntity(TaskRequestDTO dto) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(TaskStatus.PENDING) // default
                .priority(dto.getPriority())
                .dueDate(dto.getDueDate())
                .build();
    }

    private TaskResponseDTO mapToResponse(Task task) {
        return TaskResponseDTO.builder().id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO request) {
        if (request.getDueDate() != null &&
                request.getDueDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Due date cannot be in the past");
        } // Will add custom validation later
        Task task=mapToEntity(request);
        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }
}
