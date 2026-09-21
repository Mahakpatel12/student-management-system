# Student Management System

A RESTful Student Management System built using **Java and Spring Boot**. The application provides APIs to create, view, update, delete, and search student records. It also includes validation, exception handling, DTO-based data transfer, pagination, sorting, and logging.

## Features

* Create a new student
* Get all students
* Get a student by ID
* Update student details
* Delete a student
* Search students by name
* Search students by course
* Pagination and sorting
* Request validation
* Global exception handling
* Custom `StudentNotFoundException`
* DTOs for request and response handling
* Controller-Service-Repository layered architecture
* MongoDB database integration
* Logging using SLF4J
* APIs tested using Postman
## Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Database:** MongoDB
* **Database Integration:** Spring Data MongoDB
* **API:** REST API
* **Validation:** Jakarta Bean Validation
* **Build Tool:** Maven
* **Testing:** Postman
* **Logging:** SLF4J
* **IDE:** IntelliJ IDEA

## API Endpoints

| Method | Endpoint                            | Description               |
| ------ | ----------------------------------- | ------------------------- |
| POST   | `/students`                         | Create a new student      |
| GET    | `/students`                         | Get all students          |
| GET    | `/students/{id}`                    | Get a student by ID       |
| PUT    | `/students/{id}`                    | Update student details    |
| DELETE | `/students/{id}`                    | Delete a student          |
| GET    | `/students/search?name=...`         | Search students by name   |
| GET    | `/students/searchCourse?course=...` | Search students by course |

### Pagination & Sorting

The `GET /students` endpoint supports pagination and sorting using Spring's `Pageable`.

Example:

```text
GET /students?page=0&size=10
```

Sorting example:

```text
GET /students?page=0&size=10&sort=name,asc
```

The page number starts from **0**.

## Validation

The application validates incoming student data before processing requests.

Examples:

* `rollNo` cannot be null.
* `name` cannot be blank.
* `academicYear` must be between 1 and 4.
* Multiple validation errors are returned together in the response.

## Exception Handling

The application uses a custom `StudentNotFoundException` and a global exception handler using `@ControllerAdvice`.

For example, if a student does not exist, the API returns:

```json
{
  "status": 404,
  "message": "Student not found"
}
```
## Project Structure

```text
src/main/java/com/example/student_management
│
├── controller
│   └── StudentController.java
│
├── service
│   └── StudentService.java
│
├── repository
│   └── StudentRepository.java
│
├── entity
│   └── Student.java
│
├── dto
│   ├── StudentRequestDTO.java
│   └── StudentResponseDTO.java
│
├── mapper
│   └── StudentMapper.java
│
└── exception
    ├── StudentNotFoundException.java
    ├── GlobalExceptionHandler.java
    └── ErrorResponse.java
```

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Mahakpatel12/student-management-system.git
cd student-management-system
```

### 2. Configure MongoDB

The application reads the MongoDB connection string from the `MONGODB_URI` environment variable.

The `application.properties` file contains:

```properties
spring.data.mongodb.uri=${MONGODB_URI}
```

Set `MONGODB_URI` in your local environment or IDE Run Configuration.

**Do not commit your MongoDB connection string or password to GitHub.**

### 3. Run the application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or run the main Spring Boot application class from IntelliJ IDEA.

The application runs on:

```text
http://localhost:8080
```

### 4. Test the APIs

You can use **Postman** to test the REST APIs.

Example:

```text
POST http://localhost:8080/students
```

Example request body:

```json
{
  "rollNo": 101,
  "name": "Rahul",
  "course": "Computer Science",
  "academicYear": 3
}
```

## Future Enhancements

* Spring Security and authentication
* Role-based authorization
* API documentation using Swagger/OpenAPI
* Unit and integration testing
* Advanced student search