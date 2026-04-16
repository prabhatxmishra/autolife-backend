package com.prabhatxmishra.autolife.repository;

import com.prabhatxmishra.autolife.entity.Task;
import com.prabhatxmishra.autolife.enums.TaskPriority;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    Page<Task> findByPriority(TaskPriority priority, Pageable pageable);

    Page<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority, Pageable pageable);
}
