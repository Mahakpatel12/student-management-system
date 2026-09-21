package com.example.student_management.mapper;

import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;
import com.example.student_management.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDTO dto){
        Student student = new Student();
        student.setRollNo(dto.getRollNo());
        student.setName(dto.getName());
        student.setCourse(dto.getCourse());
        student.setAcademicYear(dto.getAcademicYear());

        return student;
    }

    public StudentResponseDTO toResponseDTO(Student student){
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setRollNo(student.getRollNo());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setCourse(student.getCourse());
        studentResponseDTO.setAcademicYear(student.getAcademicYear());

        return studentResponseDTO;
    }
}
