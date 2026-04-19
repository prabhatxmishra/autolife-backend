package com.prabhatxmishra.autolife.Specification;

import com.prabhatxmishra.autolife.entity.Task;
import com.prabhatxmishra.autolife.enums.TaskPriority;
import com.prabhatxmishra.autolife.enums.TaskStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecification {
    public static Specification<Task> hasStatus(TaskStatus status)
    {
        return new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return status==null? null: cb.equal(root.get("status"),status);
            }
        };
    }

    public static Specification<Task> hasPriority(TaskPriority priority)
    {
        return new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return priority==null? null: cb.equal(root.get("priority"), priority);
            }
        };
    }
    public static Specification<Task> dueDateBetween(LocalDateTime from, LocalDateTime to)
    {
        return new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (from == null && to == null) {
                    return null;
                }

                if (from != null && to != null) {
                    return cb.between(root.get("dueDate"), from, to);
                }

                if (from != null) {
                    return cb.greaterThanOrEqualTo(root.get("dueDate"), from);
                }

                return cb.lessThanOrEqualTo(root.get("dueDate"), to);
            }
        };
    }
    public static Specification<Task> search(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) {
                return null;
            }

            String pattern = "%" + keyword.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("title")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern)
            );
        };
    }
}
