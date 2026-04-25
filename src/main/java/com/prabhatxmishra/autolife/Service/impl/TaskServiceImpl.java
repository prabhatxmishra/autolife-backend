package com.prabhatxmishra.autolife.Service.impl;

import com.prabhatxmishra.autolife.DTO.TaskRequestDTO;
import com.prabhatxmishra.autolife.DTO.TaskResponseDTO;
import com.prabhatxmishra.autolife.ExceptionHandling.BadRequestException;
import com.prabhatxmishra.autolife.ExceptionHandling.ResourceNotFoundException;
import com.prabhatxmishra.autolife.Service.TaskService;
import com.prabhatxmishra.autolife.Specification.TaskSpecification;
import com.prabhatxmishra.autolife.entity.Task;
import com.prabhatxmishra.autolife.enums.TaskPriority;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import com.prabhatxmishra.autolife.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
            throw new BadRequestException("Due date cannot be in the past");
        }

        if (taskRepository.existsByTitleAndDueDate(
                request.getTitle(), request.getDueDate())) {
            throw new BadRequestException("Task already exists for this time");
        }

        Task task=mapToEntity(request);
        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    @Override
    public Page<TaskResponseDTO> getAllTasks(TaskStatus status, TaskPriority priority, LocalDateTime from, LocalDateTime to, String search, Pageable pageable) {
        Specification<Task> spec = Specification
                .where(TaskSpecification.hasStatus(status))
                .and(TaskSpecification.hasPriority(priority))
                .and(TaskSpecification.dueDateBetween(from, to))
                .and(TaskSpecification.search(search));

        Page<Task> taskPage = taskRepository.findAll(spec, pageable);

        return taskPage.map(this::mapToResponse);
    }


    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO request) {
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

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);
        return mapToResponse(task);
    }

    @Override
    public void deleteTask(Long id)
    {
       Task task=taskRepository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException("Task with the given id not found"));

        taskRepository.delete(task);
    }
}
