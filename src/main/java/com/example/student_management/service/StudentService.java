package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.exception.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.student_management.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        Student save = studentRepository.save(student);
        log.info("Student Created Successfully: " + student.getName());
        return save;
    }

    public Page<Student> findStudent(Pageable pageable){
        return studentRepository.findAll(pageable);
    }

    public Optional<Student> findById(String id) {
        Optional<Student> byId = studentRepository.findById(id);
        return byId;
    }

    public void deleteById(String id){
        studentRepository.deleteById(id);
        log.info("Student deleted successfully with id: " + id);
    }

    public Student updateStudent(String id, Student student){
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isPresent()){
            Student student1 = byId.get();
            student1.setName(student.getName());
            student1.setCourse(student.getCourse());
            student1.setAcademicYear(student.getAcademicYear());
            studentRepository.save(student1);
            log.info("Student updated successfully with id: " + id);
            return student1;
        }
        log.error("Student not found with id: " + id);
        throw new StudentNotFoundException("Student not found");
    }

    public List<Student> searchByName(String name){
        List<Student> byName = studentRepository.findByName(name);
        log.info("Searching student by name: " + name);
        return byName;
    }

    public List<Student> searchByCourse(String course){
        List<Student> byCourse = studentRepository.findByCourse(course);
        log.info("Searching student by course: " + course);
        return byCourse;
    }
}
