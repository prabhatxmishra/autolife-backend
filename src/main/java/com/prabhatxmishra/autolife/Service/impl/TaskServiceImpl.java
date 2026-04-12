package com.prabhatxmishra.autolife.Service.impl;

import com.prabhatxmishra.autolife.DTO.TaskRequestDTO;
import com.prabhatxmishra.autolife.DTO.TaskResponseDTO;
import com.prabhatxmishra.autolife.ExceptionHandling.BadRequestException;
import com.prabhatxmishra.autolife.ExceptionHandling.ResourceNotFoundException;
import com.prabhatxmishra.autolife.Service.TaskService;
import com.prabhatxmishra.autolife.entity.Task;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import com.prabhatxmishra.autolife.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Override
    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return taskPage.map(this::mapToResponse);
    }

    @Override
    public TaskResponseDTO updateTasks(Long id, TaskRequestDTO request) {
        Task task=taskRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Task not found with id:" +id));
        if (request.getDueDate() != null &&
                request.getDueDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Due date cannot be in the past");
        }
        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }

        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }

        taskRepository.save(task);
        return mapToResponse(task);
    }
}
