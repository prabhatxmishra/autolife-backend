package com.prabhatxmishra.autolife.DTO;

import com.prabhatxmishra.autolife.enums.TaskPriority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskRequestDTO {

   @NotBlank
   private String title;

   private String description;

   @NotNull
   private TaskPriority priority;

   @Future
   private LocalDateTime dueDate;

   // No-args constructor
   public TaskRequestDTO() {
   }

   // All-args constructor
   public TaskRequestDTO(String title, String description,
                         TaskPriority priority, LocalDateTime dueDate) {
      this.title = title;
      this.description = description;
      this.priority = priority;
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

   public LocalDateTime getDueDate() {
      return dueDate;
   }

   public void setDueDate(LocalDateTime dueDate) {
      this.dueDate = dueDate;
   }
}