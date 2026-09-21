package com.example.student_management.controller;

import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;
import com.example.student_management.entity.Student;
import com.example.student_management.mapper.StudentMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.student_management.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO dto){
        Student student = studentMapper.toEntity(dto);
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(studentMapper.toResponseDTO(savedStudent));
    }

    @GetMapping
    public Page<StudentResponseDTO> getAllStudents(Pageable pageable){
        Page<Student> allStudent = studentService.findStudent(pageable);
        Page<StudentResponseDTO> response = allStudent.map(
                                               student -> studentMapper.toResponseDTO(student));
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable String id){
        Optional<Student> student = studentService.findById(id);
        if(student.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(studentMapper.toResponseDTO(student.get()));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        studentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable String id ,@Valid @RequestBody StudentRequestDTO dto){
        Student student = studentMapper.toEntity(dto);
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentMapper.toResponseDTO(updatedStudent));
    }

    @GetMapping("/search")
    public List<StudentResponseDTO> searchByName(@RequestParam String name){
        List<Student> students = studentService.searchByName(name);
        return students.stream()
                       .map(student -> studentMapper.toResponseDTO(student))
                       .toList();
    }

    @GetMapping("/searchCourse")
    public List<StudentResponseDTO> searchByCourse(@RequestParam String course){
        List<Student> students = studentService.searchByCourse(course);
        return students.stream()
                       .map(student -> studentMapper.toResponseDTO(student))
                       .toList();
    }

}

