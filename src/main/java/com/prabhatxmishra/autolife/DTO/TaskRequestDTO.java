package com.prabhatxmishra.autolife.DTO;

import com.prabhatxmishra.autolife.enums.TaskPriority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestDTO {

   @NotBlank
   private String title;

   private String description;

   @NotNull
   private TaskPriority priority;

   @Future
   private LocalDateTime dueDate;
}
