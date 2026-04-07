package com.prabhatxmishra.autolife.repository;

import com.prabhatxmishra.autolife.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
