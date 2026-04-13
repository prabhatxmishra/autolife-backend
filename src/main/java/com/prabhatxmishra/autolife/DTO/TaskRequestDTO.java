package com.prabhatxmishra.autolife.DTO;

import com.prabhatxmishra.autolife.enums.TaskPriority;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public class TaskRequestDTO {

   private String title;

   private String description;

   private TaskPriority priority;

   private TaskStatus status;

   @Future(message = "Due date must be in the future")
   private LocalDateTime dueDate;

   // No-args constructor
   public TaskRequestDTO() {
   }

   // All-args constructor
   public TaskRequestDTO(String title, String description,
                         TaskPriority priority, TaskStatus status,
                         LocalDateTime dueDate) {
      this.title = title;
      this.description = description;
      this.priority = priority;
      this.status = status;
      this.dueDate = dueDate;
   }

   // Getters and Setters

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public TaskPriority getPriority() {
      return priority;
   }

   public void setPriority(TaskPriority priority) {
      this.priority = priority;
   }

   public TaskStatus getStatus() {
      return status;
   }

   public void setStatus(TaskStatus status) {
      this.status = status;
   }

   public LocalDateTime getDueDate() {
      return dueDate;
   }

   public void setDueDate(LocalDateTime dueDate) {
      this.dueDate = dueDate;
   }
}