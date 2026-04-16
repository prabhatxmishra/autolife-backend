package com.prabhatxmishra.autolife.Controller;


import com.prabhatxmishra.autolife.DTO.TaskRequestDTO;
import com.prabhatxmishra.autolife.DTO.TaskResponseDTO;
import com.prabhatxmishra.autolife.Service.TaskService;
import com.prabhatxmishra.autolife.enums.TaskPriority;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO request)
    {
        TaskResponseDTO response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public Page<TaskResponseDTO> retrieveTasks(  @RequestParam(required = false) TaskStatus status,
                                                 @RequestParam(required = false) TaskPriority priority,
                                                 @RequestParam(required = false) LocalDateTime from,
                                                 @RequestParam(required = false) LocalDateTime to,
                                                 @RequestParam(required = false) String search,
                                                 @PageableDefault(size = 10, sort = "createdAt",
                                                 direction = Sort.Direction.DESC) Pageable pageable)
    {
        return taskService.getAllTasks(status, priority,from,to,search, pageable);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTasks(@PathVariable Long id,
                                                       @RequestBody @Valid TaskRequestDTO request)
    {
        TaskResponseDTO response = taskService.updateTask(id, request);
       return ResponseEntity.ok((response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
