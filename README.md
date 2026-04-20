# 🚀 AutoLife Backend

A production-oriented **Spring Boot backend system** designed to manage personal operations like tasks, reminders, and future intelligent insights.

> Focus: Clean architecture, scalable querying, and real-world backend practices — not just CRUD APIs.

---

## 🎯 Project Overview

AutoLife Backend is built to simulate a **real-world backend system** with:

* Scalable API design
* Dynamic querying
* Data integrity enforcement
* Clean separation of concerns

Currently, the **Task Management module** is fully implemented with production-grade practices.

---

## 🛠 Tech Stack

* **Java 21**
* **Spring Boot 4.x**
* **Spring Data JPA / Hibernate**
* **MySQL**
* **Maven**

---

## 🏗 Architecture

```
Controller → Service → Repository → Database
```

* **DTO-based API design** (Request / Response separation)
* **Layered architecture** for maintainability
* **Global exception handling**
* Clean and modular code structure

---

## 📦 Core Features (Task Module)

### ✅ Task Management

* Create, update, delete tasks
* Track priority and due dates
* Default status handling (PENDING)

---

### 🔍 Dynamic Filtering (Specification API)

Supports flexible, runtime filtering:

* Filter by **status**
* Filter by **priority**
* Filter by **due date range (from/to)**
* **Search** across title & description (case-insensitive)

✔ Built using **JPA Specification API**
✔ Clean, reusable predicate-based design
✔ Avoids method explosion in repository

---

### 📊 Pagination & Sorting

* Pageable-based pagination
* Sorting via query parameters

Example:

```
GET /tasks?page=0&size=10&sort=createdAt,desc
```

---

### 🧾 DTO-Based Design

* Separate request and response models
* Prevents exposing internal entity structure
* Improves API flexibility and security

---

### ⚙️ Validations

* Business validation:

  * Due date cannot be in the past
* Request validation using `@Valid`

---

### 🔁 Partial Updates (PATCH)

* Supports partial updates (PATCH semantics)
* Updates only provided fields
* Avoids overwriting existing data

---

### ❗ Global Exception Handling

* Centralized exception management using `@RestControllerAdvice`
* Custom exceptions:

  * `ResourceNotFoundException`
  * `BadRequestException`
* Consistent API error responses

---

### 🛡 Data Integrity (Duplicate Handling)

* Prevents duplicate tasks using:

  * **Service-level validation**
  * **Database-level unique constraint** (title + dueDate)

---

### 🧠 Clean API Design

* RESTful endpoints
* Proper HTTP methods:

  * `POST`, `GET`, `PATCH`, `DELETE`
* Correct status codes:

  * `201 Created`
  * `200 OK`
  * `204 No Content`
  * `400 Bad Request`
  * `404 Not Found`

---

## 📡 API Endpoints

### ➕ Create Task

```
POST /tasks
```

---

### 📄 Get Tasks (with filters)

```
GET /tasks
```

Supports:

```
?status=PENDING
?priority=HIGH
?search=work
?from=2026-04-20T00:00:00&to=2026-04-25T23:59:59
```

---

### ✏️ Update Task

```
PATCH /tasks/{id}
```

---

### ❌ Delete Task

```
DELETE /tasks/{id}
```

---

## 🧪 Example Request

```json
{
  "title": "Complete backend module",
  "description": "Finish specification API implementation",
  "priority": "HIGH",
  "dueDate": "2026-05-15T19:30:00"
}
```

---

## 📊 Key Concepts Demonstrated

* DTO vs Entity separation
* Pagination vs Page handling
* Dynamic query building (Specification API)
* Service-level vs DB-level validation
* Partial updates (PATCH semantics)
* Layered architecture
* Exception handling best practices

---

## 🚧 Upcoming Features

* ⏱ Reminder System with Scheduler (`@Scheduled`)
* 🔐 JWT Authentication & Authorization
* ⚡ Redis caching
* 📩 Event-driven notifications (Kafka)
* 📊 Insights APIs (analytics)

---

## 🔥 Why This Project Stands Out

This project goes beyond basic CRUD by focusing on:

* Scalable query design
* Clean architecture
* Real-world backend constraints
* Maintainable and extensible code

---

## 📌 Getting Started

1. Clone the repo
2. Configure MySQL in `application.properties`
3. Run the application:

```
mvn spring-boot:run
```

---

## 👨‍💻 Author

**Prabhat Mishra**

---

## ⭐ Final Note

This project is built to demonstrate **backend engineering depth**, not just functionality.

> “From CRUD to production-grade backend design.”
